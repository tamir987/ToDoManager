package Listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ApplicationListener
 *
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ApplicationListener() {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		ServletContext application = arg0.getServletContext();
		application.removeAttribute("UnKnownB");
		application.removeAttribute("firefox");
		application.removeAttribute("Netscape");
		application.removeAttribute("chrome");
		application.removeAttribute("Opera");
		application.removeAttribute("safari");
		application.removeAttribute("IE");
		application.removeAttribute("UnKnown");
		application.removeAttribute("IPhone");
		application.removeAttribute("Android");
		application.removeAttribute("Unix");
		application.removeAttribute("Mac");
		application.removeAttribute("Windows");
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
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
