package domain.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.ResultSet;

public class Review implements Serializable
{
	private String username;
	private int itemId;
	private DataBase db;
	/**
	 * Constructor initializing the local variables and saving the Review in the database
	 * @param username Username of the user making the review.
	 * @param itemId The ID of the item being reviewed.
	 * @param rate The rating given by the user.
	 * @throws Exception If the given rate is not legal / the user does not exists / the user already added review to that item.
	 */
	public Review(String username, int itemId, int rate) throws Exception
	{
		if(rate > 5 || rate < 1) {
			throw new IllegalArgumentException("Unavailable rate.(Review.class)");
		}
		db = DataBase.getInstance();
		this.itemId = itemId;
		this.username = username;
		
		String sql = "select count(*) from users where username = '" + username + "'";
		ArrayList<Object> res = db.query(sql);

		if(res.size() != 1) {
			throw new IllegalStateException("Cannot add review to invalid username.(Review.class");
		}
		sql = "select * from item where id = '" + itemId + "'";
		res = db.query(sql);

		if(res.size() != 1) {
			throw new IllegalStateException("Cannot add review to unexisting item.(Review.class");
		}
		
		sql = "select * from review where itemId = " + itemId + 
				" and username = '" + username + "'";
		res = db.query(sql);
		if(res.size() > 0) {
			throw new IllegalStateException("Review to the item already added by this user.");
		}
		
		sql = "INSERT INTO review (itemid, username, rating) values (?, ?, ?)";
		Object[] elements = {itemId, username, rate};
		db.update(sql, elements);	
	}
}
