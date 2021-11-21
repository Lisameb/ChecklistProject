package Login;

import java.util.ArrayList;

public class UserCreator implements ILogin{
	
	private UserVo user;
	private int userID;
	private String name;
	private String password;
	
	
	public boolean existName(ArrayList<IUser> users) {
		for(IUser user: users) {
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
	public UserVo getUserVo(ArrayList<IUser> users) {
		
		if(!this.existName(users)) {
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


}
