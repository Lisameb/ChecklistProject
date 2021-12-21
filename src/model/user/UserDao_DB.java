package model.user;

import java.sql.*;

import model.DaoFactory;

public class UserDao_DB implements IDaoUser{
	
	//to test the code until we have a connection to the db
	private DaoFactory daofactory = DaoFactory.getInstance();

	@Override
	public void insert(UserVo user) {
		
		String query = "INSERT INTO user (name, password) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "')";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			stmt.execute(query);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Insert fehlgeschlagen!");
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(UserVo user) {
		
		String query = "UPDATE user SET password = '" + user.getPassword() + "' WHERE name = '" + user.getUsername() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			stmt.execute(query);
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Update fehlgeschlagen!");
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		// calls method from Database Abstraction Layer -> JDBC
		// not tested yet
		
	}

	@Override
	public void delete(UserVo user) {
	
		String query = "DELETE FROM user WHERE name = '" + user.getUsername() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			Boolean b = stmt.execute(query);
			stmt.close();
			if(b) {
				//TODO more gui windows -> shows that delete was successful
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Delete fehlgeschlagen!");
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		// not tested yet
		
	}

	@Override
	public UserVo getByUserID(UserVo user) {
		// TODO Auto-generated method stub
		// assign saved attributes from DB to user and return the same user
		// calls method from Database Abstraction Layer -> JDBC
		return user;
	}
	
	public boolean checkPassword(UserVo user) {
		
		
		String query = "SELECT password FROM user WHERE name = '" + user.getUsername() + "'";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			ResultSet resultset = stmt.executeQuery(query);
			String pw = "";
			if(resultset.next()) {
				pw = resultset.getString("password");
				
			}
			if(user.getPassword().equals(pw) && !user.getPassword().isEmpty()) {
				return true;
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("checkPassword fehlgeschlagen!");
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean validatePassword(UserVo user) {
		if(user.getPassword().length() > 5){
			return true;
		}
		return false;
	}
	
	/*
	 * Returns false unless the SELECT statement returns a dataset.
	 * */
	
	public boolean checkUsernameExists(String username) {
		
		Boolean foundUser = false;
		String resultString = "";
		
		String query = "SELECT name FROM user WHERE name='" + username + "';";
		try {
			Statement stmt = daofactory.getCon().createStatement();
			stmt.execute(query);
			ResultSet result = stmt.getResultSet();
			
			if(result.next()) {
				resultString = result.getString("name");
			}
			if(!resultString.equals("")) {
				foundUser = true;
			}
			stmt.close();
			return foundUser;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("checkUsernameExists fehlgeschlagen!");
			e.printStackTrace();
		}
		
		return foundUser;
	}

}
