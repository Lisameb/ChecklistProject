package Login;

import java.lang.reflect.InvocationTargetException;

public class LoginFactory {
	
	private static LoginFactory instance;
	private ILogin login; 
	
	public static synchronized LoginFactory getInstance() {
		if (instance == null)
			instance = new LoginFactory();
		return instance;
	}
	
	
	public ILogin getILogin() {
		
		//TODO complex logic (also known as magic)
		
		String className = "UserCreator";
			try {
				login = (ILogin) Class.forName(className).getDeclaredConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return login;
	}
}
