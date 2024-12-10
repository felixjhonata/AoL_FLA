package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.user.Admin;
import model.user.User;
import model.user.UserController;
import view.admin.CompleteAccountPage;
import view.customer.CustomerDashboard;

public class RegisterPage {
	
	private Scene scene;
	private GridPane gridPane;
	private Label nameLbl, phoneLbl, emailLbl, passwordLbl, roleLbl, errorLbl;
	private TextField nameTF, phoneTF, emailTF;
	private PasswordField passwordTF;
	private ComboBox<String> roleCB;
	private Button loginBtn, registerBtn;
	private HBox hBox;
	private UserController uc;
	
	
	private void init() {
		
		uc = new UserController();
		
		gridPane = new GridPane();
		
		nameLbl = new Label("Name: ");
		phoneLbl = new Label("Phone No: ");
		emailLbl = new Label("Email: ");
		passwordLbl = new Label("Password: ");
		roleLbl = new Label("Role: ");
		errorLbl = new Label();
		
		nameTF = new TextField();
		phoneTF = new TextField();
		emailTF = new TextField();
		passwordTF = new PasswordField();
		
		roleCB = new ComboBox<String>();
		roleCB.getItems().addAll("admin", "customer");
		
		loginBtn = new Button("Login");
		registerBtn = new Button("Register");
		hBox = new HBox();
		
		scene = new Scene(gridPane, Main.WINDOW_WIDTH, Main.WINDOW_HEIGTH);
	}
	
	private void setPosition() {
		gridPane.add(nameLbl, 0, 0);
		gridPane.add(nameTF, 1, 0);
		gridPane.add(phoneLbl, 0, 1);
		gridPane.add(phoneTF, 1, 1);
		gridPane.add(emailLbl, 0, 2);
		gridPane.add(emailTF, 1, 2);
		gridPane.add(passwordLbl, 0, 3);
		gridPane.add(passwordTF, 1, 3);
		gridPane.add(roleLbl, 0, 4);
		gridPane.add(roleCB, 1, 4);
		gridPane.add(errorLbl, 1, 5);
		gridPane.add(hBox, 1, 6);
		
		hBox.getChildren().addAll(loginBtn, registerBtn);
		hBox.setAlignment(Pos.CENTER_RIGHT);
		hBox.setSpacing(5);
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
	}
	
	private void setFunctionality() {
		loginBtn.setOnAction(e -> {
			new LoginPage();
		});
		
		registerBtn.setOnAction(e -> {
			User user = uc.register(nameTF, phoneTF, emailTF, passwordTF, roleCB, errorLbl);
			Main.setUser(user);
			
			if(user == null) return;
			if(user instanceof Admin) {
				new CompleteAccountPage();
			} else {
				new CustomerDashboard();
			}
		});
	}
	
	public RegisterPage() {
		init();
		setPosition();
		setFunctionality();
		
		Main.redirect(scene);
	}

}
