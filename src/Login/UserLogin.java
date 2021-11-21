package Login;

import java.util.ArrayList;

public class UserLogin implements ILogin{
	
	private String name;
	private String password;
	private ArrayList<UserVo> users;
	private UserVo matchedUser;
	
	
	
	public boolean checkLoginData() {
		
		for(UserVo user: users) {
			if(user.getUsername().equals(name) && user.getPassword().equals(password)) {
				this.matchedUser = user;
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
	
	public ArrayList<UserVo> getUsers(){
		return users;
	}
	
	public void setUsers(ArrayList<UserVo> users) {
		this.users = users;
	}
	
	

	@Override
	public UserVo getUserVo() {
		
		if(this.checkLoginData()) {
			return this.matchedUser;			
		}
		return null;
		
	}
	


}
