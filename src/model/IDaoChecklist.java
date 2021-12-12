package model;

public abstract interface IDaoChecklist {
	public abstract void insert(ChecklistVo checklist);
	public abstract void delete(ChecklistVo checklist);
	public abstract int getChecklistID(ChecklistVo checklist);
}
