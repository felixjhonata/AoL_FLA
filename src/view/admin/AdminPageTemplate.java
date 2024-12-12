package view.admin;

import javafx.scene.Scene;
import view.Main;

public abstract class AdminPageTemplate {
	
	protected Scene scene;
	protected AdminNavBar navBar;
	
	protected abstract void init();
	protected abstract void setPosition();
	protected abstract void setTable();
	protected abstract void setFunctionality();
	
	public AdminPageTemplate() {
		
		navBar = new AdminNavBar();
		init();
		setTable();
		setPosition();
		setFunctionality();
		Main.redirect(scene);
		
	}

}
