package Login;

import java.util.ArrayList;

public abstract interface IUser {
	
	public abstract UserVo getUserVo();
	public abstract String getUsername();
	public abstract void setUsername(String username);
	public abstract String getPassword();
	public abstract void setPassword(String password);

}
