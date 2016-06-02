package llui1;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.* ;

/**
 * @author Ashutosh
 */

public class drawStringMidlet extends MIDlet 
{
    private Display display ;
    private myCanvas canvas ;
    
    public drawStringMidlet()
    {
        display = Display.getDisplay(this) ;
        canvas = new myCanvas(this) ;
    }        
    
    protected void startApp() 
    {
        display.setCurrent(canvas);
    }
    
    protected void pauseApp() 
    {
        
    }
    
    protected void destroyApp(boolean unconditional) 
    {
        
    }
    
    public void exitMIDlet()
    {
        destroyApp(true) ;
        notifyDestroyed() ;
    }        
}
