package view.admin;

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
import model.hotel.HotelController;
import model.reservation.Reservation;
import model.reservation.ReservationController;
import model.user.Admin;
import model.user.UserController;
import view.Main;

public class ReservationPage extends AdminPageTemplate{
	
	private TableView<Reservation> reservationTV;
	private ObservableList<Reservation> observableList;
	private BorderPane borderPane;
	private List<Reservation> reservationList;
	private ReservationController rc;
	private Admin admin;
	private HBox hbox, hbox2;
	private Label title, customerLbl, roomTypeLbl, checkInLbl, checkOutLbl, roomAmountLbl, paymentMethodLbl, errorLbl;
	private GridPane gridPane;
	private ComboBox<String> customerCB, paymentCB, roomCB;
	private DatePicker checkInDP, checkOutDP;
	private Spinner<Integer> roomAmountS;
	private UserController uc;
	private HotelController hc;
	private Button addBtn, payBtn, deleteBtn;
	
	@Override
	protected void init() {
		
		uc = new UserController();
		hc = new HotelController();
		
		admin = (Admin) Main.getUser();
		
		rc = new ReservationController();
		
		borderPane = new BorderPane();
		reservationTV = new TableView<Reservation>();	
		hbox = new HBox();
		hbox2 = new HBox();
		
		title = new Label("Add Reservation");
		gridPane = new GridPane();
		customerLbl = new Label("Customer: ");
		roomTypeLbl = new Label("Room Type: ");
		checkInLbl = new Label("Check In Date: ");
		checkOutLbl = new Label("Check Out Date");
		roomAmountLbl = new Label("Room Amount: ");
		paymentMethodLbl = new Label("Payment Method: ");
		errorLbl = new Label();
		
		customerCB = new ComboBox<String>();
		paymentCB = new ComboBox<String>();
		roomCB = new ComboBox<String>();
		
		roomAmountS = new Spinner<Integer>(1, Integer.MAX_VALUE, 1);
		
		checkInDP = new DatePicker();
		checkOutDP = new DatePicker();
		
		addBtn = new Button("Add");
		deleteBtn = new Button("Delete");
		payBtn = new Button("Pay");
		
		scene = new Scene(borderPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGTH);
		
		setOptions();
		
	}
	
	private void setOptions() {
		
		customerCB.getItems().addAll(uc.getCustomersID());
		paymentCB.getItems().addAll("Cash", "Card", "E-Wallet");
		roomCB.getItems().addAll(hc.getRoomTypeNames(admin.getHotel()));
		
		checkInDP.setValue(LocalDate.now());
		checkOutDP.setValue(LocalDate.now().plusDays(1));
		
	}

	@Override
	protected void setPosition() {
		borderPane.setTop(navBar);
		
		hbox2.getChildren().addAll(payBtn, deleteBtn, addBtn);
		hbox2.setSpacing(5);
		hbox2.setAlignment(Pos.CENTER_RIGHT);
		
		gridPane.add(title, 0, 0);
		gridPane.add(customerLbl, 0, 1);
		gridPane.add(customerCB, 1, 1);
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
		
		hbox.getChildren().addAll(reservationTV, gridPane);
		
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		
		borderPane.setCenter(hbox);
	}

	@Override
	protected void setTable() {
		
		reservationList = rc.getReservations(admin.getHotel());
		
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
		
		observableList = FXCollections.observableArrayList(reservationList);
		
		reservationTV.setItems(observableList);
		reservationTV.setPrefHeight(300);
		reservationTV.setPrefWidth(800);
		reservationTV.setMaxHeight(300);
	}

	@Override
	protected void setFunctionality() {
		
		addBtn.setOnAction(e -> {
			Reservation reservation = rc.addReservation(admin.getHotel(), customerCB, roomCB, checkInDP, checkOutDP, roomAmountS, paymentCB, errorLbl);
		
			if(reservation != null) {
				observableList.add(reservation);
			}
		});
		
		deleteBtn.setOnAction(e -> {
			Reservation reservation = reservationTV.getSelectionModel().getSelectedItem();
			
			if(rc.deleteReservation(reservation, errorLbl)) {
				observableList.remove(reservation);
			}
		});
		
		payBtn.setOnAction(e -> {
			Reservation reservation = reservationTV.getSelectionModel().getSelectedItem();
			
			if(rc.payReservation(reservation, admin, errorLbl)) {
				
				reservation.setState(reservation.getState().handleStatus());
				reservationTV.refresh();
			}
		});
		
	}

}
