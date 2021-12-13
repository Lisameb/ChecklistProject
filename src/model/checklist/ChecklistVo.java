package model.checklist;

public class ChecklistVo {

	
	private int checklistID;
	private String checklistName;
	private String username;
	
	
	public ChecklistVo(int checklistID, String checklistName, String username) {
		this.setChecklistID(checklistID);
		this.setChecklistName(checklistName);
		this.setUsername(username);
	}
	
	public ChecklistVo(String checklistName, String username) {
		this.setChecklistName(checklistName);
		this.setUsername(username);
	}

	public int getChecklistID() {
		return checklistID;
	}

	public void setChecklistID(int checklistID) {
		this.checklistID = checklistID;
	}

	public String getChecklistName() {
		return checklistName;
	}

	public void setChecklistName(String checklistName) {
		this.checklistName = checklistName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
