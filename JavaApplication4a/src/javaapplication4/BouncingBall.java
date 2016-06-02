package javaapplication4;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class BouncingBall extends JFrame implements KeyListener {

    private int x = 109;         // Ball's X coordinate
    private final int WIDTH = 30;      // Ball's width
    private final int HEIGHT = 30;     // Ball's height
    private final int TIME_DELAY = 30; // Time delay
    private int movey = 2;       // Pixels to move ball
    private final int MINIMUM_Y = 50;  // Max height of ball
    private final int MAXIMUM_Y = 500; // Min height of ball
    private int y = 400;               // Ball's Y coordinate
    private boolean goingUp = true;    // Direction indicator
    private Timer timer;               // Timer object
    private final int MINIMUM_X = 50;  // Max height of ball
    private final int MAXIMUM_X = 800;
    private int movex = 4;
    private boolean goingLeft = true;
    private Random generator;
    
    private final int YRECT = MAXIMUM_Y + 20;
    private int xRect;
    private int score;

    /**
     * init method
     *
     */
    public BouncingBall() {
        timer = new Timer(TIME_DELAY, new TimerListener());
        timer.start();
        generator = new Random();
        addKeyListener(this);
        score = 0;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MAXIMUM_X+100,MAXIMUM_Y+100);
        setVisible(true);
    }

    /**
     * paint method
     *
     * @param g The applet's Graphics object.
     */
    public void paint(Graphics g) {
        // Call the superclass paint method.
        super.paint(g);
        
        g.setColor(Color.black);
        g.drawRect(MINIMUM_X, MINIMUM_Y, MAXIMUM_X, MAXIMUM_Y);

        // Set the drawing color to red.
        g.setColor(Color.red);

        // Draw the ball.
        g.fillOval(x, y, WIDTH, HEIGHT);
        
        g.setColor(Color.blue);
        g.fillRect(xRect, YRECT, 100, 10 );
    }

    /**
     * Private inner class that handles the Timer object's action events.
     *
     *
     *
     */
    private class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Update the ball's Y coordinate.
            if (goingUp) {
                if (y > MINIMUM_Y) {
                    y -= movey;
                } else {
                    goingUp = false;
                    movey = 1 + generator.nextInt(2);
                }
            } else {
                if (y < MAXIMUM_Y) {
                    y += movey;
                } else {
                    goingUp = true;
                    movey = 1 + generator.nextInt(2);
                    
                    if ((x >= xRect) && (x <= xRect + 100))
                        score++;
                    else {
                        timer.stop();
                        System.out.println(score);
                    }
                }
            }

            if (goingLeft) {
                if (x > MINIMUM_X) {
                    x -= movex;
                } else {
                    goingLeft = false;
                    movex = 2 + generator.nextInt(3);
                }
            } else {
                if (x < MAXIMUM_X) {
                    x += movex;
                } else {
                    goingLeft = true;
                    movex = 2 + generator.nextInt(3);
                }
            }

            // Force a call to the paint method.
            repaint();
        }
    }
    


    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
    } 

    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        //displayInfo(e, "KEY PRESSED: ");
        int keyCode = e.getKeyCode();
        // System.out.println(keyCode);
        if (keyCode == 37)  // Left arrow key
            xRect-= 25;
        else if (keyCode == 39) // Right arrow key
            xRect+= 25;
        if (xRect < 0)
            xRect = 0;
        if (xRect > MAXIMUM_X)
            xRect = MAXIMUM_X;
        repaint();
    }

    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
    }

     public static void main(String[] args)
     {
          new BouncingBall();
     }
}