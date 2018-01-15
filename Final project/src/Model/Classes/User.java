package Model.Classes;

import java.util.Set;

import Model.Exceptions.ValidationException;

/**
 * Represents a User. Every User have many Items.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
public class User {

	/**
	 * Represents the User’s id.
	 */
	private int id;

	/**
	 * Represents the User’s name.
	 */
	private String name;

	/**
	 * Represents the User’s user_name.
	 */
	private String userName;

	/**
	 * Represents the User’s password.
	 */
	private String password;

	/**
	 * Represents the User’s Set of items.
	 */
	private Set<Item> items;

	public User() {
		super();
	}

	/**
	 * Creates a User’s with a specified name, user_name and password.
	 * 
	 * @param name
	 *            The User’s name.
	 * @param userName
	 *            The User’s user_name.
	 * @param password
	 *            The User’s password.
	 * @throws ValidationException
	 */
	public User(String name, String userName, String password) throws ValidationException {
		setName(name);
		setUserName(userName);
		setPassword(password);
	}

	/**
	 * Gets the User’s Id.
	 * 
	 * @return An integer number representing the User’s id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the User’s id.
	 * 
	 * @param id
	 *            An integer number containing the User’s id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the User’s name.
	 * 
	 * @return A string representing the User’s name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the User’s name.
	 * 
	 * @param name
	 *            A string containing the User’s name.
	 * @throws ValidationException
	 */
	public void setName(String name) throws ValidationException {
		Validator.checkName(name);
		this.name = name;
	}

	/**
	 * Gets the User’s user_name.
	 * 
	 * @return A string representing the User’s user_name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the User’s user_name.
	 * 
	 * @param userName
	 *            A string containing the User’s user_name.
	 * @throws ValidationException
	 */
	public void setUserName(String userName) throws ValidationException {
		Validator.checkUserName(userName);
		this.userName = userName;
	}

	/**
	 * Gets the User’s password.
	 * 
	 * @return A string representing the User’s password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the User’s password.
	 * 
	 * @param password
	 *            A string containing the User’s password.
	 * @throws ValidationException
	 */
	public void setPassword(String password) throws ValidationException {
		Validator.checkPass(password);
		this.password = password;
	}

	/**
	 * Gets the User’s Set of items.
	 * 
	 * @return A Set<Item> representing the User’s Set of items.
	 */
	public Set<Item> getItems() {
		return items;
	}

	/**
	 * Sets the User’s Set of items.
	 * 
	 * @param items
	 *            A Set<Item> containing the User’s Set of items.
	 */
	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + name + ", " + userName + ", " + password + ", " + items.size() + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof User))
			return false;
		User otherUser = (User) other;
		if ((id != otherUser.getId()) || (!name.equals(otherUser.getName()))
				|| (!userName.equals(otherUser.getUserName())) || (!password.equals(otherUser.getPassword())))// ||
																												// (items.size()
																												// !=
																												// otherUser.items.size()))
			return false;
		return true;
	}
}