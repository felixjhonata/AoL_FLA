package model.reservation;

import model.user.User;

public class Card implements PaymentMethod{

	@Override
	public boolean pay(User user, double amount) {
		System.out.println("Paid with " + this.toString() + " with total: " + amount);
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Card";
	}

}
