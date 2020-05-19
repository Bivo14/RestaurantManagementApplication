package presentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import business.Restaurant;
import data.RestaurantSerializator;

public class MainWindow implements java.io.Serializable 
{
	private Restaurant restaurant;
	private AdministratorGUI admin;
	private WaiterGUI waiter;
	private ChefGUI chef;
	
	final Runnable SOUND = (Runnable)Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
	
	public static JFrame mainFrame = new JFrame("Restaurant application");
	
	private JPanel mainPanel = new JPanel();
	
	private JButton toAdmin = new JButton("Go to Administrator");
	private JButton toWaiter = new JButton("Go to Waiter");
	private JButton toChef = new JButton("Go to Chef");
	private JButton exit = new JButton("Exit applicaiton");
	private JButton back = new JButton("Back");
	
	private JLabel userNameLabel = new JLabel("Username: ");
	private JLabel passwordLabel = new JLabel("Password: ");
	
	private JFrame loginFrame = new JFrame("Enter your credentials");
	private JPanel loginPanel = new JPanel();
	private JTextField userName = new JTextField(20);
	private JPasswordField password = new JPasswordField(20);
	private JButton loginButton = new JButton("Login");
	
	private String usernameCred = "admin";
	private String usernamePassword = "admin";
	private String waiterCred = "waiter";
	private String waiterPassword = "waiter";
	
	public String args;
	
	public MainWindow(Restaurant rest, String argument) throws IOException
	{
		this.restaurant = rest;
		this.args = argument;
		
		mainFrame.setBounds(50, 50, 640, 225);
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setBackground(new Color(66,245,225));
		mainFrame.add(mainPanel);
		
		admin = new AdministratorGUI(restaurant, mainFrame, args);
		waiter = new WaiterGUI(restaurant, mainFrame);
		chef = new ChefGUI(restaurant, mainFrame);
		
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setFont(new Font("Arial", Font.BOLD, 16));
		
		toWaiter.setOpaque(false);
		toWaiter.setContentAreaFilled(false);
		toWaiter.setBorderPainted(false);
		toWaiter.setFont(new Font("Arial", Font.BOLD, 16));
		
		toAdmin.setOpaque(false);
		toAdmin.setContentAreaFilled(false);
		toAdmin.setBorderPainted(false);
		toAdmin.setFont(new Font("Arial", Font.BOLD, 16));
		
		toChef.setOpaque(false);
		toChef.setContentAreaFilled(false);
		toChef.setBorderPainted(false);
		toChef.setFont(new Font("Arial", Font.BOLD, 16));
		
		loginFrame.setBounds(50, 50, 640, 480);
		loginPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		loginPanel.setBackground(new Color(66,245,225));
		loginPanel.add(userNameLabel);
		loginPanel.add(userName);
		loginPanel.add(passwordLabel);
		loginPanel.add(password);
			
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				loginFrame.setVisible(false);
				mainFrame.setVisible(true);
			}
		});

		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(userName.getText().equals(usernameCred) && password.getText().equals(usernamePassword))
				{
					mainFrame.setVisible(false);
					loginFrame.setVisible(false);
					waiter.getFrame().setVisible(false);
					admin.getMainFrame().setVisible(true);
					chef.getMainFrame().setVisible(false);
				}
				else
				{
					if(userName.getText().equals(waiterCred) && password.getText().toString().equals(waiterPassword))
					{
						mainFrame.setVisible(false);
						loginFrame.setVisible(false);
						waiter.getFrame().setVisible(true);
						admin.getMainFrame().setVisible(false);
						chef.getMainFrame().setVisible(false);
					}
					else
					{
						if(SOUND != null)
						{
							SOUND.run();
						}
						JOptionPane.showMessageDialog(null, "Wrong credentials");
					}
				}
			}
		});
		loginPanel.add(loginButton);
		loginPanel.add(back);
		loginFrame.add(loginPanel);
		loginFrame.pack();
		
		toAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
		toAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				loginFrame.setVisible(true);
				mainFrame.setVisible(false);
				waiter.getFrame().setVisible(false);
				admin.getMainFrame().setVisible(false);
				chef.getMainFrame().setVisible(false);
			}
		});
		mainPanel.add(toAdmin);
		
		toWaiter.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
		toWaiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				loginFrame.setVisible(true);
				mainFrame.setVisible(false);
				admin.getMainFrame().setVisible(false);
				waiter.getFrame().setVisible(false);
				chef.getMainFrame().setVisible(false);
			}
		});
		mainPanel.add(toWaiter);
		
		toChef.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
		toChef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mainFrame.setVisible(false);
				admin.getMainFrame().setVisible(false);
				waiter.getFrame().setVisible(false);
				chef.getMainFrame().setVisible(true);
			}
		});
		mainPanel.add(toChef);
		
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPanel.add(Box.createRigidArea(new Dimension(0,15)));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				RestaurantSerializator ser = new RestaurantSerializator();
				ser.serialize(restaurant, args);
				System.exit(0);
			}
		});
		mainPanel.add(exit);	
		mainFrame.setVisible(true);
	}
	
	public JFrame getMainWindow()
	{
		return mainFrame;
	}
	
	public Restaurant getRestaurantData()
	{
		return this.restaurant;
	}
}
