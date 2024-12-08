package view;

import database.DBSingleton;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.user.User;
import model.user.UserController;

public class LoginPage{

	private Scene scene;
	private Label emailLbl, passwordLbl, errorLbl;
	private TextField emailTF, passwordTF;
	private Button loginBtn, registerBtn;
	private GridPane gridPane;
	private HBox hBox;
	private UserController uc;
	
	private void init() {
		
		uc = new UserController();
		
		gridPane = new GridPane();
		loginBtn = new Button("Login");
		registerBtn = new Button("Register");
		emailLbl = new Label("Email: ");
		passwordLbl = new Label("Password: ");
		emailTF = new TextField();
		passwordTF = new PasswordField();
		errorLbl = new Label();
		hBox = new HBox();
		scene = new Scene(gridPane, 800, 500);
		
	}
	
	private void setPosition() {
		gridPane.add(emailLbl, 0, 0);
		gridPane.add(emailTF, 1, 0);
		gridPane.add(passwordLbl, 0, 1);
		gridPane.add(passwordTF, 1, 1);
		gridPane.add(errorLbl, 1, 2);
		
		hBox.getChildren().addAll(registerBtn, loginBtn);
		hBox.setAlignment(Pos.CENTER_RIGHT);
		hBox.setSpacing(5);
		
		gridPane.add(hBox, 1, 3);
		GridPane.setHalignment(hBox, HPos.RIGHT);
		GridPane.setHalignment(loginBtn, HPos.RIGHT);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
	}
	
	private void setFunctionality() {
		loginBtn.setOnAction(e -> {			
			User user = uc.login(emailTF, passwordTF, errorLbl);
			Main.setUser(user);
		});
		
		registerBtn.setOnAction(e -> {
			System.out.println("To RegisterPage");
		});
	}
	
	public LoginPage() {
		init();
		setPosition();
		setFunctionality();
		Main.redirect(scene);
	}

}
