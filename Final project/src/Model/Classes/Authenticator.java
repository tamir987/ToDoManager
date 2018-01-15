package Model.Classes;

import static Util.Accesses.ADMIN_PASSWORD;
import static Util.Accesses.ADMIN_USERNAME;

import java.util.List;
import Model.DAO.HibernateToDoListDAO;
import Model.Exceptions.ConnectionException;
import Model.Exceptions.DataAccessLayerException;

/**
 * Represents an Authenticator object. This class have one method, that checks
 * if the username & password exist in the database.
 */
public class Authenticator {
	/**
	 * Represents the User the class found after it's authentication.
	 */
	private User user;
	/**
	 * Represents the error message in case of authentication failure.
	 */
	private String message;

	/**
	 * Initialize the user & message objects.
	 */
	public Authenticator() {
		user = null;
		message = "";
	}

	/**
	 * Sets the Authenticator's User.
	 * 
	 * @param user
	 *            A User object belongs to the Authenticator class.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the Authenticator's User.
	 * 
	 * @return A User object representing the user which have been found in the
	 *         database.
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * Sets the Authenticator message.
	 * 
	 * @param message
	 *            A string containing special message of the Authenticator
	 *            class.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the Authenticator message.
	 * 
	 * @return A string special message of the Authenticator class.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Check if username & password exist in the database. Before checking the
	 * database it check if username & password are the same as ADMIN_USERNAME &
	 * ADMIN_PASSWORD. if it doesn't, it check in the database. if username &
	 * password exist in the database or equals ADMIN_USERNAME & ADMIN_PASSWORD
	 * - it return true and set a proper message. otherwise, it return false and
	 * set a proper message. If any problem with the database occurs - it throws
	 * ConnectionException or DataAccessLayerException.
	 * 
	 * @param username
	 *            A string containing the username needs to be authenticated.
	 * @param password
	 *            A string containing the password needs to be authenticated..
	 * @param data
	 *            A HibernateToDoListDAO object representing the data access
	 *            object.
	 * @return A boolean object that determine if the authentication succeed or
	 *         failed.
	 * @throws ConnectionException,
	 *             DataAccessLayerException.
	 */
	@SuppressWarnings("unchecked")
	public boolean authenticate(String username, String password, HibernateToDoListDAO data)
			throws ConnectionException, DataAccessLayerException {
		// check if username & password equals ADMIN_USERNAME & ADMIN_PASSWORD.
		if ((username.equals(ADMIN_USERNAME)) || (password.equals(ADMIN_PASSWORD))) {
			setMessage("You are trying to do illegal action. pleate try something else!");
			return true;
		}
		/*
		 * Get from database all Users that their username equals the username
		 * we got in the method. than check if any of the users got password
		 * equals the password in the method.
		 */
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