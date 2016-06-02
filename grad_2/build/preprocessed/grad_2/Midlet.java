/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grad_2;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author admin
 */
public class Midlet extends MIDlet 
{
    private Display display ;
    private myCanvasAA canvas ;
    
    public Midlet()
    {
        display = Display.getDisplay(this) ;
        canvas = new myCanvasAA(this) ;
    }        
    
    public void startApp() 
    {
        display.setCurrent(canvas);        
    }
    
    public void pauseApp() 
    {
        
    }
    
    public void destroyApp(boolean unconditional) 
    {
      
    }
    
    void exitMIDlet() 
    {
        destroyApp(true) ;
        notifyDestroyed() ;
    }
}
