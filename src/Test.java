import java.text.SimpleDateFormat;

import domain.mediator.ServerModelManager;
import domain.model.*;
import view.UserView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ClientController;

public class Test
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{	
		DataBase db = DataBase.getInstance();
		ServerModelManager sm = new ServerModelManager();
		System.out.println(sm.removeUser("This"));
	}
}
