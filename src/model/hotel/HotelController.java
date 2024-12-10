package model.hotel;

import database.DBSingleton;
import database.Database;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import model.user.Admin;

public class HotelController {
	
	Database db;
	
	public HotelController() {
		db = DBSingleton.getInstance();
	}
	
	public Hotel createHotel(TextField nameTF, TextField addressTF, Admin admin, Label errorLbl) {
		
		errorLbl.setText("");
		
		String name = nameTF.getText();
		if(name.trim().isEmpty()) {
			errorLbl.setText("Name Field must be filled!");
			return null;
		}
		
		String address = addressTF.getText();
		if(address.trim().isEmpty()) {
			errorLbl.setText("Address Field must be filled!");
			return null;
		}
		
		Hotel hotel = HotelFactory.createHotel(name, address);
		hotel = db.addHotel(hotel);
		admin = db.addAdminHotel(admin, hotel);
		
		if(hotel == null || admin == null) {
			errorLbl.setText("Failed to insert to database!");
			return null;
		}
		
		nameTF.setText("");
		addressTF.setText("");
		return hotel;
	};
	
	public String addFacility(TextField nameTF, Admin admin, Label errorLbl) {
		
		errorLbl.setText("");
		
		String facility = nameTF.getText();
		if(facility.trim().isEmpty()) {
			errorLbl.setText("Name Field must be filled!");
			return null;
		}
		
		String hotelID = admin.getHotel().getId();
		
		facility = db.addHotelFacility(hotelID, facility);
		
		if(facility == null) {
			errorLbl.setText("Failed to insert to Database!");
		} else {
			admin.getHotel().getFacilities().add(facility);
			nameTF.setText("");
			errorLbl.setText("");
		}
		
		return facility;
	}
	
	public boolean removeFacility(String name, Admin admin, Label errorLbl) {
		
		errorLbl.setText("");
		
		if(name == null) {
			errorLbl.setText("No item is selected");
			return false;
		}
		
		String hotelID = admin.getHotel().getId();
		
		boolean isSuccessful = db.removeFacility(hotelID, name);
		
		if(!isSuccessful) {
			errorLbl.setText("Error while deleting from Database!");
		}
		
		return isSuccessful;
	}
	
	public RoomType addRoomType(Admin admin, TextField nameTF, TextField priceTF, TextField descriptionTF, CheckBox isNonSmokingCB, Spinner<Integer> roomAmountS, Label errorLbl) {
		
		errorLbl.setText("");
		
		RoomType roomType = null;
		
		Hotel hotel = admin.getHotel();
		
		String name = nameTF.getText();
		if(name.trim().isEmpty()) {
			errorLbl.setText("Name Field must be filled!");
			return roomType;
		}
		
		if(priceTF.getText().trim().isEmpty()) {
			errorLbl.setText("Price Field must be filled!");
			return roomType;
		}
		
		double price;
		try {
			price = Double.valueOf(priceTF.getText());
		} catch (Exception e) {
			errorLbl.setText("Invalid input on Price Field!");
			return roomType;
		}
		
		String description = descriptionTF.getText();
		if(description.trim().isEmpty()) {
			errorLbl.setText("Description Field must be filled!");
			return roomType;
		}
		
		boolean isNonSmoking = isNonSmokingCB.isSelected();
		
		int roomAmount = roomAmountS.getValue();
		
		roomType = new RoomType(name, price, description, isNonSmoking, roomAmount);
		
		if(!db.addRoomType(roomType, hotel)) {
			errorLbl.setText("Failed to insert to database!");
			return null;
		}
		
		nameTF.setText("");
		priceTF.setText("");
		descriptionTF.setText("");
		isNonSmokingCB.setSelected(false);
		roomAmountS.getValueFactory().setValue(1);
		
		return roomType;
		
	}
	
	public boolean removeRoomType(Admin admin, RoomType roomType, Label errorLbl) {
		errorLbl.setText("");
		
		if(roomType == null) {
			errorLbl.setText("No item is selected");
			return false;
		}
		
		String hotelID = admin.getHotel().getId();
		
		boolean isSuccessful = db.removeRoomType(hotelID, roomType.getName());
		
		if(!isSuccessful) {
			errorLbl.setText("Error while deleting from Database!");
		}
		
		return isSuccessful;
	}

}
