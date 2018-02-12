package domain.model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

public class Order implements Serializable
{
	private int id;
	private String username;
	private int itemListID;
	private String status;
	private DataBase db;
	/**
	 * Constructor initializing the local variables.
	 * @param username The username of the user making the order.
	 * @param itemListID The list of items ordered.
	 */
	public Order(String username, int itemListID)
	{
		db = DataBase.getInstance();
				
		this.username = username;
		this.itemListID = itemListID;
		this.status = "Awaiting process";
	}

	public int getItemListID()
	{
		return itemListID;
	}

	public void setItemListID(int itemListID)
	{
		this.itemListID = itemListID;
	}

	public void setID(int id)
	{
		this.id=id;
	}
	
	public String getStatus()
	{
		return status;
	}
	/**
	 * changes the status of the Order and inserts it into the DataBase.
	 * @param status the status to put in the DataBase.
	 */
	public void setStatus(String status)
	{
		this.status = status;
		db.execute("update orders set status = '" + status + "' where orderid = " + id);
	}

	public int getId()
	{
		return id;
	}

	public String getUsername()
	{
		return username;
	}
	/**
	 * Inserts the Order Object into the database.
	 */
	public void insertInDataBase()
	{
		Calendar calendar = Calendar.getInstance();
	    java.sql.Timestamp tsobj = new java.sql.Timestamp(calendar.getTime().getTime());
		String sql = "insert into orders (orderid, username, itemlistid, date, status) values (?, ?, ?, ?, ?)";

		Object[] elements = { id, username, itemListID, tsobj, status};
		db.update(sql, elements);
	}
	
	
}
