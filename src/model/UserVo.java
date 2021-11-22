package model;

public class UserVo{
	
	private int userID;
	private String username;
	private String password;
	private int role;
	
	
	public UserVo(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public UserVo() {
	}
	
	public UserVo(int userID) {
		this.setUserID(userID);
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public UserVo getUserVo() {
		return this;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}
