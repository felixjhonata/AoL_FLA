package view.admin;

import javafx.scene.control.TableView;
import model.reservation.Reservation;

public class ReservationPage extends AdminPageTemplate{
	
	private TableView<Reservation> reservationTV;

	@Override
	protected void init() {
		reservationTV = new TableView<Reservation>();		
	}

	@Override
	protected void setPosition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setFunctionality() {
		// TODO Auto-generated method stub
		
	}

}
