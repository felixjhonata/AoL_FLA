package model.reservation;

public class UnPaidState implements ReservationState{

	@Override
	public ReservationState handleStatus() {
		System.out.println("Change status to Paid!");
		return new PaidState();
	}
	
	@Override
	public String toString() {
		return "Unpaid";
	}
}
