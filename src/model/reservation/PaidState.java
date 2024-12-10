package model.reservation;

public class PaidState implements ReservationState{

	@Override
	public ReservationState handleStatus() {
		System.out.println("Reservation has been Paid!");
		return this;
	}
	
}
