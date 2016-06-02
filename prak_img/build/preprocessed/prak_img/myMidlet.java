package prak_img;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author Prakhar
 */

public class myMidlet extends MIDlet implements CommandListener {

    private Display display;
    private Form f2,f1;
    private Command enter,show;
    private Image fruitimage;
    private Command exit,back;
    private int index,l;
    private ChoiceGroup option=new ChoiceGroup("Select Option", Choice.EXCLUSIVE);

    public void startApp() {
        option.append("Apple",null);
        option.append("Banana",null);
        option.append("Cherry",null);
        option.append("Kiwi",null);
        option.append("Mango", null);        
        f1=new Form("Select a fruit to show");
        f2=new Form("Fruit image");
        display=Display.getDisplay(this);
        f1.append(option);
        exit=new Command("Exit",Command.EXIT,0);
        back=new Command("Back",Command.OK,0);
        show=new Command("Show",Command.OK,0);
        f2.addCommand(back);
        f2.addCommand(exit);
        f1.addCommand(show);
        f1.addCommand(exit);
        f1.setCommandListener(this);
        f2.setCommandListener(this);
        display.setCurrent(f1);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }
    public void commandAction(Command c,Displayable d){
        if(c==show){
        String fruitsel;
        boolean get[] = new boolean[option.size()];
        option.getSelectedFlags(get);
        for (int i = 0; i < get.length; i++) {
            if (get[i]) {
                fruitsel=option.getString(i);
                try{
                    if(fruitsel=="Apple"){
                        fruitimage=Image.createImage("/apple.png");
                    }
                    else if(fruitsel=="Mango"){
                        fruitimage=Image.createImage("/mango.png");
                    }
                    else if(fruitsel=="Banana"){
                        fruitimage=Image.createImage("/banana.png");
                    }
                    else if(fruitsel=="Kiwi"){
                        fruitimage=Image.createImage("/kiwi.png");
                    }
                    else if(fruitsel=="Cherry"){
                        fruitimage=Image.createImage("/cherry.png");
                    }
                    fruitimage = resizeImage(fruitimage,200,250);
                    index=f2.append(new ImageItem(null,fruitimage , ImageItem.LAYOUT_DEFAULT, null));
                }
                catch(Exception e){
                }
            }
        }
        display.setCurrent(f2);
        }
        if(c==exit){
            destroyApp(true);
        }
        if(c==back){
            display.setCurrent(f1);
            f2.delete(index);
        }
    }
    public static Image resizeImage(Image src, int screenWidth, int screenHeight){
    int srcWidth = src.getWidth();
    int srcHeight = src.getHeight();
    Image tmp = Image.createImage(screenWidth, srcHeight);
    Graphics g = tmp.getGraphics();
    int ratio = (srcWidth << 16) / screenWidth;
    int pos = ratio / 2;
 
    for (int x = 0; x < screenWidth; x++)
    {
        g.setClip(x, 0, 1, srcHeight);
        g.drawImage(src, x - (pos >> 16), 0, Graphics.LEFT | Graphics.TOP);
        pos += ratio;
    }
 
    Image resizedImage = Image.createImage(screenWidth, screenHeight);
    g = resizedImage.getGraphics();
    ratio = (srcHeight << 16) / screenHeight;
    pos = ratio / 2;
 
    for (int y = 0; y < screenHeight; y++) {
        g.setClip(0, y, screenWidth, 1);
        g.drawImage(tmp, 0, y - (pos >> 16), Graphics.LEFT | Graphics.TOP);
        pos += ratio;
    } 
    return resizedImage;
    }
}