package de.thu.project.main.model.checklist_item;

import java.util.ArrayList;

public abstract interface IDaoChecklist_item {
	
	public abstract int getAmount(Checklist_itemVo checklist_item); 
	public abstract boolean checkCombo(Checklist_itemVo checklist_item);
	public abstract void addItem(Checklist_itemVo checklist_item, int amountAdd);
	public abstract void deleteItem(Checklist_itemVo checklist_item);
	public abstract ArrayList<String> getItemsC(int checklist_id);
	public abstract void toggleCheck2(Checklist_itemVo checklist_item);
	public abstract void toggleUnchecked(Checklist_itemVo checklist_item);
}
