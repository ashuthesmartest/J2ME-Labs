/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ng_records;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.microedition.rms.*;

/**
 * @author Ashutosh
 */
public class Midlet extends MIDlet implements CommandListener
{
    private Display display ;
    private Command cmd1 ;
    private Command cmd2 ;
    private Form f1,f2,f3,f4 ;
    private TextField nm ;
    private TextField id ;
    private TextField mj ;
    public Midlet()
    {
        display = Display.getDisplay(this) ;
        cmd1 = new Command("Exit" , Command.EXIT , 0) ;
        cmd2 = new Command("Save" , Command.SCREEN , 0) ;
        nm = new TextField("Name" , "", 8, TextField.ANY) ;
        id = new TextField("ID      " , "", 8, TextField.ANY) ;
        mj = new TextField("Major " , "", 8, TextField.ANY) ;
        f1 = new Form("User Information") ;
        f1.append(nm) ;
        f1.append(id) ;
        f1.append(mj) ;
        f1.addCommand(cmd1);        
        f1.addCommand(cmd2);        
        f1.setCommandListener(this);
    }        
    public void startApp() 
    {
        display.setCurrent(f1);
    }
    public void pauseApp() 
    {
        
    }
    
    public void destroyApp(boolean unconditional) 
    {
        
    }
    
    public void commandAction(Command c, Displayable d)
    {
        if(c == cmd1)
        {
            destroyApp(true) ;
            notifyDestroyed() ;
        }       
    }        
}
