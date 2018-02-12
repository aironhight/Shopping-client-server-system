package domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.util.Scanner;
import java.util.ArrayList;

import domain.model.*;

/**
 * 
 * The class is responsible for receiving all kinds of data from the server and managing it
 */
public class ClientReceiver implements Runnable
{
   private ObjectInputStream inFromServer;
   private Model model;

   /**
    * Two argument constructor 
    * @param inFromServer, takes and ObjectInputStream and sets it
    * @param model, takes a class implementing Model and sets it
    */
   public ClientReceiver(ObjectInputStream inFromServer, Model model ) 
   {
      this.inFromServer = inFromServer;
      this.model = model;
   }

   /**
    * The run method used in a thread in ClientMain, takes all kinds of request and enqueues them so that
    * cliendModelManager can dequeue and get them
    */
   public void run()
   {
      try
      {
    	      	  
         while (true)
         {
        	String what = (String)inFromServer.readObject();
        	System.out.println("CLIENT RECEIVER " + what);
        	
        	if(what.equals("auth")) 
        	{
        		String answer= (String)inFromServer.readObject();
        		model.addObject(answer);
        	}
        	else if(what.equals("UserInfo"))
        	{
        		model.addObject((User)inFromServer.readObject());
        	}
        	
        	else if(what.equals("removeUser"))
        	{
        		model.addObject((boolean)inFromServer.readObject());
        	}
        	else if(what.equals("searchKW"))
        	{
        		model.addObject((ArrayList<Item>)inFromServer.readObject());
        	}
        	else if(what.equals("searchCatPrice"))
        	{
        		model.addObject((ArrayList<Item>)inFromServer.readObject());
        	}
        	else if(what.equals("searchPrice"))
        	{
        		model.addObject((ArrayList<Item>)inFromServer.readObject());
        	}
        	else if(what.equals("getUserInfo"))
        	{
        		model.addObject((User)inFromServer.readObject());
        	}
        	else if(what.equals("searchCat"))
        	{
        		model.addObject((ArrayList<Item>)inFromServer.readObject());
        	}
        	else if(what.equals("getItemByID"))
        	{
        		model.addObject((Item)inFromServer.readObject());
        	}
        	else if(what.equals("orderByID"))
        	{
        		model.addObject((ArrayList<Item>)inFromServer.readObject());
        	}
        	else if(what.equals("getItemListID"))
        	{
        		model.addObject((int)inFromServer.readObject());
        	}
        	else if(what.equals("cancelOrder"))
        	{
        		model.addObject((boolean)inFromServer.readObject());
        	}
        	
         }
      }
      catch (SocketException e)
      {
         // ok
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
     
}
