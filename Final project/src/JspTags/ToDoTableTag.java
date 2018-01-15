package JspTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import Model.Classes.Item;
import java.io.*;

/**
 * JSP Tag that prints a row of a table that represents an item of the user.
 * 
 * @author Tamir Schwartzberg (tamir5021@gmail.com).
 */
public class ToDoTableTag extends SimpleTagSupport {
	/**
	 * Represents the Item needed to be print.
	 */
	private Item item;

	/**
	 * Sets the Item needed to print.
	 * 
	 * @param item
	 *            An item object representing the item needed to be printed.
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Handles the print of the row as part of the table. row contains the Item
	 * attributes.
	 * 
	 * @throws JspException,
	 *             IOException.
	 */
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<td>" + item.getMission() + "</td>");
		out.println("<td>" + item.getLocation() + "</td>");
		out.println("<td>" + item.getFutureDate() + "</td>");
	}
}
