package model.reservation;

import java.util.Date;

import model.hotel.Hotel;
import model.hotel.RoomType;
import model.user.Customer;

public class Reservation {
	
	String id;
	RoomType roomType;
	Customer customer;
	Hotel hotel;
	Date checkInDate, checkOutDate;
	int roomAmount;
	ReservationState state;
	PaymentMethod paymentMethod;
	
	public Reservation(String id, RoomType roomType, Customer customer, Hotel hotel, Date checkInDate,
			Date checkOutDate, int roomAmount, ReservationState state, PaymentMethod paymentMethod) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.customer = customer;
		this.hotel = hotel;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomAmount = roomAmount;
		this.state = state;
		this.paymentMethod = paymentMethod;
	}

	public String getId() {
		return id;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getRoomAmount() {
		return roomAmount;
	}

	public void setRoomAmount(int roomAmount) {
		this.roomAmount = roomAmount;
	}

	public ReservationState getState() {
		return state;
	}

	public void setState(ReservationState state) {
		this.state = state;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
