import java.util.ArrayList;

import java.util.Collections;

/**
 * @author Xinyi OUYANG / 20030865 / scyyo1
 * 
 * this class is used to build the piority queue
 *
 */

public class BuildPQ {
	public ArrayList<Order> orders ;
	public BuildHeap build;
	
	
    public BuildPQ(ArrayList<Order> orders) {
    	
    	this.orders = new ArrayList<Order>();
    	build = new BuildHeap(orders);
    	this.setOrders(orders);
    }
    
    
    public void PQ() {//build a priority queue
    	
    	//build max-top heap
    	build.heap(orders.size());
    	int size = orders.size();
    	
    	//exchange the top and the tail of the heap and reset the heap
    	for(int i = size - 1; i>0; i--) {
    		build.swap(0, i);
    		size--;
    		build.sorted(0, size);
    		
        }
    }
    
    
    public void PQ_BUY() { //get the max-oriented priority queue
    	
    	PQ();
    	//if has some equal price, then sort by amount (from small to large)
    	sortAmount(); 
    	
    }
    
    
    public void PQ_SELL() { //get the min-oriented priority queue
    	
    	PQ();
    	// the min-oriented priority queue is the reversed max-oriented one
    	Collections.reverse(orders); 
    	// if has some equal price, then sort by amount (from small to large)
    	sortAmount(); 
    	
    }
    
    
    public void InsertBuyOrder(Order order) {
    	
    	//insert a new buy order into the arraylist and the priority queue
    	orders.add(order);
    	PQ_BUY();
    	
    }
    
    
    public void InsertSellOrder(Order order) {
    	
    	//insert a new sell order into the arraylist and the priority queue
    	orders.add(order);
    	PQ_SELL();
    	
    }
    
    
    public void sortAmount() {
    	
    	// if the price is same, sort the orders by amount
    	for(int i = 0; i<orders.size(); i++) {
    		
    		for(int j = 0; j<orders.size(); j++) {
    			
    			if ((orders.get(i).getPrice() == orders.get(j).getPrice())){
    				
    				if(orders.get(i).getAmount() < orders.get(j).getAmount()) {
    					
    					Order temp = orders.get(j);
    					orders.set(j, orders.get(i));
    					orders.set(i, temp);				
    				}
    		    }
    		}
    	}
    }
    
    
    public ArrayList<Order> getOrders() {
        return orders;
    }
    
    
    public void setOrders(ArrayList<Order> orders) {
    	this.orders = orders;
    }
}
