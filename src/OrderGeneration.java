import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

/**
 * 
 * @author Xinyi OUYANG / 20030865 / scyyo1
 *
 * this class is used to generate a random order
 *
 */
public class OrderGeneration {
    public static int ID;
    int[] range;
    
	public OrderGeneration() {
		range = new int[2];
	}
	
	
	public double randomPrice(int min, int max) { 
		
		double ran = min+((max - min) * new Random().nextDouble());
		return ran;
		
	}
	
	
	public int randomAmount() {
		
		int ran = (int)(Math.random()*10 + 1) * 100;
		return ran;
		
	}
	
	
	public Order buyOrder() throws FileNotFoundException {
		
		//this array is used to save the range of the buy order. range[0] = min, range[1] = max.
		File file = new File();
		file.readRange(new FileReader("./src/inputFiles/rangeOfBuyPrice.txt"), range);
		Order order = generateOrder(range);
		return order;
		
	}
	
	
	public Order sellOrder() throws FileNotFoundException {
		
		//this array is used to save the range of the sell order. range[0] = min, range[1] = max.
		File file = new File();
		file.readRange(new FileReader("./src/inputFiles/rangeOfSellPrice.txt"), range);
		Order order = generateOrder(range);	
		return order;
		
	}	
	
	public Order generateOrder(int[] range) {
		double price = randomPrice(range[0],range[1]); 
		int amount = randomAmount();
		ID++;
		Order order = new Order(price, amount, ID); //generate the new sell order
		return order;
	}

}
