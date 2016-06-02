/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradedlab1;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Ashutosh
 */
public class Midlet extends MIDlet implements CommandListener
{
    private Display display ;
    private Form f2,f3,f4 ;
    ChoiceGroup cg ;
    Command cmd1 = new Command("Next" , Command.SCREEN , 1) ;
    Command cmd2 = new Command("Exit" , Command.EXIT , 0) ;
    Command cmd3 = new Command("Clear" , Command.EXIT , 2) ;
    Command cmd4 = new Command("Back" , Command.BACK , 2) ;
    Command cmd5 = new Command("TF" , Command.SCREEN , 3) ;
    Command cmd6 = new Command("Tick" , Command.SCREEN , 4) ;
    Command cmd7 = new Command("Back" , Command.BACK , 0) ;
    Command cmd8 = new Command("Back" , Command.BACK , 0) ;
    private TextBox tb1 ;
    private TextField tf1, tf2 ;
    String str ;
    Ticker tic ;
    public Midlet()
    {
      display = Display.getDisplay(this) ; 
      tb1 = new TextBox("Enter Details" , "", 80, TextField.ANY) ;
      tb1.addCommand(cmd1);
      tb1.addCommand(cmd2);
      tb1.addCommand(cmd3);
      tb1.setCommandListener(this);    
      f2 = new Form("Choices") ;
      cg = new ChoiceGroup("Select choice:",Choice.MULTIPLE);
      f2.addCommand(cmd4);
      f2.addCommand(cmd5);
      f2.addCommand(cmd6);
      cg.append("Name",null) ;
      cg.append("Major",null) ;
      cg.append("University",null) ;
      cg.append("Age",null) ;
      f2.append(cg) ;
      f2.setCommandListener(this);
      f3 = new Form("Display") ;
      f3.addCommand(cmd7);
      tf1 = new TextField("Name:" , "", 8, TextField.ANY) ;
      tf2 = new TextField("Major:" , "", 8, TextField.ANY) ;
      f3.append(tf1) ;
      f3.append(tf2) ;
      f3.setCommandListener(this);
      f4 = new Form("Ticker") ;
      f4.addCommand(cmd8);
      f4.setCommandListener(this);
    }        
    public void startApp() 
    {
        display.setCurrent(tb1);
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
            str = tb1.getString() ;
            tic = new Ticker(str);
            f4.setTicker(tic);
            display.setCurrent(f2);
        }    
        else if(c == cmd2)
        {
            destroyApp(true) ;
            notifyDestroyed() ;
        }
        else if(c == cmd3)
        {
            tb1.setString("");
        }
        else if(c == cmd4)
        {
            display.setCurrent(tb1);
        }
        else if(c == cmd5)
        {
            display.setCurrent(f3);
        }
        else if(c == cmd6)
        {
            display.setCurrent(f4);
        }
        else if(c == cmd7)
        {
            display.setCurrent(f2);
        }
        else if(c == cmd8)
        {
            display.setCurrent(f3);
        }
    }
}
