package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import domain.model.Address;
import domain.model.AuthCombination;
import domain.model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class LoginView extends JFrame implements ActionListener, View {
	
	private Controller controller;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JLabel lblViaShop;
	private JLabel lblNotRegisteredYet;
	private JButton btnHere;


	public LoginView() 
	{
		
		initialize();
		
		addComponentsToFrame();
	}
	
	
	public void start(Controller controller)
	{
		this.controller = controller;
		btnLogin.addActionListener(this);
		btnHere.addActionListener(this);
	}
	
	private void initialize()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(115, 46, 89, 21);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(204, 46, 126, 21);
		textField.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(115, 81, 89, 14);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(166, 110, 100, 25);
		
		lblViaShop = new JLabel("VIA Shop");
		lblViaShop.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblViaShop.setBounds(177, 11, 89, 24);
		
		lblNotRegisteredYet = new JLabel("Not registered yet? Just click ");
		lblNotRegisteredYet.setBounds(92, 174, 170, 14);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(204, 78, 126, 20);
		
		btnHere = new JButton("here");
		btnHere.setBounds(260, 171, 70, 21);
	}
	
	private void addComponentsToFrame()
	   {
		 contentPane.add(lblUsername);
			
		 contentPane.add(textField);
			
		 contentPane.add(lblPassword);
			
		 contentPane.add(btnLogin);
			
		 contentPane.add(lblViaShop);
			
		 contentPane.add(lblNotRegisteredYet);
			
		 contentPane.add(passwordField);
			
		 contentPane.add(btnHere);
		 setVisible(true);
	   }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnLogin)
		{
			if((textField.getText().length()> 1 ) && (getPassword().length() >= 4))
			{
				String answerFromServer = (String)controller.authenticate(new AuthCombination(textField.getText(), getPassword()));

				if(answerFromServer.equals("false"))
				{
					JOptionPane.showMessageDialog(btnLogin, 
							"Wrong combination username/password", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(answerFromServer.equals("user"))
				{
					
					User user = controller.getUsersInformation(textField.getText());
					setVisible(false);
					dispose();
					UserView view = new UserView(user);
					view.setVisible(true);
					view.start(controller);
					
				}
				else if(answerFromServer.equals("admin"))
				{
					setVisible(false);
					dispose();
					adminView view = new adminView();
					view.setVisible(true);
					view.start(controller);	
				}
				else
				{
					System.out.println("answer from server is wrong: " + answerFromServer);
				}
			}
		}
		
		else if(e.getSource() == btnHere)
		{
			setVisible(false);
			dispose();
			RegisterView view = new RegisterView();
			view.setVisible(true);
			view.start(controller);
		}
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private String getPassword()
	{
		char[] pass = passwordField.getPassword();
		
		String password = "";
		
		for (int i = 0; i < pass.length; i++) 
		{
			password += pass[i];
		}
		return password;
	}
	
}
