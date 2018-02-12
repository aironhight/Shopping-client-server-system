package domain.mediator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Observable;

import domain.model.*;

/**
 * 
 * Class is responsible for getting all request from all clients and managing them and returning 
 * the correct answers
 */
public class ServerCommunication implements Runnable
{
	
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Model model;
   
	/**
	 * Two argument constructor to set the private fields
	 * @param ServerModel - takes an object from class implementing model, in this case the ServerModelManager
	 * @param socket - takes the socket from the Server class and sets it as a connection in which server and client communicate
	 */
   public ServerCommunication(Model ServerModel, Socket socket)
   {
      try
      {
    	 this.model = ServerModel; // Server model manager
         input = new ObjectInputStream(socket.getInputStream());
         output = new ObjectOutputStream(socket.getOutputStream());
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
   
   /**
    * The run method used in ServerMain later on, the method takes care of all requests and correct ensures
    * answers
    */
   public void run()
   {
		   while(true)
		   {
			   try
			   {
				   String what = (String)input.readObject();

				   if(what.equals("search")) {
					   returnItemsByCategory();
				   }
				   else if(what.equals("post")) {
					   postItem();
				   }
				   else if(what.equals("removeItem")) {
					   removeItem();
				   }
				   else if(what.equals("auth")) {
					   auth();
				   }
				   else if(what.equals("searchPrice")) {
					   searchPrice();
				   }
				   else if(what.equals("searchKW")) {
					   searchKW();
				   }
				   else if(what.equals("searchCatPrice")) {
					   searchPriceCat();;
				   }
				   else if(what.equals("order")) {
					   makeOrder();
				   }
				   else if(what.equals("removeUser")) {
					   removeUser();
				   }
				   else if(what.equals("register")) {
					   register();
				   }
				   else if(what.equals("changeUserType")) {
					   changeUserType();
				   }
				   else if(what.equals("addReview")) {
					   addReview();
				   }
				   else if(what.equals("changePass")) {
					   changePass();
				   }
				   else if(what.equals("getUserInfo"))
				   {
					   getUserInfo();
				   }
				   else if(what.equals("changeAddress"))
				   {
					   changeAddress();
				   }
				   else if(what.equals("changeMail"))
				   {
					   changeMail();
				   }
				   else if(what.equals("getItemByID"))
				   {
					   getItemByID();
				   }
				   else if(what.equals("orderByID"))
				   {
					   getOrderByID();
				   }
				   else if(what.equals("removeItemFromOrder"))
				   {
					   removeItemFromOrder();
				   }
				   else if(what.equals("getItemListID"))
				   {
					   getItemListID();
				   }
				   else if(what.equals("cancelOrder")) {
					   cancelOrder();
				   }
			   }
			   catch (SocketException e)
			   {
				   System.out.println("Client has disconnected");
				   break;
			   }
			   catch (IOException e)
			   {
				   e.printStackTrace();
			   }
			   catch (ClassNotFoundException e)
		       {
		            e.printStackTrace();
		       }
		       catch (Exception e)
		       {
		            e.printStackTrace();
		       }
		   }
   }
   
   
   private void getUserInfo() throws ClassNotFoundException, IOException
   {
	   String username = (String)input.readObject();
	   User user = model.getUsersInformation(username);
	   output.writeObject("UserInfo");
	   output.writeObject(user);
   }
   
   
   private void auth() throws ClassNotFoundException, IOException
   {

	   AuthCombination temp = (AuthCombination)input.readObject();
	   
	   String correct = model.authenticate(temp);
	   
	   output.writeObject((String)"auth");
	   
	   if(correct.equals("admin") || correct.equals("user")) 
	   {
		   System.out.println(temp.getId() + " has connected to the server");
		   output.writeObject(correct);
	   } else {
		   System.out.println("Wrong combination");
		   output.writeObject((String)"wrong");
	   }
   }
   
   private void postItem() throws IOException, ClassNotFoundException
   {
	   Item newItem = (Item)input.readObject();
	   
	   model.postItem(newItem);
   }
   
   private void returnItemsByCategory() throws IOException, ClassNotFoundException
   {
		   
	   String cat = (String)input.readObject();
	   output.writeObject("searchCat");
	   output.writeObject(model.searchCateg(cat));
   }
   
   private void removeItem() throws ClassNotFoundException, IOException
   {
	   int id = (int)input.readObject();
	   
	   model.removeItem(id);
   }
   
   private void removeUser() throws ClassNotFoundException, IOException
   {
	   String username = (String)input.readObject();
	   
	   output.writeObject("removeUser");
	   
	   boolean answer = model.removeUser(username);
	   
	   output.writeObject(answer);
   }
   
   private void register() throws ClassNotFoundException, IOException
   {
	   User user = (User)input.readObject();
	   
	   model.register(user);
   }
   
   private void changeUserType() throws ClassNotFoundException, IOException
   {
	   String user = (String)input.readObject();
	   
	   String type = (String)input.readObject();
	   
	   model.changeUserType(user, type);
   }
   
   private void makeOrder() throws ClassNotFoundException, IOException
   {
	   String user = (String)input.readObject();
	   
	   ItemList il = (ItemList)input.readObject();
	   
	   model.makeOrder(user, il);
   }
   
   private void searchPrice() throws ClassNotFoundException, IOException
   {
	   double min = (double)input.readObject();
	   double max = (double)input.readObject();
	   
	   output.writeObject("searchPrice");
	   output.writeObject(model.searchPrice(min, max));
   }
   
   private void searchPriceCat() throws ClassNotFoundException, IOException
   {
	   String category = (String)input.readObject();
	   double min = (double)input.readObject();
	   double max = (double)input.readObject();
	   
	   output.writeObject("searchCatPrice");
	   output.writeObject(model.searchPriceNCateg(category, min, max));
   }
   
   private void searchKW() throws ClassNotFoundException, IOException
   {
	   String keyword = (String)input.readObject();
	   output.writeObject("searchKW");
	   output.writeObject(model.searchKW(keyword));
   }
   
   private void addReview() throws ClassNotFoundException, IOException
   {
	   String username = (String)input.readObject();
	   int itemID = (int)input.readObject();
	   int rate = (int)input.readObject();
	   
	   model.addReview(username, itemID, rate);
   }
   
   private void changePass() throws ClassNotFoundException, IOException
   {
	   String username = (String)input.readObject();
	   String oldPass = (String)input.readObject();
	   String newPass = (String)input.readObject();
	   
	   model.changePassword(username, oldPass, newPass);
   }
   
   private void changeMail() throws ClassNotFoundException, IOException
   {
	   String username = (String) input.readObject();
	   String mail = (String)input.readObject();
	 
	   model.changeMail(username, mail);
   }
   
   private void changeAddress() throws ClassNotFoundException, IOException
   {
	   String username = (String) input.readObject();
	   Address address = (Address)input.readObject();
	   
	   model.changeAddress(username, address);
   }
   
   private void getItemByID() throws ClassNotFoundException, IOException
   {
	   int id = (int)input.readObject();
	   
	   output.writeObject("getItemByID");
	   output.writeObject(model.getItemByID(id));
   }
   
   private void getOrderByID() throws ClassNotFoundException, IOException
   {
	   int id = (int)input.readObject();
	   
	   output.writeObject("orderByID");
	   output.writeObject(model.getOrderByID(id));
   }
   
   private void removeItemFromOrder() throws ClassNotFoundException, IOException
   {
	   int id = (int) input.readObject();
	   int ilID = (int) input.readObject();
	   model.removeItemFromOrder(id, ilID);
   }
   
   private void getItemListID() throws ClassNotFoundException, IOException
   {
	   int id = (int)input.readObject();
	   output.writeObject("getItemListID");
	   output.writeObject(model.getItemListID(id));
   }
   
   private void cancelOrder() throws ClassNotFoundException, IOException
   {
	   int id = (int)input.readObject();
	   output.writeObject("cancelOrder");
	   output.writeObject(model.cancelOrder(id));
   }
}
