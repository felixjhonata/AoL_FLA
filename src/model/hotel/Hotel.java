package model.hotel;

import java.util.List;

public class Hotel {
	
	private String id;
	private String name;
	private String address;
	private double rating;
	private List<String> facilities;
	private List<RoomType> roomTypes;
	
	public Hotel(String id, String name, String address, double rating, List<String> facilities, List<RoomType> roomTypes) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.facilities = facilities;
		this.roomTypes = roomTypes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public List<String> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<String> facilities) {
		this.facilities = facilities;
	}
	
	public List<RoomType> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(List<RoomType> roomTypes) {
		this.roomTypes = roomTypes;
	}

	public String getId() {
		return id;
	}

}
