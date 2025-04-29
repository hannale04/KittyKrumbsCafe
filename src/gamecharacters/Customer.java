package gamecharacters;

import database.Inventory;
import gameobjects.Order;

public class Customer {
	private int numItems;
	private Inventory inventory;
	
	public Customer(int numItems, Inventory inventory) {
		this.numItems = numItems;
		this.inventory = inventory;
	}
	
	public Order placeOrder() {
		return new Order(numItems, inventory);
	}
}