package Model.Classes;

import Model.Exceptions.ValidationException;

/**
 * Represents an Item. Every Item have one User.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
public class Item {

	/**
	 * Represents the Item’s id.
	 */
	private int id;

	/**
	 * Represents the Item’s mission description.
	 */
	private String mission;

	/**
	 * Represents the Item’s mission location.
	 */
	private String location;

	/**
	 * Represents the Item’s Date of occurring.
	 */
	private String futureDate;

	/**
	 * Represents the Item’s User.
	 */
	private User user;

	public Item() {
		super();
	}

	/**
	 * Creates an Item’s with a specified mission, location, user and date of
	 * occurring. set the currentDate to date of adding.
	 * 
	 * @param mission
	 *            The Item’s mission description.
	 * @param location
	 *            The Item’s mission location.
	 * @param futureDate
	 *            The Item’s date of occurring.
	 * @param user
	 *            The Item’s user which it belongs to.
	 * @throws ValidationException
	 */
	public Item(String mission, String location, String futureDate, User user) throws ValidationException {
		setMission(mission);
		setLocation(location);
		setFutureDate(futureDate);
		setUser(user);
	}

	/**
	 * Gets the Item’s Id.
	 * 
	 * @return An integer number representing the Item’s id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the Item’s id.
	 * 
	 * @param id
	 *            An integer number containing the Item’s id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the Item’s mission.
	 * 
	 * @return A string representing the Item’s mission description.
	 */
	public String getMission() {
		return mission;
	}

	/**
	 * Sets the Item’s mission.
	 * 
	 * @param mission
	 *            A string containing the Item’s mission description.
	 * @throws ValidationException
	 */
	public void setMission(String mission) throws ValidationException {
		Validator.checkField(mission, "Mission");
		this.mission = mission;
	}

	/**
	 * Gets the Item’s location.
	 * 
	 * @return A string representing the Item’s mission location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the Item’s location.
	 * 
	 * @param location
	 *            A string containing the Item’s mission location.
	 * @throws ValidationException
	 */
	public void setLocation(String location) throws ValidationException {
		Validator.checkField(location, "Location");
		this.location = location;
	}

	/**
	 * Gets the Item’s Date of occurring.
	 * 
	 * @return A String representing the Item’s mission date of occurring.
	 */
	public String getFutureDate() {
		return futureDate;
	}

	/**
	 * Sets the Item’s Date of occurring.
	 * 
	 * @param futureDate
	 *            A String containing the Item’s mission date of occurring
	 * @throws ValidationException
	 */
	public void setFutureDate(String futureDate) throws ValidationException {
		Validator.checkDate(futureDate);
		this.futureDate = futureDate;
	}

	/**
	 * Gets the Item’s User.
	 * 
	 * @return A User object representing the Item’s user which it belongs to.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the Item’s User.
	 * 
	 * @param user
	 *            A User object containing the Item’s user which it belongs to.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + mission + ", " + location + ", " + futureDate + ", " + user.getId() + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Item))
			return false;
		Item otherItem = (Item) other;

		if ((id != otherItem.getId()) || (!mission.equals(otherItem.getMission()))
				|| (!location.equals(otherItem.getLocation())) || (!futureDate.equals(otherItem.getFutureDate()))
				|| (user.getId() != otherItem.user.getId()))
			return false;
		return true;
	}
}