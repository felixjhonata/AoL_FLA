package view.admin;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.hotel.Hotel;
import model.hotel.HotelController;
import model.user.Admin;
import view.Main;

public class CompleteAccountPage {
	
	private Scene scene;
	private GridPane gridPane;
	private Label title, nameLbl, addressLbl, errorLbl;
	private TextField nameTF, addressTF;
	private Button addHotelBtn;
	private HotelController hc;
	private Admin admin;
	
	private void init() {
		hc = new HotelController();
		
		gridPane = new GridPane();
		
		title = new Label("Set Hotel");
		nameLbl = new Label("Name: ");
		addressLbl = new Label("Address: ");
		errorLbl = new Label();
		nameTF = new TextField();
		addressTF = new TextField();
		addHotelBtn = new Button("Add Hotel");
		
		scene = new Scene(gridPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGTH);
	}
	
	private void setPosition() {
		
		gridPane.add(title, 0, 0);
		gridPane.add(nameLbl, 0, 1);
		gridPane.add(nameTF, 1, 1);
		gridPane.add(addressLbl, 0, 2);
		gridPane.add(addressTF, 1, 2);
		gridPane.add(errorLbl, 1, 3);
		gridPane.add(addHotelBtn, 1, 4);
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		
		GridPane.setHalignment(addHotelBtn, HPos.RIGHT);
		GridPane.setHalignment(errorLbl, HPos.RIGHT);
	}
	

	private void setFunctionality() {
		addHotelBtn.setOnAction(e -> {			
			Hotel hotel = hc.createHotel(nameTF, addressTF, admin, errorLbl);
			
			if(hotel == null) return;
			
			admin.setHotel(hotel);
			
			System.out.println("Admin: " + admin.getName());
			System.out.println("Hotel: " + admin.getHotel().getName());
			
			new HotelFacilitiesPage();
		});
	}
	
	public CompleteAccountPage() {	
		admin = (Admin)Main.getUser();
		
		if(admin.getHotel() != null) {
			System.out.println("Hotel: " + admin.getHotel().getName());
			new HotelFacilitiesPage();
			return;
		}
		
		init();
		setPosition();
		setFunctionality();
		Main.redirect(scene);
		
	}

}
