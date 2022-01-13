package de.thu.project.main.model.item;

import java.util.ArrayList;

public abstract interface IDaoItem {

	public abstract void insert(ItemVo item);
	public abstract void delete(ItemVo item);
	public abstract void updateCategory(ItemVo item, String category);
	public abstract void insertCategory(String category);
	public abstract int getItemID(ItemVo item);
	public abstract ArrayList<String> getCategoryItems(String category);
	public abstract ArrayList<CategoryVo> getAllCategories();
	public abstract void deleteCategory(String category);
	
}
