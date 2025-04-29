//Bank holds player currency (coins)

package gameobjects;

//import gamecharacters.Customer;
//import gameobjects.Order;
//import gameobjects.Shop;

public class Bank {
	private int bank;

	public Bank(){
		this.bank = 0; //Initiate bank to 0
	}

	//Update bank

	public void increaseBank(Order order){
		int orderCost = order.getOrderCost();
		bank = bank + orderCost; //Increase bank when customer pays
		//System.out.println("+" + totalOrderCost + " coins to bank! Current balance: " + bank + " coins"); //Display text
		}
	
	/* Use when shop class is complete
	public void decreaseBank(Shop shop) {
		int amountSpent = shop.getAmountSpent();
		bank = bank - amountSpent; //Decrease bank when player makes a purchase
		//System.out.println("-" + amountSpent + " coins to bank! Current balance: " + bank + " coins");	
	}
		*/
	
	public int getBank() {
		return bank;
	}

}	