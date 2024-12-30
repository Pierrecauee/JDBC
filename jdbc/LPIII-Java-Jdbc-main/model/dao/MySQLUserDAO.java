package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.ModelException;
import model.entities.User;

class MySQLUserDAO implements UserDAO {

	@Override
	public boolean save(User user) throws ModelException {
		String sqlInsert = " INSERT INTO users "
				+ " VALUES(DEFAULT, ?, ?, ?); ";

		DataBaseHandler dbHandler = new DataBaseHandler();
		dbHandler.prepareStatement(sqlInsert);

		dbHandler.setString(1, user.getName());
		dbHandler.setString(2, user.getSex().toString());
		dbHandler.setString(3, user.getEmail());

		int rowsAffected =  dbHandler.executeUpdate();

		dbHandler.close();

		return rowsAffected > 0;
	}

	@Override
	public boolean update(User user) throws ModelException {
		String sqlUpdate = " UPDATE users "
				+ " SET nome = ?, sexo = ?, email = ? "
				+ " WHERE id = ?; ";


		DataBaseHandler dbHandler = new DataBaseHandler();
		dbHandler.prepareStatement(sqlUpdate);

		dbHandler.setString(1, user.getName());
		dbHandler.setString(2, user.getSex().toString());
		dbHandler.setString(3, user.getEmail());
		dbHandler.setInt(4, user.getId());

		int rowsAffected =  dbHandler.executeUpdate();

		dbHandler.close();

		return rowsAffected > 0;
	}

	@Override
	public boolean delete(User user) throws ModelException {
		String sqlDelete = "DELETE FROM users WHERE id = ?;";

		DataBaseHandler dbHandler = new DataBaseHandler();
		dbHandler.prepareStatement(sqlDelete);

		dbHandler.setInt(1, user.getId());

		int rowsAffected =  dbHandler.executeUpdate();

		dbHandler.close();

		return rowsAffected > 0;
	}

	@Override
	public List<User> listAll() throws ModelException {
		List<User> users = new ArrayList<>();

		String sqlQuery = " SELECT * FROM users ";

		DataBaseHandler dbHandler = new DataBaseHandler();
		dbHandler.statement();

		dbHandler.executeQuery(sqlQuery);

		while (dbHandler.next()) {
			int userId = dbHandler.getInt("id");
			String userName = dbHandler.getString("nome");
			String userSex = dbHandler.getString("sexo");
			String userEmail = dbHandler.getString("email");

			User user = new User(userId);
			user.setName(userName);
			user.setSex(userSex.charAt(0));
			user.setEmail(userEmail);
			
			users.add(user);
		}

		dbHandler.close();

		return users;
	}

	@Override
	public User findById(int id) throws ModelException {
		String sql = "SELECT * FROM users WHERE id = ?;";
		
		DataBaseHandler dbHandler = new DataBaseHandler();
		dbHandler.prepareStatement(sql);
		dbHandler.setInt(1, id);

		dbHandler.executeQuery();
		User user = null;
		while (dbHandler.next()) {
			int userId = dbHandler.getInt("id");
			String userName = dbHandler.getString("nome");
			String userSex = dbHandler.getString("sexo");
			String userEmail = dbHandler.getString("email");

			user = new User(userId);
			user.setName(userName);
			user.setSex(userSex.charAt(0));
			user.setEmail(userEmail);
			break;
		}
		dbHandler.close();

		return user;
	}
}
