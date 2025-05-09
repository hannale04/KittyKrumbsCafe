//Bank holds player currency (coins)

package gameobjects;

import interfaces.KitchenScreen;

public class Bank {
	private int bank;

	public Bank(){
		this.bank = 0;
	}

	public void increaseBank(KitchenScreen kitchenScreen){
		int coinsEarned = kitchenScreen.getCoinsEarned();
		bank = bank + coinsEarned; //Increase bank when customer pays
		System.out.println("+" + coinsEarned + " coins to bank! Current balance: " + bank + " coins"); //Display text
		}
	
	public int getBank() {
		return bank;
	}

}	