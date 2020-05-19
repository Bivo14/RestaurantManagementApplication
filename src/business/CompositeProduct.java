package business;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements java.io.Serializable 
{
	private String productName;
	private ArrayList<MenuItem> products;
	private double price;
		
	public CompositeProduct(String productName, ArrayList<MenuItem> products)
	{
		this.productName = productName;
		this.products = products;
	}

	@Override
	public double computePrice() 
	{
		double price = 0;
		for(MenuItem mi : this.products)
		{
			price += mi.getItemPrice();
		}
		
		return price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public ArrayList<MenuItem> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<MenuItem> products) {
		this.products = products;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
}
