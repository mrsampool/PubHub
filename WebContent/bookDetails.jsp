	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
		
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>PUBHUB <small>Book Details - ${book.isbn13 }</small></h1>
		<hr class="book-primary">
		
		<form action="UpdateBook" method="post" class="form-horizontal">
		  
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" />
		  
		  <div class="form-group">
		    <label for="title" class="col-sm-4 control-label">Title</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="title" name="title" placeholder="Title" required="required" value="${book.title }" />
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="author" class="col-sm-4 control-label">Author</label>
		    <div class="col-sm-5">
		      <input type="text" class="form-control" id="author" name="author" placeholder="Author" required="required" value="${book.author }" />
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="price" class="col-sm-4 control-label">Price</label>
		    <div class="col-sm-5">
		      <input type="number" step="0.01" class="form-control" id="price" name="price" placeholder="Price" required="required" value="${book.price }" />
		    </div>
		  </div>
		  
   		  <div class="form-group">
		    <label for="tag" class="col-sm-4 control-label">Tags <br></label>
		    <div class="col-sm-5">
		    
		    	<div id="tags" style="display: flex; justify-content: flex-start; align-items: center; flex-wrap: wrap;">
		    	
		    		<c:forEach var="tag" items="${tags}">
		    		
		    			<div class="form-control" style="width: fit-content; margin: 1rem;">
		    			
		    				<input id="tag-${tag.tagName }" type="checkbox" name="tag-${tag.tagName }" value="${tag.tagName}" checked>
		    				<label for="tag-${tag.tagName }"> ${ tag.tagName } </label>
		    				
		    			</div>
		    			
		    		</c:forEach>
		    	
		    	</div>
		    </div>
		  </div>
		  
  		  <div class="form-group">
		    <label for="author" class="col-sm-4 control-label">Add Tags</label>
		    <div class="col-sm-5">
		      <input 
		      	type="text" 
		      	class="form-control" 
		      	id="author" 
		      	name="newTags" 
		      	placeholder="Seperate new tags by commas" 
	      		/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1" style="display: flex;">
		    
		      <button type="submit" class="btn btn-info" style="margin-right: 1rem">Update</button>
		      


	      	</div>
		  </div>
		  
		</form>
		
   		      <a href="ViewBookDetails?isbn13=${book.isbn13 }">
		      	<button class="btn btn-danger">Exit</button>
		      </a>

	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />
