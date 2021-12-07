package model;

public abstract interface IDaoItem {

	public abstract void insert(ItemVo item);
	public abstract void delete(ItemVo item);
	public abstract void updateCategory(ItemVo item, CategoryVo category);
	public abstract int getItemID(ItemVo item);
	// Methoden um Items in Checklisten/Templates zu füllen hier oder in extra Klasse für Zwischentabelle (item_temp)
	
}
