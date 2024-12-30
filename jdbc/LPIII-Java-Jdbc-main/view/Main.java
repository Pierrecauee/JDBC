package view;

import model.ModelException;
import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.entities.User;

public class Main {
	public static void main(String[] args) {
//		User emerson = new User(7);
//		emerson.setName("Emerson DAO Novo");
//		emerson.setEmail("emerson.novo@mail.com");
//		emerson.setSex('M');
		
		UserDAO dao = (UserDAO) DAOFactory.getDAO(UserDAO.class);
		
		User user = null;
		try {
			user = dao.findById(5);
		} catch (ModelException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause().getMessage());
			e.getCause().printStackTrace();
		}
		
		if (user != null) {
			System.out.println("Id: " + user.getId());
			System.out.println("Nome: " + user.getName());
			System.out.println("Sexo: " + user.getSex());
			System.out.println("Email: " + user.getEmail());
			System.out.println();
		}

	}
}
