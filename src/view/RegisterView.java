package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import domain.model.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class RegisterView extends JFrame implements View, ActionListener{

	private JPanel contentPane;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField usernameField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JTextField cityField;
	private JTextField steetField;
	private JTextField numberField;
	private JButton btnRegister;

	private Controller controller;
	
	
	public RegisterView() 
	{
		initialize();
	}
	
	public void start(Controller controller)
	{
		this.controller = controller;
		btnRegister.addActionListener(this);
	}
	
	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("First Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(74, 48, 85, 14);
		contentPane.add(lblName);
		
		JLabel lblLastName = new JLabel("Last name: ");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLastName.setBounds(74, 73, 85, 14);
		contentPane.add(lblLastName);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(74, 98, 85, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(74, 123, 85, 14);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(74, 148, 85, 14);
		contentPane.add(lblEmail);
		
		firstNameField = new JTextField();
		firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		firstNameField.setBounds(169, 45, 122, 20);
		contentPane.add(firstNameField);
		firstNameField.setColumns(10);
		
		lastNameField = new JTextField();
		lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lastNameField.setBounds(169, 70, 122, 20);
		contentPane.add(lastNameField);
		lastNameField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usernameField.setBounds(169, 95, 122, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailField.setBounds(169, 145, 122, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(169, 120, 122, 20);
		contentPane.add(passwordField);
		
		btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegister.setBounds(120, 283, 122, 40);
		contentPane.add(btnRegister);
		
		JLabel lblViaShop = new JLabel("VIA Shop");
		lblViaShop.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblViaShop.setBounds(130, 11, 85, 26);
		contentPane.add(lblViaShop);
		
		JLabel lblNewLabel = new JLabel("City:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(74, 173, 85, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Street:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(74, 204, 85, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumber.setBounds(74, 229, 85, 14);
		contentPane.add(lblNumber);
		
		cityField = new JTextField();
		cityField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cityField.setBounds(169, 175, 122, 20);
		contentPane.add(cityField);
		cityField.setColumns(10);
		
		steetField = new JTextField();
		steetField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		steetField.setBounds(169, 203, 122, 20);
		contentPane.add(steetField);
		steetField.setColumns(10);
		
		numberField = new JTextField();
		numberField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numberField.setBounds(169, 228, 122, 20);
		contentPane.add(numberField);
		numberField.setColumns(10);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btnRegister)
		{
			
			if(firstNameField.getText().length() <= 1)
				JOptionPane.showMessageDialog(firstNameField, 
						"Enter valid first name", "Error", JOptionPane.ERROR_MESSAGE);
			else if(lastNameField.getText().length() <= 1)
				JOptionPane.showMessageDialog(lastNameField, 
						"Enter valid last name", "Error", JOptionPane.ERROR_MESSAGE);
			else if(usernameField.getText().length() <= 3)
				JOptionPane.showMessageDialog(usernameField, 
						"Enter valid username", "Error", JOptionPane.ERROR_MESSAGE);
			else if(passwordField.getPassword().length < 4)
				JOptionPane.showMessageDialog(passwordField, 
						"Password must be at least 4 symbols", "Error", JOptionPane.ERROR_MESSAGE);
			else if(emailField.getText().length() < 6)
				JOptionPane.showMessageDialog(lastNameField, 
						"Enter valid email", "Error", JOptionPane.ERROR_MESSAGE);
			else if(cityField.getText().length() < 3 ||
					steetField.getText().length() < 3 ||
					numberField.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(numberField, 
						"Enter address", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				try 
				{
					controller.register(new User(usernameField.getText(), getPassword(), emailField.getText(), "user",
							firstNameField.getText(), lastNameField.getText(), new Address(steetField.getText(), numberField.getText(), cityField.getText())));
				} 
				catch (IllegalArgumentException e3) 
				{
					JOptionPane.showMessageDialog(usernameField, 
							e3.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				JOptionPane.showConfirmDialog(btnRegister, "Registration successfull", "Done", JOptionPane.PLAIN_MESSAGE);
				setVisible(false);
				dispose();
				LoginView login = new LoginView();
				login.start(controller);
			}
		}
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
