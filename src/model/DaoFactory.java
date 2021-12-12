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
	private String current_user;
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
	
	public IDaoTemplate getTemplateDao() {
		return new TemplateDao_DB();
	}
	
	public IDaoItem getItemDao() {
		return new ItemDao_DB();
	}
	
	public IDaoItem_temp getItem_tempDao() {
		return new Item_tempDao_DB();
	}
	
	public Connection getCon() {
		return con;
	}
	
	public IDaoChecklist getChecklistDao() {
		return new ChecklistDao_DB();
	}
	
	public IDaoChecklist_item getChecklist_itemDao() {
		return new Checklist_itemDao_DB();
	}

	public String getCurrent_user() {
		return current_user;
	}

	public void setCurrent_user(String current_user) {
		this.current_user = current_user;
	}
	
}
