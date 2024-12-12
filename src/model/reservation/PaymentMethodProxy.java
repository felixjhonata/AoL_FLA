package model.reservation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("DENIED ACCESS");
			alert.setHeaderText("Cash Payment Denied!");
			alert.setContentText("As a customer you can't directly pay the reservation. It can only be done by the administrator of the Hotel.");
			alert.showAndWait();
			return false;
		}
		
		return paymentMethod.pay(user, amount);
	}
	
}
