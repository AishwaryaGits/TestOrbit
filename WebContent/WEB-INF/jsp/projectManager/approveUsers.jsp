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
	            <h4 class="page-title">Approve Users</h4>
	            <div class="ml-auto text-right">
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb">
	                        <li class="breadcrumb-item"><a href="#">Home</a></li>
	                         <li class="breadcrumb-item active" aria-current="page">Approve Users</li>
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
	
	    <div class="row tableSection">
			<div class="col-12">
		    	<div class="card p-t-20 p-b-20">
					<table class='table table-striped table-bordered' id='approveUsersTable'>
						<thead>
							<tr>
								<th valign='top'>Sr.No</th>
								<th>Employee Id</th>
								<th>Name</th>
								<th>Email</th>
							
								<th> User Status </th>
									<th>User Role</th>
								
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
 
	var tableSelector = '#approveUsersTable';
	var approveButtonSelector = '.approveButton';
	var rejectButtonSelector = '.rejectButton';
	var tableSectionSelector =  '.tableSection';
	var createSectionSelector =  '.createSection';
	var approveSectionSelector =  '.approveSection';
	var preloaderSelector = '.preloader';
	var currentRow;
	
    function showLoader(flag) {
   	 if (flag === true) {
   		 $(preloaderSelector).fadeIn();
   	 } else {
   		 $(preloaderSelector).fadeOut();
   	 }
    }
    
	function manageVisibleSection(section){
		$(tableSectionSelector).hide('fast');
		$(approveSectionSelector).hide('fast');
		$(createSectionSelector).hide('fast');
		
		switch(section) {
		case "table" :
			$(tableSectionSelector).show('fast');
			return;
			
		case "create" :
			$(createSectionSelector).show('fast');
			return;
			
		case "approve" :
			$(approveSectionSelector).show('fast');
			return;
		}
	}
	
	 
	function fetchApproveUsersTableRows() { 
		showLoader(true);
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/generic/listUsersToApprove.htm", 	
            contentType: "application/json", 
            success: function (data) {
            	var roleName="";
	           	var obj=JSON.parse(data);
	           	if(obj.STS == "200"){
				var transformedData = []
				if (obj && obj.CONTENT) {
					obj.CONTENT.forEach(function(item) {
						if(item.roleId == 1001){
							roleName="Project Manager";
						}else if(item.roleId == 1002){
							roleName="Test Manager";
						}else if(item.roleId == 1003){
							roleName="Tester";
						}
						transformedData.push({
					    	"userId":item.userId,
							"employeeId": item.employeeId,
					        "name": item.firstName + " " +item.lastName,
					        "email": item.email,
					        "status": item.userStatus,
					        "empRole": roleName,
					    })
					})
				}
				$(tableSelector).DataTable({
					data: transformedData,
				    columns: [
				    	{ data: null },
				        { data: 'employeeId' },
				        { data: 'name' },
				        { data: 'email' },
				        { data: 'status' },
				        { data: 'empRole' },
				    	{ 
				    		data: null,
				    		render: function (data, type, row, meta){
				    			return '<button id="approveBtn" class="btn btn-secondary btn-flat approveButton" name="approveBtn" type="button">Approve</button>' +
		                        '&nbsp;&nbsp;<button id="rejectBtn" class="btn btn-danger btn-flat rejectButton" name="rejectBtn" type="button">Reject</button>'
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
		manageVisibleSection('table');
		
		
		fetchApproveUsersTableRows();
	
		
		// approve button click handler
		$(tableSelector).on("click", approveButtonSelector, function() {
			
			currentRow = $(this).closest('tr').index();
			var data = $(tableSelector).DataTable().row(currentRow).data();
			console.log(data.userId);
			var dataOp = { 
				"userIdResource": data.userId,
				"userStatus": 'Y'
			}
			
			showLoader(true);
	    	$.ajax({
	            type: "POST", 		//GET or POST or PUT or DELETE verb
	            url: "${pageContext.servletContext.contextPath}/generic/approveUser.htm",
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
	
		})
	
		
		// reject button click handler
		$(tableSelector).on("click", rejectButtonSelector, function() {
			currentRow = $(this).closest('tr').index();
			var data = $(tableSelector).DataTable().row(currentRow).data();
			console.log(data.userId);
			var dataOp = { 
				"userIdResource": data.userId,
				"userStatus": 'R'
			}
			
			showLoader(true);
	    	$.ajax({
	            type: "POST", 		//GET or POST or PUT or DELETE verb
	            url: "${pageContext.servletContext.contextPath}/generic/approveUser.htm",
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
	
			
		})
		
		
	 });
</script>
