package mobileapplication3;


import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;
 
// main class for bouncing square
public class BouncingSquareAssignment extends MIDlet 
{

   // start the game
   public void startApp() 
   {
      GameMain game = new GameMain(); 
      Display.getDisplay(this).setCurrent(game);
      new Thread(game).start();       
   }
 
   public void pauseApp() { }
 
   public void destroyApp(boolean unconditional) { }
 
	
   class GameMain extends GameCanvas implements Runnable 
   {
    
	   private int centX, centY;
	   private static final int RADS = 5;      
	   private int xSpeed = 1,  ySpeed = 1;  
		   
	   private static final int INF_AREA_HEI = 20;  
	   private static final int UPDA_INT = 30;  
	 
       // Constructor
	   public GameMain() 
	   {
		   super(false);
	   }
 
      
      public void run() 
      {
         int canvWid = getWidth();
         int canvHeig = getHeight();
 
         centX = canvWid / 2;   
         centY = canvHeig / 2;
 
         
         int xMin = RADS;
         int yMin = RADS;
         int xMax = canvWid - 1 - RADS;
         int yMax = canvHeig - 1 - RADS;
 
         
         Graphics g = getGraphics();
 
         // Loop for the game
         while (true) {
             
            int stateOfKey = getKeyStates(); // function to get the key pressed
            // using bitwise 'and' to check the key state
            if ((stateOfKey & RIGHT_PRESSED) != 0) {if(xSpeed>0) xSpeed++; else xSpeed--;} 
            if ((stateOfKey & LEFT_PRESSED) != 0) {if(xSpeed>0) xSpeed--; else xSpeed++;}
            if ((stateOfKey & UP_PRESSED) != 0) {if(ySpeed>0) ySpeed++; else ySpeed--;}
            if ((stateOfKey & DOWN_PRESSED) != 0) {if(ySpeed>0) ySpeed--; else ySpeed++;}
           
            centX += xSpeed;
            centY += ySpeed;
            
            if (centX > xMax) {
               centX = xMax;
               xSpeed = -xSpeed;
            } else if (centX < xMin) {
               centX = xMin;
               xSpeed = -xSpeed;
            }
            if (centY > yMax) {
               centY = yMax;
               ySpeed = -ySpeed;
            } else if (centY < yMin) {
               centY = yMin;
               ySpeed = -ySpeed;
            }
            
            g.setColor(0x9900FF);
            g.fillRect(0, 0, canvWid, canvHeig);
 
           
            g.setColor(0xFFFFCC);
            g.fillRect(centX - RADS, centY - RADS, 2 * RADS, 2 * RADS);

           
            flushGraphics();
 
            try {
               Thread.sleep(UPDA_INT);
            } catch (InterruptedException ex) {
            }
         }
      }
      
      protected void keyPressed(int stateKey)
      {
        int gameAction = Integer.parseInt(getKeyName(stateKey)) ;  
      	switch(gameAction)
      	{
      	
      		case 1: if ((Math.abs(xSpeed) > 0) && (Math.abs(ySpeed) > 0))
      				 {
      				 	xSpeed = -(Math.abs(xSpeed));
      				 	ySpeed = Math.abs(xSpeed);
      				 }
      				 else if ((xSpeed == 0) && (ySpeed == 0))
      				 {
      				 	xSpeed = -1;
      				 	ySpeed = 1;
      				 }
      				 else if ((xSpeed == 0) || (ySpeed == 0))
      				 {
      				 	if (xSpeed != 0)
      				 	{
      				 		xSpeed = -(Math.abs(xSpeed));
      				 		ySpeed = Math.abs(xSpeed);
      				 	}	
      				 	else
      				 	{
      				 		ySpeed = Math.abs(ySpeed);
      				 		xSpeed = -(ySpeed);
      				 	}	
      				 }						// For Num key '1'
      				 break;
      		case 2: if ((Math.abs(xSpeed) > 0) && (Math.abs(ySpeed) > 0))
      				 {
      				 	xSpeed = 0;
      				 	ySpeed = Math.abs(ySpeed);
      				 }
      				 else if ((xSpeed == 0) && (ySpeed == 0))
      				 	ySpeed = 1;
      				 else if ((xSpeed == 0) || (ySpeed == 0))
      				 {
      				 	if (xSpeed != 0)
      				 		ySpeed = Math.abs(xSpeed);
      				 	else
      				 		ySpeed = ySpeed;	
      				 }							// For Num key '2'
      				 break;
      		case 3: if ((Math.abs(xSpeed) > 0) && (Math.abs(ySpeed) > 0))
      				 {
      				 	xSpeed = Math.abs(xSpeed);
      				 	ySpeed = xSpeed;
      				 }
      				 else if ((xSpeed == 0) && (ySpeed == 0))
      				 {
      				 	xSpeed = 1;
      				 	ySpeed = 1;
      				 }
      				 else if ((xSpeed == 0) || (ySpeed == 0))
      				 {
      				 	if (xSpeed != 0)
      				 	{
      				 		xSpeed = Math.abs(xSpeed);
      				 		ySpeed = xSpeed;
      				 	}	
      				 	else
      				 	{
      				 		ySpeed = Math.abs(ySpeed);
      				 		xSpeed = ySpeed;
      				 	}	
      				 }									// For Num key '3'
      				 break;
      		case 4: if ((Math.abs(xSpeed) > 0) && (Math.abs(ySpeed) > 0))
      				 {
      				 	xSpeed = -(Math.abs(ySpeed));
      				 	ySpeed = 0;
      				 }
      				 else if ((xSpeed == 0) && (ySpeed == 0))
      				 	xSpeed = -1;	
      				 else if ((xSpeed == 0) || (ySpeed == 0))
      				 {
      				 	if (xSpeed != 0)
      				 		xSpeed = -(Math.abs(xSpeed));
      				 	else
      				 		xSpeed = -(Math.abs(ySpeed));	
      				 }									// For Num key '4'
      				 break;
      		case 5: xSpeed = 0; ySpeed = 0;							// For Num key '5'
      				 break;
      		case 6: if ((Math.abs(xSpeed) > 0) && (Math.abs(ySpeed) > 0))
      				 {
      				 	xSpeed = Math.abs(ySpeed);
      				 	ySpeed = 0;
      				 }
      				 else if ((xSpeed == 0) && (ySpeed == 0))
      				 	xSpeed = 1;	
      				 else if ((xSpeed == 0) || (ySpeed == 0))
      				 {
      				 	if (xSpeed != 0)
      				 		xSpeed = xSpeed;
      				 	else
      				 		xSpeed = Math.abs(ySpeed);	
      				 }								// For Num key '6'
      				 break;
      		case 7: if ((Math.abs(xSpeed) > 0) && (Math.abs(ySpeed) > 0))
      				 {
      				 	xSpeed = -(Math.abs(xSpeed));
      				 	ySpeed = xSpeed;
      				 }
      				 else if ((xSpeed == 0) && (ySpeed == 0))
      				 {
      				 	xSpeed = -1;
      				 	ySpeed = -1;
      				 }
      				 else if ((xSpeed == 0) || (ySpeed == 0))
      				 {
      				 	if (xSpeed != 0)
      				 	{
      				 		xSpeed = -(Math.abs(xSpeed));
      				 		ySpeed = xSpeed;
      				 	}	
      				 	else
      				 	{
      				 		ySpeed = -(Math.abs(ySpeed));
      				 		xSpeed = ySpeed;
      				 	}	
      				 }									// For Num key '7'
      				 break;
      		case 8: if ((Math.abs(xSpeed) > 0) && (Math.abs(ySpeed) > 0))
      				 {
      				 	xSpeed = 0;
      				 	ySpeed = -(Math.abs(ySpeed));
      				 }
      				 else if ((xSpeed == 0) && (ySpeed == 0))
      				 	ySpeed = -1;
      				 else if ((xSpeed == 0) || (ySpeed == 0))
      				 {
      				 	if (xSpeed != 0)
      				 		ySpeed = -(Math.abs(xSpeed));
      				 	else
      				 		ySpeed = -(Math.abs(ySpeed));	
      				 }												// For Num key '8'
      				 break;
      		case 9: if ((Math.abs(xSpeed) > 0) && (Math.abs(ySpeed) > 0))
      				 {
      				 	xSpeed = Math.abs(xSpeed);
      				 	ySpeed = -(xSpeed);
      				 }
      				 else if ((xSpeed == 0) && (ySpeed == 0))
      				 {
      				 	xSpeed = 1;
      				 	ySpeed = -1;
      				 }
      				 else if ((xSpeed == 0) || (ySpeed == 0))
      				 {
      				 	if (xSpeed != 0)
      				 	{
      				 		xSpeed = Math.abs(xSpeed);
      				 		ySpeed = -(xSpeed);
      				 	}	
      				 	else
      				 	{
      				 		ySpeed = -(Math.abs(ySpeed));
      				 		xSpeed = -(ySpeed);
      				 	}	
      				 }								// For Num key '9'
      				 break;		 		 		 		 		 		 		 		 
      	}
        repaint();		// calling the repaint method
      }
   }
   
}
