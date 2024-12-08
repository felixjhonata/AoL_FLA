package model.user;

import database.DBSingleton;
import database.Database;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserController {
	
	Database db;
	
	public UserController() {
		db = DBSingleton.getInstance();
	}
	
	public User login(TextField emailTF, TextField passwordTF, Label errorLbl) {
		
		errorLbl.setText("");
		
		String email = emailTF.getText();
		if(email.trim().isEmpty()) {
			errorLbl.setText("Email Field must not be empty!");
			return null;
		}
		
		String password = passwordTF.getText();
		if(password.trim().isEmpty()) {
			errorLbl.setText("Password Field must not be empty!");
			return null;
		}

		User user = db.getUserByCredentials(email, password);
		if(user != null) {
			emailTF.setText("");
			passwordTF.setText("");
			
			return user;
		}
		
		errorLbl.setText("Invalid Credential");
		return null;
	}

}
