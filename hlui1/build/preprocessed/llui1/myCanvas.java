package llui1;

/**
 *
 * @author Ashutosh
 */
import javax.microedition.lcdui.* ;

public class myCanvas extends Canvas implements CommandListener
{
    private Command cmExit ;
    private drawStringMidlet midlet ;
    private String message = "Georgia Southern" ;
    
    public myCanvas(drawStringMidlet midlet)
    {
        this.midlet = midlet ;
        cmExit = new Command("Exit", Command.EXIT, 1) ;
        addCommand(cmExit) ;
        setCommandListener(this) ;
    }        

    protected void paint(Graphics g) 
    {
        Font font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM) ;
        g.setFont(font);
        g.setColor(255,255,255) ;
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0,0,0) ;
        g.drawString(message,getWidth()/2, getHeight()/2, Graphics.TOP|Graphics.RIGHT);
    }

    public void commandAction(Command c, Displayable d) 
    {
        if(c==cmExit)
        {
            midlet.exitMIDlet();
        }    
    }
    
}
