<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    
"http://www.w3.org/TR/html4/loose.dtd"> 
   
<html>    
	<head>    
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">    
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- Tell the browser to be responsive kato screen width -->
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    
	    <!-- Favicon icon -->
	    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/images/favicon.png">
		<title>
			<tiles:insertAttribute name="title" ignore="true" />
		</title> 
		
	    <!-- Custom CSS -->
	    <link href="${pageContext.servletContext.contextPath}/static/matrix-admin-master/dist/css/style.min.css" rel="stylesheet"> 
	</head>    
	<body>  
	    <script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/jquery/dist/jquery.min.js"></script>
	    <script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/popper.js/dist/umd/popper.min.js"></script>
	    <script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
       
		<div>    
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="body" />
		</div>
	</body>    
</html>  