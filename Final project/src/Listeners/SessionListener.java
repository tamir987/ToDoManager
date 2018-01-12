package Listeners;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public SessionListener() {
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		if (application.getAttribute("sessions") == null) {
			application.setAttribute("sessions", new HashSet<HttpSession>());
		}
		@SuppressWarnings("unchecked")
		Set<HttpSession> set = (Set<HttpSession>) application.getAttribute("sessions");
		set.add(session);
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		ServletContext application = session.getServletContext();
		@SuppressWarnings("unchecked")
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
