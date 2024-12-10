package model.hotel;

public class RoomType {
	
	private String name;
	private double price;
	private String description;
	private boolean isNonSmoking;
	private int roomAmount;
	
	public RoomType(String name, double price, String description, boolean isNonSmoking, int roomAmount) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.isNonSmoking = isNonSmoking;
		this.roomAmount = roomAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isNonSmoking() {
		return isNonSmoking;
	}

	public void setNonSmoking(boolean isNonSmoking) {
		this.isNonSmoking = isNonSmoking;
	}

	public int getRoomAmount() {
		return roomAmount;
	}

	public void setRoomAmount(int roomAmount) {
		this.roomAmount = roomAmount;
	}

}
