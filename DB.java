public class DB {
	
	private DB db = NULL;

	private DB() {

	}

	public static getDB() {
		if (db == NULL) {
			db = new DB();
		}
		return db;
	}

	public Flight[] getFlights(Route route) {
		//return array of flights in a given route
	}

	public boolean checkUser(String user, String pass) {
		//if user and pass are in db then return true else false
		return true;
	}

}