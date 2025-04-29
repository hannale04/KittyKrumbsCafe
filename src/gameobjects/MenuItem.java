package gameobjects;

public class MenuItem {
	private String name;
	private int price;
	private String category;
	private String description;
	
	public MenuItem(String name, int price, String category, String description) {
		this.name = name;
		this.price = price;
		this.category = category;
		this.description = description;		
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

}
