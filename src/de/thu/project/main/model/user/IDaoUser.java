package de.thu.project.main.model.user;

public abstract interface IDaoUser {

	public abstract void insert(UserVo user);
	public abstract void update(UserVo user);
	public abstract void delete(UserVo user);
	public abstract boolean checkPassword(UserVo user);
	public abstract boolean validatePassword(UserVo user);
	public abstract boolean checkUsernameExists(String username);
	public abstract RoleVo hasRole(UserVo user);
	public abstract void hasPermissions(RoleVo role);
	
}
