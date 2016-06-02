package network_client;

/**
 * The following MIDlet application is a datagram client program
 * that exchanges datagram with another MIDlet application acting
 * as a datagram server program.
 */

// include MIDlet class libraries
import javax.microedition.midlet.*;
// include networking class libraries
import javax.microedition.io.*;
// include GUI class libraries
import javax.microedition.lcdui.*;
// include I/O class libraries
import java.io.*;

public class DatagramClient extends MIDlet implements CommandListener 
{
  // define the GUI components for entering the message text to be sent
  private Form mainScreen;
  private TextField sendingField;
  private Display myDisplay = null;
  private ChoiceGroup cg ;
  private TextField tf ;
  private DatagramClientHandler client;
  
  // define the GUI components for displaying the returned message
  private Form resultScreen;
  private TextField t1 , t2 , t3;
  private String resultString;
  
  // the "send" button on the mainScreen
  Command sendCommand = new Command("SEND", Command.CANCEL, 1);
  Command clear = new Command("CLEAR", Command.OK, 1);
  Command start = new Command("START", Command.OK, 1);
  
  public DatagramClient()
  {
    // initialize the GUI components
    myDisplay = Display.getDisplay(this);
    mainScreen = new Form("Datagram Client");
    cg = new ChoiceGroup("Select Operation",Choice.EXCLUSIVE);
    tf = new TextField("", "" , 8 , TextField.ANY) ;
    cg.append("Add", null) ;
    cg.append("Subtract", null) ;
    cg.append("Multiply", null) ;
    cg.append("Divide" , null) ;
    mainScreen.append(cg) ;
    mainScreen.append(tf) ;
    mainScreen.addCommand(sendCommand);
    mainScreen.addCommand(clear);
    mainScreen.setCommandListener(this);
  }
  
  public void startApp() 
  {
    myDisplay.setCurrent(mainScreen);
    client = new DatagramClientHandler();
  }
  
  public void pauseApp() 
  {
      
  }
  
  public void destroyApp(boolean unconditional) 
  {
      
  }
  
  public void commandAction(Command c, Displayable s) 
  {
    if (c == sendCommand) 
    {
      // get the message text from user input
      int sendMessage = cg.getSelectedIndex() ;
      String text = tf.getString() ;
      
      // send and receive datagram messages
      try 
      {
        if(sendMessage == 0)
        {    
            resultString = client.send_receive(sendMessage);
        }    
      } 
      catch (IOException e) 
      {
        System.out.println("Failed in send_receive():" + e);
      }
      
      // display the returned message
      resultScreen = new Form("Result from Server");
      t1 = new TextField("X", "" , 8, TextField.ANY);
      t2 = new TextField("Y", "" , 8, TextField.ANY);
      t3 = new TextField("Result", "" , 10, TextField.ANY);
      resultScreen.append(t1);
      resultScreen.append(t2);
      resultScreen.append(t3);
      resultScreen.setCommandListener(this);
      myDisplay.setCurrent(resultScreen);
    }
  }
  
  class DatagramClientHandler extends Object 
  {
    private DatagramConnection dc;
    // Datagram object to be sent
    private Datagram sendDatagram;
    // Datagram object to be received
    private Datagram receiveDatagram;
    
    public DatagramClientHandler() 
    {
      try 
      {
        // establish a DatagramConnection at port 9000
        dc = (DatagramConnection)Connector.open("datagram://" + ":9000");
        
        /* Since the datagram server program runs on the same machine
         * where the client program runs on, and the server program
         * listens to port 9001, the destination address of datagram
         * to be sent is set to "localhost:9001". If the server
         * program runs on a different machine, "localhost" in the
         * connect string needs to be replaced with that machine's ip
         * address.
         */
        sendDatagram = dc.newDatagram(
        dc.getMaximumLength(), "datagram://localhost:9001");
        
        // initialize the Datagram object to be received
        receiveDatagram = dc.newDatagram(dc.getMaximumLength());
      } 
      catch (IOException e) 
      {
        System.out.println(e.toString());
      }
    }
    
    public String send_receive(String msg) throws IOException 
    {
      int length = msg.length();
      byte[] message = new byte[length];
      
      // copy the send message text into a byte array
      System.arraycopy(msg.getBytes(), 0, message, 0, length);
      sendDatagram.setData(message, 0, length);
      sendDatagram.setLength(length);
      
      // use retval to store the received message text
      String retval = "";
      try {
        // send the message to server program
        dc.send(sendDatagram);
        
        // wait and receive message from the server
        dc.receive(receiveDatagram);
        
        // put the received message in a byte array
        byte[] data = receiveDatagram.getData();
        
        // transform the byte array to a string
        retval = new String(data, 0, receiveDatagram.getLength());
      } 
      finally
      {
        if (dc != null) dc.close();
      }
      
      // return the received message text to the calling program
      return retval;
    }
  }
}