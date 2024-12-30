package model.dao;

import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
	
	private static Map<Class<?>, Object> daos = new HashMap<>();
	
	static {
		daos.put(UserDAO.class, new MySQLUserDAO());
	}
	
	public static Object getDAO(Class<?> cls) {
		return daos.get(cls);
	}
}
