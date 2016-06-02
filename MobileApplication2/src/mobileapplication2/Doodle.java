/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mobileapplication2;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Doodle extends MIDlet
{
  private Display  display;       // The display
  private BallCanvas canvas;   // Canvas 
 
  public Doodle()
  {
    display = Display.getDisplay(this);
    canvas  = new BallCanvas(this);
    
    
  }
 
  protected void startApp()
  {
      canvas.start();
    display.setCurrent( canvas );
  }
 
  protected void pauseApp()
  { }

  protected void destroyApp( boolean unconditional )
  { }
 
  public void exitMIDlet()
  {
    destroyApp(true);
    notifyDestroyed();
  }
}
