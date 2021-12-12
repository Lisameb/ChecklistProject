package model;

public class Checklist_itemVo {
	private int checklist_id;
	private int item_id;
	private int amount;
	private boolean checked;
	
	public Checklist_itemVo(int checklist_id, int item_id) {
		this.setChecklist_id(checklist_id);
		this.setItem_id(item_id);
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getChecklist_id() {
		return checklist_id;
	}

	public void setChecklist_id(int checklist_id) {
		this.checklist_id = checklist_id;
	}
	
	
}
