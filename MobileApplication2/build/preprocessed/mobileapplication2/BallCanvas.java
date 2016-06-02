/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileapplication2;

import javax.microedition.lcdui.*;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

/**
 * @author ARJUN
 */
public class BallCanvas extends GameCanvas implements CommandListener,Runnable {
    
    
    private Doodle midlet;
     private Command Ext;
    private int xrec=getWidth()/2;
    private int yrec=getHeight()/2;
    private int xrecmov=3;
    private int yrecmov=3;
    private int rechgt=30;
    private int recwdth=30;
    int x=3;
    int y=3;
    private int xrecmov2=(int)(3*1.4);
    private int yrecmov2=(int)(3*1.4);
    private int directball=0;
   private int slptme=60;
    
    public BallCanvas(Doodle midlet) { /* intial constructor*/
        super(false);
    this.midlet=midlet;
        Ext=new Command("Exit", Command.EXIT, 1);
      addCommand(Ext);
    setCommandListener(this);
    } 
    
    public void start(){
        Thread run=new Thread(this);
        run.start();
    }
     public void createback(Graphics g){  /* create inital white background*/
        
         g.setColor(255,255,255);
         g.fillRect(0, 0, getWidth(),getHeight());
       
     }
    
     public void screenchange(Graphics graphics){  /*func to show current rec pos*/
         createback(graphics);              
         rectmove();                                    
        graphics.setColor(0,0,0);
       
        graphics.fillRect(xrec, yrec, recwdth, rechgt);
        
        flushGraphics();
        
     }
    
     public void rectmove(){     /* func to update rect cordinates and reflection of rect. on collision with boudaries*/ 
         if(directball==0){
             xrecmov=x;
             yrecmov=y;
             xrec +=xrecmov;
             yrec -=yrecmov;
         }
         else if(directball==1){
             xrecmov=x;
             yrecmov=y;
             xrec +=xrecmov;
             yrec +=yrecmov;
         }
         else if(directball==2){
             xrecmov=x;
             yrecmov=y;
             xrec -=xrecmov;
             yrec +=yrecmov;
         }
         else if(directball==3){
             xrecmov=x;
             yrecmov=y;
             xrec -=xrecmov;
             yrec -=yrecmov;
         }
         else if(directball==4){ //2
             
             yrec -=yrecmov2;
         }
         else if(directball==5){ //8
             
             yrec +=yrecmov2;
         }
         else if(directball==6){ //4
             xrec -=xrecmov2;
             
         }
         else if(directball==7){//6
             xrec +=xrecmov2;
             
         }
         else if(directball==8){
             xrecmov=0;
             yrecmov=0;
         }
         
         
         
         if(directball==3 && xrec<0){
             directball =0;
         }
         
         else if(directball==4 && yrec<0){ //2
               directball=5; 
         }
         
         else if(directball==5 && yrec>getHeight()-30){//8
               directball=4; 
         }
         else if(directball==6 && xrec<0){//4
               directball=7; 
         }
         else if(directball==7 && xrec>getWidth()-30){//6
               directball=6; 
         }
         
         else if(directball==3 && yrec<0){
               directball=2; 
         }
         
         else if(directball==3 && yrec<0){
               directball=2; 
         }
         else if(directball==0 && yrec<0){
               directball=1; 
         }
         else if(directball==0 && xrec>getWidth()-30){
               directball=3; 
         }
         else if(directball==1 && xrec>getWidth()-30){
               directball=2; 
         }
          else if(directball==1 && yrec>getHeight()-30){
               directball=0; 
         }
          else if(directball==2 && xrec<0){
               directball=1; 
         }
         else if(directball==2 && yrec>getHeight()-30){
               directball=3; 
         }
         
         
         
     }
    
    protected  void keyPressed(int keyCode) { /* direct the ball in direction acc. to the key pressed and increase/decrease speed of rect.*/ 
         int gact = getGameAction(keyCode);
    switch (gact) {
    case UP: 
           
            slptme -=5;

      break;
    case DOWN: slptme +=5; 
    if(slptme==125){
        directball=8;
            break;
    }
       
    }
    
        if(keyCode==KEY_NUM1){
         directball=3;
        }
        else if(keyCode==KEY_NUM3){
         directball=0;
        }
        else if(keyCode==KEY_NUM7){
         directball=2;
        }
        else if(keyCode==KEY_NUM9){
         directball=1;
        }
        else if(keyCode==KEY_NUM2){
         directball=4;
        }
        else if(keyCode==KEY_NUM4){
         directball=6;
        }
        else if(keyCode==KEY_NUM6){
         directball=7;
        }
        else if(keyCode==KEY_NUM8){
         directball=5;
        }
        else if(keyCode==KEY_NUM5){
         directball=8;
        }
       
    }
    
   

    public void run() {
        while(true){
            try{
                screenchange(getGraphics());
                Thread.sleep(slptme);
            }
            
            catch(Exception e){
                e.getMessage();
            }
        }
        
    }

    public void commandAction(Command c, Displayable d) {
        if(c==Ext){
          
           midlet.exitMIDlet();
        }
    }

}
