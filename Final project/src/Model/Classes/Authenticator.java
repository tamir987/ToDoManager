package Model.Classes;

import static Util.Accesses.ADMIN_PASSWORD;
import static Util.Accesses.ADMIN_USERNAME;

import java.util.List;
import Model.DAO.HibernateToDoListDAO;
import Model.Exceptions.ConnectionException;
import Model.Exceptions.DataAccessLayerException;

public class Authenticator {
	private User user;
	private String message;

	public Authenticator() {
		user = null;
		message = "";
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return this.user;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

	@SuppressWarnings("unchecked")
	public boolean authenticate(String username, String password, HibernateToDoListDAO data)
			throws ConnectionException, DataAccessLayerException {
		if ((username.equals(ADMIN_USERNAME)) || (password.equals(ADMIN_PASSWORD))) {
			setMessage("You are trying to do illegal action. pleate try something else!");
			return true;
		}
		List<User> users = (List<User>) data.getByField(User.class, "userName", username);
		for (User item : users) {
			if (password.equals(item.getPassword())) {
				setMessage("Username or password already exist!");
				setUser(item);
				return true;
			}
		}
		return false;
	}
}