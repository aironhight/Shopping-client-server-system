package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import org.omg.PortableServer.ServantRetentionPolicyValue;

import controller.Controller;
import domain.model.*;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class adminView extends JFrame implements ActionListener, View
{
	private Controller controller;
	
	private JTextField txtOrderIdOrd;
	private JTextField txtUsernameAcc;
	private JTextField txtPasswordAcc;
	private JTextField txtEmailAcc;
	private JTextField txtAddressAcc;
	private JTextField txtTypeAcc;
	private JTextField txtNewPassAcc;
	private JTextField txtNameIt;
	private JTextField txtCategoryIt;
	private JTextField txtPriceIt;
	private JTextField txtIDIt;
	private JLabel lblNameIt;
	private JLabel lblCategoryIt;
	private JLabel lblPriceIt;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JLabel lblAddress;
	private JLabel lblType;
	private JLabel lblId;
	private JLabel lblDescription;
	private JLabel lblKeywords;
	private JButton btnRefreshAcc;
	private JButton btnUpdateAcc;
	private JButton btnDeleteAccount;
	private JButton btnRemoveOrd;
	private JButton btnCreateUpdIt;
	private JButton btnRefreshIt;
	private JButton btnDeleteItem;
	private JButton buttonRefreshOrd;
	private JComboBox comboBoxResultOrd;
	private JLabel lblNewPass;
	private JTextArea txtrDescrIt;
	private JTextArea txtrKeywordsIt;
	private boolean itemsVisibility;
	private JButton btnCancelOrd;
	private JButton btnLogOut;
	
	
	public adminView()
	{
		itemsVisibility = false;
		initialize();
//		setVisible(true);
	}
	
	public void start(Controller controller)
	{
		this.controller = controller;
		btnDeleteAccount.addActionListener(this);
		btnRefreshAcc.addActionListener(this);
		btnCreateUpdIt.addActionListener(this);
		buttonRefreshOrd.addActionListener(this);
		btnRefreshIt.addActionListener(this);
		btnDeleteItem.addActionListener(this);
		btnRemoveOrd.addActionListener(this);
		btnUpdateAcc.addActionListener(this);
		btnCancelOrd.addActionListener(this);
		btnLogOut.addActionListener(this);
	}

	private void initialize()
	{
		setBounds(100, 100, 644, 441);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 628, 402);
		getContentPane().add(tabbedPane);
		
		JPanel Accounts = new JPanel();
		tabbedPane.addTab("Accounts", null, Accounts, null);
		Accounts.setLayout(null);
		
		txtUsernameAcc = new JTextField();
		txtUsernameAcc.setBounds(10, 25, 202, 20);
		Accounts.add(txtUsernameAcc);
		txtUsernameAcc.setColumns(10);
		
		txtPasswordAcc = new JTextField();
		txtPasswordAcc.setBounds(10, 73, 202, 20);
		Accounts.add(txtPasswordAcc);
		txtPasswordAcc.setColumns(10);
		
		txtEmailAcc = new JTextField();
		txtEmailAcc.setBounds(10, 168, 202, 20);
		Accounts.add(txtEmailAcc);
		txtEmailAcc.setColumns(10);
		
		txtAddressAcc = new JTextField();
		txtAddressAcc.setBounds(10, 207, 202, 20);
		Accounts.add(txtAddressAcc);
		txtAddressAcc.setColumns(10);
		
		txtTypeAcc = new JTextField();
		txtTypeAcc.setBounds(10, 243, 86, 20);
		Accounts.add(txtTypeAcc);
		txtTypeAcc.setColumns(10);
		
		lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 11, 86, 14);
		Accounts.add(lblUsername);
		
		lblPassword = new JLabel("Old Password");
		lblPassword.setBounds(10, 56, 86, 14);
		Accounts.add(lblPassword);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 150, 86, 14);
		Accounts.add(lblEmail);
		
		lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 190, 86, 14);
		Accounts.add(lblAddress);
		
		lblType = new JLabel("Type");
		lblType.setBounds(10, 229, 46, 14);
		Accounts.add(lblType);
		
		btnRefreshAcc = new JButton("Refresh");
		btnRefreshAcc.setBounds(222, 24, 110, 23);
		Accounts.add(btnRefreshAcc);
		
		btnUpdateAcc = new JButton("Update");
		btnUpdateAcc.setBounds(10, 274, 110, 23);
		Accounts.add(btnUpdateAcc);
		
		txtNewPassAcc = new JTextField();
		txtNewPassAcc.setColumns(10);
		txtNewPassAcc.setBounds(10, 119, 202, 20);
		Accounts.add(txtNewPassAcc);
		
		
		
		lblNewPass = new JLabel("New Password");
		lblNewPass.setBounds(10, 104, 129, 14);
		Accounts.add(lblNewPass);
		
		btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.setBounds(484, 317, 129, 46);
		Accounts.add(btnDeleteAccount);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(514, 20, 97, 25);
		Accounts.add(btnLogOut);
		
		JPanel Orders = new JPanel();
		tabbedPane.addTab("Orders", null, Orders, null);
		Orders.setLayout(null);
		
		buttonRefreshOrd = new JButton("Refresh");
		buttonRefreshOrd.setBounds(10, 46, 116, 25);
		Orders.add(buttonRefreshOrd);
		
		comboBoxResultOrd = new JComboBox();
		comboBoxResultOrd.setBounds(142, 13, 470, 22);
		Orders.add(comboBoxResultOrd);
		
		txtOrderIdOrd = new JTextField();
		txtOrderIdOrd.setText("order ID");
		txtOrderIdOrd.setBounds(12, 13, 116, 22);
		Orders.add(txtOrderIdOrd);
		txtOrderIdOrd.setColumns(10);
		
		btnRemoveOrd = new JButton("Remove Item");
		btnRemoveOrd.setBounds(10, 82, 116, 48);
		Orders.add(btnRemoveOrd);
		
		btnCancelOrd = new JButton("Cancel order");
		btnCancelOrd.setBounds(10, 143, 116, 25);
		Orders.add(btnCancelOrd);
		
		JPanel Items = new JPanel();
		tabbedPane.addTab("Items", null, Items, null);
		Items.setLayout(null);
		
		txtNameIt = new JTextField();
		txtNameIt.setBounds(285, 39, 86, 20);
		Items.add(txtNameIt);
		txtNameIt.setColumns(10);
		txtNameIt.setVisible(false);
		
		txtCategoryIt = new JTextField();
		txtCategoryIt.setBounds(381, 39, 86, 20);
		Items.add(txtCategoryIt);
		txtCategoryIt.setColumns(10);
		txtCategoryIt.setVisible(false);
		
		txtPriceIt = new JTextField();
		txtPriceIt.setBounds(477, 39, 71, 20);
		Items.add(txtPriceIt);
		txtPriceIt.setColumns(10);
		txtPriceIt.setVisible(false);
		
		lblNameIt = new JLabel("Name");
		lblNameIt.setBounds(285, 24, 58, 14);
		Items.add(lblNameIt);
		lblNameIt.setVisible(false);
		
		lblCategoryIt = new JLabel("Category");
		lblCategoryIt.setBounds(381, 24, 79, 14);
		Items.add(lblCategoryIt);
		lblCategoryIt.setVisible(false);
		
		lblPriceIt = new JLabel("Price");
		lblPriceIt.setBounds(477, 24, 71, 14);
		Items.add(lblPriceIt);
		lblPriceIt.setVisible(false);
		
		btnCreateUpdIt = new JButton("Create Item");
		btnCreateUpdIt.setBounds(10, 4, 127, 55);
		Items.add(btnCreateUpdIt);
		
		txtIDIt = new JTextField();
		txtIDIt.setBounds(10, 343, 86, 20);
		Items.add(txtIDIt);
		txtIDIt.setColumns(10);
		
		btnRefreshIt = new JButton("Refresh");
		btnRefreshIt.setBounds(106, 342, 89, 23);
		Items.add(btnRefreshIt);
		
		btnDeleteItem = new JButton("Delete item");
		btnDeleteItem.setBounds(147, 4, 128, 55);
		Items.add(btnDeleteItem);
		
		lblId = new JLabel("ID");
		lblId.setBounds(10, 325, 46, 14);
		Items.add(lblId);
		
		txtrDescrIt = new JTextArea();
		txtrDescrIt.setText("");
		txtrDescrIt.setBounds(10, 84, 263, 55);	
		txtrDescrIt.setLineWrap(true);
		txtrDescrIt.setWrapStyleWord(true);
		Items.add(txtrDescrIt);
		txtrDescrIt.setVisible(false);
		
		
		txtrKeywordsIt = new JTextArea();
		txtrKeywordsIt.setText("");
		txtrKeywordsIt.setBounds(283, 84, 263, 55);
		txtrKeywordsIt.setLineWrap(true);
		txtrKeywordsIt.setWrapStyleWord(true);
		Items.add(txtrKeywordsIt);
		txtrKeywordsIt.setVisible(false);
		
		lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 70, 86, 14);
		Items.add(lblDescription);
		lblDescription.setVisible(false);
		
		lblKeywords = new JLabel("Keywords(separate by comma)");
		lblKeywords.setBounds(283, 70, 184, 14);
		Items.add(lblKeywords);
		lblKeywords.setVisible(false);
	}
	
	private void changeVisibilityItems()
	{

		txtNameIt.setVisible(!itemsVisibility);
		lblNameIt.setVisible(!itemsVisibility);
		txtCategoryIt.setVisible(!itemsVisibility);
		lblCategoryIt.setVisible(!itemsVisibility);
		txtPriceIt.setVisible(!itemsVisibility);
		lblPriceIt.setVisible(!itemsVisibility);
		txtrDescrIt.setVisible(!itemsVisibility);
		lblDescription.setVisible(!itemsVisibility);
		txtrKeywordsIt.setVisible(!itemsVisibility);
		lblKeywords.setVisible(!itemsVisibility);
		itemsVisibility = !itemsVisibility;
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
    {
		if(e.getSource() == btnLogOut) {
			setVisible(false);
			dispose();
			LoginView log = new LoginView();
			log.start(controller);
			log.setVisible(true);
		}
		
		if(e.getSource() == btnCancelOrd)
		{
			boolean succ = controller.cancelOrder(Integer.parseInt(txtOrderIdOrd.getText()));
			if(succ) {
				JOptionPane.showConfirmDialog(btnDeleteAccount, "Order cancelled", "Done", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showConfirmDialog(btnDeleteAccount, "Order not found", "Error", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		
		if(e.getSource() == btnUpdateAcc)
		{
			if(txtPasswordAcc.getText().length() > 5) {
				controller.changePassword(txtUsernameAcc.getText(), txtPasswordAcc.getText(), txtNewPassAcc.getText());
			}
			if(txtEmailAcc.getText().length() > 5) {
				controller.changeMail(txtUsernameAcc.getText(), txtEmailAcc.getText());
			}
			if(txtTypeAcc.getText().equals("admin") || txtTypeAcc.getText().equals("user")) {
				controller.changeUserType(txtUsernameAcc.getText(), txtTypeAcc.getText());
			}
		}
		
		if(e.getSource() == buttonRefreshOrd)
		{
			comboBoxResultOrd.removeAllItems();
			ArrayList<Item> res = controller.getItemsFromOrder(Integer.parseInt(txtOrderIdOrd.getText()));
			if(res != null || res.size() > 0) {
				for(int i=0; i<res.size(); i++) {
					comboBoxResultOrd.addItem(res.get(i));

				}
			}
		}
		
		if(e.getSource() == btnRemoveOrd)
		{
			Item it = (Item)comboBoxResultOrd.getSelectedItem();
			controller.removeItemFromOrder(it.getId(), controller.getItemListID(Integer.parseInt(txtOrderIdOrd.getText())));
			comboBoxResultOrd.removeAllItems();
			ArrayList<Item> res = controller.getItemsFromOrder(Integer.parseInt(txtOrderIdOrd.getText()));
			if(res != null || res.size() > 0) {
				for(int i=0; i<res.size(); i++) {
					comboBoxResultOrd.addItem(res.get(i));

				}
			}
		}
		
		if(e.getSource() == btnCreateUpdIt) 
    	{
    		
			txtNameIt.setEditable(true);txtrDescrIt.setEditable(true);
			txtCategoryIt.setEditable(true); txtPriceIt.setEditable(true);
			txtrKeywordsIt.setEditable(true);
    		changeVisibilityItems();
    		
    		if(e.getSource() == btnCreateUpdIt && btnCreateUpdIt.getText().equals("Save item")) 
    		{
    			Item it =  new Item(txtNameIt.getText(), txtCategoryIt.getText(),
    					txtrDescrIt.getText(), Double.parseDouble(txtPriceIt.getText()));
    			if(txtrKeywordsIt.getText().length() > 0) {
    				String[] st = txtrKeywordsIt.getText().split(",");
    				for(int i=0; i<st.length; i++) {
    					it.addKeyWord(st[i]);
    				}
    			}
    			
    			controller.postItem(it);
    			
    			
    			changeVisibilityItems();
    			
    			JOptionPane.showMessageDialog(btnCreateUpdIt, 
						"Item successfully uploaded", "Success!", JOptionPane.INFORMATION_MESSAGE);
    			
    			btnCreateUpdIt.setText("Create item");
    		}
    		btnCreateUpdIt.setText("Save item");
    	}
		
		if(e.getSource() == btnRefreshIt)
		{
			Item it = controller.getItemByID(Integer.parseInt(txtIDIt.getText()));
			if(!itemsVisibility) {
				changeVisibilityItems();
			}
			txtNameIt.setText(it.getName());
			txtrDescrIt.setText(it.getDescription());
			txtCategoryIt.setText(it.getCategory());
			txtPriceIt.setText("" + it.getPrice());
			txtrKeywordsIt.setText(it.getKeyWords());
			
			txtNameIt.setEditable(false);txtrDescrIt.setEditable(false);
			txtCategoryIt.setEditable(false); txtPriceIt.setEditable(false);
			txtrKeywordsIt.setEditable(false);
		}
		
		else if(e.getSource() == btnDeleteItem)
		{
			String a = JOptionPane.showInputDialog("enter ID");
			if(a!=null) {
				controller.removeItem(Integer.parseInt(a));
			}	
		}
		
    	else if(e.getSource() == btnDeleteAccount)
    	{
    		System.out.println("DELETE BUTTON CLICKED");
    		
    		
    			String username = JOptionPane.showInputDialog("Enter username:");
    			boolean removed = controller.removeUser(username);
    			if(removed) {
    				JOptionPane.showConfirmDialog(btnDeleteAccount, "Account deleted", "Done", JOptionPane.PLAIN_MESSAGE);
    			} else {
    				JOptionPane.showConfirmDialog(btnDeleteAccount, "Account not found", "Error", JOptionPane.PLAIN_MESSAGE);
    			}
    	}
		
    	else if(e.getSource() == btnRefreshAcc)
    	{
    		txtPasswordAcc.setText("");
			txtEmailAcc.setText("");
			txtAddressAcc.setText("");
			txtTypeAcc.setText("");
    		User user = controller.getUsersInformation(txtUsernameAcc.getText());
    		if(user==null)
    		{
    			JOptionPane.showMessageDialog(btnRefreshAcc, 
						"Can't find user with username " + txtUsernameAcc.getText(), "Error", JOptionPane.ERROR_MESSAGE);
    		}
    		else
    		{
    			txtEmailAcc.setText(user.getEmail());
    			txtAddressAcc.setText(user.getAddress());
    			txtTypeAcc.setText(user.getType());
    		}
    	}
		
      }
}

