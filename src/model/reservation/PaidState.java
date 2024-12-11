package model.reservation;

public class PaidState implements ReservationState{

	@Override
	public ReservationState handleStatus() {
		System.out.println("Reservation has been Paid!");
		return this;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Paid";
	}
	
}
