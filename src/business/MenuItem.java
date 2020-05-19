package business;

import java.util.ArrayList;

public abstract class MenuItem implements java.io.Serializable 
{
	protected String itemName;
	protected double itemPrice;
	protected ArrayList<MenuItem> products;
	public abstract double computePrice();
		
	public MenuItem() {};
	
	public MenuItem(String nume, double pret)
	{
		this.itemName = nume;
		this.itemPrice = pret;
		this.products = new ArrayList<MenuItem>();
	}

	public String getItemName() 
	{
		return itemName;
	}

	public void setItemName(String itemName) 
	{
		this.itemName = itemName;
	}

	public double getItemPrice() 
	{
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) 
	{
		this.itemPrice = itemPrice;
	}
	
	public String productsAsString()
	{
		StringBuilder sb = new StringBuilder();
		for(MenuItem mi: products)
		{
			sb.append(mi.getItemName() + " ");
		}
		return sb.toString();
	}

	public ArrayList<MenuItem> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<MenuItem> products) {
		this.products = products;
	}
	
}
