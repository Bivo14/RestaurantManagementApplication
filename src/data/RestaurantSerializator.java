package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import business.Restaurant;

public class RestaurantSerializator 
{
	public static void serialize(Restaurant rest, String args)
	{
		try
		{
			FileOutputStream outFile = new FileOutputStream(args);
			ObjectOutputStream outObject = new ObjectOutputStream(outFile);
			outObject.writeObject(rest);
			outObject.close();
			outFile.close();
			System.out.println("Data serialized successfully");
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
	}
	
	public static Restaurant deserialize(String args)
	{
		Restaurant rest = null;
		try
		{
			FileInputStream inFile = new FileInputStream(args);
			ObjectInputStream inObject = new ObjectInputStream(inFile);
			rest = (Restaurant) inObject.readObject();
			inObject.close();
			inFile.close();
			System.out.println("Data loaded successfully");
			return rest;
		}
		catch(IOException i)
		{
			System.out.println(i);
			rest = new Restaurant();
			serialize(rest, args);
			return rest;
		}
		catch(ClassNotFoundException c)
		{
			System.out.println("Class not found");
			c.printStackTrace();
			return rest = new Restaurant();
		}
	}
}
