package gameobjects;

public class MenuItem {
	private int id;
	private String name;
	private int price;
	private String category;
	
	public MenuItem(int id, String name, String category, int price) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getPrice() {
		return price;
	}

	//To-string that returns name
	public String toString() {
		return name;
	}
}
