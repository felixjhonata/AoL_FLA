package model.reservation;

import model.user.User;

public class Cash implements PaymentMethod{

	@Override
	public boolean pay(User user, double amount) {
		// TODO: Make Cash Payment logic here
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Cash";
	}

}
