package model;

public abstract interface IDaoUser {

	public abstract void insert(UserVo user);
	public abstract void update(UserVo user);
	public abstract void delete(UserVo user);
	public abstract UserVo getByUserID(UserVo user);
	
}
