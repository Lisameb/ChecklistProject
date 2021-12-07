package model;

public abstract interface IDaoTemplate {
	
	public abstract void insert(TemplateVo template);
	public abstract void delete(TemplateVo template);
	public abstract int getTemplateID(TemplateVo template);
	
}
