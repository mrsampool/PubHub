package examples.pubhub.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;



public interface TagDAO {
	
	public boolean addTag(String isbn, String tagName);
	public boolean removeTag(String isbn, String tagName);
	
	public boolean updateTags( HttpServletRequest request );
	
	public List<Tag> getAllTagsByBook(String isbn);
	public List<Book> getAllBooksByTag(String tagName);
}