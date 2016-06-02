/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grad_2;

import javax.microedition.lcdui.*;

/**
 *
 * @author admin
 */

class rect
{
    int y;
    int color;
    public rect()
    {
        this.y=0;
        this.color=0;
    }
}
public class myCanvasAA extends Canvas implements CommandListener
{
    private Midlet midlet;
    private Command exitBtn ;
    rect dots[] = new rect[4];
    int curr=0 ;
    public myCanvasAA(Midlet midlet)
    {
        this.midlet = midlet ;
        for(int i=0;i<3;i++)
        {
            dots[i] = new rect();
            if(i==0)
            {
                dots[i].y = 0 ;
            }
            else if(i==1)
            {
                dots[i].y = 50 ;
            }
            else if(i==2)
            {
                dots[i].y = 100 ;
            }    
            else if(i==3)
            {
                dots[i].y = 150 ;
            }    
        }
       // repaint() ;
    }        
    protected void paint(Graphics g) 
    {
        Font font = Font.getFont(Font.FACE_SYSTEM,Font.STYLE_PLAIN, Font.SIZE_LARGE);
        g.setFont(font);
        g.setColor(0, 0, 255);
        g.fillRect(0, 0, getWidth(), 50);
        g.setColor(255,255,255);
        g.drawRect(0, 50, getWidth(), 50);
        g.drawRect(0, 100, getWidth(), 50);
        g.drawRect(0, 150, getWidth(), 50);
        g.drawString("Name:", 0, 0, Graphics.TOP|Graphics.LEFT);
        g.drawString("School: SOE", 0, 50, Graphics.TOP|Graphics.LEFT);
        g.drawString("Univ: SNU", 0, 100, Graphics.TOP|Graphics.LEFT);
        g.drawString("Roll No:", 0, 150, Graphics.TOP|Graphics.LEFT);
        for(int i=0 ; i<4 ; i++)
        {
            if(dots[i].color == 1)
            {
                g.setColor(0,0,255); 
                g.fillRect(0, dots[i].y, getWidth(), 50);
            } 
            g.setColor(255,255,255);
        }    
    }

    public void commandAction(Command c, Displayable d) 
    {
        if (c == exitBtn) 
        {    
            midlet.exitMIDlet();
        }
    }
    
    protected void keyPressed(int keyCode) 
    {
        int appact = Integer.parseInt(getKeyName(keyCode));
        switch(appact)
        {
            case 3:  System.out.println("HI");
                     break ;
        }    
        repaint() ;
    }
    
}


