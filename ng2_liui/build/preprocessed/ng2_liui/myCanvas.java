package ng2_liui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author Ashutosh
 */
public class myCanvas extends Canvas implements CommandListener  
{
    private Command exitBtn, clrBtn ;
    private Midlet midlet;
    private int startx = 0, starty = 0;
    private boolean clearDisplay = false;
    public myCanvas(Midlet midlet)
    { 
        this.midlet = midlet;
        exitBtn = new Command("Exit", Command.EXIT, 1);
        clrBtn = new Command("Clear", Command.SCREEN, 1);
        addCommand(exitBtn);
        addCommand(clrBtn);
        setCommandListener(this) ;
    }    
    protected void paint(Graphics g) 
    {
        if (clearDisplay)
        {
            g.setColor(255, 255, 255);
            g.fillRect(0, 0, getWidth(), getHeight());
            clearDisplay = false;
            startx = starty = 0;
            return;
        }
        g.setColor(0, 0, 0);
        g.drawString("X= "+ this.startx, 20, 40, UP);
        g.drawString("Y= "+ this.starty, 20, 80, UP);
    }

    public void commandAction(Command c, Displayable d) 
    {
        if (c == exitBtn)
        {    
            midlet.exitMIDlet();
        }    
        else if (c == clrBtn)
        {
            clearDisplay = true; 
            repaint();
        }
    }
    
    protected void pointerPressed(int x, int y)
    {
        startx = x;
        starty = y;
        repaint();
        
    }
    
}
