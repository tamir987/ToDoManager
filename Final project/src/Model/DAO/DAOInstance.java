package Model.DAO;

/**
 * Represents the singleton implementation of the ToDoList data access object.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
public class DAOInstance {

	/**
	 * Represents the static data access object.
	 */
	private static HibernateToDoListDAO myDAO = null;

	/**
	 * Constructs a new Singleton HibernateToDoListDAO object.
	 * 
	 * @return the HibernateToDoListDAO object after building it if necessary.
	 */
	public static HibernateToDoListDAO getInstance() {
		if (myDAO == null) {
			myDAO = new HibernateToDoListDAO();
		}

		return myDAO;
	}
}