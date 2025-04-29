package testing;

import gameobjects.Bar;
import gameobjects.MenuItem;
import database.Inventory;
import database.Recipe;
import gameobjects.Order;
import java.util.ArrayList;

public class BarTest {
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		Bar bar = new Bar();
		Order order = new Order(3, inventory);
		bar.viewOrder(order);
		System.out.println("Order: " + order);
		
		ArrayList<MenuItem> orderItems = order.getOrder();
		if (!orderItems.isEmpty()) {
			MenuItem firstItem = orderItems.get(0);
			String menuItemName = firstItem.getName();
			
			Recipe recipe = new Recipe();
			bar.viewRecipe(recipe, menuItemName);
		
			bar.selectIngredients(recipe, menuItemName);
		} else {
			System.out.println("Order is empty!");
		}
	}
}
