/**
 * 
 * @author Xinyi OUYANG / 20030865 / scyyo1
 * 
 * Order [Price, Amount, ID]
 */

public class Order {
    private double Price;
    private int Amount;
    private int ID;
    
    public Order(double Price, int Amount, int ID){
    	this.setPrice(Price);
    	this.setAmount(Amount);
    	this.setID(ID);
    }
    
    public double getPrice() {
        return Price;
    }
    
    public void setPrice(double Price) {
    	this.Price = (double)Math.round(Price*100)/100;
    	//this.Price = Price;
    	
    }

    public int getAmount() {
        return Amount;
    }
        
    public void setAmount(int Amount) {
        this.Amount = Amount;
    }
   
    public int getID() {
        return ID;
    }
        
    public void setID(int ID) {
        this.ID = ID;
    }
   
    public String toString() {
    	return "{￥" + Price + "," + Amount +"}";
    }
      
}