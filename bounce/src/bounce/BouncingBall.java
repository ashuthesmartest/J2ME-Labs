package bounce;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
 
// A Bouncing Ball in MIDP
public class BouncingBall extends MIDlet {
 
   // Allocate a GameCanvas, set it to the current display, and start the game thread
   public void startApp() 
   {
      GameMain game = new GameMain(); // GameMain extends GameCanvas for the game UI
      Display.getDisplay(this).setCurrent(game);
      new Thread(game).start();       // GameMain implements Runnable to run the game thread
   }
 
   public void pauseApp() { }
 
   public void destroyApp(boolean unconditional) { }
 
   // The GameMain class is designed as an inner class, which extends GameCanvas for drawing
   // the game graphics, and implements Runnable to run the game logic in its own thread.
   class GameMain extends GameCanvas implements Runnable {
 
      // Avatar - the Bouncing Ball
      private int xCenter,  yCenter;        // (x,y) of the center of the ball
      private double speedX = 2,  speedY = 2;  // speed in x and y direction
      private double scaleX , scaleY ;
      private int direction = 0;  // direction of move, in degrees counter-clockwise
      private static final int RADIUS = 20; // radius

      private static final int UPDATE_INTERVAL = 30;  // game update interval in milliseconds
 
      // Constructor
      public GameMain() 
      {
         super(false);                     //used to prevent the suppressing of keyevents. If super(true) was called, only main game keys (2,4,6,8) would work.
      }
 
      // Running behavior of the game thread
      public void run() 
      {
         int canvasWidth = getWidth();
         int canvasHeight = getHeight();
 
         // Initializes the Ball
         xCenter = canvasWidth / 2;   // place the ball in the center of the canvas
         yCenter = canvasHeight / 2;
         direction = 0 ;
 
         // Bounds of the center of the ball
         int xMin = RADIUS;
         int yMin = RADIUS ;
         int xMax = canvasWidth - RADIUS;
         int yMax = canvasHeight - RADIUS;
 
         // Retrieve the off-screen graphics buffer for graphics drawing
         Graphics g = getGraphics();
 
         // Game loop
         while (true) 
         {
            int keyState = getKeyStates();
            if((keyState & UP_PRESSED) != 0)
            {
                speedX += 0.5*speedX ;
                speedY += 0.5*speedY ;
                if((speedX == 0) && (speedY == 0))
                {
                    speedX = 2 ;
                    speedY = 3 ;
                }    
                if(speedY >= 432)
                {
                    speedX = 2 ;
                    speedY = 3 ;
                }
            }
            if((keyState & DOWN_PRESSED) != 0)
            {
                speedX -= 0.4*speedX ;
                speedY -= 0.4*speedY ;
                if((speedX == 0) && (speedY == 0))
                {
                    speedX = 2 ;
                    speedY = 432 ;
                } 
            }
            // Update the ball's position
            xCenter += speedX;
            yCenter += speedY;
            // Check if the ball hit the bound. 'Reflect' the ball if so.
            if (xCenter > xMax) 
            {
               xCenter = xMax;
               speedX = -speedX;
            } 
            else if (xCenter < xMin) 
            {
               xCenter = xMin;
               speedX = -speedX;
            }
            if (yCenter > yMax) 
            {
               yCenter = yMax;
               speedY = -speedY;
            } 
            else if (yCenter < yMin) 
            {
               yCenter = yMin;
               speedY = -speedY;
            }
            
            // Clear screen by filling a rectangle over the entire screen
            g.setColor(0x000000);
            g.fillRect(0, 0, canvasWidth, canvasHeight);
 
            
            // Draw the ball
            g.setColor(0xffffff);
            g.fillRect(xCenter - RADIUS, yCenter - RADIUS, 2 * RADIUS, 2 * RADIUS);
 
            // Flush the off-screen buffer to the display
            flushGraphics();
 
            // Provide delay to achieve the targeted refresh rate,
            // also yield for other threads to perform their tasks.
            try {
               Thread.sleep(UPDATE_INTERVAL);
            } catch (InterruptedException e) {
            }
         }       
      }
      protected void keyPressed(int keyCode)
      {
          int gameAction = Integer.parseInt(getKeyName(keyCode));
          switch(gameAction)
          {
              case 1 :  scaleX = Math.sin(Math.PI/4) ;
                        scaleY = Math.cos(Math.PI/4) ;
                        speedX = -(2*scaleX) ;
                        speedY = -(2*scaleY) ;
                        xCenter = xCenter + (int)speedX ;
                        yCenter = yCenter + (int)speedY ;
                        break ;
              case 2 :  speedX = 0 ;
                        speedY = -2 ; 
                        break ;
              case 3 :  scaleX = Math.sin(Math.PI/4) ;
                        scaleY = Math.cos(Math.PI/4) ;
                        speedX = (2*scaleX) ;
                        speedY = -(2*scaleY) ;
                        xCenter = xCenter + (int)speedX ;
                        yCenter = yCenter + (int)speedY ;
                        break ;    
              case 4 :  speedX = -2 ;
                        speedY = 0 ;
                        break ;
              case 5 :  speedX = 0 ;
                        speedY = 0 ;
                        break ;
              case 6 :  speedX = 2 ;
                        speedY = 0 ;
                        break ;
              case 7 :  scaleX = Math.sin(Math.PI/4) ;
                        scaleY = Math.cos(Math.PI/4) ;
                        speedX = -(2*scaleX) ;
                        speedY = (2*scaleY) ;
                        xCenter = xCenter + (int)speedX ;
                        yCenter = yCenter + (int)speedY ;
                        break ;     
              case 8 :  speedX = 0 ;
                        speedY = 2 ;
                        break ;
              case 9 :  scaleX = Math.sin(Math.PI/4) ;
                        scaleY = Math.cos(Math.PI/4) ;
                        speedX = (2*scaleX) ;
                        speedY = (2*scaleY) ;
                        xCenter = xCenter + (int)speedX ;
                        yCenter = yCenter + (int)speedY ;
                        break ;                  
              default : System.out.println("Invalid Key");
          }
          repaint() ;
      } 
   }
}