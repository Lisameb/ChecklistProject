package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UserDao_DB implements IDaoUser{
	
	//to test the code until we have a connection to the db
	private ArrayList<UserVo> users = new ArrayList<UserVo>();

	@Override
	public void insert(UserVo user) {
		users.add(user);
		// TODO Auto-generated method stub
		// calls method from Database Abstraction Layer -> JDBC
		
	}

	@Override
	public void update(UserVo user) {
		// TODO Auto-generated method stub
		// calls method from Database Abstraction Layer -> JDBC
		
	}

	@Override
	public void delete(UserVo user) {
		// TODO Auto-generated method stub
		// calls method from Database Abstraction Layer -> JDBC
		
	}

	@Override
	public UserVo getByUserID(UserVo user) {
		// TODO Auto-generated method stub
		// assign saved attributes from DB to user and return the same user
		// calls method from Database Abstraction Layer -> JDBC
		return user;
	}
	
	public boolean checkPassword(UserVo user) {
		for(UserVo u: users) {
			if((u.getUsername().equals(user.getUsername())) && (u.getPassword().equals(user.getPassword()))) {
				return true;
			}
		}
		return false;
		//will be changed after db connection
		//TODO when we can connect to database to get saved password + check username
	}
	
	
	public boolean validatePassword(UserVo user) {
		if(user.getPassword().length() > 5){
			return true;
		}
		return false;
	}
	
	public boolean checkUsername(String username) {
		for(UserVo u: users) {
			if(u.getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}

}
