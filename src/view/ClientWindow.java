package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClientWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow frame = new ClientWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 366, 425);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 243, 2, -197);
		panel.add(scrollPane);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setBounds(10, 11, 46, 14);
		panel.add(lblResults);
		
		JLabel lblItem = new JLabel("Item 1");
		lblItem.setBounds(10, 51, 46, 14);
		panel.add(lblItem);
		
		JLabel lblDescription = new JLabel("description");
		lblDescription.setBounds(10, 67, 260, 53);
		panel.add(lblDescription);
		
		JLabel lblPrice = new JLabel("price");
		lblPrice.setBounds(10, 123, 46, 14);
		panel.add(lblPrice);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBounds(280, 82, 60, 23);
		panel.add(btnBuy);
		
		textField = new JTextField();
		textField.setBounds(462, 24, 147, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(619, 23, 91, 23);
		contentPane.add(btnNewButton);
	}
}
