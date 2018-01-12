package Listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Application Lifecycle Listener implementation class RequestListener
 *
 */
@WebListener
public class RequestListener implements ServletRequestListener {

	/**
	 * Default constructor.
	 */
	public RequestListener() {
	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent arg0) {
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent arg0) {
		HttpServletRequest req = (HttpServletRequest) arg0.getServletRequest();
		HttpSession session = req.getSession();
		checkIfSessionHaveInfo(session, req);
	}

	private void checkIfSessionHaveInfo(HttpSession session, HttpServletRequest request) {
		if (session.getAttribute("os") == null) {
			String browserDetails = request.getHeader("User-Agent");
			String userAgent = browserDetails;
			String user = userAgent.toLowerCase();

			String os = getOS(userAgent);
			String browser = getBrowser(user);

			ServletContext application = session.getServletContext();
			int num = (int) application.getAttribute(os);
			application.setAttribute(os, num + 1);
			num = (int) application.getAttribute(browser);
			application.setAttribute(browser, num + 1);
			session.setAttribute("os", os);
			session.setAttribute("browser", browser);

		}
	}

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
