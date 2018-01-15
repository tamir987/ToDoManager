package Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter that measure time between a Servlet request and its response.
 * This filter belongs to the Main servlet.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
@WebFilter(servletNames = "Main", filterName = "MainFilter")
public class TimeFiltering implements Filter {

	public TimeFiltering() {
	}

	public void destroy() {
	}

	/**
	 * The filter measure current time, and attach it as attribute to the
	 * request.
	 * 
	 * @param request
	 *            The request to the servlet.
	 * @param response
	 *            The response of the servlet.
	 * @param chain
	 *            The FilterChain object.
	 * @throws ServletException,
	 *             IOException.
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long startTime = System.currentTimeMillis();
		request.setAttribute("time", startTime);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
