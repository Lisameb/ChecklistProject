package de.thu.project.main.model.user;

import java.sql.*;
import java.util.ArrayList;

import de.thu.project.main.model.DaoFactory;

/********************************************** 
 * 
 * class interacts with table 'user' of our database
 * methods:
 * 		- insert new user
 * 		- update password
 * 		- delete user
 * 		- check if typed in password is correct
 * 		- validatePassowrd: check if password is valid (long enough)
 * 		- check if username already exists
 * 		- check user role
 * 		- check permissions for roles
 * 
 **********************************************/

public class UserDao_DB implements IDaoUser{
	
	private DaoFactory daofactory = DaoFactory.getInstance();

	@Override
	public void insert(UserVo user) {
		
		String query = "INSERT INTO user (name, password) VALUES (?, ?)";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Insert failed!");
			e.printStackTrace();
		}	
	}

	@Override
	public void update(UserVo user) {
		
		String query = "UPDATE user SET password = ? WHERE name = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getUsername());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Update failed!");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(UserVo user) {
	
		String query = "DELETE FROM user WHERE name = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Delete failed!");
			e.printStackTrace();
		}
	}

	
	public boolean checkPassword(UserVo user) {
		String query = "SELECT password FROM user WHERE name =?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, user.getUsername());
			ResultSet resultset = stmt.executeQuery();
			String pw = "";
			if(resultset.next()) {
				pw = resultset.getString("password");
				
			}
			stmt.close();
			if(user.getPassword().equals(pw) && !user.getPassword().isEmpty()) {
				return true;
			}
		} catch (SQLException e) {
			System.err.println("Check Password failed!");
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
		
		String query = "SELECT name FROM user WHERE name = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, username);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()) {
				resultString = result.getString("name");
			}
			if(!resultString.equals("")) {
				foundUser = true;
			}
			stmt.close();
			return foundUser;
		} catch (SQLException e) {
			System.err.println("Check Username exists failed!");
			e.printStackTrace();
		}
		
		return foundUser;
	}
	
	public RoleVo hasRole(UserVo user) {
		
		RoleVo role;
		String query = "SELECT r.* FROM role AS r INNER JOIN user_has_role AS uhr ON r.role_ID = uhr.role_ID WHERE uhr.user_name = ?";
		
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setString(1, user.getUsername());
			ResultSet result = stmt.executeQuery();
			
			String role_name;
			int role_id;
			if(result.next()) {
				role_id = result.getInt("role_ID");
				role_name = result.getString("name");
				role = new RoleVo(role_id, role_name);
				hasPermissions(role);
				stmt.close();
				return role;
			}
		} catch (SQLException e) {
			System.err.println("Has role failed!");
			e.printStackTrace();
		}
		return null;
	}
	
	public void hasPermissions(RoleVo role) {
		
		ArrayList<String> rights = new ArrayList<String>();
		String query = "SELECT ri.name FROM rights AS ri INNER JOIN role_has_rights AS rhr ON rhr.right_ID = ri.`rights-ID` WHERE rhr.role_ID = ?";
		try {
			PreparedStatement stmt = daofactory.getCon().prepareStatement(query);
			stmt.setInt(1, role.getId());
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				rights.add(result.getString("name"));
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Has permissions failed!");
			e.printStackTrace();
		}
		for(String permission : rights) {
			if(permission.equals("manage_template")) {
				role.setManageTemplates(true);
			}else if(permission.equals("create_item")) {
				role.setCreateItem(true);
			}
		}
		
	}

}
