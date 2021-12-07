package model;

public class TemplateVo {
	
	private int templateID;
	private String templateName;
	// item?
	
	public TemplateVo(String templateName) {
		this.setTemplateName(templateName);
	}
	public TemplateVo(int templateID, String templateName) {
		this.setTemplateID(templateID);
		this.setTemplateName(templateName);
	}
	
	public int getTemplateID() {
		return templateID;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public void setTemplateID(int setTemplateID) {
		this.templateID = templateID;
	}

}
