package Login;

public class UserVo {
	
	private int userID;
	private String username;
	private String password;
	private boolean role;
	private String email;
	
	public UserVo(int userID, String username, String password, boolean role, String email) {
		this.setUserID(userID);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
		this.setEmail(email);
	}
	
	public UserVo(int userID, String username, String password) {
		this.setUserID(userID);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(false);
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

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
