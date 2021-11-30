package model;

import java.sql.Connection;
import util.Config;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private static DaoFactory instance;
	private String url = Config.URL;
	private String user = Config.USER;
	private String pass = Config.PASSWORD;
	private Connection con = null;
	
	public DaoFactory() {
		try {
		    this.con = DriverManager.getConnection(url, user, pass);
		    System.out.println("Verbindung erfolgreich hergestellt");

		    } catch (SQLException e) {
		    System.out.println(e.getMessage());
		    }
	}

	public static synchronized DaoFactory getInstance() {
		if (instance == null) {
			instance = new DaoFactory();
		}
		return instance;
	}

	public IDaoUser getUserDao() {
		return new UserDao_DB();
	}
	
	public Connection getCon() {
		return con;
	}
	
	
}
