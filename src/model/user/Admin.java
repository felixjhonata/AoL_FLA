package model.user;

import model.hotel.Hotel;

public class Admin extends User{
	private Hotel hotel;

	public Admin(String id, String name, String phoneNo, String email, String password) {
		super(id, name, phoneNo, email, password);
		this.hotel = null;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
