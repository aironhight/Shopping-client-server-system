package domain.model;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Item implements Serializable
{
	private String name;
	private String category;
	private String description;
	private double price;
	private int id;
	private double avgrev;
	private ArrayList<String> keywords;
	private DataBase db;
	
	public Item(String name, String category, String description, double price)
	{
		db = DataBase.getInstance();

		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		avgrev = -1;
		keywords = new ArrayList<String>(5);
	}
	/**
	 * Saves the current item object into the database.
	 */
	public void insertInDataBase()
	{
		String sql = "INSERT INTO item (id, category, name, description, price, avgrev) VALUES (?, ?, ?, ?, ?, ?)";

		Object[] elements = { id, category, name, description, price, null };
		db.update(sql, elements);

		if (keywords.size() > 0)
		{
			for (int i = 0; i < keywords.size(); i++) 
			{
				sql = "insert into keyword (itemid, keyword) values(?, ?)";
				Object[] ele = { id, keywords.get(i) };
				db.update(sql, ele);
			}
		}
	}

	public String getName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public void setavgRev(double rev) {
		avgrev = rev;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public void setKeywords(ArrayList<String> keywords)
	{
		this.keywords = keywords;
	}
	
	public void addKeyWord(String keyword)
	{
		keywords.add(keyword);
	}
	
	public void setAvgReview(double avgrev)
	{
		this.avgrev = avgrev;
	}
	
	public double getAvgReview()
	{
		return avgrev;
	}
	
	public double getPrice()
	{
		return price;
	}
	/**
	 * @return Keywords from the keyword ArrayList.
	 */
	public String getKeyWords()
	{
		String s = "";
		for(int i=0; i<keywords.size(); i++) {
			s += keywords.get(i);
			if(i<keywords.size()-1) {
				s+=",";
			}
		}
		return s;
	}
	
	public String toString()
	{
		String s = "("+id+") " + name + "  {" + price + "}"
				+ "\n" + "{ ";
		
		for(int i=0; i<keywords.size(); i++) {
			s+= keywords.get(i);
		}
		
		return s + " }" + "\n"; 
	}
}
