package obsolete;

import java.util.ArrayList;

import model.UserVo;

public abstract interface ILogin {
	public abstract UserVo getUserVo(ArrayList<UserVo> users);
}
