package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import database.Inventory;

import gameobjects.MenuItem;
import javafx.scene.control.ComboBox;

public class Inventory {
	ArrayList<MenuItem> inventory = new ArrayList<>();
	ArrayList<MenuItem> food = new ArrayList<>();
	ArrayList<MenuItem> drinks = new ArrayList<>();
	ArrayList<MenuItem> specials = new ArrayList<>();
	
	public Inventory() {
		String query = "SELECT * FROM menu_items";
		
		try(DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query)) {
				
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
		        int price = resultSet.getInt("price");
		        String category = resultSet.getString("category");
		        String description = resultSet.getString("description");
	
		        MenuItem menuItem = new MenuItem(id, name, price, category, description);
		        inventory.add(menuItem);
		        switch(menuItem.getCategory()) {
			        case "Pastry" :
			        	food.add(menuItem);
			        	System.out.println("Menu item: " + menuItem.toString());
			        	break;
			        	
			        case "Drink" : 
			        	drinks.add(menuItem);
			        	System.out.println("drink Menu item: " + menuItem.toString());
			        	break;
			        case "Special" :
			        	specials.add(menuItem);
			        	break;
		        }
			}
			System.out.println("Inventory size: " + inventory.size());
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	public ArrayList<MenuItem> getInventory() {
		return inventory;
	}
	
	public ArrayList<MenuItem> getFood() {
		return food;
	}
	
	public ArrayList<MenuItem> getDrinks() {
		return drinks;
	}
	public ArrayList<MenuItem> getSpecials() {
		return specials;
	}
	
	public MenuItem getRandomMenuItem() {
		if(inventory.isEmpty()) {
			System.out.println("Inventory is empty!");
			return null;
		}
		Random randomMenuItem = new Random();
		int randomIndex = randomMenuItem.nextInt(inventory.size());
        return inventory.get(randomIndex);
	}	
	
	public void shuffle() {
		Collections.shuffle(food);
		Collections.shuffle(drinks);
		Collections.shuffle(specials);
	}
	
}