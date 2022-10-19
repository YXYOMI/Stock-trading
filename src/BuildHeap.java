import java.util.ArrayList;

/**
 * @author Xinyi OUYANG / 20030865 / scyyo1
 *
 */

public class BuildHeap {
	public ArrayList<Order> orders;
	
	
    public BuildHeap(ArrayList<Order> orders){
    	this.orders = new ArrayList<Order>();
    	this.setOrders(orders);
    }
    
    
    public void heap(int size) {
    	for(int i = (int)Math.floor(size/2)-1; i>=0; i--) {
    		sorted(i,size);
    	}
    }
    
    
    public void sorted(int currentIndex, int size) {
    	
    	int leftChild = 2 * currentIndex + 1; //get the index of left child
    	int rightChild = 2 * currentIndex +2; // get the index of right child
    	int minIndex = currentIndex; //default the current node is the min node
    	
    	//if left child is smaller than current node, change the minIndex to left child
    	if(leftChild < size && orders.get(leftChild).getPrice() < orders.get(minIndex).getPrice()) {
    		minIndex = leftChild; 
    	}
    	
    	//if right child is smaller than current node, change the minIndex to right child
    	if(rightChild < size && orders.get(rightChild).getPrice() < orders.get(minIndex).getPrice()) {
    		minIndex = rightChild; 
    	}
    	
    	//if the minIndex is not the currentIndex, exchange the current node and the min node, then adjust the heap again
    	if(minIndex != currentIndex) { 
    		swap(currentIndex, minIndex);
    		sorted(minIndex, size);
    	}
    }
    
    
    public void swap(int i, int j) { 
    //exchange node i and node j
        Order temp = orders.get(i);
        orders.set(i, orders.get(j));
        orders.set(j, temp);
    }
    	
    
    public ArrayList<Order> getOrders() {
        return orders;
    }
        
    
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
    	
}