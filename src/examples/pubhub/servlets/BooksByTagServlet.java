package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDaoImpl;
import examples.pubhub.model.Book;


@WebServlet("/BooksByTag")

public class BooksByTagServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String searchTag = request.getParameter("tagSearchInput");
		System.out.println("Search Tag: " + searchTag);
		
		TagDAO dao = new TagDaoImpl();
		
		List<Book> bookList = dao.getAllBooksByTag(searchTag);
		
		request.getSession().setAttribute("books", bookList);
		
		if(bookList.size()<1) {
			request.getSession().setAttribute("message", "No books have the tag you searched for");
			request.getSession().setAttribute("messageClass", "alert-warning");
		}
		
		request.getRequestDispatcher("bookSearch.jsp").forward(request, response);
	}

}
