package obsolete;

import java.util.ArrayList;

import model.UserVo;

public class UserFactory {
	private static UserFactory instance;
	private int option;
	private static ArrayList<UserVo> users;
	
	public static synchronized UserFactory getInstance() {
		if (instance == null) {
			instance = new UserFactory();
			users = new ArrayList<UserVo>();
		}
		
		return instance;
	}
	
	
	public UserVo getUserVo(String username, String password) {
		
		UserVo user = null;
		UserCreator creator = new UserCreator();
		creator.setName(username);
		if(creator.existName(users)) {
			return user; //debatable
		}

		String className = null;
		switch(option){
		case 1: className = "UserVo"; break;
		}
		
			try {
				user = (UserVo) Class.forName(className).getDeclaredConstructor().newInstance((users.size()),username, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("merde!");
			}
			
		users.add(user);
		return user;
	}


	public int getOption() {
		return option;
	}


	public void setOption(int option) {
		this.option = option;
	}

	public ArrayList<UserVo> getUsers() {
		return users;
	}
	
	public void setUsers(ArrayList<UserVo> users) {
		this.users = users;
	}

}
