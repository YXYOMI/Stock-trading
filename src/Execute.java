import java.util.ArrayList;

/**
 * 
 * @author Xinyi OUYANG / 20030865 / scyyo1
 * this class is used to execute the orders
 */

public class Execute {
	
	ArrayList<Order> EX_BUY;
	ArrayList<Order> EX_SELL;
	
	
    public Execute() {
    	this.EX_BUY = new ArrayList<Order>();
    	this.EX_SELL = new ArrayList<Order>();
    }
    
    
    public void ExecuteOrder(BuildPQ pq_buy, BuildPQ pq_sell) {
    	
    	int exAmount; // the executed amount
    	
    	for ( int i = 0; i < pq_buy.orders.size(); i++) { //traverse the buy-orders priority queue
    	
    		for ( int j = 0; j < pq_sell.orders.size(); j++) {	//traverse the sell-orders priority queue
    			
    		    //if the price of buy order is not smaller than the sell order, compare the amount
    			if ( pq_buy.orders.get(i).getPrice() >= pq_sell.orders.get(j).getPrice() ) {
    			    //if the amount in buy-orders equals to the sell order, remove the two orders in their priority queue and jump out of this circulation
    				if ( pq_buy.orders.get(i).getAmount() == pq_sell.orders.get(j).getAmount() ) {
    				   
    					exAmount = pq_buy.orders.get(i).getAmount();  // the executed amount is the buy-orders amount or the sell-orders amount(in this case, equals)
    					
    					//check whether this order is in the executed-buy-order or in the executed-sell-order list
    					checkID ( pq_buy.orders.get(i), EX_BUY, exAmount ); 
    		            checkID ( pq_sell.orders.get(j), EX_SELL, exAmount ); //check whether this order is in the executed-sell-order list
    		            
    		            //remove the executed order
    					pq_buy.orders.remove(i); 
    					pq_sell.orders.remove(j);
    					
    					// get the previous index because of the remove
    					i--; 
    					j--;
    					break;
    				}
    				
    				/* 
    				 * if the amount in buy order is larger than the sell order, 
    				 * change the amount of the buy order to the difference between two amount and remove the sell order, 
    				 * then jump to the next circulation
    				 */
    				if ( pq_buy.orders.get(i).getAmount() > pq_sell.orders.get(j).getAmount()) {
    					
    					exAmount = pq_sell.orders.get(j).getAmount(); //executed amount is the smaller one(sell-orders amount)
    					
    					//check whether this order is in the executed-buy-order or in the executed-sell-order list
    					checkID ( pq_buy.orders.get(i), EX_BUY, exAmount );
    		            checkID ( pq_sell.orders.get(j), EX_SELL, exAmount );
    		            
    		            //set the amount of this buy-order to the difference between the amount of buy-order and sell-order
    		            pq_buy.orders.get(i).setAmount(pq_buy.orders.get(i).getAmount() - pq_sell.orders.get(j).getAmount());
    		            
    		            //remove the executed order
    				    pq_sell.orders.remove(j);
    				    
    				    //get the previous index because of the remove
    				    j--;
    				    continue;
    				}
    				
    				else  {
    					exAmount = pq_buy.orders.get(i).getAmount(); //executed amount is the smaller one(buy-orders amount)
    					
    					//check whether this order is in the executed-buy-order or in the executed-sell-order list
    					checkID ( pq_buy.orders.get(i), EX_BUY, exAmount ) ;
    		            checkID ( pq_sell.orders.get(j), EX_SELL, exAmount );
    		            
    		            //set the amount of this buy-order to the difference between the amount of buy-order and sell-order
    		            pq_sell.orders.get(j).setAmount(pq_sell.orders.get(j).getAmount() - pq_buy.orders.get(i).getAmount());
    		            
    		          //remove the executed order
    				    pq_buy.orders.remove(i);
    				    
    				  //get the previous index because of the remove
    				    i--;
    				    break;
    				}
    			}
    			
    		    else 
    		    	break;
    		}		
    	}
    }
    
    
    /*
    * if the order is in the executed-orders list, change the amount in it (add the new executed amount to the previous one)
    * else add the order with its executed amount into the executed-orders list
    */
    public void checkID ( Order order, ArrayList<Order> exOrders, int exAmount ) {

    	
    	if ( exOrders.size() != 0 ) {
    		for ( int i = 0; i < exOrders.size(); i++ ) {
    			if ( order.getID() == exOrders.get(i).getID() ) {
    			    exOrders.get(i).setAmount( exOrders.get(i).getAmount() + exAmount );
    			    break;
    			}
    		    else if ( i == (exOrders.size() - 1 )){
    		    	Order newOrder = new Order(order.getPrice(), exAmount, order.getID());
    		    	exOrders.add(newOrder);
    		    	break;
    		    }
    	    }	
    	}
    	
    	// if the executed-orders list is empty, add the order into the list
    	else{
    		Order newOrder = new Order(order.getPrice(), exAmount, order.getID());
			exOrders.add(newOrder);
    	}
    }
    
    
    /*
     * after execute the order, 
     * print the PQ_BUY, PQ_SELL, cut-off BUY price, cut-off SELL price, EX_BUY, EX_SELL
     */
    public void display(BuildPQ pq_buy, BuildPQ pq_sell) {
    	
    	System.out.println("After execute : ");
    	System.out.println("PQ_BUY  = " + pq_buy.orders);
    	System.out.printf("PQ_SELL = " + pq_sell.orders + "\n\n");
    	
    	if(EX_BUY.size() != 0 && EX_SELL.size() != 0) {
    		
    		//the cut-off price is the price of the last order in the executed-orders list
    	double cutOffBuy = EX_BUY.get(EX_BUY.size() - 1).getPrice();
		double cutOffSell = EX_SELL.get(EX_SELL.size() - 1).getPrice();
		
		System.out.println("cut-off BUY price : " + cutOffBuy);
    	System.out.printf("cut-off SELL price : " + cutOffSell + "\n\n");
    	
		System.out.println("EX_BUY = " + EX_BUY);
    	System.out.println("EX_SELL = " + EX_SELL);
    	
    	}else {
    		System.out.println("There is no executed order.");
    	}
    	
		
    }
    
}
