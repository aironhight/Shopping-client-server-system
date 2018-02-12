package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;

public class SampleView extends JFrame {

	private Controller controller;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JScrollBar scrollBar;
	private JButton btnSearch;
	private JCheckBox chckbxHouseware;
	private JCheckBox chckbxPcAccessories;
	private JCheckBox chckbxCarsAndCar;
	private JCheckBox chckbxGroceries;
	private JLabel lblFilters;
	private JLabel lblOrderBy;
	private JRadioButton rdbtnPrice;
	private JRadioButton rdbtnName;

	
	public SampleView() 
	{
		
		initialize();
		
		addComponentsToFrame();

	}
	
	
	public void start(Controller controller)
	{
		this.controller = controller;
	}
	
	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollBar = new JScrollBar();
		scrollBar.setBounds(757, 11, 17, 404);
		
		textField = new JTextField();
		textField.setBounds(10, 31, 179, 20);
		textField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(199, 30, 89, 23);
		
		chckbxHouseware = new JCheckBox("Houseware");
		chckbxHouseware.setBounds(10, 89, 97, 23);
		
		chckbxPcAccessories = new JCheckBox("PC accessories");
		chckbxPcAccessories.setBounds(10, 115, 112, 23);
		
		chckbxCarsAndCar = new JCheckBox("Cars and car parts");
		chckbxCarsAndCar.setBounds(10, 141, 141, 23);
		
		chckbxGroceries = new JCheckBox("Groceries");
		chckbxGroceries.setBounds(10, 167, 97, 23);
		
		lblFilters = new JLabel("Filters:");
		lblFilters.setBounds(10, 68, 46, 14);
		
		lblOrderBy = new JLabel("Order by");
		lblOrderBy.setBounds(174, 68, 46, 14);
		
		rdbtnPrice = new JRadioButton("price");
		rdbtnPrice.setBounds(179, 89, 109, 23);
		
		rdbtnName = new JRadioButton("name");
		rdbtnName.setBounds(179, 115, 109, 23);
		
		table = new JTable();
		table.setBounds(311, 11, 463, 404);
	}
	
	private void addComponentsToFrame()
	{
		contentPane.add(scrollBar);
		
		contentPane.add(textField);

		contentPane.add(btnSearch);	
		
		contentPane.add(chckbxHouseware);
		
		contentPane.add(chckbxPcAccessories);
		
		contentPane.add(chckbxCarsAndCar);	
		
		contentPane.add(chckbxGroceries);
		
		contentPane.add(lblFilters);
		
		contentPane.add(lblOrderBy);
		
		contentPane.add(rdbtnPrice);
		
		contentPane.add(rdbtnName);
		
		contentPane.add(table);
		
		setVisible(true);
	}
	
}
