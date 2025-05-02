//Where orders are fulfilled
package gameobjects;
import gameobjects.Order;

import java.util.Scanner;

import java.util.ArrayList;

import database.Inventory;
import gameobjects.MenuItem;

public class Bar {
	private ArrayList<MenuItem> order;
	private Inventory inventory;
	private MenuItem menuItem;
	private Ingredient ingredient;
	private ArrayList<Ingredient> selectedIngredients;
	private ArrayList<Ingredient> ingredientsNeeded;
	
	public Bar() {
	
	}	

private void viewOrder(Order order) {
	this.order = order.getOrder();
}

private void fulfillOrder(String menuItemName) {
	
}

private void serveOrder() {
	
	}

private void resetBar() {
	
	}

}