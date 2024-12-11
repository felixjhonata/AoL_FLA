package model.reservation;

import model.user.User;

public class Card implements PaymentMethod{

	@Override
	public boolean pay(User user, double amount) {
		// TODO: Make Card Payment Logic here
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Card";
	}

}
