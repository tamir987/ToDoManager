package Model.HibernateFactory;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import Model.Exceptions.ConnectionException;

/**
 * Represents the sessionFactory object. handle it in a Singleton way.
 */
public class HibernateFactory {

	/**
	 * Represents the static SessionFactory object.
	 */
	private static SessionFactory sessionFactory;

	/**
	 * Constructs a new Singleton SessionFactory.
	 * 
	 * @return the SessionFactory object after building it
	 * @throws ConnectionException
	 */
	public static SessionFactory buildSessionFactory() throws ConnectionException {
		if (sessionFactory != null) {
			closeFactory();
		}
		return configureSessionFactory();
	}

	/**
	 * Builds a SessionFactory object, if it hasn't been already.
	 * 
	 * @throws ConnectionException.
	 */
	public static SessionFactory buildIfNeeded() throws ConnectionException {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		try {
			return configureSessionFactory();
		} catch (HibernateException e) {
			throw new ConnectionException(e);
		}
	}

	/**
	 * Gets the SessionFactory object.
	 * 
	 * @return The SessionFactory object.
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Gets the Session object after building it, if needed.
	 * 
	 * @return The Session object.
	 * @throws ConnectionException.
	 */
	public static Session openSession() throws ConnectionException {
		buildIfNeeded();
		return sessionFactory.openSession();
	}

	/**
	 * Close the SessionFactory object.
	 */
	public static void closeFactory() {
		if (sessionFactory != null) {
			try {
				sessionFactory.close();
			} catch (HibernateException e) {
				System.out.println("Couldn't close SessionFactory :" + e.getMessage());
			}
		}
	}

	/**
	 * Close the Session object.
	 */
	public static void close(Session session) {
		if (session != null) {
			try {
				session.close();
			} catch (HibernateException e) {
				System.out.println("Couldn't close Session :" + e.getMessage());
			}
		}
	}

	/**
	 * roll back the Session object, by it transaction.
	 * 
	 * @param currentTransaction
	 *            An Transaction object containing the Session object's
	 *            transaction.
	 */
	public static void rollback(Transaction currentTransaction) {
		try {
			if (currentTransaction != null) {
				currentTransaction.rollback();
			}
		} catch (HibernateException e) {
			System.out.println("Couldn't rollback Transaction :" + e.getMessage());
		}
	}

	/**
	 * build and configure the SessionFactory object.
	 * 
	 * @return the SessionFactory object, after building it.
	 * @throws HibernateException
	 */
	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		return sessionFactory;
	}
}