package Listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Request listener that helps processing data about the online users.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
@WebListener
public class RequestListener implements ServletRequestListener {

	public RequestListener() {
	}

	public void requestDestroyed(ServletRequestEvent arg0) {
	}

	/**
	 * Handles request initializing - whenever a request got initialized, the
	 * Request listener checks if the request's session already got the Users
	 * information attached to it as an attribute.
	 * 
	 * @param arg0
	 *            The ServletRequestEvent object.
	 */
	public void requestInitialized(ServletRequestEvent arg0) {
		HttpServletRequest req = (HttpServletRequest) arg0.getServletRequest();
		HttpSession session = req.getSession();
		checkIfSessionHaveInfo(session, req);
	}

	/**
	 * check if the request's session already got the Users information attached
	 * to it as an attribute. If it doesn't, the method gather all the
	 * information about the current user. Afterwards, attaching the relevant
	 * operation system and browser to the session as an attribute, and
	 * increasing the counting of them at the ServletContext's attributes.
	 * 
	 * @param session
	 *            The HttpSession object - current session.
	 * @param request
	 *            The HttpServletRequest object - current request.
	 */
	private void checkIfSessionHaveInfo(HttpSession session, HttpServletRequest request) {

		if (session.getAttribute("os") == null) {
			/*
			 * "User-Agent" is a string containing the information about the
			 * current user.
			 */
			String browserDetails = request.getHeader("User-Agent");
			String userAgent = browserDetails;
			String user = userAgent.toLowerCase();

			String os = getOS(userAgent);
			String browser = getBrowser(user);

			ServletContext application = session.getServletContext();
			/*
			 * Increasing the counting of the OS and BROWSER at the
			 * ServletContext attributes.
			 */
			int num = (int) application.getAttribute(os);
			application.setAttribute(os, num + 1);
			num = (int) application.getAttribute(browser);
			application.setAttribute(browser, num + 1);
			session.setAttribute("os", os);
			session.setAttribute("browser", browser);

		}
	}

	/**
	 * Handles the harvesting of the user's browser from the information about
	 * him.
	 * 
	 * @param user
	 *            A string containing information about the browser.
	 * @return A string representing the User's browser.
	 */
	private String getBrowser(String user) {
		if (user.contains("msie"))
			return "IE";
		else if (user.contains("safari") && user.contains("version"))
			return "safari";
		else if (user.contains("opr") || user.contains("opera"))
			return "Opera";
		else if (user.contains("chrome"))
			return "chrome";
		else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)
				|| (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1)
				|| (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1))
			return "Netscape";
		else if (user.contains("firefox"))
			return "firefox";
		else if (user.contains("rv"))
			return "IE";
		else
			return "UnKnownB";
	}

	/**
	 * Handles the harvesting of the user's operating system from the
	 * information about him.
	 * 
	 * @param userAgent
	 *            A string containing information about the operating system.
	 * @return A string representing the User's operating system.
	 */
	private String getOS(String userAgent) {
		if (userAgent.toLowerCase().indexOf("windows") >= 0)
			return "Windows";
		else if (userAgent.toLowerCase().indexOf("mac") >= 0)
			return "Mac";
		else if (userAgent.toLowerCase().indexOf("x11") >= 0)
			return "Unix";
		else if (userAgent.toLowerCase().indexOf("android") >= 0)
			return "Android";
		else if (userAgent.toLowerCase().indexOf("iphone") >= 0)
			return "IPhone";
		else
			return "UnKnown";
	}
}
