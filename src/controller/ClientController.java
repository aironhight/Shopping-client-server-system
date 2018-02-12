package controller;

import java.util.ArrayList;
import java.util.Observable;

import domain.mediator.Model;
import domain.mediator.ServerModelManager;
import domain.model.*;
import view.*;


public class ClientController implements Controller
{
   private Model model;
   private View view;

   /**
    * Constructor initializing the local variables.
    * @param model Object of model.
    * @param view object of View.
    */
   public ClientController(Model model, View view)
   {
      this.model = model;
      this.view = view;  
   }
   
   /**
    * Checks client info and gives answer to 
    * @param comb - Client login credentials.
    * @return admin(if the client is admin), user(if the client is normal user), false(if there is no such account).
    */
	public String authenticate(AuthCombination comb) 
	{
		return model.authenticate(comb);
	}
	/**
	 * Sends post request to the server.
	 */
	public void postItem(Item item) 
	{
		model.postItem(item);
	}
	/**
	 * Removes item with specific id from the database.
	 */
	public void removeItem(int id) 
	{
		model.removeItem(id);
	}
	/**
	 * removes user from the database
	 * @return boolean if the user was removed.
	 */
	public boolean removeUser(String username) 
	{
		return model.removeUser(username);
	}
	/**
	 * Makes register request to the server.
	 */
	public void register(User user) 
	{
		model.register(user);
	}
	/**
	 * Changes the type of the user with specific username.
	 */
	public void changeUserType(String username, String type) 
	{
		model.changeUserType(username,type);
	}
	/**
	 * Get list of items by specific category and price.
	 * @param category The category of the items.
	 * @param min The minimum price of the items.
	 * @param max The maximum price of the items.
	 * @return List of items with specific category and price
	 */
	public ArrayList<Item> searchPriceNCateg(String category, double min, double max)
	{
		return model.searchPriceNCateg(category, min, max);
	}
	/**
	 * Get list of items by specific price.
	 * @param min The minimum price of the items.
	 * @param max The maximum price of the items.
	 * @return List of items with specific price.
	 */
	public ArrayList<Item> searchPrice(double min, double max)
	{
		return model.searchPrice(min, max);
	}
	/**
	 * Get list of items by specific keyword
	 * @param Keyword to search for
	 * @return List of items with specific keyword.
	 */
	public ArrayList<Item> searchKW(String keyword)
	{
		return model.searchKW(keyword);
	}
	/**
	 * Get list of items by specific category.
	 * @param category The category of the items.
	 * @return List of items with specific category
	 */
	public ArrayList<Item> searchCateg(String category)
	{
		return model.searchCateg(category);
	}
	/**
	 * Sends makeOrder request from the view to the Client Manager.
	 */
	public void makeOrder(String username, ItemList il)
	{
		model.makeOrder(username, il);
	}
	/**
	 * Sends addReview request from the view to the Client Manager.
	 */
	public void addReview(String username, int itemId, int rate)
	{
		model.addReview(username, itemId, rate);
	}
	/**
	 * Sends changePassword request from the view to the Client Manager.
	 */
	public void changePassword(String username, String oldPass, String newPass)
	{
		model.changePassword(username, oldPass, newPass);
	}
	/**
	 * Sends getUsersType request from the view to the Client Manager.
	 */
	public String getUsersType(String username) 
	{
		return model.getUsersType(username);
	}
	/**
	 * Sends getUsersInformation request from the view to the Client Manager.
	 */
	public User getUsersInformation(String username) 
	{
		return model.getUsersInformation(username);
	}
	/**
	 * Sends changeAddress request from the view to the Client Manager.
	 */
	public void changeAddress(String username, Address address)
	{
		model.changeAddress(username, address);
	}
	/**
	 * Sends changeMail request from the view to the Client Manager.
	 */
	public void changeMail(String username, String mail)
	{
		model.changeMail(username, mail);
	}
	/**
	 * Sends getItemByID request from the view to the Client Manager.
	 */
	public Item getItemByID(int id)
	{
		return model.getItemByID(id);
	}
	/**
	 * Sends getItemsFromOrder request from the view to the Client Manager.
	 */
	public ArrayList<Item> getItemsFromOrder(int orderID)
	{
		return model.getOrderByID(orderID);
	}
	/**
	 * Sends removeItemFromOrder request from the view to the Client Manager.
	 */
	public void removeItemFromOrder(int itemID, int itemListID)
	{
		model.removeItemFromOrder(itemID, itemListID);
	}
	/**
	 * Sends getItemListID request from the view to the Client Manager.
	 */
	public int getItemListID(int orderID)
	{
		return model.getItemListID(orderID);
	}
	/**
	 * Sends cancelOrder request from the view to the Client Manager.
	 */
	public boolean cancelOrder(int orderID)
	{
		return model.cancelOrder(orderID);
	}
	  
}