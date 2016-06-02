package llui2;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import llui2.Midlet;

/**
 * @author Prakhar
 */

class myCircle{
    int x;
    int y;
    int color;
    public myCircle(){
        this.x=0;
        this.y=0;
        this.color=0;
    }
}


public class myCanvas extends Canvas implements CommandListener{
    
    private Command exitBtn,startBtn;
    private Midlet midlet;
    myCircle dots[][] = new myCircle[3][3];
    int currX,currY;
    public myCanvas(Midlet midlet){ 
        this.midlet = midlet;
        exitBtn = new Command("Exit", Command.EXIT, 1);
        startBtn = new Command("Start", Command.OK, 2);
        addCommand(exitBtn);
        addCommand(startBtn);
        setCommandListener(this); 
        for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    dots[i][j] = new myCircle();
                    //row
                    if(i==0)
                        dots[i][j].y=getHeight()/7;
                    else if(i==1)
                        dots[i][j].y=3*getHeight()/7;
                    else
                        dots[i][j].y=5*getHeight()/7;
                    //column
                    if(j==0){
                        dots[i][j].x=getWidth()/7;}
                    else if(j==1)
                        dots[i][j].x=3*getWidth()/7;
                    else
                        dots[i][j].x=5*getWidth()/7;
                    dots[i][j].color=0;
            }
        }
    }
    
    public void commandAction(Command c, Displayable d){ 
        if (c == exitBtn) midlet.exitMIDlet();
        else if (c == startBtn){
            dots[1][1].color=1 ;
            currX=1;currY=1;
            repaint();
        }
    }
    
    protected void paint(Graphics g){
        Font font = Font.getFont(Font.FACE_SYSTEM,Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        g.setFont(font);
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0, 0, 0);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(dots[i][j].color==1){
                    g.setColor(0,255,0); 
                    g.fillArc(dots[i][j].x, dots[i][j].y, 30, 30, 0, 360);
                }
                else g.drawArc(dots[i][j].x, dots[i][j].y, 30, 30, 0, 360);
                g.setColor(0,0,0);
            }
        }
    }
    
    protected void keyPressed(int keyCode) {
        int gameAction = Integer.parseInt(getKeyName(keyCode));
        System.out.println(gameAction);
        switch (gameAction) {
        case 1:            
              dots[currX][currY].color=0;      
              if(((currX-1)<0)||((currY-1)<0)){}
              else{
                  currX=((((currX-1) % 3) + 3) % 3);
                  currY=((((currY-1) % 3) + 3) % 3);
              }
              dots[currX][currY].color=1;
              break;
        case 2:
              dots[currX][currY].color=0;
              currX=((((currX-1) % 3) + 3) % 3);
              System.out.println(currX);
              System.out.println(currY);
              dots[currX][currY].color=1;
              break;
        case 3:
              dots[currX][currY].color=0;
              if(((currX-1)<0)||((currY+1)>2)){}
              else{
                currX=((((currX-1) % 3) + 3) % 3);
                currY=((((currY+1) % 3) + 3) % 3);
              }
              dots[currX][currY].color=1;
              break;
        case 4:
              dots[currX][currY].color=0;
              currY=((((currY-1) % 3) + 3) % 3);
              dots[currX][currY].color=1;
              break;
        case 5:
              dots[currX][currY].color=0;
              currX=1;
              currY=1;
              dots[currX][currY].color=1;
              break;
        case 6:
              dots[currX][currY].color=0;
              currY=((((currY+1) % 3) + 3) % 3);
              dots[currX][currY].color=1;
              break;
        case 7:
              dots[currX][currY].color=0;
              if(((currX+1)>2)||((currY-1)<0)){}
              else{
                currX=((((currX+1) % 3) + 3) % 3);
                currY=((((currY-1) % 3) + 3) % 3);
              }
              dots[currX][currY].color=1;
              break;
        case 8:
              dots[currX][currY].color=0;
              currX=((((currX+1) % 3) + 3) % 3);
              dots[currX][currY].color=1;
              break;
        case 9:            
              dots[currX][currY].color=0;
              if(((currX+1)>2)||((currY+1)>2)){}
              else{
                currX=((((currX+1) % 3) + 3) % 3);
                currY=((((currY+1) % 3) + 3) % 3);
              }
              dots[currX][currY].color=1;
              break;        
        default:
              break;
        }
        repaint();
      }
}
