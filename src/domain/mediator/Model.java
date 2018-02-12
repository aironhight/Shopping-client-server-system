package domain.mediator;

import domain.model.*;
import java.util.ArrayList;


public interface Model {

	public String authenticate(AuthCombination comb);
	public void postItem(Item item);
	public void removeItem(int id);
	public boolean removeUser(String username);
	public void register(User user);
	public void changeUserType(String username, String type);
	public ArrayList<Item> searchPriceNCateg(String category, double min, double max);
	public ArrayList<Item> searchPrice(double min, double max);
	public ArrayList<Item> searchKW(String keyword);
	public ArrayList<Item> searchCateg(String category);
	public void makeOrder(String username, ItemList il);
	public void addReview(String username, int itemId, int rate);
	public void changePassword(String username, String oldPass, String newPass);
	public String getUsersType(String username);
	public void notifyAboutItemsUpdate(Item item);
	public void addObject(Object obj);
	public User getUsersInformation(String username);
	public void changeAddress(String username, Address address);
	public void changeMail(String username, String mail);
	public Item getItemByID(int id);
	public ArrayList<Item> getOrderByID(int orderID);
	public void removeItemFromOrder(int itemID, int itemListID);
	public int getItemListID(int orderID);
	public boolean cancelOrder(int orderID);
}
