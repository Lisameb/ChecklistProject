package model;

public class Item_tempVo {

	private int template_id;
	private int item_id;
	private int amount; // please help ; getAmount()-Methode
	
	public Item_tempVo(TemplateVo template, ItemVo item) {
		template_id = template.getTemplateID();
		item_id = item.getItemID();
		
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(int template_id) {
		this.template_id = template_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
}
