package model.item;

import java.util.ArrayList;

public abstract interface IDaoItem {

	public abstract void insert(ItemVo item);
	public abstract void delete(ItemVo item);
	public abstract void updateCategory(ItemVo item, CategoryVo category);
	public abstract int getItemID(ItemVo item);
	public abstract ArrayList<String> getCategoryItems(String category);
	public abstract ArrayList<CategoryVo> getAllCategories();
	
}
