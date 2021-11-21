/*
 * 
 * Deprecated
 * 
 */




//package Login;
//
//import java.lang.reflect.InvocationTargetException;
//
//public class LoginFactory {
//	
//	private static LoginFactory instance;
//	private ILogin login;
//	private int option;
//	
//	public static synchronized LoginFactory getInstance() {
//		if (instance == null)
//			instance = new LoginFactory();
//		return instance;
//	}
//	
//	
//	public ILogin getILogin() {
//		
//		//TODO complex logic (also known as magic)
//		String className = null;
//		
//		switch(option){
//		case 2: className = "UserLogin"; break;
//		}
//		
//		
//			try {
//				login = (ILogin) Class.forName(className).getDeclaredConstructor().newInstance();
//			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//					| InvocationTargetException | NoSuchMethodException | SecurityException
//					| ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		
//		return login;
//	}
//
//
//	public int getOption() {
//		return option;
//	}
//
//
//	public void setOption(int option) {
//		this.option = option;
//	}
//}
