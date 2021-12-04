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


import examples.pubhub.model.Tag;

@WebServlet("/TagsByBook")

public class TagsByBookServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String isbn13 = request.getParameter("isbn13");
		
		TagDAO dao = new TagDaoImpl();
		
		List<Tag> tagList = dao.getAllTagsByBook(isbn13);
		
		request.getSession().setAttribute("tags", tagList);
		
		request.getRequestDispatcher("bookPublishingHome.jsp").forward(request, response);
	}

}
