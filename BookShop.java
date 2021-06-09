package info.planet;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;


public class BookShop {

	private JFrame frame;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTable table;
	private JTextField txt4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookShop window = new BookShop();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BookShop() {
		initialize();
		connect();
		table_load();
	}
	
	
			Connection con;
			PreparedStatement pst;
			ResultSet rs;
	
		public void connect()
		{
			try
			{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Registered");
			

			String url="jdbc:mysql://localhost:3306/Bookshop";
			String user="Bhagyashri";
			String pass="28112000";

			con=DriverManager.getConnection(url,user,pass);
			}
			catch(ClassNotFoundException ex)
			{
			}
			catch(SQLException ex)
			{
			}
			
		}
		
		public void table_load()
		{
			try {
			pst=con.prepareStatement("select * from Book");
			rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
				}
		
		catch(SQLException e)
				{
			e.printStackTrace();
				}
		}
		
		
		
		

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 851, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(246, 10, 221, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(32, 97, 372, 229);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbl1 = new JLabel("Book Name :");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl1.setBounds(10, 37, 112, 26);
		panel.add(lbl1);
		
		txt1 = new JTextField();
		txt1.setBounds(117, 37, 245, 31);
		panel.add(txt1);
		txt1.setColumns(10);
		
		JLabel lbl2 = new JLabel("Edition :");
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl2.setBounds(10, 96, 96, 26);
		panel.add(lbl2);
		
		txt2 = new JTextField();
		txt2.setBounds(117, 96, 245, 31);
		panel.add(txt2);
		txt2.setColumns(10);
		
		JLabel lbl3 = new JLabel("Price :");
		lbl3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl3.setBounds(10, 165, 96, 26);
		panel.add(lbl3);
		
		txt3 = new JTextField();
		txt3.setBounds(117, 165, 245, 31);
		panel.add(txt3);
		txt3.setColumns(10);
		
		JButton btn1 = new JButton("Save");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Name,Edition,Price;
				
				Name=txt1.getText();
				Edition=txt2.getText();
				Price=txt3.getText();
				
				try {
					
					pst=con.prepareStatement("insert into book(Name,Edition,Price)values(?,?,?)");
					pst.setString(1,Name);
					pst.setString(2,Edition);
					pst.setString(3,Price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					table_load();
					txt1.setText("");
					txt2.setText("");
					txt3.setText("");
					txt1.requestFocus();
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
			}
		});
		btn1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn1.setBounds(32, 349, 85, 51);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("Exit");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btn2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn2.setBounds(178, 349, 85, 51);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new JButton("Clear");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txt1.setText("");
				txt2.setText("");
				txt3.setText("");
				txt1.requestFocus();
			}
		});
		btn3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn3.setBounds(318, 349, 85, 51);
		frame.getContentPane().add(btn3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(422, 97, 405, 317);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(32, 429, 372, 70);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lbl4 = new JLabel("Book ID :");
		lbl4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl4.setBounds(10, 25, 89, 24);
		panel_1.add(lbl4);
		
		txt4 = new JTextField();
		txt4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				try {
					
					String id=txt4.getText();
					
					pst=con.prepareStatement("select Name,Edition,Price from Book where id=?");
					pst.setString(1, id);
					ResultSet rs=pst.executeQuery();
					
					if(rs.next()==true)
					{
						String Name=rs.getString(1);
						String Edition=rs.getString(2);
						String Price=rs.getString(3);
						
						txt1.setText(Name);
						txt2.setText(Edition);
						txt3.setText(Price);
					}
					else {
						txt1.setText("");
						txt2.setText("");
						txt3.setText("");
						
					}
				
					
				}
				catch(SQLException ex) {
					
				}
			}
		});
		txt4.setBounds(122, 25, 240, 24);
		panel_1.add(txt4);
		txt4.setColumns(10);
		
		JButton btn4 = new JButton("Update");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Name,Edition,Price,ID;
				
				Name=txt1.getText();
				Edition=txt2.getText();
				Price=txt3.getText();
				ID=txt4.getText();
				
				try {
					
					pst=con.prepareStatement("update Book set Name=?,Edition=?,Price=? where ID=?");
					pst.setString(1,Name);
					pst.setString(2,Edition);
					pst.setString(3,Price);
					pst.setString(4,ID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!");
					table_load();
					txt1.setText("");
					txt2.setText("");
					txt3.setText("");
					txt1.requestFocus();
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btn4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn4.setBounds(460, 448, 100, 51);
		frame.getContentPane().add(btn4);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ID;
				
				
				ID=txt4.getText();
				
				try {
					
					pst=con.prepareStatement("delete from Book where ID=?");
					
					pst.setString(1,ID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!");
					table_load();
					txt1.setText("");
					txt2.setText("");
					txt3.setText("");
					txt1.requestFocus();
					
				}
				catch(SQLException e1)
				{
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(658, 450, 100, 49);
		frame.getContentPane().add(btnNewButton);
	}
}
