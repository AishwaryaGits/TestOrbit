<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<style>
   	html, body, .main-wrapper, .auth-wrapper {
		height: 100%;
	}
	
	.mainPageBg {
		background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);
	}
	
	.auth-wrapper {
		height: auto;
	}
	
	.auth-wrapper .auth-box {
		background: white;
	    border-radius: 25px;
	    padding: 40px;
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
        
        <div class="auth-wrapper d-flex no-block justify-content-center align-items-center bg-dark mainPageBg1">
            <div class="auth-box border-secondary">
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
                <div id="registrationform">

                    <!-- Form --> 
                    <form:form class="form-horizontal" action="addNewUser${userAs}/${managerId}.htm" method="post"  modelAttribute="userDto" id="registrationform" >
                        <div class="row">
                            <div class="col-12">
                               
                               <div class="form-group">
                                   	<form:input 
                                   		path="firstName"
                                   		type="text" 
                                   		class="form-control" 
                                   		id="firstName" 
                                   		placeholder="First Name" 
                                   		aria-label="firstName" 
                                   		aria-describedby="firstName" 
                                   		required="true"
                                   		pattern="[a-zA-Z]+"
                                   		oninvalid="InvalidMsg(this, 'Please enter first name in alphabets only.')"
                                   	/>
                               </div>
  
                              <div class="form-group">
                                    <form:input 
                                        path="lastName"
                                    	type="text" 
                                    	class="form-control" 
                                    	id="lastName" 
                                    	placeholder="Last Name" 
                                    	aria-label="lastName" 
                                    	aria-describedby="lastName" 
                                    	required="true"
                                    	pattern="[a-zA-Z]+"
                                    	oninvalid="InvalidMsg(this, 'Please enter last name in alphabets only.')"
                                    />
                              </div>
                                
                              <div class="form-group">
                                    <form:input 
                                    	path="employeeId"
                                    	type="text" 
                                    	class="form-control" 
                                    	id="empId" 
                                    	placeholder="Employee ID" 
                                    	aria-label="empId"
                                    	maxlength="8"
                                    	pattern="\d*"
                                    	aria-describedby="empId" 
                                    	required="true"
                                    />
                               </div>
                               
                               <div class="form-group">
                                    <form:input 
                                    	path="email"
                                    	type="email" 
                                    	class="form-control" 
                                    	id="email" 
                                    	placeholder="Email" 
                                    	aria-label="email" 
                                    	aria-describedby="email" 
                                    	required="true"
                                    />
                                </div>
                                
                               <div class="form-group">
                                    <form:input 
                                    	path="contactNo"
                                    	type="tel" 
                                    	class="form-control" 
                                    	id="contactNumber" 
                                    	placeholder="Contact Number"
                                    	pattern="[0-9]{10}" 
                                    	maxlength="10"
                                    	aria-label="contactNumber" 
                                    	aria-describedby="contactNumber" 
                                    	required="true"
                                    />
                                </div>

                                 <div 
                                	class="form-group" >
                                    <label for="userAs">  Register As:</label>                               
									<select class="form-control custom-select" name="userAs" id="userRegSel">
									  <option value="1001">Manager</option>
									  <option value="1002">Test Manager</option>
									  <option value="1003">Tester</option>
									</select>
                                </div>
                                
                                <div 
                                	class="form-group"
                                	id="manager-list-section"
                                >
                                    <label for="lastName">Manager Name</label>                               
									<select class="form-control custom-select" name="managerId" id="managerId"></select>
                                </div>
                                
                                <div class="form-group">
                                    <form:input 
                                    	path="userPassword"
                                    	type="password" 
                                    	class="form-control" 
                                    	id="userPassword" 
                                    	name="userPassword" 
                                    	placeholder="User Password" 
                                    	aria-label="UserPassword" 
                                    	aria-describedby="user-password" 
                                    	required="true"
                                    />
                                </div>
                                
                                <div class="form-group">
                                    <input 
                                    	type="password" 
                                    	class="form-control" 
                                    	id="confirmPassword" 
                                    	name="confirmPassword" 
                                    	placeholder="Confirm Password" 
                                    	aria-label="ConfirmPassword" 
                                    	aria-describedby="confirm-password" 
                                    	required="true"
                                    />
                                </div>
                                
                            </div>
                        </div>
                        <div class="row border-secondary">
                            <div class="col-12">
                                <div class="form-group">
                                    <div class="p-t-20">
                                     <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/generic/loginUser.htm" id="to-login" name="action">Back To Login</a>
                                        <button 
                                        	class="btn btn-success float-right" 
                                        	type="submit">
                                        	Register
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        
                    </form:form>
                </div>
                
            </div>
        </div>

    </div>

    <script type="text/javascript">
    	var preloaderSelector = '.preloader';
    	var userPassword = $("input[name='userPassword']");
    	var confirmPassword = $("input[name='confirmPassword']");
    	var managerListSectionSelector = '#manager-list-section';
    	var userRegistrationSelector = '#userRegSel';
    	var managerListSelector = '#managerId';
    	
    	function InvalidMsg(element, msg) {
    		
    	     if(element.validity.patternMismatch){
    	    	 element.setCustomValidity(msg);
    	    }    
    	    else {
    	    	element.setCustomValidity('');
    	    }
    	    return true;
    	}
    	
    	function showLoader(flag) {
			if (flag === true) {
			 $(preloaderSelector).fadeIn();
			} else {
			 $(preloaderSelector).fadeOut();
			}
		}
    
	    $(document).ready(function() {
	    	
	    	var optionData = [], optionStr = "";
	    	
		    $('[data-toggle="tooltip"]').tooltip();
		    $(managerListSectionSelector).hide();
	    	
			$.ajax({
	            type: "POST",
	            url: "${pageContext.servletContext.contextPath}/generic/listActiveProjectManagers.htm",
	            contentType: "application/json",
	            success: function (data) {
	            	showLoader(false);
	             	var obj=JSON.parse(data);
	             	if(obj.STS == "200"){
			        	if (obj && obj.CONTENT) {
			            	optionData=obj.CONTENT;
		             	}
			        	
			        	optionData.forEach(function(item){
			        		optionStr += "<option value='" + item.userId + "' >" + item.firstName +" " + item.lastName + "</option>"
			        	})
			        	
						$(managerListSelector).append(optionStr);
						
		        	}else if(obj.STS == "500"){
		        		optionStr += "<option value='0' selected>Select</option>"
		        		$(managerListSelector).append(optionStr);
		        	}
	            },
	            error: function (data) {
	            	showLoader(false);
	            	console.log('Error occurred - ', err )
	            }	
	        });
			
		    
	    	confirmPassword.on('blur', function () {
		    	if (userPassword.val() !== confirmPassword.val()){
		    		confirmPassword[0].setCustomValidity("Passwords don't match")
		    	} else {
		    		confirmPassword[0].setCustomValidity("")
		    	}
			});
		
		    $(userRegistrationSelector).on('change', function(e) {
		    	if(e.target.value != '1001') {
		    		$(managerListSectionSelector).show("slow")
		    	} else {
		    		$(managerListSectionSelector).hide("slow")
		    	}
		    });
		    
		    $("#registrationform").submit(function(e) {
		    	var managerId = $("#managerId").val();
		    	var userType = $("#userRegSel").val();
		    	if( userType !== '1001' && managerId === "0") {
		    		e.preventDefault();
		    	}
		    })
		    
	    });
   	</script>
  