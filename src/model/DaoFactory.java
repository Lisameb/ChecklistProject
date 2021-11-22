package model;

public class DaoFactory {
	private static DaoFactory instance;

	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			instance = new DaoFactory();
		}
		return instance;
	}

	public IDaoUser getUserDao() {
		return new UserDao_DB();
	}
	
	
}
