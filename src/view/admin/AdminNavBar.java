package view.admin;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.LoginPage;
import view.Main;

public class AdminNavBar extends HBox{

	private Button hotelFacility, roomType, logoutBtn, reservation;
	
	private void init() {
		reservation = new Button("Reservation");
		hotelFacility = new Button("Hotel Facility");
		roomType = new Button("Room Type");
		logoutBtn = new Button("Logout");
	}
	
	private void setPosition() {
		this.getChildren().addAll(hotelFacility, roomType, reservation, logoutBtn);
		this.setSpacing(5);
	}
	
	private void setFunctionality() {
		hotelFacility.setOnAction(e -> {
			new HotelFacilitiesPage();
			return;
		});
		
		logoutBtn.setOnAction(e -> {
			Main.setUser(null);
			new LoginPage();
			return;
		});
		
		roomType.setOnAction(e -> {
			new RoomTypePage();
		});
		
		reservation.setOnAction(e -> {
			new ReservationPage();
		});
	}
	
	public AdminNavBar() {
		super();
		
		init();
		setPosition();
		setFunctionality();
		
	}
	
}
