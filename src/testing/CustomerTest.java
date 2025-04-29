package testing;

import database.Inventory;
import gamecharacters.Customer;
import gameobjects.Order;

public class CustomerTest {
	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		Customer customer = new Customer(3, inventory);
		Order order = customer.placeOrder();
		System.out.println(order);
	}
}
