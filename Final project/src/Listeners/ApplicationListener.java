package Listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Servlet listener that helps processing data about the online users.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

	public ApplicationListener() {
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/**
	 * Handles ServletContext initializing - whenever a Servlet got initialized,
	 * the Servlet listener initializing the Users information attributes to it.
	 * 
	 * @param arg0
	 *            The ServletContextEvent object.
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext application = arg0.getServletContext();
		application.setAttribute("UnKnownB", 0);
		application.setAttribute("firefox", 0);
		application.setAttribute("Netscape", 0);
		application.setAttribute("chrome", 0);
		application.setAttribute("Opera", 0);
		application.setAttribute("safari", 0);
		application.setAttribute("IE", 0);
		application.setAttribute("UnKnown", 0);
		application.setAttribute("IPhone", 0);
		application.setAttribute("Android", 0);
		application.setAttribute("Unix", 0);
		application.setAttribute("Mac", 0);
		application.setAttribute("Windows", 0);
	}
}
