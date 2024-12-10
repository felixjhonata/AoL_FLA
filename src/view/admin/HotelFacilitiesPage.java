package view.admin;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.hotel.HotelController;
import model.user.Admin;
import view.Main;

public class HotelFacilitiesPage extends AdminPageTemplate{
	
	private GridPane gridPane;
	private BorderPane borderPane;
	private HBox hbox, hbox2;
	private TableView<String> facilitiesTV;
	private Admin admin;
	private ObservableList<String> observableList;
	private Label title, nameLbl, errorLbl;
	private TextField nameTF;
	private Button addBtn, deleteBtn;
	private HotelController hc;
	
	@Override
	protected void init() {
		hc = new HotelController();
		
		gridPane = new GridPane();
		hbox = new HBox();
		hbox2 = new HBox();
		borderPane = new BorderPane();
		
		facilitiesTV = new TableView<>();
		title = new Label("Add Facility");
		nameLbl = new Label("Name: ");
		errorLbl = new Label();
		addBtn = new Button("Add");
		nameTF = new TextField();
		deleteBtn = new Button("Delete");
		
		scene = new Scene(borderPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGTH);
		
		admin = (Admin) Main.getUser();
		List<String> facilities = admin.getHotel().getFacilities();
		
		observableList = FXCollections.observableArrayList(facilities);
	}
	
	@Override
	protected void setTable() {
		TableColumn<String, String> column = new TableColumn<>("Hotel Facilities");
		column.setCellValueFactory(data -> {
			return new SimpleStringProperty(data.getValue());
		});
		
		column.prefWidthProperty().bind(facilitiesTV.widthProperty());
		
		facilitiesTV.getColumns().add(column);
		facilitiesTV.setItems(observableList);
		
		facilitiesTV.setPrefHeight(300);
		facilitiesTV.setPrefWidth(200);
		facilitiesTV.setMaxHeight(300);
	}
	
	@Override
	protected void setPosition() {		
		gridPane.add(title, 0, 0);
		gridPane.add(nameLbl, 0, 1);
		gridPane.add(nameTF, 1, 1);
		gridPane.add(errorLbl, 1, 2);
		
		hbox2.getChildren().addAll(deleteBtn, addBtn);
		hbox2.setSpacing(5);
		hbox2.setAlignment(Pos.CENTER_RIGHT);
		
		gridPane.add(hbox2, 1, 3);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);
		
		hbox.getChildren().addAll(facilitiesTV, gridPane);
		
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		
		borderPane.setTop(navBar);
		borderPane.setCenter(hbox);
	}
	
	@Override
	protected void setFunctionality() {
		addBtn.setOnAction(e -> {
			String facility = hc.addFacility(nameTF, admin, errorLbl);
			if(facility != null) {
				observableList.add(facility);
			}
		});
		
		deleteBtn.setOnAction(e -> {
			String selected = facilitiesTV.getSelectionModel().getSelectedItem();
			
			boolean isSuccessful = hc.removeFacility(selected, admin, errorLbl);
			
			if(isSuccessful) {
				observableList.remove(selected);
			}
		});
	}
	
	public HotelFacilitiesPage() {
		super();
	}

}
