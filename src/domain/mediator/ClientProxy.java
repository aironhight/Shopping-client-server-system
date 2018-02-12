package domain.mediator;

import java.io.IOException;
import java.util.ArrayList;

import domain.model.*;

/**
 * Class responsible for sending requests and objects to the ClientModelManager
 * and returning them to the Controller
 */
public class ClientProxy implements Model{

	private ClientModelManager proxy;
	
	public ClientProxy()
	{
		proxy = new ClientModelManager(this);
	}

	public synchronized void addObject(Object obj)
	{
		proxy.addObject(obj);
	}
	
	@Override
	public void notifyAboutItemsUpdate(Item item) 
	{
		proxy.notifyAboutItemsUpdate(item);
	}

	@Override
	public void postItem(Item item) 
	{
		proxy.postItem(item);
	}

	@Override
	public void removeItem(int id) 
	{
		proxy.removeItem(id);
	}

	@Override
	public boolean removeUser(String user) 
	{
		return proxy.removeUser(user);
	}

	@Override
	public void register(User user)
	{
		proxy.register(user);
	}

	@Override
	public void changeUserType(String user, String type) 
	{
		proxy.changeUserType(user, type);
	}

	@Override
	public ArrayList<Item> searchCateg(String category) 
	{
		return proxy.searchCateg(category);
	}

	@Override
	public ArrayList<Item> searchKW(String keyword) 
	{
		return proxy.searchKW(keyword);
	}

	@Override
	public ArrayList<Item> searchPriceNCateg(String category, double min, double max)
	{
		return proxy.searchPriceNCateg(category, min, max);
	}

	@Override
	public ArrayList<Item> searchPrice(double min, double max)
	{
		return proxy.searchPrice(min, max);
	}

	@Override
	public void makeOrder(String username, ItemList il)
	{
		proxy.makeOrder(username, il);
	}

	@Override
	public void addReview(String username, int itemId, int rate)
	{
		proxy.addReview(username, itemId, rate);
	}

	@Override
	public void changePassword(String username, String oldPass, String newPass)
	{
		proxy.changePassword(username, oldPass, newPass);
	}

	@Override
	public String getUsersType(String username)
	{
		return "you are in the wrong place, buddy...(Proxy)";
	}

	@Override
	public String authenticate(AuthCombination comb) 
	{
		return proxy.authenticate(comb);
	}

	@Override
	public User getUsersInformation(String username) {
		return proxy.getUsersInformation(username);
	}
	
	public void changeAddress(String username, Address address) {
		proxy.changeAddress(username, address);
	}
	public void changeMail(String username, String mail) {
		proxy.changeMail(username, mail);
	}

	@Override
	public Item getItemByID(int id)
	{
		return proxy.getItemByID(id);
	}

	@Override
	public ArrayList<Item> getOrderByID(int orderID)
	{
		return proxy.getOrderByID(orderID);
	}

	@Override
	public void removeItemFromOrder(int itemID, int itemListID)
	{
		proxy.removeItemFromOrder(itemID, itemListID);
	}

	@Override
	public int getItemListID(int orderID)
	{
		return proxy.getItemListID(orderID);
	}

	@Override
	public boolean cancelOrder(int orderID)
	{
		return proxy.cancelOrder(orderID);
	}
}
