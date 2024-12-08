package model.user;

import java.util.Random;

import database.DBSingleton;
import database.Database;

public abstract class User {
	protected String id;
	protected String name;
	protected String phoneNo;
	protected String email;
	protected String password;
	
	public User(String id, String name, String phoneNo, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static User login(String email, String password) {
		Database db = DBSingleton.getInstance();		
		return db.getUserByCredentials(email, password);
	}
	
	private static String createID(String prefix) {
		return String.format("%s%d", prefix, new Random().nextInt(1000));
	}
	
	public static User register(String name, String phoneNo, String email, String password, String role) {
		Database db = DBSingleton.getInstance();
		String id;
		
		User user = null;
		
		if(role.equals("admin")) {
			id = createID("AD");
			user = new Admin(id, name, phoneNo, email, password);
		} else {
			id = createID("CU");
			user = new Customer(id, name, phoneNo, email, password);
		}
		
		if(db.addUser(user)) {
			return user;
		} else {
			System.out.println("Failed to register!");
			return null;
		}
	}
}
