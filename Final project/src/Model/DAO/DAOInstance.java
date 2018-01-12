package Model.DAO;

public class DAOInstance {
	private static HibernateToDoListDAO myDAO = null;

	public static HibernateToDoListDAO getInstance() {
		if (myDAO == null) {
			myDAO = new HibernateToDoListDAO();
		}

		return myDAO;
	}
}
