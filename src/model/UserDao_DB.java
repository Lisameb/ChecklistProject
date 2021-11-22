package model;

public class UserDao_DB implements IDaoUser{

	@Override
	public void insert(UserVo user) {
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
		return true;
		//TODO when we can connect to database to get saved password
	}
	
	
	public boolean validatePassword(UserVo user) {
		if(user.getPassword().length() > 5){
			return true;
		}
		return false;
	}

}
