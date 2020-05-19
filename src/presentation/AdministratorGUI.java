package presentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import business.BaseProduct;
import business.CompositeProduct;
import business.IRestaurantProcessing;
import business.MenuItem;
import business.Order;
import business.Restaurant;
import data.RestaurantSerializator;

public class AdministratorGUI implements IRestaurantProcessing 
{
	private Restaurant restaurant;
	
	static int i = 0;
	static int j = 0;
	
	final Runnable SOUND = (Runnable)Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
	
	private JFrame frame = new JFrame("Administrator panel");
	private JFrame frameCreate = new JFrame("Administrator panel");
	private JFrame productCompFrame = new JFrame("Administrator panel");
	private JFrame frameDelete = new JFrame("Administrator panel");
	private JFrame frameEdit = new JFrame("Administrator panel");
	private JFrame frameView = new JFrame("Administrator panel");

	private JButton exit = new JButton("Exit");
	private JButton createItemButton = new JButton("Add base product");
	private JButton addItem = new JButton("ADD");
	private JButton createCompositeButton = new JButton("Add composite product");
	private JButton addCompositeItem = new JButton("ADD Product");
	private JButton addCompositeItemMain = new JButton("ADD Comp");
	private JButton deleteItemButton = new JButton("Delete product");
	private JButton deleteItem = new JButton("Remove");
	private JButton editMenuItem = new JButton("Edit menu item");
	private JButton editButton = new JButton("Edit item");
	private JButton backButton = new JButton("Back");
	private JButton backButton1 = new JButton("Back");
	private JButton backButton2 = new JButton("Back");
	private JButton backButton3 = new JButton("Back");
	private JButton backButton4 = new JButton("Back");
	private JButton backButton5 = new JButton("Back");
	private JButton showItemsButton = new JButton("View menu");
	
	private JPanel panou = new JPanel();
	private JPanel panouCreate = new JPanel();
	private JPanel panouView = new JPanel();
	private JPanel panouEdit = new JPanel();
	private JPanel panouDelete = new JPanel();
	private JPanel productCompPanel = new JPanel();
	
	private JLabel productToAddInComp = new JLabel("Base product name: ");
	private JLabel compProdName = new JLabel("Composite product name: ");
	private JLabel itemNameLabel = new JLabel("Item name");
	private JLabel itemPriceLabel = new JLabel("Item price");
	private JLabel prodToDelete = new JLabel("Product name: ");;
	private JLabel prodToEdit = new JLabel("Product name: ");
	private JLabel newPrice = new JLabel("New price: ");
	private JLabel newNameLabel = new JLabel("New name: ");
	private JLabel editIngredientsL = new JLabel("New ingredients: ");
	
	private JTextField itemName = new JTextField(15);
	private JTextField 	itemPrice = new JTextField(5);
	private JTextField compProdNameJTF = new JTextField(15);
	private JTextField productToAddInCompJTF = new JTextField(15);
	private JTextField itemToDeleteName = new JTextField(30);
	private JTextField newPriceJTF = new JTextField(5);
	private JTextField itemToEditName = new JTextField(10);
	private JTextField newNameJTF = new JTextField(10);
	private JTextField editIngredientsJTF = new JTextField(20);
	
	
	private JFrame mwJFrame;
	public String argument;
	private ArrayList<MenuItem> compProductItems;
	String[] columns = {"Product","Price","Ingredients"};
	String[][] data = new String[2000][2000];
	private JTable menu;
	
	public AdministratorGUI(Restaurant rest, JFrame mwJFrame2, String args)
	{
		
		this.restaurant = rest;
		this.mwJFrame = mwJFrame2;
		this.argument = args;
			
		frame.setBounds(50, 50, 300, 350);
		panou.setLayout(new BoxLayout(panou,BoxLayout.Y_AXIS));
		panou.setBackground(new Color(66,245,225));
		//frame.setVisible(true);
		
		createItemButton.setOpaque(false);
		createItemButton.setContentAreaFilled(false);
		createItemButton.setBorderPainted(false);
		createItemButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		createCompositeButton.setOpaque(false);
		createCompositeButton.setContentAreaFilled(false);
		createCompositeButton.setBorderPainted(false);
		createCompositeButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		deleteItemButton.setOpaque(false);
		deleteItemButton.setContentAreaFilled(false);
		deleteItemButton.setBorderPainted(false);
		deleteItemButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		editMenuItem.setOpaque(false);
		editMenuItem.setContentAreaFilled(false);
		editMenuItem.setBorderPainted(false);
		editMenuItem.setFont(new Font("Arial", Font.BOLD, 16));
		
		showItemsButton.setOpaque(false);
		showItemsButton.setContentAreaFilled(false);
		showItemsButton.setBorderPainted(false);
		showItemsButton.setFont(new Font("Arial", Font.BOLD, 16));
		
		backButton5.setOpaque(false);
		backButton5.setContentAreaFilled(false);
		backButton5.setBorderPainted(false);
		backButton5.setFont(new Font("Arial", Font.BOLD, 16));
		
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setFont(new Font("Arial", Font.BOLD, 16));
		
		if(restaurant.getMenu().isEmpty() == false)
		{
			
			for(MenuItem mi: restaurant.getMenu())
			{
				data[i][j] = mi.getItemName();
				data[i][j + 1] = Double.toString(mi.getItemPrice());
				data[i][j + 2] = mi.productsAsString();
				i++;
			}
		}
		
		createItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panou.add(Box.createRigidArea(new Dimension(0,15)));
		createItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				frameCreate.setVisible(true);
			}
		});
		panou.add(createItemButton);
		
		createCompositeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panou.add(Box.createRigidArea(new Dimension(0,15)));
		createCompositeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				productCompFrame.setVisible(true);
			}
		});
		panou.add(createCompositeButton);
		
		deleteItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panou.add(Box.createRigidArea(new Dimension(0,15)));
		deleteItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				frameDelete.setVisible(true);
			}
		});
		panou.add(deleteItemButton);
		
		editMenuItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		panou.add(Box.createRigidArea(new Dimension(0,15)));
		editMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
				frameEdit.setVisible(true);
			}
		});
		panou.add(editMenuItem);
			
		backButton.addActionListener(new backButtonListener());
		backButton1.addActionListener(new backButtonListener());
		backButton2.addActionListener(new backButtonListener());
		backButton3.addActionListener(new backButtonListener());
		backButton4.addActionListener(new backButtonListener());
		panouView.setBackground(new Color(66,245,225));
		showItemsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panou.add(Box.createRigidArea(new Dimension(0,15)));
		showItemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frameView = new JFrame("Administrator panel");
				frameView.setBounds(50, 50, 640, 480);
				panouView = new JPanel();
				panouView.setLayout(new FlowLayout());
				panouView.add(backButton2);
				data = new String[2000][2000];
				for(int k = 0; k < restaurant.getMenu().size(); k++)
				{
					data[k][0] = restaurant.getMenu().get(k).getItemName();
					data[k][1] = Double.toString(restaurant.getMenu().get(k).getItemPrice());
					data[k][2] = restaurant.getMenu().get(k).productsAsString();
				}
				menu = new JTable(data,columns);
				menu.setBounds(60, 60, 400, 400);
				JScrollPane sp = new JScrollPane(menu);
				panouView.add(sp);
				frameView.add(panouView);
				frame.setVisible(false);
				frameView.setVisible(true);
			}
		});
		panou.add(showItemsButton);
		
		
		backButton5.setAlignmentX(Component.CENTER_ALIGNMENT);
		panou.add(Box.createRigidArea(new Dimension(0,15)));
		backButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				frameCreate.setVisible(false);
				frameView.setVisible(false);
				frameDelete.setVisible(false);
				frameEdit.setVisible(false);
				frame.setVisible(false);
				mwJFrame.setVisible(true);
			}
		});
		panou.add(backButton5);
		
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		panou.add(Box.createRigidArea(new Dimension(0,15)));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				RestaurantSerializator ser = new RestaurantSerializator();
				ser.serialize(restaurant, argument);
				System.exit(0);
			}
		});
		panou.add(exit);
		
		frame.add(panou);
		
		frameCreate.setBounds(50, 50, 640, 480);
		panouCreate.setLayout(new FlowLayout(FlowLayout.LEFT));
		panouCreate.setBackground(new Color(66,245,225));
		panouCreate.add(itemNameLabel);
		panouCreate.add(itemName);
		panouCreate.add(itemPriceLabel);
		panouCreate.add(itemPrice);
		panouCreate.add(addItem);
		addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String itName = itemName.getText();
				double pret = 0;
				if(itemPrice.getText().equals("") == false)
				{
					pret = Double.parseDouble(itemPrice.getText());
				}	
				BaseProduct bp = new BaseProduct(itName, pret);
				createMenuItem(bp);
				data[i][j] = restaurant.getMenu().get(i).getItemName();
				data[i][j + 1] = Double.toString(restaurant.getMenu().get(i).getItemPrice());
				data[i][j + 2] = restaurant.getMenu().get(i).productsAsString();
				i++;
				if(SOUND != null)
				{
					SOUND.run();
				}
				JOptionPane.showMessageDialog(null, "Product added successfully");
			}
		});
		frameCreate.add(panouCreate);
		panouCreate.add(backButton);
		frameCreate.pack();
				
		productCompFrame.setBounds(50, 50, 640, 480);
		compProductItems = new ArrayList<MenuItem>();
		addCompositeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String prodName = productToAddInCompJTF.getText();
				for(MenuItem mi: restaurant.getMenu())
				{
					if(mi.getItemName().equals(prodName))
					{
						compProductItems.add(mi);
						if(compProductItems.contains(mi) == true)
						{
							if(SOUND != null)
							{
								SOUND.run();
							}
							JOptionPane.showMessageDialog(null, "Product added to the recipe");
						}
					}
				}
			}
		});
		
		productCompPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		productCompPanel.setBackground(new Color(66,245,225));
		productCompPanel.add(productToAddInComp);
		productCompPanel.add(productToAddInCompJTF);
		productCompPanel.add(compProdName);
		productCompPanel.add(compProdNameJTF);
		productCompPanel.add(addCompositeItem);
		productCompPanel.add(addCompositeItemMain);
		addCompositeItemMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
				String prodName = compProdNameJTF.getText();
				System.out.println(prodName);
				CompositeProduct cp = new CompositeProduct("", null);
				cp.setItemName(prodName);
				ArrayList<MenuItem> prodCopy = new ArrayList<MenuItem>();
				for(MenuItem mi: compProductItems)
				{
					prodCopy.add(mi);
				}
				cp.setProducts(prodCopy);
				cp.setItemPrice(cp.computePrice());
				createMenuItem(cp);
				data[i][j] = restaurant.getMenu().get(i).getItemName();
				data[i][j + 1] = Double.toString(restaurant.getMenu().get(i).getItemPrice());
				data[i][j + 2] = restaurant.getMenu().get(i).productsAsString();
				i++;
				if(SOUND != null)
				{
					SOUND.run();
				}
				JOptionPane.showMessageDialog(null, "Product added successfully");
				compProductItems.clear();
			}
		});
		productCompFrame.add(productCompPanel);
		productCompPanel.add(backButton1);
		productCompFrame.pack();
				
		frameDelete.setBounds(50, 50, 450, 150);
		panouDelete.setLayout(new FlowLayout());
		panouDelete.setBackground(new Color(66,245,225));
		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String itName = itemToDeleteName.getText();
				int deletedCount = deleteMenuItem(itName);
				i -= deletedCount;
				if(SOUND != null)
				{
					SOUND.run();
				}
				JOptionPane.showMessageDialog(null, "Product removed successfully");
			}
		});
		panouDelete.add(prodToDelete);
		panouDelete.add(itemToDeleteName);
		panouDelete.add(deleteItem);
		panouDelete.add(backButton3);
		frameDelete.add(panouDelete);
		frameDelete.pack();	
		
		frameEdit.setBounds(50, 50, 640, 480);
		panouEdit.setLayout(new FlowLayout());
		panouEdit.setBackground(new Color(66,245,225));
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String itName = itemToEditName.getText();
				double newPriceDouble = 0;
				if(newPriceJTF.getText().equals("") == false)
				{
					newPriceDouble = Double.parseDouble(newPriceJTF.getText());
				}
				String newName = newNameJTF.getText();
				ArrayList<MenuItem> ingredientsList = new ArrayList<MenuItem>();

				int contor = 0;
				ArrayList<String> strSplit = restaurant.editIngredients(editIngredientsJTF.getText());
				for(int w = 0; w < strSplit.size(); w++)
				{
					contor++;
					for(MenuItem mi: restaurant.getMenu())
					{
						if(mi.getItemName().equals(strSplit.get(w)))
						{		
							ingredientsList.add(mi);
							break;
						}
					}
				}
				if(contor == ingredientsList.size() || ingredientsList.isEmpty())
				{	
					editMenuItem(itName,newPriceDouble,newName,ingredientsList);
					if(SOUND != null)
					{
						SOUND.run();
					}
					JOptionPane.showMessageDialog(null, "Product modified successfully");
				}
				else
				{
					if(SOUND != null)
					{
						SOUND.run();
					}
					JOptionPane.showMessageDialog(null, "One of the products doesn't exist");
				}
				
			}				
		});
		
		panouEdit.add(prodToEdit);
		panouEdit.add(itemToEditName);
		panouEdit.add(newPrice);
		panouEdit.add(newPriceJTF);
		panouEdit.add(newNameLabel);
		panouEdit.add(newNameJTF);
		panouEdit.add(editIngredientsL);
		panouEdit.add(editIngredientsJTF);
		panouEdit.add(editButton);
		panouEdit.add(backButton4);
		frameEdit.add(panouEdit);
		frameEdit.pack();
	}
	
	public class backButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			frameCreate.setVisible(false);
			frameView.setVisible(false);
			frameDelete.setVisible(false);
			frameEdit.setVisible(false);
			productCompFrame.setVisible(false);
			frame.setVisible(true);
		}
	}
	
	public JFrame getMainFrame()
	{
		return frame;
	}
	
	@Override
	public void createMenuItem(MenuItem prodToCreate) 
	{
		// TODO Auto-generated method stub
		restaurant.createMenuItem(prodToCreate);
	}
	@Override
	public int deleteMenuItem(String itemName) 
	{
		// TODO Auto-generated method stub
		return restaurant.deleteMenuItem(itemName);
	}
	@Override
	public void editMenuItem(String prodToModify, double price, String newName, ArrayList<MenuItem> ingredients) 
	{
		// TODO Auto-generated method stub
		restaurant.editMenuItem(prodToModify, price, newName, ingredients);
	}

	@Override
	public void createOrder(Order orderToCreate) {
		/* TODO Auto-generated method stub */
	}
	
	@Override
	public double computeOrderPrice(Order orderToCompute) {
		/* TODO Auto-generated method stub*/ 
		return 0;
	}
	
	@Override
	public void generateOrderBill(Order order, String products) {
		/* TODO Auto-generated method stub*/
	}
}
