package domain.model;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String type;
	private Address address;
	private DataBase db;
	/**
	 * Constructor initializing the local variables.
	 * @param username The username of the user.
	 * @param password The password of the user.
	 * @param email The email of the user.
	 * @param type The type of the user.
	 * @param firstName The first name of the user.
	 * @param lastName The last name of the user.
	 * @param address The Address of the user.
	 */
	public User(String username, String password, String email, String type, String firstName, String lastName, Address address)
	{
		db = DataBase.getInstance();
					
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.address = address;
	}
	/**
	 * Inserts the User object into the Database
	 * @throws IllegalArgumentException if the username or the email is already taken.
	 */
	public void insertInDataBase() throws IllegalArgumentException
	{
		ArrayList<Object> res = db.query("select * from users where username = '" + username +"'");
		if(res.size() > 0) {
			throw new IllegalArgumentException("Username already taken.");
		}
		
		res = db.query("select * from users where email = '" + email + "'");
		if(res.size() > 0) {
			throw new IllegalArgumentException("Email already taken.");
		}	
		
		String sql = "INSERT INTO users (username, password, email, firstname, lastname, address, type) " +
				"VALUES(?, ?, ?, ?, ?, ?, ?)";
				
		Object[] elements = {username, password, email, firstName, lastName, address.toString(), type};
		db.update(sql, elements);
	}
	
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getAddress() {
		return address.toString();
	}
	
	public String toString()
	{
		return firstName + " " + lastName;
	}
	
}
