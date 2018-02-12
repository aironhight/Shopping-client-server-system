package domain.model;

import java.sql.*; //Only God can judge me.
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import java.io.Serializable;

public class DataBase implements Serializable
{
	static Connection conn = null;
	final String userID = "postgres";
	final String password = "aironhight";
	final String database = "postgres";
	private static DataBase instance;
	/**
	 * Constructor connecting to the postgreSQL driver.
	 */		
	private DataBase()
	{
		try
		{
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e)
		{
			System.out.println("Error in finding the jdbc driver. (DataBase.class/DataBase())");
		}
	}
	/**
	 * Get instance of the database.
	 * @return instance of the database
	 */
	public static DataBase getInstance()
	{
		if(instance == null) {
			instance = new DataBase();
		}
		return instance;
	}

	/**
	 * Method opening connection to the database.
	 * @throws SQLException
	 */
	private void openDatabase() throws SQLException
	{	
		try {
			conn = DriverManager.getConnection(
			 "jdbc:postgresql://localhost:5432/"+database
			 ,userID
			 ,password);
			conn.setSchema("SEP2");
		} catch (Exception e) {
			System.out.println("asdsa");
		}
	}
	/**
	 * Method closing connection to the database.
	 * @throws SQLException
	 */
	private void closeDatabase() throws SQLException
	{
		try {
		conn.close();
		} catch (SQLException e) {
			System.out.println("Error closing database connection.(DataBase.class/closeDatabase() )" );
		}
	}
	/**
	 * Executes SQL code and returns list of the rows.
	 * @param sql The SQL code to be executed
	 * @return ArrayList with object representing each row returned from the database
	 */
	public ArrayList<Object> query(String sql) 
	{
		PreparedStatement statement = null;
		ArrayList<Object> list = null;
		ResultSet resultSet = null;
		
		try {
		openDatabase();
		
		statement = conn.prepareStatement(sql);
		
		resultSet = statement.executeQuery();
		
		list = new ArrayList<Object>();
		while (resultSet.next())
		{
			Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
			for (int i = 0; i < row.length; i++)
			{
				row[i] = resultSet.getObject(i + 1);
			}
			list.add(row);
		}
		resultSet.close();
		statement.close();

		closeDatabase();
		
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error opening database.(DataBase.class/queryNSE() )" );
		}
		return list;
	}
	/**
	 * Executes SQL code and retrieves ResultSet representing all of the rows returned.
	 * @param sql The SQL code to be executed
	 * @return ResultSet representing each row retrieved from the query.
	 */
	public ResultSet queryRS(String sql)
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			openDatabase();
			
			statement = conn.prepareStatement(sql);
			
			resultSet = statement.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Error opening database.(DataBase.class/queryNSE() )" );
		}
		
		return resultSet;
	}
	/**
	 * Inserts objects into the database.
	 * @param sql SQL code to be executed.
	 * @param statementElements elements to be added to the database.
	 * @return
	 */
	public int update(String sql, Object[] statementElements)
	{
		int result = -1;
		
		try {
			openDatabase();
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			for(int i=0; i<statementElements.length; i++)
			{
				statement.setObject(i+1, statementElements[i]);
			}
			
			result = statement.executeUpdate();
			
			closeDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	/**
	 * Executes an SQL statement.
	 * @param sql the SQL statement to be executed.
	 */
	public int execute(String sql)
	{
		int result = -1;

		try
		{
			openDatabase();

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.executeUpdate();

			closeDatabase();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return result;
	}
}
