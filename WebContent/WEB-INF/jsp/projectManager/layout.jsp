<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"    
"http://www.w3.org/TR/html4/loose.dtd">    

<html dir="ltr">    
	<head>    
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		
	    <!-- Tell the browser to be responsive to screen width -->
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    
	    <!-- Favicon icon -->
	    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/images/favicon.png">
		
		<title>
			<tiles:insertAttribute name="title" ignore="true" />
		</title> 
		
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
		<link href="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.css" rel="stylesheet">
	    <link href="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/toastr/build/toastr.min.css" rel="stylesheet">
	    <!-- Custom CSS -->
	    <link href="${pageContext.servletContext.contextPath}/static/matrix-admin-master/dist/css/style.min.css" rel="stylesheet"> 
	    
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->    
		<style>
			table {
				width: 100% !important;
			}
		</style>
	</head>    
	<body>   
	
		<script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/jquery/dist/jquery.min.js"></script>
	    <script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/popper.js/dist/umd/popper.min.js"></script>
	    <script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/bootstrap/dist/js/bootstrap.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/extra-libs/DataTables/datatables.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>
	    <script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
		<script src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/libs/toastr/build/toastr.min.js"></script>
		        
		<div id="main-wrapper">
			<tiles:insertAttribute name="header" /> 
			<tiles:insertAttribute name="menu" />
			<tiles:insertAttribute name="body" /> 
			<tiles:insertAttribute name="footer" />
		</div>
	        
	</body>    
</html>  