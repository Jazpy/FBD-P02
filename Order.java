package Taqueria;

public class Order {
	private int clientID;
	private int dishID;
	private int quantity;
	private int total;
	
	public Order(int clientID, int dishID, int quantity, int total) {
		this.clientID= clientID;
		this.dishID = dishID;
		this.quantity = quantity;
		this.total = total;
	}
	
	public int getClientID() {
		return this.clientID;
	}
	
	public int getDishID() {
		return this.dishID;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public int getTotal() {
		return this.total;
	}
	
	public String toString() {
		return this.clientID + "|" + this.dishID + "|"
				+ this.quantity + "|" + this.total;
	}
}
