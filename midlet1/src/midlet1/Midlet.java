package midlet1;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author Ashutosh
 */
public class Midlet extends MIDlet implements CommandListener
{
    private Display display ;
    private Form f ;
    private Command start ;
    private Command exit ;
    String s = "Georgia Southern" ;
    public void startApp() 
    {
        display = Display.getDisplay(this) ;
        f= new Form("Hello") ;
        f.append(s) ;
        start = new Command("Print", Command.OK, 0) ;
        exit = new Command("Exit", Command.EXIT, 0) ;
        f.addCommand(start) ;
        f.addCommand(exit) ;
        f.setCommandListener(this) ;
        display.setCurrent(f);
    }
    
    public void pauseApp() 
    {
    }
    
    public void destroyApp(boolean unconditional) 
    {
        notifyDestroyed() ;
    }
    public void commandAction(Command c, Displayable d)
    {
        if(c==start)
        {
            f.append("SNU") ;
            display.setCurrent(f);
        }    
        if(c==exit)
        {
            destroyApp(true) ;
        }    
    }        
}
