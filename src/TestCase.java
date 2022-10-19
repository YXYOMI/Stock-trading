import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * 
 * @author Xinyi OUYANG / 20030865 / scyyo1
 * 
 * this class has two test cases. case1 get orders buy reading file, case2 get orders buy generating.
 */

public class TestCase {
	
    public TestCase() {}
    
    public void testCase1() throws FileNotFoundException {
    	
    	ArrayList<Order> buyOrders = new ArrayList<Order>();
    	ArrayList<Order> sellOrders = new ArrayList<Order>();
    	ArrayList<Order> insertBuyOrders = new ArrayList<Order>();
    	ArrayList<Order> insertSellOrders = new ArrayList<Order>();
    	
    	System.out.printf("Reading the file...\n\n");
    	
    	//read the orders file, and save the orders into the ArrayList<Order> buyOrders/sellOrders
    	File file = new File();
    	file.readFile(new FileReader("./src/inputFiles/BuyOrders.txt"), buyOrders);
    	file.readFile(new FileReader("./src/inputFiles/SellOrders.txt"), sellOrders);
    	
    	//build the priority queue
    	BuildPQ pq_buy = new BuildPQ(buyOrders);
    	pq_buy.PQ_BUY();
    	BuildPQ pq_sell = new BuildPQ(sellOrders);
    	pq_sell.PQ_SELL();
    	
    	System.out.println("Original priority queue : ");
    	System.out.println("PQ_BUY  = " + buyOrders);
    	System.out.printf("PQ_SELL = " + sellOrders + "\n\n");
    	
    	//read the insert file, and save the insert orders into the ArrayList<Order> insertBuyOrders/insertSellOrders
    	file.readFile(new FileReader("./src/inputFiles/InsertBuyOrders.txt"), insertBuyOrders);
    	file.readFile(new FileReader("./src/inputFiles/InsertSellOrders.txt"), insertSellOrders);
    	
    	//insert the orders into the priority queue from the ArrayList<Order> insertBuyOrders/insertSellOrders
    	for(int i = 0; i < insertBuyOrders.size(); i++) {
    		pq_buy.InsertBuyOrder(insertBuyOrders.get(i));
    	}
    	
    	for(int i = 0; i < insertSellOrders.size(); i++) {
    		pq_sell.InsertSellOrder(insertSellOrders.get(i));
    	}
    	
    	System.out.println("After insert :");
    	System.out.println("PQ_BUY  = " + buyOrders);
    	System.out.printf("PQ_SELL = " + sellOrders + "\n\n");
    	
    	//execute the orders and display
    	Execute test1 = new Execute();
    	test1.ExecuteOrder(pq_buy,pq_sell);
    	test1.display(pq_buy,pq_sell);
    	
    }
    
    
    public void testCase2() throws FileNotFoundException {
    	
    	ArrayList<Order> buyOrders = new ArrayList<Order>();
    	ArrayList<Order> sellOrders = new ArrayList<Order>();
    	
    	randomBuyOrders(buyOrders);
    	randomSellOrders(sellOrders);
    	
    	BuildPQ pq_buy = new BuildPQ(buyOrders);
    	pq_buy.PQ_BUY();
    	BuildPQ pq_sell = new BuildPQ(sellOrders);
    	pq_sell.PQ_SELL();
    	
    	System.out.println("Original priority queue : ");
    	System.out.println("PQ_BUY  = " + buyOrders);
    	System.out.printf("PQ_SELL = " + sellOrders + "\n\n");
    	
    	Execute test2 = new Execute();
    	test2.ExecuteOrder(pq_buy,pq_sell);
    	test2.display(pq_buy,pq_sell);
    	
    }
    
    
    public void randomBuyOrders (ArrayList<Order> orders) throws FileNotFoundException {   	
    	
    		OrderGeneration generate = new OrderGeneration();  		
    		
    		//generate 5 new buy orders and add them to the ArrayList
    		for(int i = 0; i<=5; i++) {
    			Order order = generate.buyOrder();
    			orders.add(order);
    	    }
    }
    
    public void randomSellOrders (ArrayList<Order> orders) throws FileNotFoundException {   	
    	
		    OrderGeneration generate = new OrderGeneration();  		
		
		    //generate 5 new sell orders and add them to the ArrayList
		    for(int i = 0; i<=5; i++) {
			    Order order = generate.sellOrder();
			    orders.add(order);
	        }
    }
    
}
