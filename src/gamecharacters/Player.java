package gamecharacters;

import gameobjects.Bank;

public class Player {
	//Player attributes
	String name;
	String order;
	String customerName;
	private Bank bank;

	//Constructor
	public Player(String name, int bank) {
		this.name = name;
		this.bank = new Bank();
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public int getBank() {
		return bank.getBank();
	}
	
	public String getOrder() {
		return order;
	}
	
	
	public void prepOrder() {
		//Get ingredients
	}
	
	public void serveOrder() {
		
	}
}