package de.thu.project.main.model;

import java.sql.Connection;
import de.thu.project.main.util.Config;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.thu.project.main.model.checklist.ChecklistDao_DB;
import de.thu.project.main.model.checklist.IDaoChecklist;
import de.thu.project.main.model.checklist_item.Checklist_itemDao_DB;
import de.thu.project.main.model.checklist_item.IDaoChecklist_item;
import de.thu.project.main.model.item.IDaoItem;
import de.thu.project.main.model.item.ItemDao_DB;
import de.thu.project.main.model.item_template.IDaoItem_temp;
import de.thu.project.main.model.item_template.Item_tempDao_DB;
import de.thu.project.main.model.template.IDaoTemplate;
import de.thu.project.main.model.template.TemplateDao_DB;
import de.thu.project.main.model.user.IDaoUser;
import de.thu.project.main.model.user.UserDao_DB;
import de.thu.project.main.model.user.UserVo;

public class DaoFactory {
	private static DaoFactory instance;
	private String url = Config.URL;
	private String user = Config.USER;
	private String pass = Config.PASSWORD;
	private UserVo current_user;
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

	public String getCurrent_user_name() {
		return current_user.getUsername();
	}
	
	public UserVo getCurrent_user() {
		return current_user;
	}

	public void setCurrent_user(String current_user) {
		this.current_user = new UserVo();
		this.current_user.setUsername(current_user);
		
	}
	
}
