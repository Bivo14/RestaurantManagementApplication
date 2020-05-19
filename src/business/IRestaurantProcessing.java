package business;

import java.io.IOException;
import java.util.ArrayList;

public interface IRestaurantProcessing 
{
	public void createMenuItem(MenuItem prodToAdd);
	public int deleteMenuItem(String itemName);
	public void editMenuItem(String prodToModify, double price, String newName, ArrayList<MenuItem> ingredients);
	
	public void createOrder(Order orderToCreate);
	public double computeOrderPrice(Order orderToCompute);
	public void generateOrderBill(Order order, String products) throws IOException ;
}
