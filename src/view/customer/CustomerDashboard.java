package view.customer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.hotel.Hotel;
import model.hotel.HotelController;
import model.hotel.RoomType;
import model.reservation.Reservation;
import model.reservation.ReservationController;
import model.user.Customer;
import view.LoginPage;
import view.Main;

public class CustomerDashboard {
	
	private Scene scene;
	private GridPane gridPane;
	private BorderPane borderPane;
	private Button logoutBtn;
	private Customer customer;
	private TableView<Reservation> reservationTV;
	private TableView<String> facilitiesTV;
	private TableView<RoomType> roomTV;
	private HBox hbox, hbox2, hbox3;
	
	private Label title, hotelLbl, roomTypeLbl, checkInLbl, checkOutLbl, roomAmountLbl, paymentMethodLbl, errorLbl;
	private ComboBox<String> hotelCB, paymentCB, roomCB;
	private DatePicker checkInDP, checkOutDP;
	private Spinner<Integer> roomAmountS;
	private Button addBtn, payBtn;
	
	private ReservationController rc;
	private HotelController hc;
	private ObservableList<Reservation> observableList;
	private ObservableList<String> facilitiesList;
	private ObservableList<RoomType> roomList;
	private Hotel selectedHotel;
	private VBox vbox;
	
	private void init() {
		
		rc = new ReservationController();
		hc = new HotelController();
		
		gridPane = new GridPane();
		borderPane = new BorderPane();
		hbox = new HBox();
		hbox2 = new HBox();
		hbox3 = new HBox();
		vbox = new VBox();
		
		title = new Label("Add Reservation");
		hotelLbl = new Label("Customer: ");
		roomTypeLbl = new Label("Room Type: ");
		checkInLbl = new Label("Check In Date: ");
		checkOutLbl = new Label("Check Out Date");
		roomAmountLbl = new Label("Room Amount: ");
		paymentMethodLbl = new Label("Payment Method: ");
		errorLbl = new Label();
		
		hotelCB = new ComboBox<String>();
		paymentCB = new ComboBox<String>();
		roomCB = new ComboBox<String>();
		
		roomAmountS = new Spinner<Integer>(1, Integer.MAX_VALUE, 1);
		
		checkInDP = new DatePicker();
		checkOutDP = new DatePicker();
		
		addBtn = new Button("Add");
		payBtn = new Button("Pay");
		
		customer = (Customer) Main.getUser();
		
		logoutBtn = new Button("Logout");
		reservationTV = new TableView<Reservation>();	
		roomTV = new TableView<>();
		facilitiesTV = new TableView<String>();
		
		scene = new Scene(borderPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGTH);		
	
	}
	
	private void refreshTable() {
		facilitiesList = FXCollections.observableArrayList(selectedHotel.getFacilities());
		
		TableColumn<String, String> column = new TableColumn<>("Hotel Facilities");
		column.setCellValueFactory(data -> {
			return new SimpleStringProperty(data.getValue());
		});
		
		column.prefWidthProperty().bind(facilitiesTV.widthProperty());
		
		facilitiesTV.getColumns().add(column);
		facilitiesTV.setItems(facilitiesList);
		
		facilitiesTV.setPrefHeight(300);
		facilitiesTV.setMaxHeight(300);
		facilitiesTV.setPrefWidth(500);
		
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
		
		List<RoomType> roomTypes = selectedHotel.getRoomTypes();
		
		roomList = FXCollections.observableArrayList(roomTypes);
		
		roomTV.getColumns().addAll(nameColumn, descriptionColumn, priceColumn, isNonSmokingColumn, roomAmountColumn);
		roomTV.setItems(roomList);
		
		roomTV.setPrefHeight(300);
		roomTV.setMaxHeight(300);
		roomTV.setPrefWidth(500);
		
	}
	
	private void setPosition() {		
		borderPane.setTop(logoutBtn);
		hbox2.getChildren().addAll(payBtn, addBtn);
		hbox2.setAlignment(Pos.CENTER_RIGHT);
		hbox2.setSpacing(5);
		
		gridPane.add(hotelLbl, 0, 0);
		gridPane.add(hotelCB, 1, 0);
		gridPane.add(title, 0, 1);
		gridPane.add(roomTypeLbl, 0, 2);
		gridPane.add(roomCB, 1, 2);
		gridPane.add(checkInLbl, 0, 3);
		gridPane.add(checkInDP, 1, 3);
		gridPane.add(checkOutLbl, 0, 4);
		gridPane.add(checkOutDP, 1, 4);
		gridPane.add(roomAmountLbl, 0, 5);
		gridPane.add(roomAmountS, 1, 5);
		gridPane.add(paymentMethodLbl, 0, 6);
		gridPane.add(paymentCB, 1, 6);
		gridPane.add(errorLbl, 1, 7);
		gridPane.add(hbox2, 1, 8);
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(10);
		
		hbox.getChildren().addAll(reservationTV, gridPane);
		
		hbox3.getChildren().addAll(facilitiesTV, roomTV);
		
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setSpacing(10);
		
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		vbox.getChildren().addAll(hbox3, hbox);
		
		borderPane.setCenter(vbox);
	}
	
	private void setOptions() {
		
		hotelCB.getItems().addAll(hc.getAllHotelID());
		selectedHotel = hc.getHotelByID(hotelCB.getItems().getFirst());
		
		hotelCB.getSelectionModel().select(0);
		
		setRoomOptions();
		paymentCB.getItems().addAll("Cash", "Card", "E-Wallet");
		
		checkInDP.setValue(LocalDate.now());
		checkOutDP.setValue(LocalDate.now().plusDays(1));
	}
	
	private void setRoomOptions() {
		roomCB.getItems().addAll(hc.getRoomTypeNames(selectedHotel));
	}
	
	private void setFunctionality() {
		logoutBtn.setOnAction(e -> {
			Main.setUser(null);
			new LoginPage();
		});
		
		hotelCB.setOnAction(e -> {
			setRoomOptions();
			refreshTable();
		});
		
		addBtn.setOnAction(e -> {
			Reservation reservation = rc.addReservation(selectedHotel, customer, roomCB, checkInDP, checkOutDP, roomAmountS, paymentCB, errorLbl);
		
			if(reservation != null) {
				observableList.add(reservation);
			}
		});
		
		payBtn.setOnAction(e -> {
			Reservation reservation = reservationTV.getSelectionModel().getSelectedItem();
			
			if(rc.payReservation(reservation, customer, errorLbl)) {
				
				reservation.setState(reservation.getState().handleStatus());
				reservationTV.refresh();
			}
		});
	}
	
	private void setTable() {
		
		TableColumn<Reservation, String> reservationIDColumn = new TableColumn<Reservation, String>("Reservation ID");
		reservationIDColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("id"));
		
		TableColumn<Reservation, String> hotelNameColumn = new TableColumn<Reservation, String>("Hotel Name");
		hotelNameColumn.setCellValueFactory(data -> {
			return new SimpleStringProperty(data.getValue().getHotel().getName());
		});
		
		TableColumn<Reservation, String> customerNameColumn = new TableColumn<Reservation, String>("Customer Name");
		customerNameColumn.setCellValueFactory(data -> {
			return new SimpleStringProperty(data.getValue().getCustomer().getName());
		});
		
		TableColumn<Reservation, String> roomTypeColumn = new TableColumn<Reservation, String>("Room Type");
		roomTypeColumn.setCellValueFactory(data -> {
			return new SimpleStringProperty(data.getValue().getRoomType().getName());
		});
		
		TableColumn<Reservation, String> checkInDateColumn = new TableColumn<Reservation, String>("Check In Date");
		checkInDateColumn.setCellValueFactory(data -> {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	         return new SimpleStringProperty(dateFormat.format(data.getValue().getCheckInDate()));
		});
		
		TableColumn<Reservation, String> checkOutDateColumn = new TableColumn<Reservation, String>("Check Out Date");
		checkOutDateColumn.setCellValueFactory(data -> {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	         return new SimpleStringProperty(dateFormat.format(data.getValue().getCheckOutDate()));
		});
		
		TableColumn<Reservation, Integer> roomAmountColumn = new TableColumn<Reservation, Integer>("Room Amount");
		roomAmountColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("roomAmount"));
		
		TableColumn<Reservation, String> stateColumn = new TableColumn<Reservation, String>("State");
		stateColumn.setCellValueFactory(data -> {
			return new SimpleStringProperty(data.getValue().getState().toString());
		});
		
		TableColumn<Reservation, String> paymentTypeColumn = new TableColumn<Reservation, String>("Payment Type");
		paymentTypeColumn.setCellValueFactory(data -> {
			return new SimpleStringProperty(data.getValue().getPaymentMethod().toString());
		});
		
		reservationTV.getColumns().addAll(reservationIDColumn, hotelNameColumn, customerNameColumn, roomTypeColumn, checkInDateColumn, checkOutDateColumn, roomAmountColumn, stateColumn, paymentTypeColumn);
		
		observableList = FXCollections.observableArrayList(rc.getReservations(customer));
		
		reservationTV.setItems(observableList);
		reservationTV.setPrefHeight(300);
		reservationTV.setPrefWidth(800);
		reservationTV.setMaxHeight(300);
	}

	public CustomerDashboard() {
		init();
		setTable();
		setOptions();
		refreshTable();
		setPosition();
		setFunctionality();
		Main.redirect(scene);
	}
	
}
