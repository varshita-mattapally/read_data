package read_data;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class mysql_java {

	private JFrame frame;
	private JTextField t1;
	private JTextField t2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mysql_java window = new mysql_java();
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
	public mysql_java() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1142, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("From");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
		lblNewLabel.setBounds(127, 31, 121, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_1.setBounds(52, 120, 100, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Marks");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_1_1.setBounds(52, 186, 100, 30);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		t1 = new JTextField();
		t1.setBounds(179, 120, 134, 30);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(179, 186, 134, 30);
		frame.getContentPane().add(t2);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n=t1.getText();
				String m=t2.getText();
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ece2","root","mrec");
					String q="insert into eceAB values('"+n+"','"+m+"')";
					Statement sta=con.createStatement();
					sta.execute(q);
					con.close();
					JOptionPane.showMessageDialog(btnNewButton,"done! ");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 26));
		btnNewButton.setBounds(110, 283, 152, 42);
		frame.getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(416, 55, 451, 270);
		frame.getContentPane().add(table);
		
		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("con.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jbdc:mysql://localhost:3306/ece2","root","mrec");
					Statement sta=con.createStatement();
					String q="select * from eceAB";
					ResultSet rs=sta.executeQuery(q);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					int cols=rsmd.getColumnCount();
					String[] colname=new String[cols];
					for(int i=0;i<cols;i++)
					{
						colname[i]=rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colname);
						String n1,m1;
						while(rs.next())
						{
							n1=rs.getString(1);
							m1=rs.getString(2);
							
							String[] row= {n1,m1};
							model.addRow(row);
						}
						sta.close();
						con.close();
						
					}
					
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNewButton_1.setBounds(447, 376, 121, 37);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTabelModel());
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNewButton_2.setBounds(664, 376, 100, 34);
		frame.getContentPane().add(btnNewButton_2);
	}
}
