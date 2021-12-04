package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDaoImpl;

@WebServlet("/DeleteTag")
public class DeleteTagServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean isSuccess = false;
		String isbn13 = request.getParameter("isbin13");
		String tagName = request.getParameter("tagName");
		
		TagDAO tagDAOobj = new TagDaoImpl();
		
		isSuccess = tagDAOobj.removeTag(isbn13, tagName);
		
		if(isSuccess) {
			request.getSession().setAttribute("message", "Tag removed successfully");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("ViewBookDetails?isbn13=" + isbn13);
		} else {
			request.getSession().setAttribute("message", "There was a problem removing this tag");
			request.getSession().setAttribute("messageClass", "alert-danger");
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		}
	}
}