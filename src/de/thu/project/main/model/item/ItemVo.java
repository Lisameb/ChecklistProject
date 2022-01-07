package de.thu.project.main.model.item;

public class ItemVo {
	
	private int itemID;
	private String itemName;
	private String category; 
	
	public ItemVo(String itemName) {
		this.setItemName(itemName);
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
}
