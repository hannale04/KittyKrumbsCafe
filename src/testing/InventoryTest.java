package testing;

import database.Inventory;
import gameobjects.MenuItem;

public class InventoryTest {
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		MenuItem randomMenuItem = inventory.getRandomMenuItem();
	}
}

