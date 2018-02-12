package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import controller.ServerController;
import domain.mediator.ServerModelManager;
import domain.model.Address;
import domain.model.AuthCombination;
import domain.model.Item;
import domain.model.ItemList;
import domain.model.User;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class UserView extends JFrame implements View, ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldSearchKey;
	private JTextArea textArea;
	private JButton btnCart;
	private JButton btnAddToCart;
	private JButton btnSearchKey;
	private JButton btnMakeOrder;

	private ArrayList<Item> cart;
	private Item selectedItem;

	private Controller controller;
	private User user;

	private JTextField textFieldMin;
	private JTextField textFieldMax;
	private JTextField textFieldSearchCat;
	private JButton btnSearchByCatPrice;
	private JButton btnSearchCat;
	private JButton btnSearchPrice;
	private JButton btnLogOut;
	private JButton btnMyAccount;
	private JButton btnAddReview;
	private JPanel panel_8;	
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UserView frame = new UserView(new User("Anon", "4chan",
							"neverforget@gmail.com", "user", "Anonymouse", "Unanonymouse",
							new Address("HolyRoad", "10", "Heaven")));
					Controller controller = new ServerController(
							new ServerModelManager(), new UserView(new User(
									"Anon", "4chan", "neverforget@gmail.com",
									"user", "Death", "Life", new Address(
											"HolyRoad", "10", "Heaven"))));
					frame.start(controller);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void start(Controller controller) {
		this.controller = controller;
		this.setVisible(true);
		cart = new ArrayList<>();
	}

	/**
	 * Create the frame.
	 */
	public UserView(User user) {
		this.user = user;
		
		setTitle("User: " + user.getUsername());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelLeft = new JPanel();
		contentPane.add(panelLeft);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

		JPanel panelList = new JPanel();
		panelLeft.add(panelList);
		panelList.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_3 = new JPanel();
		panelList.add(panel_3);

		btnLogOut = new JButton("LogOut");
		panel_3.add(btnLogOut);
		btnLogOut.addActionListener(this);

		btnMyAccount = new JButton("My Account");
		panel_3.add(btnMyAccount);
		btnMyAccount.addActionListener(this);

		btnAddToCart = new JButton("Add To Cart");
		panel_3.add(btnAddToCart);
		btnAddToCart.addActionListener(this);
		btnAddToCart.setEnabled(false);

		btnCart = new JButton("View Cart");
		panel_3.add(btnCart);
		btnCart.addActionListener(this);
		btnCart.setEnabled(false);

		btnMakeOrder = new JButton("Make Order");
		panel_3.add(btnMakeOrder);
		btnMakeOrder.addActionListener(this);
		btnMakeOrder.setEnabled(false);

		JPanel panel_2 = new JPanel();
		panelList.add(panel_2);
		panel_2.setLayout(new GridLayout(5, 1, 0, 0));

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);

		textFieldSearchKey = new JTextField();
		panel_4.add(textFieldSearchKey);
		textFieldSearchKey.setColumns(10);

		btnSearchKey = new JButton("Search by Keyword");
		panel_4.add(btnSearchKey);
		btnSearchKey.addActionListener(this);

		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);

		JLabel lblMin = new JLabel("Min");
		panel_5.add(lblMin);

		textFieldMin = new JTextField();
		panel_5.add(textFieldMin);
		textFieldMin.setColumns(6);

		JLabel lblMax = new JLabel("Max");
		panel_5.add(lblMax);

		textFieldMax = new JTextField();
		panel_5.add(textFieldMax);
		textFieldMax.setColumns(6);

		btnSearchPrice = new JButton("Search by Price");
		panel_5.add(btnSearchPrice);
		btnSearchPrice.addActionListener(this);

		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);

		textFieldSearchCat = new JTextField();
		panel_6.add(textFieldSearchCat);
		textFieldSearchCat.setColumns(10);

		btnSearchCat = new JButton("Search by Category");
		panel_6.add(btnSearchCat);
		btnSearchCat.addActionListener(this);

		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);

		btnSearchByCatPrice = new JButton("Search by Category and Price");
		panel_7.add(btnSearchByCatPrice);
		btnSearchByCatPrice.addActionListener(this);
		
		panel_8 = new JPanel();
		panel_2.add(panel_8);
				
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "4", "3", "2", "1", "0"}));
		panel_8.add(comboBox);
		
		btnAddReview = new JButton("Add Review");
		panel_8.add(btnAddReview);
		btnAddReview.setEnabled(false);
		btnAddReview.addActionListener(this);

		JPanel panelDescription = new JPanel();
		panelDescription.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panelDescription.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panelLeft.add(panelDescription);
		panelDescription.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelDescription.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		JPanel panelRight = new JPanel();
		panelRight.setBorder(new EmptyBorder(0, 3, 0, 0));
		panelRight.setAlignmentX(Component.RIGHT_ALIGNMENT);
		contentPane.add(panelRight);
		panelRight.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneTable = new JScrollPane();
		panelRight.add(scrollPaneTable);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowVerticalLines(false);
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, }, new String[] { "ID",
						"Items" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { Long.class, Object.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(0).setMinWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent event) {
						if (table.getSelectedRow() == -1) {
							btnAddToCart.setEnabled(false);
							textArea.setText("");
						} else {
							if (table.getValueAt(table.getSelectedRow(), 0) != null) {
								btnAddToCart.setEnabled(true);
								selectedItem = (Item) table.getValueAt(
										table.getSelectedRow(), 1);
								textArea.setText(selectedItem.getDescription());
								btnAddReview.setEnabled(true);
							} else {
								btnAddToCart.setEnabled(false);
								btnAddReview.setEnabled(false);
								textArea.setText("");
							}
						}
					}
				});

		scrollPaneTable.setViewportView(table);
		
/*		ArrayList<Item> items = new ArrayList<>();
		items.add(new Item("Something", "uknown", "Something is very interesting", 35));
		items.add(new Item("Something more", "uknown", "Something more is very much interesting and I want it", 56.0));
		items.add(new Item("Something else", "uknown", "Something else is very interesting", 89.5));
		items.add(new Item("Something more", "uknown", "Something more is very much interesting and I want it", 56.0));
		items.add(new Item("Something else", "uknown", "Something else is very interesting", 89.5));
		items.add(new Item("Something", "uknown", "Something is very interesting", 35));
		items.add(new Item("Something more", "uknown", "Something more is very much interesting and I want it", 56.0));
		items.add(new Item("Something else", "uknown", "Something else is very interesting", 89.5));
		
		addItems(items);*/
	}

	// -----------------------------------Handlers-------------------------------------//

	private void addItems(ArrayList<Item> items) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.clearSelection();
		model.setRowCount(1);

		for (int i = 0; i < items.size(); i++) {
			model.addRow(new Object[] { items.get(i).getId(), items.get(i) });

		}
		System.out.println("its empty");
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearchKey) {
			addItems(controller.searchKW(textFieldSearchKey.getText()));
		}
		if (e.getSource() == btnSearchCat) {
			addItems(controller.searchCateg(textFieldSearchCat.getText()));
			System.out.println("Something");
		}
		if (e.getSource() == btnSearchPrice) {
			addItems(controller.searchPrice(
					Double.parseDouble(textFieldMin.getText()),
					Double.parseDouble(textFieldMax.getText())));
		}
		if (e.getSource() == btnSearchByCatPrice) {
			addItems(controller.searchPriceNCateg(textFieldSearchCat.getText(),
					Double.parseDouble(textFieldMin.getText()),
					Double.parseDouble(textFieldMax.getText())));
		}
		if (e.getSource() == btnAddToCart) {
			cart.add(selectedItem);
			btnCart.setEnabled(true);
			btnMakeOrder.setEnabled(true);
		}
		if (e.getSource() == btnCart) {
			addItems(cart);
		}
		if (e.getSource() == btnLogOut) {
			setVisible(false);
			dispose();
			LoginView log = new LoginView();
			log.start(controller);
			log.setVisible(true);
		}
		if (e.getSource() == btnMyAccount) {
			UserWindow frame = new UserWindow(user);
			frame.start(controller);
			while(frame.isActive()){}
			this.user = controller.getUsersInformation(user.getUsername());
		}
		if (e.getSource() == btnMakeOrder){
			ItemList li = new ItemList();
			cart.forEach((i) -> li.addItem(i));
			controller.makeOrder(user.getUsername(), li);
		}
		if(e.getSource() == btnAddReview){
			int score = Integer.parseInt((String) comboBox.getSelectedItem());
			System.out.println(score);
			System.out.println("something pls");
			controller.addReview(user.getUsername(), selectedItem.getId(), score);
		}
	}
}

@SuppressWarnings("serial")
class OrderHistory extends JFrame implements View {
	private JPanel contentPane;
	private JTable tableOrders;
	private JTable tableItems;

	private ArrayList<Long> orders;

	private Controller controller;

	@Override
	public void start(Controller controller) {
		this.controller = controller;
		// orders = controller.getUserOrders(user.getUsername());
		this.setVisible(true);
		loadUp();
	}

	/**
	 * Create the frame.
	 */
	public OrderHistory() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelLeft = new JPanel();
		contentPane.add(panelLeft);
		panelLeft.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPaneOrders = new JScrollPane();
		panelLeft.add(scrollPaneOrders);

		tableOrders = new JTable();
		scrollPaneOrders.setViewportView(tableOrders);
		tableOrders.setModel(new DefaultTableModel(
				new Object[][] { { null }, }, new String[] { "Order" }));

		tableOrders.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent event) {
						if (tableOrders.getValueAt(
								tableOrders.getSelectedRow(), 0) != null) {
						} else {
							DefaultTableModel model = (DefaultTableModel) tableItems
									.getModel();
							model.setRowCount(0);
						}
					}
				});

		JPanel panelRight = new JPanel();
		contentPane.add(panelRight);
		panelRight.setLayout(new GridLayout(1, 0, 0, 0));

		JScrollPane scrollPaneIItems = new JScrollPane();
		panelRight.add(scrollPaneIItems);

		tableItems = new JTable();
		scrollPaneIItems.setViewportView(tableItems);
		tableItems.setModel(new DefaultTableModel(new Object[][] { { null }, },
				new String[] { "Items" }));
	}

	private void loadUp() {
		DefaultTableModel model = (DefaultTableModel) tableOrders.getModel();
		orders.forEach((o) -> model.addRow(new Object[] { o }));
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}

@SuppressWarnings("serial")
class UserWindow extends JFrame implements View {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldEmail;
	private JTextField textFieldFName;
	private JTextField textFieldLName;
	private JTextField textFieldStreet;
	private JTextField textFieldPassword;
	private JTextField textFieldOldPassword;
	private JTextField textFieldNumber;
	private JTextField textFieldCity;

	private Controller controller;
	private User user;

	@Override
	public void start(Controller controller) {
		this.controller = controller;
		this.setVisible(true);
		loadUp();
	}

	/**
	 * Create the frame.
	 */
	public UserWindow(User user) {
		this.user = user;

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(7, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);

		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setBackground(Color.LIGHT_GRAY);
		txtpnUsername.setText("Username");
		panel_1.add(txtpnUsername);

		textFieldUsername = new JTextField();
		panel_1.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.setEditable(false);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);

		JTextPane txtpnEmail = new JTextPane();
		txtpnEmail.setBackground(Color.LIGHT_GRAY);
		txtpnEmail.setText("Email");
		panel_2.add(txtpnEmail);

		textFieldEmail = new JTextField();
		panel_2.add(textFieldEmail);
		textFieldEmail.setColumns(15);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);

		JTextPane txtpnFirstName = new JTextPane();
		txtpnFirstName.setBackground(Color.LIGHT_GRAY);
		txtpnFirstName.setText("First Name");
		panel_3.add(txtpnFirstName);
		

		textFieldFName = new JTextField();
		panel_3.add(textFieldFName);
		textFieldFName.setColumns(10);
		textFieldFName.setEditable(false);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);

		JTextPane txtpnLastName = new JTextPane();
		txtpnLastName.setBackground(Color.LIGHT_GRAY);
		txtpnLastName.setText("Last Name");
		panel_4.add(txtpnLastName);
		
		textFieldLName = new JTextField();
		panel_4.add(textFieldLName);
		textFieldLName.setColumns(10);
		textFieldLName.setEditable(false);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);

		JTextPane txtpnAddress = new JTextPane();
		txtpnAddress.setBackground(Color.LIGHT_GRAY);
		txtpnAddress.setText("Address");
		panel_5.add(txtpnAddress);

		textFieldStreet = new JTextField();
		panel_5.add(textFieldStreet);
		textFieldStreet.setColumns(10);

		textFieldNumber = new JTextField();
		panel_5.add(textFieldNumber);
		textFieldNumber.setColumns(5);

		textFieldCity = new JTextField();
		panel_5.add(textFieldCity);
		textFieldCity.setColumns(10);

		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);

		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setBackground(Color.LIGHT_GRAY);
		txtpnPassword.setText("New Password");
		panel_6.add(txtpnPassword);

		textFieldPassword = new JPasswordField();
		panel_6.add(textFieldPassword);
		textFieldPassword.setColumns(10);

		JPanel panel = new JPanel();
		contentPane.add(panel);

		JTextPane txtpnOldPassword = new JTextPane();
		txtpnOldPassword.setBackground(Color.LIGHT_GRAY);
		txtpnOldPassword.setText("Old Password");
		panel.add(txtpnOldPassword);

		textFieldOldPassword = new JPasswordField();
		panel.add(textFieldOldPassword);
		textFieldOldPassword.setColumns(10);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmChanges();
			}
		});
		panel.add(btnUpdate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancel);
	}

	private void loadUp() {
		setUserData();
	}

	// -----------------------------------Handlers-------------------------------------//

	private void setUserData() {
		User user = this.user;

		String[] address = user.getAddress().split(", ");

		textFieldUsername.setText(user.getUsername());
		textFieldEmail.setText(user.getEmail());
		textFieldFName.setText(user.getFirstName());
		textFieldLName.setText(user.getLastName());
		textFieldStreet.setText(address[0]);
		textFieldNumber.setText(address[1]);
		textFieldCity.setText(address[2]);
	}

	private void changeUserData() {
		String address = textFieldStreet.getText()+ ", "
					+ textFieldNumber.getText() + ", "
					+ textFieldCity.getText();
		if (!textFieldPassword.getText().isEmpty()) {
			controller.changePassword(user.getUsername(), textFieldOldPassword.getText(), textFieldPassword.getText());
		} 
		if(!textFieldEmail.getText().equals(user.getEmail())){
			controller.changeMail(user.getUsername(), textFieldEmail.getText());
		}
		if(address.equals(user.getAddress())){
			controller.changeAddress(user.getUsername(), new Address(textFieldStreet.getText(), textFieldNumber.getText(), textFieldCity.getText()));
		}
	}

	private void confirmChanges() {
		String auth = controller.authenticate(new AuthCombination(user.getUsername(),
				textFieldOldPassword.getText()));

		if (auth.equals("user") || auth.equals("admin")) {
			changeUserData();
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Passwords do not match",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}