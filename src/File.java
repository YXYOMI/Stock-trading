import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Xinyi OUYANG / 20030865 / scyyo1
 * 
 * this class is used to read file and save the orders from the file to the ArrayList<Order> or the array
 *
 */

public class File {
	public static int ID;
	
	
	public void readFile (FileReader filereader, ArrayList<Order> orders) {
		
        BufferedReader reader = null;
    	
    	try {
    		reader = new BufferedReader(filereader);
    		String tempString = null;
    		
    		while((tempString = reader.readLine()) != null) {
    			String[] newOrder = tempString.split(",");
    			double price = Double.parseDouble(newOrder[0]);
    			int amount = Integer.parseInt(newOrder[1]);
    			ID++;
    			Order order = new Order(price, amount, ID);
    			orders.add(order);
    		}
    		
    		reader.close();
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
	
	
	public void readRange(FileReader filereader, int[] range) {
		
        BufferedReader reader = null;
    	
    	try {
    		reader = new BufferedReader(filereader);
    		String tempString = null;
    		
    		while((tempString = reader.readLine()) != null) {
    			String[] ran = tempString.split(",");
    		    range[0] = Integer.parseInt(ran[0]);
    			range[1] = Integer.parseInt(ran[1]);
    		}
    		
    		reader.close();
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
	}
}
