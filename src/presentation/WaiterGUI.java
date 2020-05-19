package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import business.IRestaurantProcessing;
import business.MenuItem;
import business.Order;
import business.Restaurant;


public class WaiterGUI implements IRestaurantProcessing
{
	private Restaurant restaurant;
	private ImageClass myIC;
	
	final Runnable SOUND = (Runnable)Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.default");
	
	static int orderID = 1;
	static int x = 0;
	static int y = 0;
	
	private JFrame waiterFrame = new JFrame("Waiter panel");
	private JFrame addOrderFrame = new JFrame("Waiter panel");
	private JFrame viewOrdersFrame = new JFrame("Waiter panel");
	
	private JPanel panouWaiter = new JPanel();
	private JPanel panouAddOrder = new JPanel();
	private JPanel panouViewOrders = new JPanel();
	
	private JLabel addItemToOrder = new JLabel("Insert product: ");
	private JLabel 	tableNumber = new JLabel("Table number");
	
	private JTextField tableNumberJTF = new JTextField(5);
	private JTextField addItemJTF;
	
	private JButton addOrderButton = new JButton("Add order");
	private JButton addOrder = new JButton("New order");
	private JButton addItemToOrderButton = new JButton("Add product");
	private JButton viewOrders = new JButton("View active orders");
	private JButton button = new JButton("Generate Bill");
	private JButton backButton1 = new JButton("Back");
	private JButton backButton2 = new JButton("Back");
	private JButton backButton3 = new JButton("Back");
	private JButton exit = new JButton("Exit");
	
	private JFrame mwJFrame;
	private ArrayList<MenuItem> orderedItems;
	String[] columns = {"Order ID","Date","Table","Ordered items","Total price"};
	String[][] data = new String[2000][2000];
	private JTable orders;
	
	public WaiterGUI(Restaurant rest, JFrame mwJFrame2)
	{
		
		this.restaurant = rest;
		this.mwJFrame = mwJFrame2;
		this.myIC = new ImageClass();
		
		waiterFrame.setBounds(50, 50, 640, 480);
		panouWaiter.setLayout(new GridBagLayout());
		panouWaiter.setBackground(new Color(66,245,225));
		waiterFrame.add(panouWaiter);
		
		addOrder.setOpaque(false);
		addOrder.setContentAreaFilled(false);
		addOrder.setBorderPainted(false);
		addOrder.setFont(new Font("Arial", Font.BOLD, 16));
		
		viewOrders.setOpaque(false);
		viewOrders.setContentAreaFilled(false);
		viewOrders.setBorderPainted(false);
		viewOrders.setFont(new Font("Arial", Font.BOLD, 16));
		
		backButton3.setOpaque(false);
		backButton3.setContentAreaFilled(false);
		backButton3.setBorderPainted(false);
		backButton3.setFont(new Font("Arial", Font.BOLD, 16));
		
		String imgPath = "Images/waiter.png";
		JLabel picLabel = myIC.addImage(imgPath);
		panouWaiter.add(picLabel);
		
		addOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
		panouWaiter.add(Box.createRigidArea(new Dimension(0,25)));
		addOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				waiterFrame.setVisible(false);
				addOrderFrame.setVisible(true);
			}
		});
		panouWaiter.add(addOrder);
		
		viewOrders.setAlignmentX(Component.CENTER_ALIGNMENT);
		panouWaiter.add(Box.createRigidArea(new Dimension(0,25)));
		viewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				waiterFrame.setVisible(false);
				viewOrdersFrame.setVisible(true);
			}
		});
		panouWaiter.add(viewOrders);
		
		addOrderFrame.setBounds(50, 50, 640, 480);
		panouAddOrder.setLayout(new FlowLayout());
		panouAddOrder.add(tableNumber);
		panouAddOrder.add(tableNumberJTF);
		panouAddOrder.add(addItemToOrder);
		addItemJTF = new JTextField(15);
		panouAddOrder.add(addItemJTF);
		
		orderedItems = new ArrayList<MenuItem>();
		addItemToOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String prodName = addItemJTF.getText();
				for(MenuItem mi: restaurant.getMenu())
				{
					if(mi.getItemName().equals(prodName))
					{
						orderedItems.add(mi);
						if(orderedItems.contains(mi) == true)
						{
							if(SOUND != null)
							{
								SOUND.run();
							}
							JOptionPane.showMessageDialog(null, "Product added to order");
						}
					}
				}
			}
		});
		panouAddOrder.add(addItemToOrderButton);
		
		if(restaurant.getOrdersList().isEmpty() == false)
		{
			for(Order o: restaurant.getOrdersList())
			{
				orderID++;
				o.setTotalPrice(computeOrderPrice(o));
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String strDate = df.format(o.getDate());
				data[x][y] = Integer.toString(o.getOrderID());
				data[x][y + 1] = strDate;
				data[x][y + 2] = Integer.toString(o.getTableNumber());
				data[x][y + 3] = o.productsAsString();
				data[x][y + 4] = Double.toString(o.getTotalPrice());
				x++;
			}
		}
			
		addOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Date orderDate = new Date();
				Order orderToAdd = new Order(orderID,orderDate,Integer.parseInt(tableNumberJTF.getText()));
				ArrayList<MenuItem> orderItemsCopy = new ArrayList<MenuItem>();
				for(MenuItem mi: orderedItems)
				{
					orderItemsCopy.add(mi);
				}
				orderToAdd.setProducts(orderItemsCopy);
				createOrder(orderToAdd);
				orderID++;
				
				if(restaurant.getOrdersList().contains(orderToAdd) == true)
				{
					orderToAdd.setTotalPrice(computeOrderPrice(orderToAdd));
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
					String strDate = df.format(orderToAdd.getDate());
					data[x][y] = Integer.toString(orderToAdd.getOrderID());
					data[x][y + 1] = strDate;
					data[x][y + 2] = Integer.toString(orderToAdd.getTableNumber());
					data[x][y + 3] = orderToAdd.productsAsString();
					data[x][y + 4] = Double.toString(orderToAdd.getTotalPrice());
					x++;
					if(SOUND != null)
					{
						SOUND.run();
					}
					JOptionPane.showMessageDialog(null, "Order added successfully");
				}
				orderedItems.clear();
			}
		});
		panouAddOrder.setBackground(new Color(66,245,225));
		panouAddOrder.add(addOrderButton);

		backButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				addOrderFrame.setVisible(false);
				waiterFrame.setVisible(true);
			}
		});
		panouAddOrder.add(backButton1);
		
		addOrderFrame.add(panouAddOrder);
		addOrderFrame.pack();
		
		viewOrdersFrame.setBounds(50, 50, 1280, 720);
		panouViewOrders.setLayout(new FlowLayout());
		panouViewOrders.setBackground(new Color(66,245,225));
		orders = new JTable(data, columns);
		orders.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		orders.getColumnModel().getColumn(1).setPreferredWidth(100);
		orders.getColumnModel().getColumn(2).setPreferredWidth(25);
		orders.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(orders.getSelectedRow() != -1)
				{
					Order orderToDelete = new Order(0,null,0);
					Date recoverDate = null;
					orderToDelete.setOrderID(Integer.parseInt(data[orders.getSelectedRow()][0]));
					try {
						recoverDate = new SimpleDateFormat("dd-MM-yyyy").parse(data[orders.getSelectedRow()][1]);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					orderToDelete.setDate(recoverDate);
					orderToDelete.setTableNumber(Integer.parseInt(data[orders.getSelectedRow()][2]));
					String products = data[orders.getSelectedRow()][3];
					orderToDelete.setTotalPrice(Double.parseDouble(data[orders.getSelectedRow()][4]));
					try {
						generateOrderBill(orderToDelete, products);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for(Order o: restaurant.getOrdersList())
					{
						if(o.getOrderID() == orderToDelete.getOrderID())
						{
							restaurant.getOrdersMap().remove(o);
						}
					}	
					Iterator<Order> it = restaurant.getOrdersList().iterator();
					while(it.hasNext())
					{
						if(it.next().getOrderID() == orderToDelete.getOrderID())
						{
							it.remove();
						}
					}	
					
					for(int k = orders.getSelectedRow(); k < x; k++)
					{
						data[k][0] = data[k + 1][0];
						data[k][1] = data[k + 1][1];
						data[k][2] = data[k + 1][2];
						data[k][3] = data[k + 1][3];
						data[k][4] = data[k + 1][4];
					}
					x--;
					
					if(restaurant.getOrdersList().contains(orderToDelete) == false)
					{
						if(SOUND != null)
						{
							SOUND.run();
						}
						JOptionPane.showMessageDialog(null, "Bill generated successfully");
					}
				}
			}
		});
		
		JScrollPane sp = new JScrollPane(orders);
		sp.setBounds(50, 50, 800, 800);
		panouViewOrders.add(sp, BorderLayout.CENTER);
		
		backButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				viewOrdersFrame.setVisible(false);
				waiterFrame.setVisible(true);
			}
		});

		panouViewOrders.add(button);
		panouViewOrders.add(backButton2);
		
		viewOrdersFrame.add(panouViewOrders);
		
		backButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panouWaiter.add(Box.createRigidArea(new Dimension(0,25)));
		backButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				waiterFrame.setVisible(false);
				mwJFrame.setVisible(true);
			}
		});
		panouWaiter.add(backButton3);		
		//waiterFrame.setVisible(true);
	}
	
	public JFrame getFrame()
	{
		return waiterFrame;
	}
	
	@Override
	public void createOrder(Order orderToCreate) {
		// TODO Auto-generated method stub
		restaurant.createOrder(orderToCreate);
		
	}

	@Override
	public double computeOrderPrice(Order orderToCompute) {
		// TODO Auto-generated method stub
		return restaurant.computeOrderPrice(orderToCompute);
	}

	@Override
	public void generateOrderBill(Order order, String products) throws IOException {
		// TODO Auto-generated method stub
		restaurant.generateOrderBill(order, products);
	}
	
	@Override
	public void createMenuItem(MenuItem prodToAdd) {
		/* TODO Auto-generated method stub */
	}
	
	@Override
	public int deleteMenuItem(String itemName) { 
		/* TODO Auto-generated method stub */ 
		return 0;
	}
	
	@Override
	public void editMenuItem(String prodToModify, double price, String newName, ArrayList<MenuItem> ingredients) {
		/* TODO Auto-generated method stub*/
	}
}
