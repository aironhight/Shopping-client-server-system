package domain.model;

import java.io.Serializable;

public class AuthCombination implements Serializable
{

	private String id;
	private String pass;
	/**
	 * Constructor initializing the local variables.
	 * @param id Username
	 * @param pass Password
	 */
	public AuthCombination(String id, String pass) 
	{
		super();
		this.id = id;
		this.pass = pass;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getPass() 
	{
		return pass;
	}

	public void setPass(String pass)
	{
		this.pass = pass;
	}
	
	
}