package model.reservation;

import java.time.LocalDate;

import model.hotel.Hotel;
import model.hotel.RoomType;
import model.user.Customer;

public class Reservation {
	
	String id;
	RoomType roomType;
	Customer customer;
	Hotel hotel;
	LocalDate checkInDate, checkOutDate;
	int roomAmount;
	ReservationState state;
	
	public Reservation(String id, RoomType roomType, Customer customer, Hotel hotel, LocalDate checkInDate,
			LocalDate checkOutDate, int roomAmount, ReservationState state) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.customer = customer;
		this.hotel = hotel;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomAmount = roomAmount;
		this.state = state;
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

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
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

}
