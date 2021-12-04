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
		  
		  <input type="hidden" class="form-control" id="isbn13" name="isbn13" required="required" value="${book.isbn13 }" />
		  
		  
	    <label for="title" class="col-sm-4 control-label text-right">Title</label>
	    <div class="col-sm-5">
	      <p class="form-control text-left" id="title">${book.title } </p>
	    </div>
	    
   	    <label for="author" class="col-sm-4 control-label text-right">Author</label>
	    <div class="col-sm-5">
	      <p class="form-control text-left" id="author">${book.author } </p>
	    </div>
	    
   	    <label for="price" class="col-sm-4 control-label text-right">Price</label>
	    <div class="col-sm-5">
	      <p class="form-control text-left" id="price">${book.price } </p>
	    </div>
		  
   		  <div class="form-group">
		    <label for="tag" class="col-sm-4 control-label text-right">Tags <br></label>
		    <div class="col-sm-5">
		    
		    	<div id="tags" style="display: flex; justify-content: flex-start; align-items: center; flex-wrap: wrap;">
		    	
		    		<c:forEach var="tag" items="${tags}">
		    			
		    				<p class="form-control" style="width: fit-content; margin: 0.5rem;"> ${ tag.tagName } </p>
		    			
		    		</c:forEach>
		    	
		    	</div>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <a href="ViewBookDetails?isbn13=${book.isbn13 }&edit=true">
		      	<button class="btn btn-info">Edit</button>
		      </a>
		    </div>
		  </div>

	  </div>
	</header>
	
	<!-- Footer -->
	<jsp:include page="footer.jsp" />