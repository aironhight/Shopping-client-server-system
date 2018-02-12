package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.naming.ldap.Control;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.View;
import controller.*;

public class ClientView extends JFrame implements View, ActionListener
{
	
	private ClientController controller;
	
	
	
	public ClientView() 
	{
		super("VIA Online Shop");
		
		initializeLogin();
		addComponentsToLoginFrame();
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		 if (arg1 == null || arg1.toString().length() < 1)
	     {
			 return;
	     }
	}

	@Override
	public void start(Controller controller) 
	{
		this.controller = (ClientController)controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
	}
	
	
	private void initializeLogin()
	{

	}
	
	private void addComponentsToLoginFrame()
	   {

	   }
	

}
