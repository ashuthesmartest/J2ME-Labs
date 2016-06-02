package llui2;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.* ;

/**
 * @author Ashutosh
 */
public class Midlet extends MIDlet 
{
    private Display display ;
    private myCanvas canvas ;
    
    public Midlet()
    {
        display = Display.getDisplay(this) ;
        canvas = new myCanvas(this) ;
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
    
    public void exitMIDlet()
    {
        destroyApp(true) ;
        notifyDestroyed() ;
    }        
}
