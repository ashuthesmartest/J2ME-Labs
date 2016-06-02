/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmid;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author Ashutosh
 */
public class Midlet extends MIDlet implements CommandListener
{
    private Display display ;
    private Command cmd1 ;
    private Command cmd2 ;
    private Form f1 ;
    private StringItem str ;
    private TextField un ;
    private TextField ps ;
    public Midlet()
    {
        display = Display.getDisplay(this) ;
        str = new StringItem("Game Centre:", "Please Login") ;
        cmd1 = new Command("Exit" , Command.EXIT , 0) ;
        cmd2 = new Command("Submit" , Command.SCREEN , 1) ;
        un = new TextField("Username:" , "", 8, TextField.ANY) ;
        ps = new TextField("Password:" , "", 8, TextField.PASSWORD) ;
        f1 = new Form("User Login") ;
        f1.append(str) ;
        f1.append(un) ;
        f1.append(ps) ;
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
        else if(c == cmd2)
        {
            Alert a1 = null ;
            String str = null ;
            str = "Username=" + un.getString() + "\nPassword=" + ps.getString() ;
            a1 = new Alert("Login Info", str, null, AlertType.INFO) ;
            display.setCurrent(a1);
        }    
    }        
}
