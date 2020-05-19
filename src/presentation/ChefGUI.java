package presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import business.Restaurant;

public class ChefGUI implements Observer
{
	private Restaurant restaurant;
	private ImageClass myIC;
	
	private JFrame mwJFrame;
	private JFrame mainFrame;
	
	private JButton backButton = new JButton("Back");
	
	private JPanel panou = new JPanel();
	
	public ChefGUI(Restaurant rest, JFrame mwJFrame2) throws IOException
	{
		this.restaurant = rest;
		this.mwJFrame = mwJFrame2;
		this.myIC = new ImageClass();
		
		restaurant.addObserver(this);
		
		mainFrame = new JFrame("Chef panel");
		mainFrame.setBounds(50, 50, 640, 480);
		mainFrame.setVisible(false);
		panou.setBackground(new Color(66,245,225));
		 
		String imgPath = "Images/chef.png";
		JLabel picLabel = myIC.addImage(imgPath);
		panou.add(picLabel);
		
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setBorderPainted(false);
		backButton.setFont(new Font("Arial", Font.BOLD, 40));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				mainFrame.setVisible(false);
				mwJFrame.setVisible(true);
			}
		});
		
		panou.add(backButton);
		mainFrame.add(panou);
	}
	
	public JFrame getMainFrame()
	{
		return this.mainFrame;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		this.getMainFrame().setVisible(true);
		System.out.println(arg.toString());
		int confirm = JOptionPane.showConfirmDialog(null, "Order ready!");
		if(confirm == 0)
		{
			System.out.println("Chef is ready to cook!");
			this.getMainFrame().setVisible(false);
		}
		else
		{
			System.out.println("Chef is already cooking!");
			this.getMainFrame().setVisible(false);
		}
	}
}
