package Login;

import java.util.ArrayList;

public class UserLogin implements ILogin{
	
	private String name;
	private String password;
	private UserVo matchedUser;
	
	
	
	public boolean checkLoginData(ArrayList<IUser> users) {
		
		for(IUser user: users) {
			if(user.getUsername().equals(name) && user.getPassword().equals(password)) {
				this.setMatchedUser((UserVo) user);
				return true;
			}
		}
		return false;
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
	
	

	@Override
	public UserVo getUserVo(ArrayList<IUser> users) {
		
		if(this.checkLoginData(users)) {
			return this.getMatchedUser();			
		}
		return null;
		
	}




	public UserVo getMatchedUser() {
		return matchedUser;
	}




	public void setMatchedUser(UserVo matchedUser) {
		this.matchedUser = matchedUser;
	}
	


}
