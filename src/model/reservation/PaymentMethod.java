package model.reservation;

import model.user.User;

public interface PaymentMethod {
	
	public boolean pay(User user, double amount);

}
