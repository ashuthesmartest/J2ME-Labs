package rms;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.*;


public class FormMidlet extends MIDlet implements CommandListener 
{
   private RecordStore rs = null ;
   private Display display;
   private TextField major;
   private TextField Name;
   private TextField id;
   private Form form;
   private Form form1;
   private Form form2;
   private Command proceed;
   private Command retrieve;
   private Command back;
   private Command delete;
   private Command edit;
   private Command store;   
   private Alert alert;
   private RecordStore recordstore = null;
   String ms,msg2,msg4 ;
   public FormMidlet() 
   {
      display = Display.getDisplay(this);
      proceed = new Command("Proceed", Command.SCREEN,2);
      store = new Command("Store", Command.OK,2);      
      Name = new TextField("Name : ", "", 10, TextField.ANY);
      id = new TextField("ID : ", "", 10, TextField.ANY);
      major = new TextField("Major : ", "", 10, TextField.ANY);
      retrieve = new Command("Retrieve", Command.SCREEN, 2);
      form = new Form("Enter Data");
      form1= new Form("Display Information");
      form2= new Form("Delete Records");
      form.append(Name);
      form.append(id);
      form.append(major);
      back = new Command("Back", Command.SCREEN,2);
      delete = new Command("Delete", Command.CANCEL,1);
      edit = new Command("edit", Command.BACK,1);
      form.setCommandListener(this) ;
      form1.setCommandListener(this) ;
      form2.setCommandListener(this) ;      
      form.addCommand(proceed) ;
      form1.addCommand(retrieve) ;
      form1.addCommand(edit) ;
      form1.addCommand(store) ;
      form2.addCommand(delete);
      form2.addCommand(back);
    }

    public void startApp() 
    {
      display.setCurrent(form);
    }

    public void pauseApp() 
    {
        
    }

    public void destroyApp(boolean unconditional) 
    {
        
    }

    public void commandAction(Command c, Displayable d)
    {
        if (c == back)
        {
            display.setCurrent(form);
            form1.deleteAll();
        }
        if (c == edit)
        {
            display.setCurrent(form);
            form1.deleteAll();
        }        
        if (c == proceed)
        {
            ms=Name.getString();
            ms =  "Name : " + ms + "\n" ;
            msg2 = "ID: " + id.getString() + "\n";
            msg4 = "Major : " + major.getString() + "\n";
            form1.append(ms) ;
            form1.append(msg2) ;
            form1.append(msg4) ;
            display.setCurrent(form1);
        }
        if (c == store)
        {
             try
            {
                recordstore = RecordStore.openRecordStore("myRecordStore", true );
            }
            catch (Exception error)
            {
                alert = new Alert("Error Creating", error.toString(), null, AlertType.WARNING);
                alert.setTimeout(Alert.FOREVER);
                display.setCurrent(alert);
            }
            try
            {
                String[] outputData ={ms,msg2,msg4};
                for(int x=0;x<3;x++)
                {
                    byte[] byteOutputData = outputData[x].getBytes();
                    recordstore.addRecord(byteOutputData, 0,byteOutputData.length);
                }
                alert = new Alert("Record Added!!!!!");
                alert.setTimeout(Alert.FOREVER);
                display.setCurrent(alert);
            }
            catch ( Exception error)
            {
                alert = new Alert("Error Writing", error.toString(), null, AlertType.WARNING);
                alert.setTimeout(Alert.FOREVER);
                display.setCurrent(alert);
            }
        }
        if (c == retrieve)
        {
            try
            {
                int length = 0;
                byte[] byteInputData = new byte[4];
                String[] k1=new String[recordstore.getNumRecords()];
                for (int x = 1; x <= recordstore.getNumRecords(); x++)
                {
                    if (recordstore.getRecordSize(x) > byteInputData.length)
                    {
                        byteInputData = new byte[recordstore.getRecordSize(x)];
                    }
                    length = recordstore.getRecord(x, byteInputData, 0);
                    k1[x-1]=new String(byteInputData, 0, length);
                }
                for(int x=0;x<recordstore.getNumRecords();x++)
                {
                    form2.append(k1[x]);
                }
                display.setCurrent(form2);
            }
            catch (Exception error)
            {
                alert = new Alert("Error Reading", error.toString(), null, AlertType.WARNING);
                alert.setTimeout(Alert.FOREVER);
                display.setCurrent(alert);
            }
            try
            {
                recordstore.closeRecordStore();
            }
            catch (Exception error)
            {
                alert = new Alert("Error Closing", error.toString(), null, AlertType.WARNING);
                alert.setTimeout(Alert.FOREVER);
                display.setCurrent(alert);
            }
            if (RecordStore.listRecordStores() != null)
            {
                try
                {
                    RecordStore.deleteRecordStore("myRecordStore");
                }
                catch (Exception error)
                {
                    alert = new Alert("Error Removing", error.toString(), null, AlertType.WARNING);
                    alert.setTimeout(Alert.FOREVER);
                    display.setCurrent(alert);
                }
            }
        }
        if (c == delete)
        {
            try 
            {
                rs = RecordStore.openRecordStore("myRecordStore", true) ;
            } 
            catch (RecordStoreException ex) 
            {
                ex.printStackTrace();
            }
            try 
            {
                rs.deleteRecord(rs.getNextRecordID() - 1);
            } 
            catch (RecordStoreException ex) 
            {
                ex.printStackTrace();
            }
        }    
    }
}