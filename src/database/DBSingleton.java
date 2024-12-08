package database;

public class DBSingleton {

	private static volatile Database instance;
	
	private DBSingleton() {};
	
	public static Database getInstance() {
		
		if(instance == null) {
			synchronized (DBSingleton.class) {
				if(instance == null) {
					instance = new Database();
				}
			}
		}
		
		return instance;
	}
	
}
