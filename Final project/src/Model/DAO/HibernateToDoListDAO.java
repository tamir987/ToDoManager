package Model.DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import Model.Exceptions.ConnectionException;
import Model.Exceptions.DataAccessLayerException;
import Model.HibernateFactory.HibernateFactory;

/**
 * Represents the ToDoList data access object. Generic data access object.
 */
public class HibernateToDoListDAO implements IToDoListDAO {

	/**
	 * Creates a data access object. build the sessionFactory if needed.
	 * 
	 * @throws ConnectionException.
	 */
	public HibernateToDoListDAO() throws ConnectionException {
		HibernateFactory.buildIfNeeded();
	}

	public List<?> getByQuery(Class<?> clazz, String hql) throws DataAccessLayerException, ConnectionException {
		Session session = null;
		List<?> objects = null;
		try {
			session = startOperation();
			Query query = session.createQuery(hql);
			objects = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			handleException(e, session);
		} finally {
			HibernateFactory.close(session);
		}
		return objects;
	}

	public List<?> getAll(Class<?> clazz) throws DataAccessLayerException, ConnectionException {
		Session session = null;
		List<?> objects = null;
		try {
			session = startOperation();
			Query query = session.createQuery("from " + clazz.getName());
			objects = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			handleException(e, session);
		} finally {
			HibernateFactory.close(session);
		}
		return objects;
	}

	public void save(Object obj) throws DataAccessLayerException, ConnectionException {
		Session session = null;
		try {
			session = startOperation();
			session.save(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			handleException(e, session);
		} finally {
			HibernateFactory.close(session);
		}
	}

	public void update(Object obj) throws DataAccessLayerException, ConnectionException {
		Session session = null;
		try {
			session = startOperation();
			session.update(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			handleException(e, session);
		} finally {
			HibernateFactory.close(session);
		}
	}

	public void delete(Class<?> clazz, int id) throws DataAccessLayerException, ConnectionException {
		Session session = null;
		try {
			Object obj = getById(clazz, id);
			session = startOperation();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			handleException(e, session);
		} finally {
			HibernateFactory.close(session);
		}
	}

	public void delete(Object obj) throws DataAccessLayerException, ConnectionException {
		Session session = null;
		try {
			session = startOperation();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			handleException(e, session);
		} finally {
			HibernateFactory.close(session);
		}
	}

	public Object getById(Class<?> clazz, int id) throws DataAccessLayerException, ConnectionException {
		Session session = null;
		Object obj = null;
		try {
			session = startOperation();
			obj = session.load(clazz, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			handleException(e, session);
		} finally {
			HibernateFactory.close(session);
		}
		return obj;
	}

	public List<?> getByField(Class<?> clazz, String field, String value)
			throws DataAccessLayerException, ConnectionException {
		Session session = null;
		List<?> objects = null;
		try {
			session = startOperation();
			String hql = "FROM " + clazz.getName() + " WHERE " + field + " = '" + value + "'";
			Query query = session.createQuery(hql);
			objects = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			handleException(e, session);
		} finally {
			HibernateFactory.close(session);
		}
		return objects;
	}

	/**
	 * roll back the last operation on database.
	 * 
	 * @param e
	 *            The HibernateException we got.
	 * @param session
	 *            The session we currently using.
	 * @throws DataAccessLayerException.
	 */
	private void handleException(HibernateException e, Session session) throws DataAccessLayerException {
		HibernateFactory.rollback(session.getTransaction());
		throw new DataAccessLayerException(e);
	}

	/**
	 * Open session and begin transaction.
	 * 
	 * @return a new session after it began transaction.
	 * @throws ConnectionException.
	 */
	private Session startOperation() throws ConnectionException {
		Session session = HibernateFactory.openSession();
		session.beginTransaction();
		return session;
	}
}