package gameobjects;

public class MenuItem {
	private int id;
	private String name;
	private int price;
	private String category;
	private String description;
	
	public MenuItem(int id, String name, int price, String category, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.description = description;		
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	//To-string that returns name
	public String toString() {
		return name;
	}
}
