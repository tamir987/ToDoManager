package JspTags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import Model.Classes.Item;
import java.io.*;

public class ToDoTableTag extends SimpleTagSupport {
	private Item item;

	public void setItems(Item item) {
		this.item = item;
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<td>" + item.getMission() + "</td>");
		out.println("<td>" + item.getLocation() + "</td>");
		out.println("<td>" + item.getFutureDate() + "</td>");
	}
}
