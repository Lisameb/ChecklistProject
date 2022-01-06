package de.thu.project.main.model.item;

public class CategoryVo {

	private String categoryName;
	
	public CategoryVo(String category) {
		this.setCategoryName(category);
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
