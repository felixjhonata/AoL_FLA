package view.admin;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.hotel.HotelController;
import model.hotel.RoomType;
import model.user.Admin;
import view.Main;

public class RoomTypePage extends AdminPageTemplate{
	
	private BorderPane borderPane;
	private GridPane gridPane;
	private HBox hbox, hbox2;
	private TableView<RoomType> roomTypesTV;
	private ObservableList<RoomType> observableList;
	private Admin admin;
	private Label title, nameLbl, descriptionLbl, priceLbl, isNonSmokingLbl, roomAmountLbl, errorLbl;
	private TextField nameTF, descriptionTF, priceTF;
	private CheckBox isNonSmokingCB;
	private Spinner<Integer> roomAmountS;
	private Button addBtn, deleteBtn;
	private HotelController hc;
	
	@Override
	protected void init() {
		hc = new HotelController();
		admin = (Admin) Main.getUser();
		
		borderPane = new BorderPane();
		gridPane = new GridPane();
		hbox = new HBox();
		hbox2 = new HBox();
		
		title = new Label("Add Room Type");
		nameLbl = new Label("Name: ");
		descriptionLbl = new Label("Description: ");
		priceLbl = new Label("Price: ");
		isNonSmokingLbl = new Label("Non Smoking: ");
		roomAmountLbl = new Label("Room Amount: ");
		errorLbl = new Label();
		nameTF = new TextField();
		descriptionTF = new TextField();
		priceTF = new TextField();
		isNonSmokingCB = new CheckBox();
		roomAmountS = new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE));
		
		addBtn = new Button("Add");
		deleteBtn = new Button("Delete");
		
		roomTypesTV = new TableView<RoomType>();
		
		scene = new Scene(borderPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGTH);
	}
	
	@Override
	protected void setTable() {
		TableColumn<RoomType, String> nameColumn = new TableColumn<>("Name");
		TableColumn<RoomType, String> descriptionColumn = new TableColumn<>("Description");
		TableColumn<RoomType, Double> priceColumn = new TableColumn<>("Price");
		TableColumn<RoomType, String> isNonSmokingColumn = new TableColumn<>("Non Smoking");
		TableColumn<RoomType, Integer> roomAmountColumn = new TableColumn<>("Room Amount");
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<RoomType, String>("name"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<RoomType, String>("description"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<RoomType, Double>("price"));
		isNonSmokingColumn.setCellValueFactory(data -> {
			return new SimpleStringProperty(data.getValue().isNonSmoking() ? "Yes" : "No");
		});
		roomAmountColumn.setCellValueFactory(new PropertyValueFactory<RoomType, Integer>("roomAmount"));
		
		List<RoomType> roomTypes = admin.getHotel().getRoomTypes();
		
		observableList = FXCollections.observableArrayList(roomTypes);
		
		roomTypesTV.getColumns().addAll(nameColumn, descriptionColumn, priceColumn, isNonSmokingColumn, roomAmountColumn);
		roomTypesTV.setItems(observableList);
		
		roomTypesTV.setPrefHeight(300);
		roomTypesTV.setMaxHeight(300);
	}
	
	@Override
	protected void setPosition() {	
		hbox2.getChildren().addAll(deleteBtn, addBtn);
		hbox2.setAlignment(Pos.CENTER_RIGHT);
		hbox2.setSpacing(5);
		
		gridPane.add(title, 0, 0);
		gridPane.add(nameLbl, 0, 1);
		gridPane.add(nameTF, 1, 1);
		gridPane.add(descriptionLbl, 0, 2);
		gridPane.add(descriptionTF, 1, 2);
		gridPane.add(priceLbl, 0, 3);
		gridPane.add(priceTF, 1, 3);
		gridPane.add(roomAmountLbl, 0, 4);
		gridPane.add(roomAmountS, 1, 4);
		gridPane.add(isNonSmokingLbl, 0, 5);
		gridPane.add(isNonSmokingCB, 1, 5);
		gridPane.add(errorLbl, 1, 6);
		gridPane.add(hbox2, 1, 7);
		gridPane.setVgap(10);
		
		gridPane.setAlignment(Pos.CENTER);
		
		hbox.getChildren().addAll(roomTypesTV, gridPane);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(10);
		
		borderPane.setTop(navBar);
		borderPane.setCenter(hbox);
	}
	
	@Override
	protected void setFunctionality() {
		addBtn.setOnAction(e -> {
			RoomType roomType = hc.addRoomType(admin, nameTF, priceTF, descriptionTF, isNonSmokingCB, roomAmountS, errorLbl);
			
			if(roomType != null) {
				observableList.add(roomType);
			}
		});
		
		deleteBtn.setOnAction(e -> {
			RoomType roomType = roomTypesTV.getSelectionModel().getSelectedItem();
			boolean isSuccessful = hc.removeRoomType(admin, roomType, errorLbl);
			
			if(isSuccessful) {
				observableList.remove(roomType);
			}
		});
	}
	
	public RoomTypePage() {
		super();
	}

}
