package domain.model;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Serializable;

public class ItemList implements Serializable
{
	private static DataBase db;
	private ArrayList<Item> itemlist;
	private int id;
	/**
	 * Constructor initializing the local variables.
	 */
	public ItemList()
	{
		db = DataBase.getInstance();
		itemlist = new ArrayList<Item>();
	}
	
	public int size() {
		return itemlist.size();
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getID()
	{
		return id;
	}
	/**
	 * Adds item to the list
	 * @param item The item to be added to the list.
	 */
	public void addItem(Item item) {
		itemlist.add(item);
	}
	/**
	 * Gets item from the list at specific index.
	 * @param index the index of the Item in the list
	 * @return Item at the specified index from the list.
	 */
	public Item getItemByIndex(int index)
	{
		return itemlist.get(index);
	}
	/**
	 * Removes a specific item from the list.
	 * @param item the item to be removed from the lsit
	 */
	public void removeItem(Item item) {
		itemlist.remove(item);
	}
	
	public String toString() {
		String s = "";
		
		for(int i=0; i<size(); i++) {
			s += (i+1) + ". " + itemlist.get(i) + "\n";
		}
		return s;
	}
	/**
	 * Gets the item's ID from the database
	 * @param item The item which will be examined in the database.
	 * @return the item's ID
	 */
	public int getItemsID(Item item)
	{
		int itemID = -1;
		ResultSet rs = db.queryRS("select id from item where name = '" + item.getName() + "' and price = " + item.getPrice()
				+ " and category = '" + item.getCategory() + "' and description = '" 
				+ item.getDescription() + "'");
		try {
			while(rs.next())
			itemID = rs.getInt("id");
		} catch (SQLException e) {
			System.out.println("SQLException (ItemList.class/getItemsID)");
			e.printStackTrace();
		}
		return itemID;
	}
	/**
	 * Saves the ItemList in the database
	 */
	public void insertInDataBase()
	{
		String sql = "insert into itemlist (listid, itemid)  VALUES (?, ?)";

		for(int i=0; i<itemlist.size(); i++) {
			Object[] elements = { id, itemlist.get(i).getId()};
			db.update(sql, elements);
		}
	}
	/**
	 * Get list of items by specific category.
	 * @param category The category of the items.
	 * @return List of items with specific category
	 */
	public static ArrayList<Item> getByCategory(String category)
	{
			ArrayList<Item> items = new ArrayList<Item>();
			
			String sql = "select * from item where category = '"
					+ category + "'";
			
			ResultSet rs = db.queryRS(sql);
		try {	
			while(rs.next()) {
				int id = rs.getInt("id");
				
				ArrayList<String> kwarr = new ArrayList<String>(5);
				
				ResultSet kw = db.queryRS("select keyword from keyword where itemID = " + id);
				while(kw.next()) {
					kwarr.add(kw.getString("keyword"));
				}
				
				String desc = rs.getString("description");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				double rev = rs.getDouble("avgrev");
				
				Item temp = new Item(name, category, desc, price);
				temp.setKeywords(kwarr);
				temp.setavgRev(rev);
				items.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception (ItemList.class/(getByCategory))");
		}
		
		return  items;
	}
	/**
	 * Get list of items by specific price.
	 * @param min The minimum price of the items.
	 * @param max The maximum price of the items.
	 * @return List of items with specific price.
	 */
	public static ArrayList<Item> getByPrice(double min, double max)
	{
		ArrayList<Item> items = new ArrayList<Item>();
		
		String sql = "select * from item where price between " + min + " and " + max;
		
		ResultSet rs = db.queryRS(sql);
	try {	
		while(rs.next()) {
			
			
			int id = rs.getInt("id");
			
			ArrayList<String> kwarr = new ArrayList<String>(5);
			
			ResultSet kw = db.queryRS("select keyword from keyword where itemID = " + id);
			while(kw.next()) {
				kwarr.add(kw.getString("keyword"));
			}
			
			String cat = rs.getString("category");
			String desc = rs.getString("description");
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			double rev = rs.getDouble("avgrev");			
			Item temp = new Item(name, cat, desc, price);
			
			temp.setKeywords(kwarr);
			temp.setavgRev(rev);
			items.add(temp);
		}
	} catch (SQLException e) {
		System.out.println("SQL Exception (ItemList.class/(getByPrice))");
	}
	
	return  items;
	}
	/**
	 * Get list of items by specific category and price.
	 * @param category The category of the items.
	 * @param min The minimum price of the items.
	 * @param max The maximum price of the items.
	 * @return List of items with specific category and price
	 */
	public static ArrayList<Item> getByCategoryAndPrice(String category, double min, double max)
	{
			ArrayList<Item> items = new ArrayList<Item>();
			
			
			String sql = "select * from item where category = '"
					+ category + "'" + " and price between " + min + " and " + max;
			
			ResultSet rs = db.queryRS(sql);
			
			
		try {	
			while(rs.next()) {
				int id = rs.getInt("id");
				
				ArrayList<String> kwarr = new ArrayList<String>(5);
				
				ResultSet kw = db.queryRS("select keyword from keyword where itemID = " + id);
				while(kw.next()) {
					kwarr.add(kw.getString("keyword"));
				}
				
				
				String desc = rs.getString("description");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				double rev = rs.getDouble("avgrev");
				
				Item temp = new Item(name, category, desc, price);
				temp.setKeywords(kwarr);
				temp.setavgRev(rev);
				items.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception (ItemList.class/(getByCategoryAndPrice))");
		}
		return  items;
	}
	/**
	 * Get list of items by specific keyword
	 * @param Keyword to search for
	 * @return List of items with specific keyword.
	 */
	public static ArrayList<Item> getByKeyword(String word)
	{
			ArrayList<Item> items = new ArrayList<Item>();
			
			String sql = "select * from item i join keyword k on(i.id = k.itemID) where k.keyword = '" +
			word + "'";

			ResultSet rs = db.queryRS(sql);
			
		try {	
			
			while(rs.next()) {
				int id = rs.getInt("id");
				
				ArrayList<String> kwarr = new ArrayList<String>(5);
				
				ResultSet kw = db.queryRS("select keyword from keyword where itemID = " + id);
				while(kw.next()) {
					kwarr.add(kw.getString("keyword"));
				}
				
				String desc = rs.getString("description");
				String cat = rs.getString("category");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				double rev = rs.getDouble("avgrev");

				Item temp = new Item(name, cat, desc, price);
				temp.setavgRev(rev);
				temp.setKeywords(kwarr);
				items.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("SQL Exception (ItemList.class/(getByCategoryAndPrice))");
		}
		return  items;
	}
	
}
