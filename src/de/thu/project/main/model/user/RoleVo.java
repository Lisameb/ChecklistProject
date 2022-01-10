package de.thu.project.main.model.user;

import java.util.HashMap;

public class RoleVo {
	
	private int id;
	private String name;
	private Boolean manageTemplates = false;
	private Boolean createItem = false;
	
	public RoleVo(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getManageTemplates() {
		return manageTemplates;
	}
	public void setManageTemplates(Boolean manageTemplates) {
		this.manageTemplates = manageTemplates;
	}
	public Boolean getCreateItem() {
		return createItem;
	}
	public void setCreateItem(Boolean createItem) {
		this.createItem = createItem;
	}


}
