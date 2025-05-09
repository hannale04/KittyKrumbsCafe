package gameobjects;

import java.util.ArrayList;
import java.util.Random;

import database.Inventory;
import interfaces.KitchenScreen;
import gameobjects.Order;
import interfaces.MainScreen;

public class Order {
	private int numItems; //Between 1-5 items
	private int orderCost;
	private ArrayList<MenuItem> order = new ArrayList<>();
	
	//Constructor
	public Order(Inventory inventory) {
		Random random = new Random();
		this.numItems = random.nextInt(5) + 1;
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
				sb.append("1 " + item.getName() + " \n");
		    } else {
		    	sb.append("null item");
		    }
		}
		return sb.toString();
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	
	//Calculate total by combining item prices
	public int getOrderCost() {
		return orderCost;
	}
	
	public String toStringKitchen(int orderTime) {
		StringBuilder sb = new StringBuilder();
		
		for(MenuItem item : order) {
			if(item != null) {
				sb.append(item.getName())
				  .append(" (").append(item.getPrice()).append(" coins)\n");
		    } else {
		    	sb.append("null item");
		    }
		}
		sb.append("\nOrder Time: ").append(orderTime).append(" seconds\n");
		sb.append("Total: ").append(orderCost).append(" coins");		return sb.toString();
	}
	
}
