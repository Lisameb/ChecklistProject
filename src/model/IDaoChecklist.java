package model;

import java.util.ArrayList;

public abstract interface IDaoChecklist {
	public abstract void insert(ChecklistVo checklist);
	public abstract boolean delete(ChecklistVo checklist);
	public abstract int getChecklistID(ChecklistVo checklist);
	public abstract ArrayList<String> getAllChecklist(String username);
}
