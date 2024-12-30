package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class MySQLConnectionFactory {

	// Nome do Driver (classe) e URL do banco
	private static final String JDBC_DRIVER_NAME = 
			"com.mysql.jdbc.Driver";

	private static final String DATABASE_URL = 
			"jdbc:mysql://127.0.0.1/facebook";

	// Credenciais
	static final String USER = "root";
	static final String PASSWORD = "root1234";
	
	public static Connection getConnetion() 
			throws SQLException, ClassNotFoundException {
		
		Class.forName(JDBC_DRIVER_NAME);

		return DriverManager.getConnection(
				DATABASE_URL, USER, PASSWORD);
	}
}
