package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.user.User;

public class Main extends Application{
	
	private static Stage stage;
	private static User user;
	
	public static User getUser() {
		return user;
	}
	
	public static void setUser(User user) {
		Main.user = user;
	}
	
	public static void redirect(Scene newScene) {
		stage.setScene(newScene);
		stage.centerOnScreen();
		stage.setResizable(false);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Main.stage = stage;
		stage.setTitle("Hotel Booking Service");
		new LoginPage();
	}

}
