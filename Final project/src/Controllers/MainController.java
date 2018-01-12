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
 * Servlet implementation class MainController
 */
@WebServlet(name = "Main", urlPatterns = "/Main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		HibernateToDoListDAO data = getDAO(session);
		String action = request.getParameter("action");
		String currentPage = null;
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

	private HibernateToDoListDAO getDAO(HttpSession session) {
		HibernateToDoListDAO data = null;
		if (session.getAttribute("data") == null) {
			data = DAOInstance.getInstance();
			session.setAttribute("data", data);
		} else
			data = (HibernateToDoListDAO) session.getAttribute("data");
		return data;
	}

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
			Validator.checkUserName(userName);
			Validator.checkPass(password);
			Authenticator authenticator = new Authenticator();

			if (authenticator.authenticate(userName, password, data)) {
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

	private RequestDispatcher handleRegister(HttpSession session, HttpServletRequest request, HibernateToDoListDAO data)
			throws ValidationException, ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String name = request.getParameter("name");

		Validator.checkPassAndConfirmation(password, confirmPassword);
		Authenticator authenticator = new Authenticator();

		if (authenticator.authenticate(userName, password, data)) {
			request.setAttribute("errorMessage", authenticator.getMessage());
			rd = request.getRequestDispatcher(REGISTER_PAGE);
		} else {
			User user = new User(name, userName, password);
			data.save(user);
			session.setAttribute("user", user);
			rd = request.getRequestDispatcher(TABLE_PAGE);
		}
		return rd;
	}

	private RequestDispatcher handleAddItem(HttpSession session, HttpServletRequest request, HibernateToDoListDAO data)
			throws ValidationException, ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		User user = (User) session.getAttribute("user");
		String mission = request.getParameter("mission");
		String location = request.getParameter("location");
		String futureDate = request.getParameter("futureDate");
		Item item = new Item(mission, location, futureDate, user);
		data.save(item);
		User newUser = (User) data.getById(User.class, user.getId());
		session.setAttribute("user", newUser);
		rd = request.getRequestDispatcher(TABLE_PAGE);
		return rd;
	}

	private RequestDispatcher handleDeleteItem(HttpSession session, HttpServletRequest request,
			HibernateToDoListDAO data) throws ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		User user = (User) session.getAttribute("user");
		String itemId = request.getParameter("item");
		int id = Integer.parseInt(itemId);
		Item item = (Item) data.getById(Item.class, id);
		data.delete(item);
		User newUser = (User) data.getById(User.class, user.getId());
		session.setAttribute("user", newUser);
		rd = request.getRequestDispatcher(TABLE_PAGE);
		return rd;
	}

	private RequestDispatcher handleToEditItem(HttpSession session, HttpServletRequest request,
			HibernateToDoListDAO data) throws ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		String itemId = request.getParameter("item");
		int id = Integer.parseInt(itemId);
		Item item = (Item) data.getById(Item.class, id);
		request.setAttribute("itemToUpdate", item);
		rd = request.getRequestDispatcher(UPDATE_TODO_PAGE);
		return rd;
	}

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
		User newUser = (User) data.getById(User.class, user.getId());
		session.setAttribute("user", newUser);
		rd = request.getRequestDispatcher(TABLE_PAGE);
		return rd;
	}

	private RequestDispatcher handleDeleteUser(HttpSession session, HttpServletRequest request,
			HibernateToDoListDAO data) throws ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		User user = (User) session.getAttribute("user");
		data.delete(user);
		rd = request.getRequestDispatcher(LOGIN_PAGE);
		return rd;
	}

	private RequestDispatcher handleUserZone(HttpSession session, HttpServletRequest request, HibernateToDoListDAO data)
			throws ValidationException, ConnectionException, DataAccessLayerException {
		RequestDispatcher rd = null;
		User user = (User) session.getAttribute("user");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		Validator.checkPassAndConfirmation(password, confirmPassword);
		user.setName(name);
		user.setPassword(password);
		data.update(user);
		session.setAttribute("user", user);
		rd = request.getRequestDispatcher(TABLE_PAGE);
		return rd;
	}
}
