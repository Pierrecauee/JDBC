package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import model.ModelException;

public class DataBaseHandler {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private Statement statement;
	private ResultSet rs;

	public void statement() throws ModelException {
		try {
			connection = MySQLConnectionFactory.getConnetion();

			statement = connection.createStatement();
		} catch (SQLException sqle) {
			raiseError("Erro ao rodar SQL", sqle);
		} 
		catch (ClassNotFoundException cnfe) {
			raiseError("Classe do Driver JDBC não encontrada.", cnfe);
		}
		catch (Exception e) {
			raiseError("Erro não conhecido ao criar conexão.", e);
		}
	}

	private void raiseError(String message, Exception e) 
			throws ModelException {
		try {
			connection.close();
		} catch (Exception e2) {}
		
		throw new ModelException(message, e);
	}

	public void prepareStatement(String sql) throws ModelException {
		try {
			connection = MySQLConnectionFactory.getConnetion();

			preparedStatement = connection.prepareStatement(sql);
		} 
		catch (ClassNotFoundException cnfe) {
			raiseError("Classe do Driver JDBC não encontrada.", cnfe);
		}
		catch (SQLException sqle) {
			raiseError("Erro ao rodar SQL", sqle);
		}
		catch (Exception e) {
			raiseError("Erro não conhecido ao criar conexão.", e);
		}
	}

	public void setString(int index, String value) throws ModelException {
		try {
			preparedStatement.setString(index, value);
		} 
		catch (SQLException sqle) {
			raiseError("Erro ao setar String no pStatement", sqle);
		} 
		catch (Exception e) {
			raiseError("Erro não conhecido ao setar String no pStatement", e);
		}	
	}

	public int executeUpdate() throws ModelException {
		try {
			return preparedStatement.executeUpdate();
		} 
		catch (SQLException sqle) {
			raiseError("Erro ao executar DML", sqle);
		} 
		catch (Exception e) {
			raiseError("Erro não conhecido ao executar DML", e);
		}

		return 0;
	}

	public void close() {
		if (preparedStatement != null)
			try {
				preparedStatement.close();
			} catch (SQLException e) {}

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {}
		}
		
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e) {}
		}
		
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {}
		
		preparedStatement = null;
		rs = null;
		statement = null;
		connection = null;
	}

	public void setInt(int index, int value) 
			throws ModelException {
		try {
			preparedStatement.setInt(index, value);
		} 
		catch (SQLException sqle) {
			raiseError("Erro ao setar Int no pStatement", sqle);
		} 
		catch (Exception e) {
			raiseError("Erro não conhecido ao setar Int no pStatement", e);
		}
	}

	public void executeQuery(String sqlQuery) 
			throws ModelException {
		
		try {
			rs = statement.executeQuery(sqlQuery);
		} 
		catch (SQLException sqle) {
			raiseError("Erro ao executar DQL", sqle);
		} 
		catch (Exception e) {
			raiseError("Erro não conhecido ao executar DQL", e);
		}		
	}
	
	public void executeQuery() throws ModelException {
		try {
			rs = preparedStatement.executeQuery();
		} 
		catch (SQLException sqle) {
			raiseError("Erro ao executar DQL", sqle);
		} 
		catch (Exception e) {
			raiseError("Erro não conhecido ao executar DQL", e);
		}		
	}

	public boolean next() throws ModelException {
		try {
			return rs.next();
		}
		catch (SQLException sqle) {
			raiseError("Erro ao percorrer RS", sqle);
		} 
		catch (Exception e) {
			raiseError("Erro não conhecido ao percorrer RS", e);
		}

		return false;
	}

	public String getString(String column) 
			throws ModelException {
		
		try {
			return rs.getString(column);
		}
		catch (SQLException sqle) {
			raiseError("Erro ao recuperar String do RS", sqle);
		} 
		catch (Exception e) {
			raiseError("Erro não conhecido ao recuperar String do RS", e);
		}
		
		return "";
	}
	public Date getDate(String column) 
			throws ModelException {
		
		try {
			return rs.getDate(column);
		}
		
		catch (SQLException sqle) {
			raiseError("Erro ao recuperar Data do RS", sqle);
		} 
		catch (Exception e) {
			raiseError("Erro não conhecido ao recuperar Data do RS", e);
		}
		
		return null;
	}
	public int getInt(String column) throws ModelException {
		try {
			return rs.getInt(column);
		}
		
		catch (SQLException sqle) {
			raiseError("Erro ao recuperar Int do RS", sqle);
		} 
		catch (Exception e) {
			raiseError("Erro não conhecido ao recuperar Int do RS", e);
		}
		
		return 0;
	}

	public void setDate(int i, java.sql.Date date) {
	}
}
