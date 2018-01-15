package Model.DAO;

import java.util.List;

import Model.Exceptions.ConnectionException;
import Model.Exceptions.DataAccessLayerException;

/**
 * Represents the ToDoList data access object interface. Generic data access
 * object interface.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
public interface IToDoListDAO {

	/**
	 * Get a list from specified table.
	 * 
	 * @return A list representing the object(s) of specified table.
	 * @param clazz
	 *            The table we want to extract from.
	 * @throws DataAccessLayerException,
	 *             ConnectionException.
	 */
	List<?> getAll(Class<?> clazz) throws DataAccessLayerException, ConnectionException;

	/**
	 * Get list from specified table by specific query.
	 * 
	 * @return A list representing object(s) selected by the query.
	 * @param clazz
	 *            The table we want to extract from.
	 * @param hql
	 *            The query.
	 * @throws DataAccessLayerException,
	 *             ConnectionException.
	 */
	List<?> getByQuery(Class<?> clazz, String hql) throws DataAccessLayerException, ConnectionException;

	/**
	 * Get list from specified table by specific field and value.
	 * 
	 * @return A list representing object(s) selected by the field and value.
	 * @param clazz
	 *            The table we want to extract from.
	 * @param field
	 *            The field.
	 * @param value
	 *            The value of the field.
	 * @throws DataAccessLayerException,
	 *             ConnectionException.
	 */
	List<?> getByField(Class<?> clazz, String field, String value) throws DataAccessLayerException, ConnectionException;

	/**
	 * Add object to his matching table.
	 * 
	 * @param obj
	 *            The object we want to add.
	 * @throws DataAccessLayerException,
	 *             ConnectionException.
	 */
	void save(Object obj) throws DataAccessLayerException, ConnectionException;

	/**
	 * Update object by finding the object in his matching table and replace it
	 * with the new object. it find it by Id.
	 * 
	 * @param obj
	 *            The object we want to Update.
	 * @throws DataAccessLayerException,
	 *             ConnectionException.
	 */
	void update(Object obj) throws DataAccessLayerException, ConnectionException;

	/**
	 * delete object by Id. first find it in his matching table, and then delete
	 * it.
	 * 
	 * @param clazz
	 *            The table we want to delete from.
	 * @param id
	 *            The id of the object we want to delete.
	 * @throws DataAccessLayerException,
	 *             ConnectionException.
	 */
	void delete(Class<?> clazz, int id) throws DataAccessLayerException, ConnectionException;

	/**
	 * delete specific object from his matching table.
	 * 
	 * @param obj
	 *            The object we want to delete.
	 * @throws DataAccessLayerException,
	 *             ConnectionException.
	 */
	void delete(Object obj) throws DataAccessLayerException, ConnectionException;

	/**
	 * Get Object from specified table by id.
	 * 
	 * @return An Object representing object selected by his id.
	 * @param clazz
	 *            The table we want to extract from.
	 * @param id
	 *            The id of the object.
	 * @throws DataAccessLayerException,
	 *             ConnectionException.
	 */
	Object getById(Class<?> clazz, int id) throws DataAccessLayerException, ConnectionException;
}
