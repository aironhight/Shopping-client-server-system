package domain.mediator;

import java.util.Observable;
import domain.model.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServerModelManager extends Observable implements Model 
{

	private DataBase db;

	public ServerModelManager() 
	{
		db = DataBase.getInstance();
		
		Thread serverConnection = new Thread(new Server(this), "Server");
		serverConnection.start();
	}

	public void notifyAboutItemsUpdate(Item item) 
	{
		super.setChanged();
		super.notifyObservers(item);
	}
	/**
	 * Authenticates the user
	 * @param comb The user's login info.
	 * @return String with answer if the user is authenticated and what type is it.
	 */
	public String authenticate(AuthCombination comb) 
	{
		String sql = "select type from users where username = '" + comb.getId() +
					"' and password = '" + comb.getPass() + "' and type = 'user'" ;
		ArrayList<Object> ar = db.query(sql);
		
		if(ar.size() > 0) {
			return "user";//if the client is normal user
		}
		sql = "select type from users where username = '" + comb.getId() +
				"' and password = '" + comb.getPass() + "' and type = 'admin'";
		
		ar = db.query(sql);
		if(ar.size() > 0) {
			return "admin"; //if the client is admin
		}
		
		return "false"; //if there is no such combination 
	}
	/**
	 * Posts an item into the database
	 * @param item The item to be posted in the database.
	 */
	public void postItem(Item item) 
	{
		ResultSet rs = db.queryRS("select max(id) from item");
		int id = 0;
		try
		{
			while (rs.next()) {
				id = rs.getInt("max") + 1;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		item.setID(id);
		item.insertInDataBase();
	}
	/**
	 * Removes item with specific ID from the database.
	 * @param id the id of the item
	 */
	public void removeItem(int id) 
	{
		String sql = "delete from keyword where itemid = " + id;
		db.execute(sql);
		sql = "delete from item where id = " + id;
		db.execute(sql);
	}
	/**
	 * Removes a user from the database with specific username
	 * @param username the user to be removed
	 * @return if the user was removed.
	 */
	public boolean removeUser(String username) 
	{
		String sql = "delete from users where username = '" + username + "'";
		db.execute(sql);
		ArrayList<Object> res = db.query("select username from users where username = '"+ username + "'");
		return res.size()==0;
	}
	/**
	 * Registers a user into the database.
	 * @param user The user Object to be inserted into the database.
	 */
	public void register(User user)
	{
		user.insertInDataBase();
	}
	/**
	 * Changes the type of an user.
	 * @param username The username of the user.
	 * @param type The new type of the user.
	 */
	public void changeUserType(String username, String type) 
	{
		String sql = "UPDATE users SET type = '" + type +"'" + 
					" WHERE username = '" + username +"'";
	}
	/**
	 * Get list of items by specific category.
	 * @param category The category of the items.
	 * @return List of items with specific category
	 */
	public ArrayList<Item> searchCateg(String category) 
	{
		ItemList il = new ItemList();
		ArrayList<Item> ar = il.getByCategory(category);
		for(int i=0; i<ar.size(); i++) {
			ar.get(i).setID(il.getItemsID(ar.get(i)));
		}
		return ar;
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
		ItemList il = new ItemList();
		ArrayList<Item> ar = il.getByCategoryAndPrice(category, min, max);
		for(int i=0; i<ar.size(); i++) {
			ar.get(i).setID(il.getItemsID(ar.get(i)));
		}
		return ar;
	}
	/**
	 * Get list of items by specific price.
	 * @param min The minimum price of the items.
	 * @param max The maximum price of the items.
	 * @return List of items with specific price.
	 */
	public ArrayList<Item> searchPrice(double min, double max)
	{
		ItemList il = new ItemList();
		ArrayList<Item> ar = il.getByPrice(min, max);
		for(int i=0; i<ar.size(); i++) {
			ar.get(i).setID(il.getItemsID(ar.get(i)));
		}
		return ar;
	}
	/**
	 * Get list of items by specific keyword
	 * @param Keyword to search for
	 * @return List of items with specific keyword.
	 */
	public ArrayList<Item> searchKW(String keyword)
	{
		ItemList il = new ItemList();
		ArrayList<Item> ar = il.getByKeyword(keyword);
		for(int i=0; i<ar.size(); i++) {
			ar.get(i).setID(il.getItemsID(ar.get(i)));
		}
		return ar;
	}
	/**
	 * Makes order and inserts it into the database.
	 * @param username The username of the user making the order.
	 * @param il The list of items for the order.
	 */
	public void makeOrder(String username, ItemList il)
	{
		int ilid = 1;
		ResultSet rs = db.queryRS("select max(listid) from itemlist");
		try
		{
			while(rs.next()) {	
					ilid = rs.getInt("max") +1;
			}
		} catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		il.setID(ilid);
		il.insertInDataBase();
		
		rs = db.queryRS("select max(orderid) from orders");
		try
		{
			while(rs.next()) {
				ilid = rs.getInt("max") + 1;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Order ord = new Order(username, il.getID());
		ord.setID(ilid);
		ord.insertInDataBase();
	}
	/**
	 * Makes a new review object with the constructor(username, itemID, rate)
	 */
	public void addReview(String username, int itemId, int rate)
	{
		try {
			Review rev = new Review(username, itemId, rate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Changes the password of the user.
	 */
	public void changePassword(String username, String oldPass, String newPass)
	{
		ArrayList<Object> res = db.query("select count(*) from users where username = '" +
						username + "' and password = '" + oldPass + "'");
		if(res.size() == 0) {
			throw new IllegalArgumentException("Wrong password.");
		}
		
		db.execute("UPDATE users SET password = '" + newPass + 
				"' WHERE username = '" + username + "'");
	}
	
	public synchronized void addObject(Object obj)
	{
		//empty
	}
	/**
	 * Returns the type of the user.
	 * @param username username of the user to be examined.
	 */
	public String getUsersType(String username)
	{
		ResultSet rs = db.queryRS("select type from users where username = '" + username +"'");
		String result = "";
		try {
			result = rs.getString("type");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * gets the information about the user.
	 * @param username
	 */
	@Override
	public User getUsersInformation(String username)
	{
		ResultSet rs = db.queryRS("select * from users where username = '" + username + "'");
		User us = null;
		try
		{
			while(rs.next())
			{
				String user = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String add = rs.getString("address");
				String fn = rs.getString("firstname");
				String ln = rs.getString("lastname");
				String adrstr = rs.getString("address");
				String[] adr = adrstr.split(", ");
				Address address = new Address(adr[0], adr[1], adr[2]);
				
				us = new User(user, password, email, "user", fn, ln, address);	
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us;
	}
	/**
	 * changes the adress of a user.
	 * @param username the user.
	 * @param address the new address.
	 */
	@Override
	public void changeAddress(String username, Address address)
	{
		db.execute("UPDATE users SET address = '" + address.toString()  + "' WHERE username = '" + username + "'");
	}
	/**
	 * changes the email of a user
	 * @param username the user's username.
	 * @param mail the new email.
	 */
	@Override
	public void changeMail(String username, String mail)
	{
		db.execute("UPDATE users SET email = '" + mail + "' WHERE username = '" + username + "'");	
	}
	/**
	 * Returns item object by ID
	 * @param id The ID of the item
	 * @return Item object with specific ID.
	 */
	public Item getItemByID(int id)
	{
		ResultSet rs = db.queryRS("select * from item where id = " + id);
		String name ="";
		String cat = "";
		String desc = "";
		double price = -1;
		
		
		try {
			while(rs.next()) {
				name = rs.getString("name");
				cat = rs.getString("category");
				desc = rs.getString("description");
				price = rs.getDouble("price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Item it =  new Item(name, cat, desc, price);
		rs = db.queryRS("select keyword from keyword where itemid = " + id);
		try {
			while(rs.next()) {
				String keyword = rs.getString("keyword");
				it.addKeyWord(keyword);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return it;
	}
	/**
	 * Gets Items from order by OrderID
	 * @param orderID the ID of the order.
	 * @return list of items from the order with specified ID.
	 */
	@Override
	public ArrayList<Item> getOrderByID(int orderID)
	{
		ArrayList<Item> il = new ArrayList<Item>();
		ResultSet res = db.queryRS("select * from item i join itemlist l on (i.id = l.itemID)"
				+ "join orders o on (o.itemlistid = l.listid) "
				+ "where orderid = " + orderID);
		try {
			while(res.next()) {
				String name = res.getString("name");
				String cat = res.getString("category");
				String desc = res.getString("description");
				double price = res.getDouble("price");
				int id = res.getInt("id");
				
				Item it =  new Item(name, cat, desc, price);
				it.setID(id);
				il.add(it);
				
				ResultSet rs = db.queryRS("select keyword from keyword where itemid = " + id);
				while(rs.next()) {
					it.addKeyWord(rs.getString("keyword"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return il;
	}
	/**
	 * Removes item from order.
	 * @param itemID The item to be removed
	 * @param ilID the ItemList ID to remove the item from.
	 */
	@Override
	public void removeItemFromOrder(int itemID, int ilID)
	{
		db.execute("delete FROM itemlist WHERE itemid = " + itemID + 
				" and listID = " + ilID);
		System.out.println("im here bruh");
	}
	/**
	 * Gets the ID of an itemlist by order ID
	 * @param orderID the ID of the order.
	 * @return the item list's ID.
	 */
	public int getItemListID(int orderID)
	{
		ResultSet rs = db.queryRS("select itemlistid from orders where orderid = "+ orderID);
		int tor = -1;
		try
		{
			while(rs.next()) {
				tor = rs.getInt("itemlistid");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return tor;
	}

	@Override
	/**
	 * Sets status of order with specific ID to "Cancelled"
	 * @param The ID of the order.
	 * @return true if there is such order and the status was changed.
	 */
	public boolean cancelOrder(int orderID)
	{
		ArrayList<Object> res = db.query("select * from orders where orderID = " + orderID);
		if(res.size() == 1) {
			db.execute("update orders set status = 'Cancelled' where orderID=" +orderID);
			return true;
		} else {
			return false;
		}
	}

}
