//Where orders are fulfilled
package gameobjects;
import gameobjects.Order;
import java.util.Scanner;

import java.util.ArrayList;

import database.Inventory;
import gameobjects.MenuItem;
import database.Recipe;

public class Bar {
	private ArrayList<MenuItem> order;
	private Inventory inventory;
	private MenuItem menuItem;
	private Ingredient ingredient;
	private ArrayList<Ingredient> selectedIngredients;
	private ArrayList<Ingredient> ingredientsNeeded;
	
	public Bar() {
	
	}	

public void viewOrder(Order order) {
	this.order = order.getOrder();
}

public void viewRecipe(Recipe recipe, String menuItemName) {
	ArrayList<String> ingredients = recipe.getRecipeForMenuItem(menuItemName);
	System.out.println("Recipe for " + menuItemName + ": " + ingredients);
}

public void selectIngredients(Recipe recipe, String menuItemName) {
	ArrayList<String> ingredientsNeeded = recipe.getRecipeForMenuItem(menuItemName);
	
	ArrayList<String> ingredientsSelected = new ArrayList<>();
		Ingredient ingredient = 
		
		System.out.println("Selected ingredients for " + menuItemName + ": " + ingredientsSelected);
}

private void serveOrder() {
	
	}

private void resetBar() {
	
	}

}