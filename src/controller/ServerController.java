package controller;

import view.View;
import domain.mediator.Model;
import domain.model.Address;
import domain.model.AuthCombination;
import domain.model.Item;
import domain.model.ItemList;
import domain.model.User;

import java.util.ArrayList;
import java.util.Observable;

public class ServerController implements Controller {

	private Model model;
	private View view;
	
	public ServerController(Model model, View view)
	{
		this.model = model;
		this.view = view;
	    ((Observable) this.model).addObserver(view);
	}

	@Override
	public String authenticate(AuthCombination comb) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void postItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeItem(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeUserType(String username, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Item> searchPriceNCateg(String category, double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Item> searchPrice(double min, double max) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Item> searchKW(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Item> searchCateg(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeOrder(String username, ItemList il) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReview(String username, int itemId, int rate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String username, String oldPass, String newPass) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getUsersType(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUsersInformation(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeAddress(String username, Address address)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeMail(String username, String mail)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item getItemByID(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Item> getItemsFromOrder(int orderID)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeItemFromOrder(int itemID, int itemListID)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getItemListID(int orderID)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean cancelOrder(int orderID)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
