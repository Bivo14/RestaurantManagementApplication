import java.io.IOException;

import business.MenuItem;
import business.Restaurant;
import data.RestaurantSerializator;
import presentation.MainWindow;
import presentation.WaiterGUI;

public class DriverClass
{
	public static void main(String[] args) throws IOException
	{
		RestaurantSerializator ser = new RestaurantSerializator();
		//Restaurant rest = new Restaurant();
		Restaurant rest = ser.deserialize(args[0]);
		MainWindow start = new MainWindow(rest, args[0]);
	}
}
