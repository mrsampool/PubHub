package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDaoImpl;

import examples.pubhub.model.Tag;
import examples.pubhub.model.Book;

public class TestTagDAO {
	static final TagDAO dao = new TagDaoImpl();
	
	public boolean testAdd(String isbn, String tagName) {
		return dao.addTag(isbn,tagName);
	}
	
	public static boolean testRemoveTag(String isbn, String tagName) {
		return dao.removeTag(isbn,tagName);
	}
	
	public static void testTagsByBook(String isbn){
		List<Tag> list = dao.getAllTagsByBook(isbn);
		
		for(int i = 0; i < list.size(); i++) {
			Tag t = list.get(i);
			System.out.println(t.getTagName());
		}
	}
	
	public static void testBooksByTag(String tagName) {
		List<Book> list = dao.getAllBooksByTag(tagName);
		
		for(Book book : list) {
			System.out.println(book.getIsbn13());
		}
	}
	
	public static void main(String[] args) {
		
		String isbn = "1111111111111";
		String tag = "good";
		
		testBooksByTag(tag);
	}

}
