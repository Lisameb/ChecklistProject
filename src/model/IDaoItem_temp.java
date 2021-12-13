package model;

import java.util.ArrayList;

public abstract interface IDaoItem_temp {

	public abstract int getAmount(Item_tempVo item_temp); 
	public abstract boolean checkCombo(Item_tempVo item_temp);
	public abstract void addItem(Item_tempVo item_temp, int amountAdd);
	public abstract void deleteItem(Item_tempVo item_temp);
	public abstract void changeAmount(Item_tempVo item_temp);
	public abstract ArrayList<String> getItemsT(int temp_id);
}
