package model.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HotelFactory {
	
	private HotelFactory() {}
	
	private static String createID() {
		return String.format("HO%03d", new Random().nextInt(1000));
	}
	
	public static Hotel createHotel(String name, String address) {
		String id = createID();
		List<RoomType> roomTypes = new ArrayList<RoomType>();
		List<String> facilities = new ArrayList<String>();
		double rating = 0;
		
		return new Hotel(id, name, address, rating, facilities, roomTypes);
	}

}
