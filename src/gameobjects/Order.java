package gameobjects;

import java.util.ArrayList;

import database.Inventory;

public class Order {
	private int orderCost;
	private ArrayList<MenuItem> order = new ArrayList<>();
	
	//Constructor
	public Order(int numItems, Inventory inventory) {
		this.orderCost = 0;
		System.out.println("Creating order with " + numItems + " items...");
		
		for (int i = 0; i < numItems; i++) {
			MenuItem menuItem = inventory.getRandomMenuItem(); 
		if(menuItem != null) {
			order.add(menuItem);
			orderCost+= menuItem.getPrice();
		} else {
			System.out.println("Error: Random menu item is null");
			}
		}
	}
	public ArrayList<MenuItem> getOrder(){
		return order;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(MenuItem item : order) {
			if(item != null) {
				sb.append(item.getName() + " (").append(item.getPrice()).append(" coins").append(")\n");
		    } else {
		    	sb.append("null item");
		    }
		}
		sb.append("Total: ").append(orderCost).append(" coins");
		return sb.toString();
	}
	
	//Calculate total by combining item prices
	public int getOrderCost() {
		return orderCost;
	}
	
}
