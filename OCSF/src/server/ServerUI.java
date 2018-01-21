package server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import java.awt.ScrollPane;

import javax.swing.JTextArea;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

public class ServerUI extends JFrame
{
	private JPanel contentPane;
	private JTextArea serverConsole;
	private JLabel lblDbName;
	private JTextField textFieldDBName;
	private JLabel lblPort;
	private JTextField textFieldPort;
	private JButton btnConnect;
	private JButton btnExit;
	private JTextField textFieldUser;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField textFieldPass;
	Server server;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ServerUI()//Constructor
	{
		super();
		initialize();
	}
	
	private void initialize()//initialize Method
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setTitle("Server");
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Arial", Font.PLAIN, 13));
		btnExit.setBounds(451, 333, 74, 29);
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		contentPane.add(btnExit);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		scrollPane.setBounds(78, 218, 334, 122);
		contentPane.add(scrollPane);
		
		serverConsole = new JTextArea();
		serverConsole.setFont(new Font("Courier New", Font.PLAIN, 16));
		serverConsole.setForeground(new Color(0, 255, 0));
		scrollPane.setViewportView(serverConsole);
		serverConsole.setEditable(false);
		
		lblDbName = new JLabel("DB Name:");
		lblDbName.setFont(new Font("Arial",Font.PLAIN, 16));
		lblDbName.setBounds(60, 34, 86, 14);
		contentPane.add(lblDbName);
		
		textFieldDBName = new JTextField();
		textFieldDBName.setForeground(new Color(139, 0, 0));
		textFieldDBName.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		textFieldDBName.setBounds(171, 34, 186, 20);
		contentPane.add(textFieldDBName);
		textFieldDBName.setColumns(10);
		textFieldDBName.setText("DBFinal");
		
		lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPort.setBounds(60, 67, 64, 14);
		contentPane.add(lblPort);
		
		textFieldPort = new JTextField();
		textFieldPort.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		textFieldPort.setForeground(new Color(165, 42, 42));
		textFieldPort.setBounds(171, 67, 186, 20);
		contentPane.add(textFieldPort);
		textFieldPort.setColumns(10);
		textFieldPort.setText("5555");
		
		btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Arial", Font.PLAIN, 13));
		btnConnect.setBounds(58, 184, 113, 23);
		btnConnect.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					server.setPort(Integer.valueOf(textFieldPort.getText()));
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					server.initDBConnection(textFieldDBName.getText(), 
							textFieldUser.getText(), 
							new String(textFieldPass.getPassword()));
					display("SQL connection succeed");
				}
				catch(Exception e1)
				{
					display("SQL connection failed.");
					JOptionPane.showMessageDialog(null, "SQL connection failed");
				}
				
				try
				{
					server.listen(); //Start listening for connections
					display("Server is listening on port " + textFieldPort.getText());
				}
				catch(Exception e1)
				{
					display("ERROR - Could not listen for clients!");
					JOptionPane.showMessageDialog(null, "ERROR - Could not listen for clients");
				}
			}
		});
		
		contentPane.add(btnConnect);
		
		textFieldUser = new JTextField();
		textFieldUser.setForeground(new Color(165, 42, 42));
		textFieldUser.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		textFieldUser.setBounds(171, 98, 186, 20);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		textFieldUser.setText("root");
		
		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUsername.setBounds(60, 98, 101, 14);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPassword.setBounds(60, 133, 101, 14);
		contentPane.add(lblPassword);
		
		textFieldPass = new JPasswordField();
		textFieldPass.setForeground(new Color(165, 42, 42));
		textFieldPass.setFont(new Font("Palatino Linotype", Font.BOLD, 16));
		textFieldPass.setBounds(171, 133, 186, 20);
		contentPane.add(textFieldPass);
		textFieldPass.setColumns(10);
		textFieldPass.setText("");
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(165, 42, 42));
		lblNewLabel.setBounds(0, 0, 584, 462);
		contentPane.add(lblNewLabel);
	}
	
	public void display(String s)
	{
		serverConsole.append(s + "\n");
	}
	
	public void setServer(Server server)
	{
		this.server = server;
	}
	
	public static void main(String[] args)
	{
		ServerUI serverui = new ServerUI();
		Server server = new Server(serverui);
		serverui.setServer(server);
		}
}
