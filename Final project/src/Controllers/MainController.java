package Controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.Classes.Authenticator;
import Model.Classes.Item;
import Model.Classes.User;
import Model.Classes.Validator;
import Model.DAO.DAOInstance;
import Model.DAO.HibernateToDoListDAO;
import Model.Exceptions.ConnectionException;
import Model.Exceptions.DataAccessLayerException;
import Model.Exceptions.ValidationException;
import static Util.Views.*;
import static Util.Accesses.*;

/**
 * Servlet that controls all processes.
 * 
 * GET requests to the servlet redirects to login page - the application home
 * page. POST requests to the servlets arrives with "action" attribute which
 * help the servlet to determine which action to do - process the request's
 * information or forward it to a specific page.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 * Youtube link : https://youtu.be/kfDwyxXmwJM
 */
@WebServlet(name = "Main", urlPatterns = "/Main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainController() {
		super();
	}

	/**
	 * Get a request and forward it to the login page - the application home
	 * page.
	 * 
	 * @param response
	 *            The response of the servlet.
	 * @param request
	 *            The request to the servlet.
	 * @throws ServletException,
	 *             IOException.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
	}

	/**
	 * Handles the POST request - process it and forward the request to the
	 * right location.
	 * 
	 * @param response
	 *            The response of the servlet.
	 * @param request
	 *            The request to the servlet.
	 * @throws ServletException,
	 *             IOException.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		HibernateToDoListDAO data = getDAO(session);
		String currentPage = null;
		String action = request.getParameter("action");
		/*
		 * The "action" parameter represents the action the servlet need to do.
		 * if it catches an exception, it forward the request to the same page
		 * with an error message, or simply forward it to the error page.
		 */
		try {
			switch (action) {
			case "Login":
				currentPage = LOGIN_PAGE;
				rd = handleLogin(session, request, data);
				break;
			case "ToAdmin":
				rd = request.getRequestDispatcher(ADMIN_PAGE);
				break;
			case "ToRegister":
				rd = request.getRequestDispatcher(REGISTER_PAGE);
				break;
			case "Register":
				currentPage = REGISTER_PAGE;
				rd = handleRegister(session, request, data);
				break;
			case "AddItem":
				currentPage = CREATE_TODO_PAGE;
				rd = handleAddItem(session, request, data);
				break;
			case "DeleteItem":
				rd = handleDeleteItem(session, request, data);
				break;
			case "ToAdd":
				rd = request.getRequestDispatcher(CREATE_TODO_PAGE);
				break;
			case "ToEditItem":
				rd = handleToEditItem(session, request, data);
				break;
			case "EditItem":
				currentPage = UPDATE_TODO_PAGE;
				rd = handleEditItem(session, request, data);
				break;
			case "Logout":
				session.invalidate();
				rd = request.getRequestDispatcher(LOGIN_PAGE);
				break;
			case "DeleteUser":
				rd = handleDeleteUser(session, request, data);
				break;
			case "UserZone":
				currentPage = ACCOUNT_PAGE;
				rd = handleUserZone(session, request, data);
				break;
			case "ToToDo":
				rd = request.getRequestDispatcher(TABLE_PAGE);
				break;
			case "ToUser":
				rd = request.getRequestDispatcher(ACCOUNT_PAGE);
				break;
			case "ToLogin":
				rd = request.getRequestDispatcher(LOGIN_PAGE);
				break;
			}
		} catch (ValidationException e) {
			request.setAttribute("errorMessage", e.getMessage());
			rd = request.getRequestDispatcher(currentPage);
		} catch (ConnectionException e) {
			request.setAttribute("errorMessage", "Problem with connection to database!");
			rd = request.getRequestDispatcher(ERROR_PAGE);
		} catch (DataAccessLayerException e) {
			request.setAttribute("errorMessage", "Problem with working with database!");
			rd = request.getRequestDispatcher(ERROR_PAGE);
		}
		rd.forward(request, response);
	}

	/**
	 * Check if current session already used the data access object. if it
	 * doesn't, it asks for an instance of the data access object.
	 * 
	 * @param session
	 *            Current HttpSession object - the user session.
	 * @return A HibernateToDoListDAO object representing the data access
	 *         object.
	 */
	private HibernateToDoListDAO getDAO(HttpSession session) {

		HibernateToDoListDAO data = null;
		if (session.getAttribute("data") == null) {
			data = DAOInstance.getInstance();
			session.setAttribute("data", data);
		} else
			data = (HibernateToDoListDAO) session.getAttribute("data");
		return data;
	}

	/**
	 * Handle the login request. authenticate & validate the input. If the
	 * authentication & validation succeed - it attaches the User to the
	 * session, and forward the request inside the application. If the
	 * authentication failed - it forward the request to the same page, but with
	 * an error message (meaning - there is no user with same info). if the
	 * validation failed - it throws a ValidationException. If any problem with
	 * the database occurs - it throws ConnectionException or
	 * DataAccessLayerException. In case of any exception thrown - the doPost
	 * method handles it.
	 * 
	 * @param session
	 *            Current HttpSession object - the user session.
	 * @param request
	 *            The request to the servlet.
	 * @param data
	 *            The data access object.
	 * @return A RequestDispatcher object representing the next page the request
	 *         should be forwarded to.
	 * @throws ValidationException,
	 *             ConnectionException, DataAccessLayerException.
	 */

	private RequestDispatcher handleLogin(HttpSession session, HttpServletRequest request, HibernateToDoListDAO data)
			throws ValidationException, ConnectionException, DataAccessLayerException {

		RequestDispatcher rd = null;
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		// handle admin Login
		if ((userName.equals(ADMIN_USERNAME)) && (password.equals(ADMIN_PASSWORD)))
			rd = request.getRequestDispatcher(ADMIN_PAGE);
		// handle user login
		else {
			/*
			 * Validate username & password by regex, and then authenticate them
			 * with the database.
			 */
			Validator.checkUserName(userName);
			Validator.checkPass(password);

			Authenticator authenticator = new Authenticator();

			if (authenticator.authenticate(userName, password, data)) {
				// User attachment to the session.
				User user = authenticator.getUser();
				session.setAttribute("user", user);
				rd = request.getRequestDispatcher(TABLE_PAGE);
			} else {
				request.setAttribute("errorMessage", "Invalid Credentials!!");
				rd = request.getRequestDispatcher(LOGIN_PAGE);
			}
		}
		return rd;
	}

	/**
	 * Handle the register request. authenticate & validate the input. If the
	 * authentication & validation succeed - it attaches the User to the
	 * session, and forward the request inside the application. If the
	 * authentication failed - it forward the request to the same page, but with
	 * an error message(meaning - there is already a user with same info). if
	 * the validation failed - it throws a ValidationException. If any problem
	 * with the database occurs - it throws ConnectionException or
	 * DataAccessLayerException. In case of any exception thrown - the doPost
	 * method handles it.
	 * 
	 * @param session
	 *            Current HttpSession object - the user session.
	 * @param request
	 *            The request to the servlet.
	 * @param data
	 *            The data access object.
	 * @return A RequestDispatcher object representing the next page the request
	 *         should be forwarded to.
	 * @throws ValidationException,
	 *             ConnectionException, DataAccessLayerException.
	 */

	private RequestDispatcher handleRegister(HttpSession session, HttpServletRequest request, HibernateToDoListDAO data)
			throws ValidationException, ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String name = request.getParameter("name");
		// Validate password and it's confirmation.
		Validator.checkPassAndConfirmation(password, confirmPassword);
		Authenticator authenticator = new Authenticator();

		if (authenticator.authenticate(userName, password, data)) {
			request.setAttribute("errorMessage", authenticator.getMessage());
			rd = request.getRequestDispatcher(REGISTER_PAGE);
		} else {
			/*
			 * Create a new user & insert it to the database. if it can't create
			 * the user - it throws a ValidationException. otherwise, it attach
			 * the user to the session.
			 */
			User user = new User(name, userName, password);
			data.save(user);
			session.setAttribute("user", user);
			rd = request.getRequestDispatcher(TABLE_PAGE);
		}
		return rd;
	}

	/**
	 * Handle the new item adding request. validates the input by trying to
	 * create a new Item. If the validation succeed, so it creates a new item
	 * and save it in the database. then it forward the request to the User's
	 * table of items. if the validation failed - it throws a
	 * ValidationException. If any problem with the database occurs - it throws
	 * ConnectionException or DataAccessLayerException. In case of any exception
	 * thrown - the doPost method handles it.
	 * 
	 * @param session
	 *            Current HttpSession object - the user session.
	 * @param request
	 *            The request to the servlet.
	 * @param data
	 *            The data access object.
	 * @return A RequestDispatcher object representing the next page the request
	 *         should be forwarded to - in this case, the TABLE_PAGE.
	 * @throws ValidationException,
	 *             ConnectionException, DataAccessLayerException.
	 */
	private RequestDispatcher handleAddItem(HttpSession session, HttpServletRequest request, HibernateToDoListDAO data)
			throws ValidationException, ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		User user = (User) session.getAttribute("user");
		String mission = request.getParameter("mission");
		String location = request.getParameter("location");
		String futureDate = request.getParameter("futureDate");
		/*
		 * Trying to create a new Item. if it fails, it throws
		 * ValidationException.
		 */
		Item item = new Item(mission, location, futureDate, user);
		data.save(item);
		/*
		 * Update the user - because it got a new Item added to it's list - and
		 * attach it to the session.
		 */
		User newUser = (User) data.getById(User.class, user.getId());
		session.setAttribute("user", newUser);
		rd = request.getRequestDispatcher(TABLE_PAGE);
		return rd;
	}

	/**
	 * Handle the item deleting request. If any problem with the database occurs
	 * - it throws ConnectionException or DataAccessLayerException. In case of
	 * any exception thrown - the doPost method handles it.
	 * 
	 * @param session
	 *            Current HttpSession object - the user session.
	 * @param request
	 *            The request to the servlet.
	 * @param data
	 *            The data access object.
	 * @return A RequestDispatcher object representing the next page the request
	 *         should be forwarded to - in this case, the TABLE_PAGE.
	 * @throws ConnectionException,
	 *             DataAccessLayerException.
	 */
	private RequestDispatcher handleDeleteItem(HttpSession session, HttpServletRequest request,
			HibernateToDoListDAO data) throws ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		User user = (User) session.getAttribute("user");
		String itemId = request.getParameter("item");
		int id = Integer.parseInt(itemId);
		Item item = (Item) data.getById(Item.class, id);
		data.delete(item);
		/*
		 * Update the user - because it got an Item removed from it's list - and
		 * attach it to the session.
		 */
		User newUser = (User) data.getById(User.class, user.getId());
		session.setAttribute("user", newUser);
		rd = request.getRequestDispatcher(TABLE_PAGE);
		return rd;
	}

	/**
	 * Handle the transition to the UPDATE_TODO_PAGE request. before forwarding
	 * to the page which the user can update an Item in it, it get the Item by
	 * it's ID from the database and attach it to the request. If any problem
	 * with the database occurs - it throws ConnectionException or
	 * DataAccessLayerException. In case of any exception thrown - the doPost
	 * method handles it.
	 * 
	 * @param session
	 *            Current HttpSession object - the user session.
	 * @param request
	 *            The request to the servlet.
	 * @param data
	 *            The data access object.
	 * @return A RequestDispatcher object representing the next page the request
	 *         should be forwarded to - in this case, the UPDATE_TODO_PAGE.
	 * @throws ConnectionException,
	 *             DataAccessLayerException.
	 */
	private RequestDispatcher handleToEditItem(HttpSession session, HttpServletRequest request,
			HibernateToDoListDAO data) throws ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		String itemId = request.getParameter("item");
		int id = Integer.parseInt(itemId);
		Item item = (Item) data.getById(Item.class, id);
		// attachment of the Item to the request.
		request.setAttribute("itemToUpdate", item);
		rd = request.getRequestDispatcher(UPDATE_TODO_PAGE);
		return rd;
	}

	/**
	 * Handle the item updating request. validates the input by trying to set
	 * new attributes to the Item. If the validation succeed, it update the Item
	 * in the database. Then it forward the request to the User's table of
	 * items. if the validation failed - it throws a ValidationException. If any
	 * problem with the database occurs - it throws ConnectionException or
	 * DataAccessLayerException. In case of any exception thrown - the doPost
	 * method handles it.
	 * 
	 * @param session
	 *            Current HttpSession object - the user session.
	 * @param request
	 *            The request to the servlet.
	 * @param data
	 *            The data access object.
	 * @return A RequestDispatcher object representing the next page the request
	 *         should be forwarded to - in this case, the TABLE_PAGE.
	 * @throws ValidationException,
	 *             ConnectionException, DataAccessLayerException.
	 */
	private RequestDispatcher handleEditItem(HttpSession session, HttpServletRequest request, HibernateToDoListDAO data)
			throws ValidationException, ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		User user = (User) session.getAttribute("user");
		String mission = request.getParameter("mission");
		String location = request.getParameter("location");
		String futureDate = request.getParameter("futureDate");
		Item item = (Item) getServletContext().getAttribute("itemToUpdate");
		item.setMission(mission);
		item.setLocation(location);
		item.setFutureDate(futureDate);
		data.update(item);
		/*
		 * Update the user - because it got an Item updated - and attach it to
		 * the session.
		 */
		User newUser = (User) data.getById(User.class, user.getId());
		session.setAttribute("user", newUser);
		rd = request.getRequestDispatcher(TABLE_PAGE);
		return rd;
	}

	/**
	 * Handle the user deleting request. The data access object deletes the user
	 * from the Users table and it's Items from the Items table. Afterwards, it
	 * invalidate the session and forwards to the LOGIN_PAGE. If any problem
	 * with the database occurs - it throws ConnectionException or
	 * DataAccessLayerException. In case of any exception thrown - the doPost
	 * method handles it.
	 * 
	 * @param session
	 *            Current HttpSession object - the user session.
	 * @param request
	 *            The request to the servlet.
	 * @param data
	 *            The data access object.
	 * @return A RequestDispatcher object representing the next page the request
	 *         should be forwarded to - in this case, the LOGIN_PAGE.
	 * @throws ConnectionException,
	 *             DataAccessLayerException.
	 */
	private RequestDispatcher handleDeleteUser(HttpSession session, HttpServletRequest request,
			HibernateToDoListDAO data) throws ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		User user = (User) session.getAttribute("user");
		data.delete(user);
		session.invalidate();
		rd = request.getRequestDispatcher(LOGIN_PAGE);
		return rd;
	}

	/**
	 * Handle the user updating request. validates the input by trying to set
	 * new attributes to the User. If the validation succeed, it update the User
	 * in the database. Then it forward the request to the User's table of
	 * items. if the validation failed - it throws a ValidationException. If any
	 * problem with the database occurs - it throws ConnectionException or
	 * DataAccessLayerException. In case of any exception thrown - the doPost
	 * method handles it.
	 * 
	 * @param session
	 *            Current HttpSession object - the user session.
	 * @param request
	 *            The request to the servlet.
	 * @param data
	 *            The data access object.
	 * @return A RequestDispatcher object representing the next page the request
	 *         should be forwarded to - in this case, the TABLE_PAGE.
	 * @throws ValidationException,
	 *             ConnectionException, DataAccessLayerException.
	 */
	private RequestDispatcher handleUserZone(HttpSession session, HttpServletRequest request, HibernateToDoListDAO data)
			throws ValidationException, ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		User user = (User) session.getAttribute("user");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		// Validate password and it's confirmation.
		Validator.checkPassAndConfirmation(password, confirmPassword);
		user.setName(name);
		user.setPassword(password);
		/*
		 * Update the user - because it got updated - and attach it to the
		 * session.
		 */
		data.update(user);
		session.setAttribute("user", user);
		rd = request.getRequestDispatcher(TABLE_PAGE);
		return rd;
	}
}
