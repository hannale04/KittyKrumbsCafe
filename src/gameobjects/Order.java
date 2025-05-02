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
		boolean exists;
		MenuItem menuItem;
		for (int i = 0; i < numItems; i++) {
			menuItem = inventory.getRandomMenuItem(); 
			while(true) {
				exists = order.contains(menuItem);
				if (! exists) {
					order.add(menuItem);
					orderCost+= menuItem.getPrice();
					break;
				}
				else {
					menuItem = inventory.getRandomMenuItem(); 
				}
			}
		}
	}
	
	public ArrayList<MenuItem> getOrder(){
		return order;
	}
	
	public ArrayList<Integer> getOrderIds(){
		ArrayList<Integer> ids = new ArrayList<>();
		for(int i=0; i<order.size(); i++) {
			ids.add(order.get(i).getId());
		}
		return ids;
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
