package Login;

import java.util.ArrayList;

public class UserCreator implements ILogin{
	
	private UserVo user;
	private int userID;
	private String name;
	private String password;
	private ArrayList<UserVo> users;
	
	
	public boolean existName(String name) {
		for(UserVo user: users) {
			if(user.getUsername().equals(name)) {
			
				return true;
			}
		}
		return false;
	}
	
	public boolean validatePassword(String password){
		if(password.length() > 5)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public UserVo getUserVo() {
		
		if(!this.existName(this.name)) {
			if(this.validatePassword(password)) {
				this.user = new UserVo(userID,name, password);				
			}
		}
			
		return this.user;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<UserVo> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserVo> users) {
		this.users = users;
	}
	

}
