package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.JFrame;
import controller.Controller;
import domain.mediator.Model;
import domain.model.*;


public class ServerView extends JFrame implements View, ActionListener{

	public ServerView()
	{

	}
	@Override
	public void start(Controller controller) {
		// TODO Auto-generated method stub
		
	}
	
	public void displayItem()
	{
		System.out.println("HEHE");
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		displayItem();
		System.out.println((Item)arg1 + " has been added to the ItemList");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
