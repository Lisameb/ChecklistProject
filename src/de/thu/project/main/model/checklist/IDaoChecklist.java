package de.thu.project.main.model.checklist;

import java.util.ArrayList;

public abstract interface IDaoChecklist {
	public abstract void insert(ChecklistVo checklist);
	public abstract boolean delete(ChecklistVo checklist);
	public abstract int getChecklistID(ChecklistVo checklist);
	public abstract ArrayList<String> getAllChecklist(String username);
	public abstract void changeChecklistName(ChecklistVo old, String newName);
}
