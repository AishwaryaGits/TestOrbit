<style>
	.lds-ripple {
	    left: calc(50% - 175px);
	}
</style>

<div class="page-wrapper">

	<div class="preloader">
	    <div class="lds-ripple">
	        <div class="lds-pos"></div>
	        <div class="lds-pos"></div>
	    </div>
	</div>
	
	<!-- ============================================================== -->
	<!-- Bread crumb and right sidebar toggle -->
	<!-- ============================================================== -->
	 <div class="page-breadcrumb">
	    <div class="row">
	        <div class="col-12 d-flex no-block align-items-center">
	            <h4 class="page-title">Assign Testers to Test Managers</h4>
	            <div class="ml-auto text-right">
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb">
	                        <li class="breadcrumb-item"><a href="#">Home</a></li>
	                         <li class="breadcrumb-item active" aria-current="page">Assign Testers</li>
	                    </ol>
	                </nav>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- ============================================================== -->
	<!-- End Bread crumb and right sidebar toggle -->
	<!-- ============================================================== -->
       
	<!-- ============================================================== -->
	<!-- Container fluid  -->
	<!-- ============================================================== -->
	<div class="container-fluid">
	
	    <div class="row">
			<div class="col-12">
		    	<div class="card m-b-10">

					<form class="form-horizontal" id="createForm">
		            	<div class="card-body">
		                    <div class="form-group row">
		                        <label for="testManager" class="col-md-3 control-label col-form-label">Test Manager</label>
		                        <div class="col-md-4">
		                           	<select class="form-control custom-select" name="testManager" id="testManager"></select>
		                        </div>
		                    </div>
		                </div>
		          	</form>

		        </div>
		    </div>
	   </div>
	   
	    <div class="row tableSection">
			<div class="col-12">
		    	<div class="card p-t-20 p-b-20">
					<table class='table table-striped table-bordered' id='testerTable'>
						<thead>
							<tr>
								<th valign='top'>Sr.No</th>
								<th>Tester Name</th>
								<th>Employee Id</th>
								<th>Email</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id='dataRow'></tbody>	
					</table>
		        </div>
		    </div>
	   </div>
	    

	</div>
	<!-- ============================================================== -->
	<!-- End Container fluid  -->
	<!-- ============================================================== -->

</div>

 <script type="text/javascript">
 
	var tableSelector = '#testerTable';
	var preloaderSelector = '.preloader';
	var assignButtonSelector = "#assignBtn";
	var testManagerSelector = "#testManager";
	
    function showLoader(flag) {
   	 if (flag === true) {
   		 $(preloaderSelector).fadeIn();
   	 } else {
   		 $(preloaderSelector).fadeOut();
   	 }
    }
    
	 
	function addTestManagerOption() { 
		var optionData={}, optionStr = "", optionUnsigned="";
		
		$.ajax({
            type: "POST", 		//GET or POST or PUT or DELETE verb
            url: "${pageContext.servletContext.contextPath}/generic/listAllActiveTestManagersUnderProjectManager.htm", 		// Location of the service
           	
            contentType: "application/json",//Data sent to server
            success: function (data) {//On Successful service call
             	var obj=JSON.parse(data);
             	if(obj.STS == "200"){
	        	if (obj && obj.CONTENT) {
	            	optionData=obj.CONTENT;
             	}}
	            
    			optionUnsigned="<option value=''>Unassigned</option>";
    			optionStr += optionUnsigned;
    			
    			for (var property in optionData) {
    				optionStr += "<option value='" + optionData[property].userId + "' >" + optionData[property].firstName +" "+optionData[property].lastName + "</option>"
    			}
    			
    			$(testManagerSelector).append(optionStr);
    			
    			showLoader(false);
            	 
            },
            error: function (data) {//On Successful service call
            	console.log('Error occurred - ', err )
            }	
        });
		showLoader(true);
	

	} 
	
	function fetchTestersTableRows() { 
		showLoader(true);
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/generic/listUnassignedTestersToTestManagers.htm", 	
            contentType: "application/json", 
            success: function (data) {
            	
            	var roleName="";
	            var obj=JSON.parse(data);
	           	
	           	if(obj.STS == "200") {
	           		
					var transformedData = []
					if (obj && obj.CONTENT) {
						obj.CONTENT.forEach(function(item) {
							
							transformedData.push({
						    	"userId":item.userId,
						        "name": item.firstName + " " +item.lastName,
								"employeeId": item.employeeId,
						        "email": item.email,
						    })

						})
					}
					$(tableSelector).DataTable({
						data: transformedData,
					    columns: [
					    	{ data: null },
					        { data: 'name' },
					        { data: 'employeeId' },
					        { data: 'email' },
					    	{ 
					    		data: null,
					    		render: function (data, type, row, meta){
					    			return '<button id="assignBtn" class="btn btn-secondary btn-flat assignBtn" name="assignBtn" type="button">Assign</button>'
					    		}
					    	},
					    ],
					    rowCallback: function ( row, data, index ) {
				        	$('td:eq(0)',row).html(index + 1);
				        }
					});
            	}
				
				showLoader(false);
            },
            error: function (err) {
               console.log('Error occurred - ', err )
            }	
        });
	} 
	
	
	$(document).ready(function() {
		
		// showLoader(false);
		$('[data-toggle="tooltip"]').tooltip();
		
		addTestManagerOption();
		fetchTestersTableRows();
		
		// assign button click handler
		$(tableSelector).on("click", assignButtonSelector, function() {
			
			currentRow = $(this).closest('tr').index();
			var data = $(tableSelector).DataTable().row(currentRow).data();
			var selectedTestManager = $(testManagerSelector).val();
			
			console.log(data);
			console.log(selectedTestManager);
			var dataOp = { 
					"userIdTestManager": selectedTestManager,
					"userIdTester": data.userId,
				}
			showLoader(true);
	    	$.ajax({
	            type: "POST", 		//GET or POST or PUT or DELETE verb
	            url: "${pageContext.servletContext.contextPath}/projectManager/assignTesterToTestManager.htm",
	            data: JSON.stringify(dataOp), 	
	            contentType: "application/json",//Data sent to server
	            success: function (data) {//On Successful service call
	            	location.reload();
					manageVisibleSection('table');
					showLoader(false);
	            },
	            error: function (err) {//On Successful service call
	            	console.log('Error occurred - ', err )
	            }	
	        });
	    	
	
		});
		
	
	 });
</script>
