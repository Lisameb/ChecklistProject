package Login;

import java.util.ArrayList;

public abstract interface ILogin {
	public abstract UserVo getUserVo(ArrayList<IUser> users);
}
