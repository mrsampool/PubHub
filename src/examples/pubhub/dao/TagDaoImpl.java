package examples.pubhub.dao;

import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TagDaoImpl implements TagDAO{
	
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	public boolean addTag(String isbn, String tagName) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags values (?,?);";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			stmt.setString(2, tagName);
			
			if(stmt.executeUpdate() != 0)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		
		} finally {
			closeResources();
		}
		
	}
	public boolean removeTag(String isbn, String tagName) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE isbn_13=? AND tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			stmt.setString(2, tagName);
			
			if(stmt.executeUpdate() != 0)
				return true;
			
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		
		} finally {
			closeResources();
		}
	}
	
	public List<Tag> getAllTagsByBook(String isbn){
		
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Tag tag = new Tag();
				
				tag.setIsbn13(rs.getString("isbn_13"));
				tag.setTagName(rs.getString("tag_name"));
				
				tags.add(tag);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return tags;
	}
	public List<Book> getAllBooksByTag(String tagName){
		
		List<Book> books = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM books WHERE isbn_13 IN( SELECT isbn_13 FROM book_tags WHERE tag_name =?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tagName);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;
	}
	public boolean updateTags( HttpServletRequest request) {
		
		String isbn13 = request.getParameter("isbn13");
		
		//Separate New Tags & Get Old Tags
		String newTags = request.getParameter("newTags");
		String[] newTagsArray = newTags.split(",");
		List<Tag> oldTagsArray = getAllTagsByBook(isbn13);
		
		//Initialize Success tracker
		List<Boolean> tagSuccess = new ArrayList<>();
		
		//Check New Tags For Duplicates
		HashSet<String> uniqueTags = new HashSet<>();
		for (String tag : newTagsArray) {
			uniqueTags.add(tag.trim() );
		}
		
		//Remove Unchecked tags
		for(Tag oldTag : oldTagsArray) {
			
			String oldTagName = oldTag.getTagName();
			String oldTagParameter = request.getParameter("tag-" + oldTagName);
			
			if(oldTagParameter == null) {
				tagSuccess.add( removeTag(isbn13, oldTagName) ) ;
			};
		}
		
		//Add new tags
		for(String tag : uniqueTags) {
			
			if(tag.length() > 0) {
				tagSuccess.add( addTag(isbn13, tag) );
			}
		}
		return !tagSuccess.contains(false);
	}
	
	private void closeResources() {
		try {
			if(stmt != null)
				stmt.close();
		
		} catch (SQLException e) {
			System.out.println("Coud not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
