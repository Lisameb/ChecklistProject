package model;

import java.sql.Connection;
import util.Config;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.checklist.ChecklistDao_DB;
import model.checklist.IDaoChecklist;
import model.checklist_item.Checklist_itemDao_DB;
import model.checklist_item.IDaoChecklist_item;
import model.item.IDaoItem;
import model.item.ItemDao_DB;
import model.item_template.IDaoItem_temp;
import model.item_template.Item_tempDao_DB;
import model.template.IDaoTemplate;
import model.template.TemplateDao_DB;
import model.user.IDaoUser;
import model.user.UserDao_DB;

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
