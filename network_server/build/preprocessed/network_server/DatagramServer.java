package network_server;

// include MIDlet class libraries
import javax.microedition.midlet.*;
// include networking class libraries
import javax.microedition.io.*;
// include GUI class libraries
import javax.microedition.lcdui.*;
// include I/O class libraries5
import java.io.*;

public class DatagramServer extends MIDlet
{
  // define the GUI components for displaying the received message
  private Display myDisplay = null;
  private Form mainScreen;
  private StringItem resultField;
  
  // text string for storing the received message
  private String resultString;
  
  public DatagramServer() 
  {
    // initialize the GUI components
    myDisplay = Display.getDisplay(this);
    mainScreen = new Form("Message Received");
    resultField = new StringItem(null, null);
  }
  
  public void startApp() 
  {
    myDisplay.setCurrent(mainScreen);  
    // perform the receive, reverse and send back tasks
    DatagramServerHandler server = new DatagramServerHandler();
    try 
    {
      resultString = server.receive_reverse_send();
    } 
    catch (IOException e) 
    {
      System.out.println("Failed in receive_reverse_send():" + e);
    }
    
    // display the received message text
    resultField.setText(resultString);
    mainScreen.append(resultField);
  }
  
  public void pauseApp() 
  {
      
  }
  
  public void destroyApp(boolean unconditional) 
  {
      
  }
  
  class DatagramServerHandler extends Object 
  {
    // the server program listens to port 9001
    private static final String defaultPortNumber="9001";
    private String msg;
    private DatagramConnection dc;
    
    // define the Datagram objects for messages
    private Datagram sendDatagram;
    private Datagram receiveDatagram;
    
    public DatagramServerHandler() {
      try {
        // Create a "server" mode connection on the default port
        dc = (DatagramConnection)Connector.open("datagram://:" + defaultPortNumber);
        
        // construct a Datagram object for receiving message
        receiveDatagram = dc.newDatagram(dc.getMaximumLength());
        
        // construct a Datagram object for sending message
        sendDatagram = dc.newDatagram(dc.getMaximumLength());
        
      } catch (Exception e) {
        System.out.println("Failed to initialize Connector");
      }
    }
    
    public String receive_reverse_send() throws IOException {
      String receiveString = "";
      try{
        // wait to receive datagram message
        dc.receive(receiveDatagram);
        
        // extract data from the Datagram object receiveDatagram
        byte[] receiveData = receiveDatagram.getData();
        int receiveLength = receiveDatagram.getLength();
        
        // store message text in receiveString
        receiveString = (new String(receiveData)).trim();
        
        // reverse the string
        StringBuffer reversedString =
        (new StringBuffer(receiveString)).reverse();
        
        // getting the reply-to address from the Datagram object
        String address = receiveDatagram.getAddress();
        
        // construct the sendDatagram with the reversed text
        // and the reply-to address.
        int sendLength = reversedString.length();
        byte[] sendData = new byte[sendLength];
        System.arraycopy(reversedString.toString().getBytes(),
        0, sendData, 0, sendLength);
        sendDatagram.setData(sendData, 0, sendLength);
        sendDatagram.setAddress(address);
        
        // send the reversed string back to client program
        dc.send(sendDatagram);
      } finally {
        if (dc != null) dc.close();
      }
      return receiveString;
    }
  }
}