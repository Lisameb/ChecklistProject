package Login;

public class UserVo implements IUser{
	
	private int userID;
	private String username;
	private String password;
	
	
	public UserVo(int userID, String username, String password) {
		this.setUserID(userID);
		this.setUsername(username);
		this.setPassword(password);
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
}
