package run;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ClientGui1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGui1 frame = new ClientGui1();
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
	public ClientGui1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(416, 34, 180, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(606, 34, 72, 21);
		contentPane.add(btnSearch);
		
		JCheckBox chckbxFilter = new JCheckBox("filter 1");
		chckbxFilter.setBounds(581, 160, 97, 23);
		contentPane.add(chckbxFilter);
		
		JCheckBox chckbxFilter_1 = new JCheckBox("filter 2");
		chckbxFilter_1.setBounds(581, 186, 97, 23);
		contentPane.add(chckbxFilter_1);
		
		JCheckBox chckbxFilter_2 = new JCheckBox("filter 3");
		chckbxFilter_2.setBounds(581, 212, 97, 23);
		contentPane.add(chckbxFilter_2);
		
		JLabel lblFilters = new JLabel("Filters");
		lblFilters.setBounds(618, 133, 34, 20);
		contentPane.add(lblFilters);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(416, 93, 262, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(416, 270, 262, 2);
		contentPane.add(separator_1);
	}
}
