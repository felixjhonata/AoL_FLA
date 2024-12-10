package view.customer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.user.User;
import view.LoginPage;
import view.Main;

public class CustomerDashboard {
	
	private Scene scene;
	private GridPane gridPane;
	private Label label, label2;
	private Button logoutBtn;
	private User user;
	
	private void init() {
		
		gridPane = new GridPane();
	
		user = Main.getUser();
		
		label = new Label("Customer Dashboard");
		label2 = new Label("Welcome, " + user.getName());
		logoutBtn = new Button("Logout");
		
		scene = new Scene(gridPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGTH);		
	
	}
	
	private void setPosition() {
		gridPane.add(label, 0, 0);
		gridPane.add(label2, 0, 1);
		gridPane.add(logoutBtn, 0, 2);
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
	}
	
	private void setFunctionality() {
		logoutBtn.setOnAction(e -> {
			Main.setUser(null);
			new LoginPage();
		});
	}

	public CustomerDashboard() {
		init();
		setPosition();
		setFunctionality();
		Main.redirect(scene);
	}
	
}
