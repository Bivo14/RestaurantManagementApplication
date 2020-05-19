package business;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

/**
 * 
 * @author bivo
 * Aceasta clasa reprezinta "esenta" programului.
 * Aici sunt definite toate metodele necesare rularii conform cerintelor aplicaitei.
 *
 */

@SuppressWarnings("deprecation")
public class Restaurant extends Observable implements IRestaurantProcessing, java.io.Serializable 
{
	
	
	private Map<Order, ArrayList<MenuItem>> ordersMap;
	private ArrayList<Order> ordersList;
	private ArrayList<MenuItem> menu;
	
	public Restaurant()
	{
		this.ordersMap = new HashMap<Order, ArrayList<MenuItem>>();
		this.ordersList = new ArrayList<Order>();
		this.menu = new ArrayList<MenuItem>();
	}	
	
	/**
	 * @param prodToCreate
	 * <p>Aceasta metoda este folosita pentru inserarea unui produs in meniul restaurantului
	 */
	@Override
	public void createMenuItem(MenuItem prodToCreate) 
	{		
		// TODO Auto-generated method stub
		menu.add(prodToCreate);
	}	
	
	
	/**
	 * @param itemName
	 * <p>Aceasta metoda este folosita pentru identificarea si stergerea produsului specificat de itemName
	 */
	@Override
	public int deleteMenuItem(String itemName) 
	{
		// TODO Auto-generated method stub
		int deleteCount = 0;
		Iterator<MenuItem> it = menu.iterator();
		while(it.hasNext())
		{
			if(it.next().productsAsString().contains(itemName))
			{
				deleteCount++;
				it.remove();
			}
		}
		Iterator<MenuItem> it2 = menu.iterator();
		while(it2.hasNext())
		{
			if(it2.next().getItemName().equals(itemName))
			{
				deleteCount++;
				it2.remove();
			}
		}
		return deleteCount;
	}	
	
	
	/**
	 * @param prodToModify
	 * @param price
	 * @param newName
	 * @param ingredients
	 * <p>Aceasta metoda este folosita pentru identificarea produsului dorit spre a fi editat,
	 * Ceea ce se doreste a se edita
	 * Si procesul de editare in sine
	 */
	@Override
	public void editMenuItem(String prodToModify, double price, String newName, ArrayList<MenuItem> ingredients) 
	{
		
		// TODO Auto-generated method stub
		for(MenuItem mi : menu)
		{
			if(mi.getItemName().equals(prodToModify))
			{
				if(price != 0)
				{
					mi.setItemPrice(price);
					for(MenuItem mi2: menu)
					{
						mi2.setItemPrice(mi2.computePrice());
					}
				}
				if(newName.equals("") == false)
				{
					mi.setItemName(newName);
					for(MenuItem mi2: menu)
					{
						for(MenuItem miDummy: mi2.getProducts())
						{
							if(miDummy.getItemName().equals(prodToModify))
							{
								miDummy.setItemName(newName);
							}
						}
					}
				}
				if(ingredients.size() != 0)
				{
					mi.setProducts(ingredients);
					mi.setItemPrice(mi.computePrice());
				}
			}
		}
	}
	
	/**
	 * 
	 * @param products
	 * @return Lista cu produsele ce urmeaza a fi procesate (daca toate exista in meniu)
	 */
	public ArrayList<String> editIngredients(String products)
	{
		String[] str = products.split(", ");
		ArrayList<String> strList = new ArrayList<String>();
		for(int i = 0; i < str.length; i++)
		{
			strList.add(str[i]);
		}
		return strList;
	}
		
	/**
	 * @param orderToCreate
	 * <p>Metoda care creeaza un nou order si anunta bucatarul in cazul in care comanda este mai sofisticata(contine un produs compus)
	 */
	@Override
	public void createOrder(Order orderToCreate) 
	{
		// TODO Auto-generated method stub
		boolean valid = false;
		ordersMap.put(orderToCreate, orderToCreate.getOrderItems());
		ordersList.add(orderToCreate);
		StringBuilder sb = new StringBuilder();
		sb.append("Hey chef, you have a new order\n");
		sb.append("You have to cook the following products: " + orderToCreate.productsAsString());
		sb.append("\nThe order is for the table " + orderToCreate.getTableNumber());
		for(MenuItem mi: orderToCreate.getOrderItems())
		{
			if(mi instanceof CompositeProduct)
			{
				valid = true;
				break;
			}
		}
		
		if(valid == true)
		{
			setChanged();
			notifyObservers(sb.toString());
		}	
	}	
	
	
	/**
	 * @param orderToCompute
	 * Metoda care calculeaza pretul total al unei comenzi
	 */
	@Override
	public double computeOrderPrice(Order orderToCompute) 
	{	
		double price = 0;
		// TODO Auto-generated method stub
		ArrayList<MenuItem> orderedItems = ordersMap.get(orderToCompute);
		for(MenuItem mi: orderedItems)
		{
			price += mi.getItemPrice();
		}	
		return price;
	}
	
		
	/**
	 * @param order
	 * @param products
	 * Metoda care genereaza fisierul .txt, fisier care contine chitanta
	 */
	@Override
	public void generateOrderBill(Order order, String products) throws IOException 
	{
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("Order " + order.getOrderID() + " bill.txt");
		FileWriter fw = new FileWriter(sb.toString());
		PrintWriter pw = new PrintWriter(fw);
		pw.print("Bill generated successfully\n");
		pw.print("Table number: " + order.getTableNumber() + "\n");
		pw.print("Date: " + order.getDate() + "\n");
		pw.print("Products ordered: " + products + "\n");
		pw.print("Total price: " + order.getTotalPrice() + "\n");
		pw.print("Thank you for trusting us!\n");
		pw.close();
	}	
	
	
	
	public Map<Order, ArrayList<MenuItem>> getOrdersMap() 
	{
		return ordersMap;
	}



	public void setOrdersMap(Map<Order, ArrayList<MenuItem>> ordersMap) 
	{
		this.ordersMap = ordersMap;
	}



	public ArrayList<Order> getOrdersList() 
	{
		return ordersList;
	}



	public void setOrdersList(ArrayList<Order> ordersList) 
	{
		this.ordersList = ordersList;
	}



	public ArrayList<MenuItem> getMenu()
	{
		return menu;
	}



	public void setMenu(ArrayList<MenuItem> menu)
	{
		this.menu = menu;
	}
	
}
