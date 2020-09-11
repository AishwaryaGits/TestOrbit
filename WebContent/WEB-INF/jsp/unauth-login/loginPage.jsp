
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 

<style>
	.auth-wrapper .auth-box {
	    border-radius: 10px;
	}
	.auth-wrapper .auth-box .btn{
	    border-radius: 25px;
	}
	.login-logo-image {
		text-align: center;
	    background: #00182f;
	    color: white;
	    margin-bottom: 20px;
	}
	.login-logo-image a {
		color: white;
	}
	#recoverform .text-white {
		color: #343a40 !important;
	}
	#recoverform .text-center {
		margin-bottom: 20px;
	}
</style>

<div class="main-wrapper">
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        
        <div class="auth-wrapper d-flex no-block justify-content-center align-items-center bg-dark">
            <div class="auth-box">
          		<div class="login-logo-image">
            		<a class="navbar-brand" href="#">
		                <!-- Logo icon -->
		                <b class="logo-icon p-l-10">
		                    <img src="${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/images/logo-icon.svg" alt="homepage" class="light-logo" />
		                   
		                </b>
		                <span class="logo-text">
		                    <bold>Test Orbit</bold>
		                </span>
		            </a>
            	</div>
            	
                <div id="loginform">

                    <!-- Form -->
                    <form:form action="validateUser.htm" method="post" class="form" modelAttribute="userBean" id="loginform" >
                        <div class="row p-b-30">
                            <div class="col-12">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-success text-white" id="basic-addon1"><i class="ti-user"></i></span>
                                    </div>
                                    <form:input path="userEmailId" type="email" class="form-control form-control-lg" id="userName"  placeholder="Username" aria-label="Username" aria-describedby="basic-addon1" required="true"/>
		                        	
                                   
                                </div>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text bg-warning text-white" id="basic-addon2"><i class="ti-pencil"></i></span>
                                    </div>
                                    <form:input path="userPassword" type="password" class="form-control form-control-lg" id="userPassword" placeholder="Password" aria-label="Password" aria-describedby="basic-addon1" required="true"/>
                  
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="form-group">
                                    <div class="p-t-20">
                                        <button class="btn btn-info" id="to-recover" type="button"><i class="fa fa-lock m-r-5"></i> Lost password?</button>
                                       	 <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/generic/registerUser.htm" id="to-login" name="action"> Register?</a>
                                        <button class="btn btn-success float-right" type="submit" >Login</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
                <div id="recoverform">
                    <div class="text-center">
                        <span class="text-white">Enter your e-mail address below and we will send you instructions how to recover a password.</span>
                    </div>
                    <div class="row m-t-20">
                        <!-- Form -->
                        <form class="col-12" action="index.html">
                            <!-- email -->
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text bg-danger text-white" id="basic-addon1"><i class="ti-email"></i></span>
                                </div>
                                <input type="text" class="form-control form-control-lg" placeholder="Email Address" aria-label="Username" aria-describedby="basic-addon1">
                            </div>
                            <!-- pwd -->
                            <div class="row m-t-20 p-t-20">
                                <div class="col-12">
                                    <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/generic/loginUser.htm" id="to-login" >Back To Login</a>
                                    <button class="btn btn-info float-right" type="button" name="action">Recover</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
    
   	<script>

	    $('[data-toggle="tooltip"]').tooltip();
	    $(".preloader").fadeOut();

	    $('#to-recover').on("click", function() {
	        $("#loginform").slideUp();
	        $("#recoverform").fadeIn();
	    });
	    $('#to-login').click(function(){
	        
	        $("#recoverform").hide();
	        $("#loginform").fadeIn();
	    });
    </script>

    <script type="text/javascript">
    
   /*  /* function loginUser(){
    	alert("hi");
    	var sendInfo={
    			userEmailId:$("#userName").val(),
    			userPassword:$("#userPassword").val()
    			
    	}
    	console.log(sendInfo);
    	$.ajax({
            type: "POST", 		//GET or POST or PUT or DELETE verb
            url: "${pageContext.servletContext.contextPath}/generic/validateUser.htm", 		// Location of the service
            data: JSON.stringify(sendInfo), 	
            contentType: "application/json",//Data sent to server
            success: function (data) {//On Successful service call
                                    
            },
            error: function (data) {//On Successful service call
               
            }	
        });
    	
    }
     */ 
    function registerUser(){

    	var sendInfo={
    		
    		 employeeId:"1009",
    		 firstName:"Mauli",
    		lastName:"Guru",
    		
    	 email:"hihj@gail.com",
    	 contactNo:"9988776677",
    	userPassword:"uiop",
    	 roleId:1002,
    	 userIdManager:2

    		
    	}

    	
    	
    	$.ajax({
            type: "POST", 		//GET or POST or PUT or DELETE verb
            url: "${pageContext.servletContext.contextPath}/generic/addNewUser.htm", 		// Location of the service
            data: JSON.stringify(sendInfo), 	
            contentType: "application/json",//Data sent to server
            success: function (data) {//On Successful service call
            	 /*  var obj=JSON.parse(data);  */ 
            console.log(data);
            alert("hi  iio");
            	 
            },
            error: function (data) {//On Successful service call
               
            }	
        });
    	
    }
    
   
    // we are done ....lets run it...
</script>
