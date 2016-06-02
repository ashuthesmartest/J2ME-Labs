package ng1;
import javax.microedition.lcdui.* ;
import javax.microedition.midlet.*;
/**
 * @author Ashutosh
 */
public class Midlet extends MIDlet implements CommandListener
{
    private Display display ;
    private Form f,f1 ;
    ChoiceGroup cg ;
    public Image image,image1,image2,image3,image4 ;
    Command cmd = new Command("SHOW" , Command.OK , 1) ;
    Command cmd1 = new Command("BACK" , Command.BACK , 1) ;
    public Midlet()
    {
        try
        {
            image = Image.createImage("/apple.png");
            image1 = Image.createImage("/banana.png");
            image2 = Image.createImage("/cherry.png");
            image3 = Image.createImage("/kiwi.png");
            image4 = Image.createImage("/mango.png");
            
        } 
        catch (Exception e)
        { 
          e.printStackTrace(); 
        }
    }        
    public void startApp() 
    {
        f = new Form("Home") ;
        f1 = new Form("Show Screen") ;
        cg=new ChoiceGroup("Select Apple:",Choice.EXCLUSIVE);
        display = Display.getDisplay(this) ;
        cg.append("Apple",null) ;
        cg.append("Banana",null) ;
        cg.append("Cherry",null) ;
        cg.append("Kiwi",null) ;
        cg.append("Mango",null) ;
        f.append(cg) ;
        f.addCommand(cmd);
        f1.addCommand(cmd1);
        System.out.println(f.getWidth());
        System.out.println(f.getHeight());
        display.setCurrent(f);
        f.setCommandListener(this);
        f1.setCommandListener(this);
    }
    
    public void pauseApp() 
    {
    }
    
    public void destroyApp(boolean unconditional) 
    {
    }

    public void commandAction(Command c, Displayable d) 
    {
        int a = cg.getSelectedIndex() ;
        if(c == cmd)
        {
            display.setCurrent(f1);
            switch(a)
            {
                case 0 :  f1.deleteAll(); 
                          f1.append(image) ;
                          break ;
                case 1 :  f1.deleteAll();
                          f1.append(image1) ;
                          break ;
                case 2 :  f1.deleteAll();
                          f1.append(image2) ;
                          break ;
                case 3 :  f1.deleteAll();
                          f1.append(image3) ;
                          break ;
                case 4 :  f1.deleteAll();
                          f1.append(image4) ;
                          break ;
                default : System.exit(0) ;     
            }
        }
        if(c == cmd1)
        {
            display.setCurrent(f);   
        }   
    }
}
