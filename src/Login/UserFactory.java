package Login;

import java.util.ArrayList;

public class UserFactory {
	private static UserFactory instance;
	private int option;
	private static ArrayList<IUser> users;
	
	public static synchronized UserFactory getInstance() {
		if (instance == null) {
			instance = new UserFactory();
			users = new ArrayList<IUser>();
		}
		
		return instance;
	}
	
	
	public IUser getIUser(String username, String password) {
		
		IUser user = null;
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
				user = (IUser) Class.forName(className).getDeclaredConstructor().newInstance((users.size()-1),username, password);
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

	public ArrayList<IUser> getUsers() {
		return users;
	}
	
	public void setUsers(ArrayList<IUser> users) {
		this.users = users;
	}

}
