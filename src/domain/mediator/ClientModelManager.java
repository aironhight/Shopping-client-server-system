package domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import domain.mediator.*;
import domain.model.*;

/**
 * Class responsible for sending requests and objects to the server
 * and returning them to the ClientProxy
 */
public class ClientModelManager implements Model, Observer{

	private ObjectInputStream inFromServer;
	private ObjectOutputStream outToServer;
	private Model model;
	private Socket socket;
	private ClientReceiver receiver;
	private ArrayList<Object> que;
	
	
	private final int PORT = 6789;
	private final String HOST = "localhost";
	
	/**
	 * One argument constructor 
	 * @param model to set the local field
	 */
	public ClientModelManager(Model model)
	{
		que = new ArrayList<Object>();
		this.model = model;
		try
	      {	         
			socket = new Socket(HOST, PORT);
			outToServer = new ObjectOutputStream(socket.getOutputStream());
	        inFromServer = new ObjectInputStream(socket.getInputStream()); 
			
	        receiver = new ClientReceiver(inFromServer, model);
	        new Thread(receiver, "Receiver").start();
	        
			System.out.println("Enter username & password");

	      }
		
		  catch (UnknownHostException e)
	      {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      catch (IOException e)
	      {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	}
	/**
	 * Adds object to the queue used for getting Objects from the server
	 * @param obj - Object to be added to the queue
	 */
	public synchronized void addObject(Object obj)
	{
		que.add(obj);
		notifyAll();
	}
	
	/**
	 * Sends combination of username and password to the server for authentication
	 * @param comb - the combination to be checked
	 * @return admin/user/false as string
	 */
	public synchronized String authenticate(AuthCombination comb) 
	{
		String toReturn = "";
		try
		{
			outToServer.writeObject((String)"auth");
				
			// send the authentication combo
				
			outToServer.writeObject(comb);
			while(que.size() == 0) 
			{
				try
				{
					wait();
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			toReturn = (String)que.get(0);
			que.remove(0);
					
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}


	public void notifyAboutItemsUpdate(Item item) 
	{
		// nada
	}

	/**
	 * Sends an item to the server to be uploaded and database
	 * @param the object of type Item
	 */
	public synchronized void postItem(Item item)  
	{
		try 
		{
			outToServer.writeObject((String)"post");
			
			outToServer.writeObject(item);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Sends and item's id to be removed from database
	 * @param integer with the ID of an item
	 */
	public synchronized void removeItem(int id) 
	{
		try 
		{
			outToServer.writeObject((String)"removeItem");
			
			outToServer.writeObject(id);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sends the username of a user that will be deleted from database
	 * @param string with a username of a user
	 * @return boolean true/false depending on if user with that username was removed or not
	 */
	public synchronized boolean removeUser(String username) 
	{
		boolean toReturn = false;
		try 
		{
			outToServer.writeObject((String)"removeUser");
			
			outToServer.writeObject(username);

			while(que.size() == 0) 
			{
				try
				{
					wait();
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			toReturn = (boolean)que.get(0);
			que.remove(0);
			return toReturn;
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}

	/**
	 * Sends a user to server to be added/registered
	 * @param user object containing all information
	 */
	public synchronized void register(User user) 
	{
		try 
		{
			outToServer.writeObject((String)"register");
			
			outToServer.writeObject(user);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sends the server infromation about user and his type to be changed
	 * @param username - the user to have his type changed
	 * @param type - the new type of user admin/normal user
	 */
	public synchronized void changeUserType(String username, String type) {
		try 
		{
			outToServer.writeObject((String)"changeUserType");
			
			outToServer.writeObject(username);
			
			outToServer.writeObject(type);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @author Hristo Stoyanov
	 * Sends a catagory to the server and awaits answer
	 * @param string category which will be send
	 * @return arrayList of item objects that match the category
	 */
	public synchronized ArrayList<Item> searchCateg(String category) 
	{
		try 
		{
			outToServer.writeObject((String)"search");
			
			outToServer.writeObject(category);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(que.size()==0) {
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArrayList<Item> it =(ArrayList<Item>) que.get(0);
		que.remove(0);
		return it;
	}

	/**
	 * @author Hristo Stoyanov
	 * Sends a catagory with two values to the server and awaits answer
	 * @param string category which will be send
	 * @param min, the minimum price for the search
	 * @param max, the maximum price for the search
	 * @return arrayList of item objects that match the category and are between min and max as price
	 */
	public synchronized ArrayList<Item> searchPriceNCateg(String category, double min, double max)
	{
		try
		{
			outToServer.writeObject((String)"searchCatPrice");
			outToServer.writeObject(category);
			outToServer.writeObject(min);
			outToServer.writeObject(max);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(que.size()==0) {
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArrayList<Item> it =(ArrayList<Item>) que.get(0);
		que.remove(0);
		return it;
	}
	
	/**
	 * @author Hristo Stoyanov
	 * Sends two values to the server and awaits answer of items between the two values
	 * @param min, the minimum price for the search
	 * @ param max, the maximum price for the search
	 * @return arrayList of item objects that match their price between min and max
	 */
	public synchronized ArrayList<Item> searchPrice(double min, double max)
	{
		try
		{
			outToServer.writeObject((String)"searchPrice");
			outToServer.writeObject(min);
			outToServer.writeObject(max);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(que.size()==0) {
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArrayList<Item> it =(ArrayList<Item>) que.get(0);
		que.remove(0);
		return it;
	}

	/**
	 * @author Hristo Stoyanov
	 * Sends a string to the server and awaits answer
	 * @param string keyword which will be send
	 * @return arrayList of item objects that have that keyword
	 */
	public synchronized ArrayList<Item> searchKW(String keyword)
	{
		try
		{
			outToServer.writeObject((String)"searchKW");
			outToServer.writeObject(keyword);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(que.size() == 0) {
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArrayList<Item> ret = (ArrayList<Item>)que.get(0);
		que.remove(0);
		return ret;
	}

	/**
	 * @author Hristo Stoyanov
	 * Sends username and itemList of items that will be transformed as an order
	 * @param username - the username of the user making the order
	 * @param il - ItemList of that user's purchased items
	 */
	public synchronized void makeOrder(String username, ItemList il)
	{
		try
		{
			outToServer.writeObject((String)"order");
			outToServer.writeObject(username);
			outToServer.writeObject(il);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a user's username who is making an review with the item and the rate of which
	 * @param username - the user making the review
	 * @param itemId - int ID of the item being reviewed 
	 * @param rate - the rate that has been given from the user
	 */
	public synchronized void addReview(String username, int itemId, int rate)
	{
		try
		{
			outToServer.writeObject((String)"addReview");
			outToServer.writeObject(username);
			outToServer.writeObject(itemId);
			outToServer.writeObject(rate);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a user's username that will have his password changed
	 * @param username - the username of the specific user
	 * @param oldPass - the old password of the user wanting to change 
	 * @param newPass - the new password of the user 
	 */
	public synchronized void changePassword(String username, String oldPass, String newPass)
	{
		try
		{
			outToServer.writeObject((String)"changePass");
			outToServer.writeObject(username);
			outToServer.writeObject(oldPass);
			outToServer.writeObject(newPass);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized String getUsersType(String username)
	{
		return "false";
	}

	/**
	 * Sends a username to the server and awaits full info on that user
	 * @param username - string containing the username of an user
	 * @return user - a user object with all information
	 */
	@Override
	public synchronized User getUsersInformation(String username) 
	{
		User user = null;
		try 
		{
			outToServer.writeObject((String)"getUserInfo");
			
			outToServer.writeObject(username);
			
			
			while(que.size() == 0)
			{
				try 
				{
					wait();
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			user = (User)que.get(0);
			que.remove(0);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		
		
	}
	
	/**
	 * Sends a username and a new address of that specific user
	 * @param username - string of the username of the user
	 * @param address - the new address to be set
	 */
	public synchronized void changeAddress(String username, Address address)
	{
		try
		{
			outToServer.writeObject((String)"changeAddress");
			outToServer.writeObject(username);
			outToServer.writeObject(address);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Hristo Stoyanov
	 * Sends a username and a mail to be set to that user
	 * @param username - string of the username of the user
	 * @param mail - the new email to be set
	 */
	public synchronized void changeMail(String username, String mail)
	{
		try
		{
			outToServer.writeObject((String)"changeMail");
			outToServer.writeObject(username);
			outToServer.writeObject(mail);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @author Hristo Stoyanov
	 * Sends and id to the server and waits for item with that id to be send back
	 * @param id - integer with the id of an item
	 * @return item that has id as the argument
	 */
	@Override
	public synchronized Item getItemByID(int id)
	{
		Item it = null;
		try
		{
			outToServer.writeObject((String)"getItemByID");
			outToServer.writeObject(id);
			while(que.size()==0) {
				wait();
			}
			it = (Item)que.get(0);
			que.remove(0);
			
		} catch (IOException | InterruptedException e)
		{
			e.printStackTrace();
		}
		return it;
	}

	/**
	 * @author Hristo Stoyanov
	 * Sends and order's id and awaits all items in that order to be send back
	 * @param orderID - integer of an order's id
	 * @return ArrayList of items that are in that order
	 */
	@Override
	public synchronized ArrayList<Item> getOrderByID(int orderID)
	{
		try
		{
			outToServer.writeObject((String)"orderByID");
			outToServer.writeObject(orderID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(que.size() == 0) {
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArrayList<Item> ret = (ArrayList<Item>)que.get(0);
		que.remove(0);
		return ret;
	}

	/**
	 * @author Hristo Stoyanov
	 * Sends and item's id and a order's id, has that item removed from the order
	 * @param itemID - integer, the item to be removed's id
	 * @param itemListID - integer, the order's id
	 */
	@Override
	public synchronized void removeItemFromOrder(int itemID, int itemListID)
	{
		try
		{
			outToServer.writeObject("removeItemFromOrder");
			outToServer.writeObject(itemID);
			outToServer.writeObject(itemListID);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/**
	 * @author Hristo Stoyanov
	 * Sends the order's ID and returns the id of the itemList containing the items in that order
	 * @param orderID - the id of the desired order
	 * @return int of the itemList's id containing the items in that order
	 */
	@Override
	public synchronized int getItemListID(int orderID)
	{
		try
		{
			outToServer.writeObject("getItemListID");
			outToServer.writeObject(orderID);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(que.size()==0) {
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int id = (int)que.get(0);
		que.remove(0);
		return id;
	}

	
	/**
	 * @author Hristo Stoyanov
	 * Sets status of order with specific ID to "Cancelled"
	 * @param The ID of the order.
	 * @return true if there is such order and the status was changed.
	 */
	@Override
	public synchronized boolean cancelOrder(int orderID)
	{
		try
		{
			outToServer.writeObject("cancelOrder");
			outToServer.writeObject(orderID);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(que.size()==0) {
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while(que.size()==0) {
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		boolean success = (boolean)que.get(0);
		que.remove(0);
		return success;
	}

}
