package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDaoImpl;
import examples.pubhub.model.Tag;
/**
 * Servlet implementation class ViewBookDetailsServlet
 */

// This is a "View" servlet, and has been named accordingly. All it does is send the user to a new JSP page
// But it also takes the opportunity to populate the session or request with additional data as needed.
@WebServlet("/ViewBookDetails")
public class ViewBookDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// The bookDetails.jsp page needs to have the details of the selected book saved to the request,
		// Otherwise it won't know what details to display. Ergo, we need to fetch those details before we
		// Actually redirect the user.
		
		String edit = request.getParameter("edit");
		String change = request.getParameter("change");
		
		String isbn13 = request.getParameter("isbn13");
		
		BookDAO dao = DAOUtilities.getBookDAO();
		Book book = dao.getBookByISBN(isbn13);
		
		TagDAO tagDAO = new TagDaoImpl();
		List<Tag> tags = tagDAO.getAllTagsByBook(isbn13);
		
		request.setAttribute("book", book);
		request.setAttribute("tags", tags);
	
		
		if(edit != null) {
			request.getRequestDispatcher("bookDetails.jsp").forward(request, response);
		
		} else {
			request.getRequestDispatcher("viewBookDetails.jsp").forward(request, response);	
		}

		
		// We can use a forward here, because if a user wants to refresh their browser on this page,
		// it will just show them the most recent details for their book. There's no risk of data
		// miss-handling here.
		request.getRequestDispatcher("viewBookDetails.jsp").forward(request, response);
		
	}

}
