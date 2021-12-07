package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.CategoryVo;
import model.ItemDao_DB;
import model.TemplateDao_DB;
import view.ChangeTemplateView;

public class ChangeTemplateController implements ActionListener {

	private ChangeTemplateView view;
	private TemplateDao_DB tempDao;
	private ItemDao_DB itemDao;
	
	public ChangeTemplateController (ChangeTemplateView view) {
		this.view = view;
		this.tempDao = new TemplateDao_DB();
		this.itemDao = new ItemDao_DB();
	}
	
	public void setComboBoxTemp() {
		ArrayList<String> tempList = new ArrayList<>(); 
		tempList = tempDao.getAllTemplate();
		
		for(String name : tempList) {
			view.comboBoxTemp.addItem(name);
		}
	}
	
	public void setComboBoxCat() {
		ArrayList<CategoryVo> catList = new ArrayList<>(); 
		catList = itemDao.getAllCategories();
		
		for(CategoryVo category : catList) {
			view.comboBoxCategory.addItem(category.getCategoryName());
		}
		
	}
	public void setComboBoxItem(CategoryVo category) {
		ArrayList<String> itemList = new ArrayList<>(); 
		itemList = itemDao.getCategoryItems(category);
		
		for(String name : itemList) {
			view.comboBoxTemp.addItem(name);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if(src == view.comboBoxTemp) {
			
		}
		
	}

}
