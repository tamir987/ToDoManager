package Listeners;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session listener that helps processing data about the online users.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	public SessionListener() {
	}

	/**
	 * Handles session creating - whenever a session got created, the Session
	 * listener add it to the ServletContext's Set of HttpSession attribute.
	 * 
	 * @param arg0
	 *            The HttpSessionEvent object.
	 */
	@SuppressWarnings("unchecked")
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		if (application.getAttribute("sessions") == null) {
			application.setAttribute("sessions", new HashSet<HttpSession>());
		}
		Set<HttpSession> set = (Set<HttpSession>) application.getAttribute("sessions");
		set.add(session);
	}

	/**
	 * Handles session destroying - whenever a session got destroyed, the
	 * Session listener remove it from the ServletContext's Set of HttpSession
	 * attribute. The Session listener also reducing the counting of the
	 * relevant operating system and browser at the ServletContext's attributes.
	 * 
	 * @param arg0
	 *            The HttpSessionEvent object.
	 */
	@SuppressWarnings("unchecked")
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		Set<HttpSession> set = (HashSet<HttpSession>) application.getAttribute("sessions");
		set.remove(session);
		String os = (String) session.getAttribute("os");
		String browser = (String) session.getAttribute("browser");
		int num = (int) application.getAttribute(os);
		application.setAttribute(os, num - 1);
		num = (int) application.getAttribute(browser);
		application.setAttribute(browser, num - 1);
	}
}