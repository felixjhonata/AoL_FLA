package model.user;

import java.util.Random;

import database.DBSingleton;
import database.Database;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserController {
	
	Database db;
	
	public UserController() {
		db = DBSingleton.getInstance();
	}
	
	public User login(TextField emailTF, PasswordField passwordTF, Label errorLbl) {
		
		errorLbl.setText("");
		
		String email = emailTF.getText();
		if(email.trim().isEmpty()) {
			errorLbl.setText("Email Field must be filled!");
			return null;
		}
		
		String password = passwordTF.getText();
		if(password.trim().isEmpty()) {
			errorLbl.setText("Password Field must be filled!");
			return null;
		}

		User user = db.getUserByCredentials(email, password);
		if(user == null) {
			errorLbl.setText("Invalid Credential");
			return null;
		}
		
		emailTF.setText("");
		passwordTF.setText("");
		
		return user;
	}
	
	private boolean checkOnlyNumber(String value) {
		int length = value.length();
		
		boolean isOnlyNum = true;
		for(int i = 0; i < length; i++) {
			if(value.charAt(i) > '9' || value.charAt(i) < '0') {
				isOnlyNum = false;
				break;
			}
		}
		return isOnlyNum;
	}
	
	private String createID(String prefix) {
		return String.format("%s%03d", prefix, new Random().nextInt(1000));
	}
	
	public User register(TextField nameTF, TextField phoneTF, TextField emailTF, PasswordField passwordTF, ComboBox<String> roleCB, Label errorLbl) {
		String name = nameTF.getText();
		if(name.trim().isEmpty()) {
			errorLbl.setText("Name Field must be filled!");
			return null;
		}
		
		String phone = phoneTF.getText();
		if(phone.trim().isEmpty()) {
			errorLbl.setText("Phone Field must be filled!");
			return null;
		} else if (!checkOnlyNumber(phone)) {
			errorLbl.setText("Phone field must only be filled with Numbers!");
			return null;
		}
		
		String email = emailTF.getText();
		if(email.trim().isEmpty()) {
			errorLbl.setText("Email Field must be filled!");
			return null;
		}
		
		String password = passwordTF.getText();
		if(password.trim().isEmpty()) {
			errorLbl.setText("Password Field must be filled!");
			return null;
		}
		
		String role = roleCB.getValue();
		String id;
		User user;
		
		if("admin".equals(role)) {
			id = createID("AD");
			user = new Admin(id, name, phone, email, password);
		} else if ("customer".equals(role)) {
			id = createID("CU");
			user = new Customer(id, name, phone, email, password);
		} else {
			errorLbl.setText("Role must be picked!");
			return null;
		}
		
		if(!db.addUser(user)) {
			errorLbl.setText("Failed to register user!");
			return null;
		}
		
		nameTF.setText("");
		phoneTF.setText("");
		emailTF.setText("");
		passwordTF.setText("");
		roleCB.setValue(null);
		return user;
	}

}
