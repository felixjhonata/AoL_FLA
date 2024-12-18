package model.reservation;


import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import database.DBSingleton;
import database.Database;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import model.hotel.Hotel;
import model.hotel.RoomType;
import model.user.Customer;
import model.user.User;

public class ReservationController {
	
	Database db;
	
	public ReservationController() {
		db = DBSingleton.getInstance();
	}
	
	public List<Reservation> getReservations(Hotel hotel) {
		
		return db.getReservationByHotel(hotel);
		
	}
	
	public List<Reservation> getReservations(Customer customer) {
		
		return db.getReservationByCustomer(customer);
		
	}
	
	private String generateID() {
		return String.format("RE%03d", new Random().nextInt(1000));
	}
	
	public Reservation addReservation(Hotel hotel, ComboBox<String> customerCB, ComboBox<String> roomCB, 
			DatePicker checkInDateDP, DatePicker checkOutDateDP, Spinner<Integer> roomAmountS, ComboBox<String> paymentCB, Label errorLbl) {
		Reservation reservation = null;
		
		errorLbl.setText("");
		
		String customerID = customerCB.getValue();
		if(customerID == null) {
			errorLbl.setText("Customer box must be picked!");
			return reservation;
		}
		
		Customer customer = db.getCustomerByID(customerID);
		
		String roomTypeName = roomCB.getValue();
		if(roomTypeName == null) {
			errorLbl.setText("Room Type box must be picked!");
			return reservation;
		}
		
		RoomType roomType = db.getRoomType(hotel.getId(), roomTypeName);
		
		Date checkInDate = Date.from(checkInDateDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date checkOutDate = Date.from(checkOutDateDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		if(checkInDate.compareTo(checkOutDate) >= 0) {
			errorLbl.setText("Check In date must be after Check out date!");
			return reservation;
		}
		
		int roomAmount = roomAmountS.getValue();
		
		String paymentMethodName = paymentCB.getValue();
		
		String id = generateID();
		
		PaymentMethod paymentMethod;
		
		switch(paymentMethodName) {
		case "Cash":
			paymentMethod = new Cash();
			break;
		case "Card":
			paymentMethod = new Card();
			break;
		default:
			paymentMethod = new EWallet();
			break;
		}
		
		reservation = new Reservation(id, roomType, customer, hotel, checkInDate, checkOutDate, roomAmount, new UnPaidState(), paymentMethod);
		
		reservation = db.addReservation(reservation);
		
		if(reservation == null) {
			errorLbl.setText("Failed to insert to Database!");
		}
		
		return reservation;
	}
	
	public Reservation addReservation(Hotel hotel, Customer customer, ComboBox<String> roomCB, 
			DatePicker checkInDateDP, DatePicker checkOutDateDP, Spinner<Integer> roomAmountS, ComboBox<String> paymentCB, Label errorLbl) {
		Reservation reservation = null;
		
		errorLbl.setText("");
		
		String roomTypeName = roomCB.getValue();
		if(roomTypeName == null) {
			errorLbl.setText("Room Type box must be picked!");
			return reservation;
		}
		
		RoomType roomType = db.getRoomType(hotel.getId(), roomTypeName);
		
		Date checkInDate = Date.from(checkInDateDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date checkOutDate = Date.from(checkOutDateDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		int roomAmount = roomAmountS.getValue();
		
		String paymentMethodName = paymentCB.getValue();
		
		String id = generateID();
		
		PaymentMethod paymentMethod;
		
		switch(paymentMethodName) {
		case "Cash":
			paymentMethod = new Cash();
			break;
		case "Card":
			paymentMethod = new Card();
			break;
		default:
			paymentMethod = new EWallet();
			break;
		}
		
		reservation = new Reservation(id, roomType, customer, hotel, checkInDate, checkOutDate, roomAmount, new UnPaidState(), paymentMethod);
		
		reservation = db.addReservation(reservation);
		
		if(reservation == null) {
			errorLbl.setText("Failed to insert to Database!");
		}
		
		return reservation;
	}
	
	public boolean deleteReservation(Reservation reservation, Label errorLbl) {
		
		if(reservation == null) {
			errorLbl.setText("No item selected!");
			return false;
		}
		
		return db.removeReservation(reservation.getId());
		
	}
	
	public boolean payReservation(Reservation reservation, User user, Label errorLbl) {
		
		if(reservation == null) {
			errorLbl.setText("No item selected!");
			return false;
		}
		
		double amount = reservation.getRoomAmount() * reservation.getRoomType().getPrice();
		PaymentMethod paymentMethod = reservation.getPaymentMethod();
		
		PaymentMethodProxy proxy = new PaymentMethodProxy(paymentMethod);
		boolean isSuccessful = proxy.pay(user, amount);
		
		if(isSuccessful) {
			db.updateReservationStatus(reservation.getId(), reservation.getState().handleStatus());
		}
		
		return isSuccessful;
	}

}
