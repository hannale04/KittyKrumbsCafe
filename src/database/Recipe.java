package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gameobjects.Ingredient;
import gameobjects.MenuItem;

public class Recipe {
	//Using map creates a clean list of the ingredients
	private Map<String, ArrayList<String>> recipeMap;

	public Recipe() {
		this.recipeMap = new HashMap<>();
		loadRecipes();
	}
	
	private void loadRecipes() {
		String query = "SELECT menu_items.name AS item_name, ingredients.name AS ingredient_name " + 
					   "FROM recipes " + "JOIN menu_items ON recipes.menu_item_id = menu_items.id " + 
					   "JOIN ingredients ON recipes.ingredient_id = ingredients.id";
		
		try(DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query)) {
					
			while(resultSet.next()) {
				String menuItemName = resultSet.getString("item_name");
				String ingredientName = resultSet.getString("ingredient_name");
				
				if(!recipeMap.containsKey(menuItemName)) {
					recipeMap.put(menuItemName, new ArrayList<>());
				}
				recipeMap.get(menuItemName).add(ingredientName);
			}
				
			System.out.println("Recipes loaded successfully");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getRecipeForMenuItem(String menuItemName) {
		return recipeMap.getOrDefault(menuItemName, new ArrayList<String>());
	}
	
}
