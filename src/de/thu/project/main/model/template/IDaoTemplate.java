package de.thu.project.main.model.template;

import java.util.ArrayList;

public abstract interface IDaoTemplate {
	
	public abstract void insert(TemplateVo template);
	public abstract void delete(TemplateVo template);
	public abstract int getTemplateID(TemplateVo template);
	public abstract ArrayList<String> getAllTemplate();
	
}
