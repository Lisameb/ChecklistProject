package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private static DaoFactory instance;
	private String url = "jdbc:mysql://i-intra-03.informatik.hs-ulm.de:3306/dsm_proj_01";
	private String user = "dsm_proj_01";
	private String pass = "Holy_Spirit01";
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
