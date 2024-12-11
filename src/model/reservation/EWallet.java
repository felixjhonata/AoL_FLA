package model.reservation;

import model.user.User;

public class EWallet implements PaymentMethod{

	@Override
	public boolean pay(User user, double amount) {
		// TODO: Make E-Wallet payment logic here
		return false;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "E-Wallet";
	}

}
