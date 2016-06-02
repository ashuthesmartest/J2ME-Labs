package move;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
 
// Testing the MIDP Game API's GameCanvas class: off-screen graphics buffer and key state.
public class GameCanvasTest extends MIDlet {
 
   // Allocate a GameCanvas, set it to the current display, and start the game thread
   public void startApp() {
      GameMain game = new GameMain(); // GameMain extends GameCanvas for the game UI
      Display.getDisplay(this).setCurrent(game);
      new Thread(game).start();       // GameMain implements Runnable to run the game thread
   }
 
   public void pauseApp() { }
 
   public void destroyApp(boolean unconditional) { }
 
   // The GameMain class is designed as an inner class, which extends GameCanvas for drawing
   // the game graphics, and implements Runnable to run the game logic in its own thread.
   class GameMain extends GameCanvas implements Runnable {
 
      // Avatar - a Pacman
      private int xCenter,  yCenter;   // (x,y) position of the pacman's center
      private static final int RADIUS = 20;  // radius
      private int speed = 3;      // speed of move, in pixels
      private int xEye,  yEye;    // (x,y) position of the pacman's eye
      private int direction = 0;  // direction of move, in degrees counter-clockwise
      private int animationCount = 0;        // counter to control avatar's animation
 
      private static final int INFO_AREA_HEIGHT = 20;  // height of the info display area
      private static final int UPDATE_INTERVAL = 100;  // game update interval in milliseconds
 
      // Constructor
      public GameMain() {
         super(true);
      }
 
      // Running behavior of the game thread
      public void run() {
         int canvasWidth = getWidth();
         int canvasHeight = getHeight();
 
         // Initialize the Pacman
         xCenter = canvasWidth / 2;    // place the pacman in the center of canvas
         yCenter = canvasHeight / 2;
         direction = 0;          // move right
         xEye = xCenter;
         yEye = yCenter - RADIUS / 2;
         animationCount = 0;
         // bounds for the pacman's center (x, y)
         int xMin = RADIUS;
         int xMax = canvasWidth - 1 - RADIUS;
         int yMin = RADIUS + INFO_AREA_HEIGHT;
         int yMax = canvasHeight - 1 - RADIUS;
 
         // Retrieve the off-screen graphics buffer for graphics drawing
         Graphics g = getGraphics();
 
         // Game loop
         while (true) {
            // Check key state for user input
            int keyState = getKeyStates();
            if ((keyState & RIGHT_PRESSED) != 0) {
               xCenter += speed;
               if (xCenter > xMax) {
                  xCenter = xMax;
               }
               direction = 0;
               xEye = xCenter;
               yEye = yCenter - RADIUS / 2;
            } 
            else if ((keyState & UP_PRESSED) != 0) 
            {
               yCenter -= speed;
               if (yCenter < yMin) 
               {
                  yCenter = yMin;
               }
               direction = 90;  // degrees counter-clockwise
               xEye = xCenter - RADIUS / 2;
               yEye = yCenter;
            } 
            else if ((keyState & LEFT_PRESSED) != 0) {
               xCenter -= speed;
               if (xCenter < xMin) {
                  xCenter = xMin;
               }
               direction = 180;
               xEye = xCenter;
               yEye = yCenter - RADIUS / 2;
            } else if ((keyState & DOWN_PRESSED) != 0) {
               yCenter += speed;
               if (yCenter > yMax) {
                  yCenter = yMax;
               }
               direction = 270;
               xEye = xCenter + RADIUS / 2;
               yEye = yCenter;
            }
 
            // Clear screen by filling a rectangle over the entire screen
            g.setColor(0x007fcf);
            g.fillRect(0, 0, canvasWidth, canvasHeight);
 
            // Draw the Pacman
            // Circular body with a open mouth, width of the mouth varies based on animation count.
            g.setColor(0xffff00);
            g.fillArc(xCenter - RADIUS, yCenter - RADIUS, 2 * RADIUS, 2 * RADIUS,
                    5 * animationCount + direction, 360 - 10 * animationCount);
            animationCount = (animationCount + 1) % 6;   // 6 different opening
            // Draw the Pacman's eye, which alternately opens and closes
            g.setColor(0x000000);
            g.drawArc(xEye, yEye, 4, 4, 0, 360);     // outline
            if (animationCount < 3) {
               g.fillArc(xEye, yEye, 4, 4, 0, 360);  // close via fill
            }
 
            // Display information in the info area: (x, y) of the Pacman
            g.setColor(0xffffff);
            g.fillRect(0, 0, canvasWidth, INFO_AREA_HEIGHT);
            g.setColor(0x000000);
            g.drawString("(" + xCenter + "," + yCenter + ")", 4, 0, Graphics.TOP | Graphics.LEFT);
 
            // Flush the off-screen buffer to the display
            flushGraphics();
 
            // Provide delay to achieve the targeted refresh rate,
            // also yield for other threads to perform their tasks.
            try {
               Thread.sleep(UPDATE_INTERVAL);
            } catch (InterruptedException e) { }
         }
      }
   }
}