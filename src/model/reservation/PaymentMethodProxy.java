package model.reservation;

import model.user.Customer;
import model.user.User;

public class PaymentMethodProxy implements PaymentMethod{
	
	private PaymentMethod paymentMethod;

	public PaymentMethodProxy(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public boolean pay(User user, double amount) {
		if(user instanceof Customer && paymentMethod instanceof Cash) {
			return false;
		}
		
		return paymentMethod.pay(user, amount);
	}
	
}
