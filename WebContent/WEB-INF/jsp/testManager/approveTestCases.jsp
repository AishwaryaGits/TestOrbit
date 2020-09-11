<style>
	.lds-ripple {
		left: calc(50% - 175px);
	}
	.taskActionBtn {
		display: flex;
	    align-items: flex-end;
	    justify-content: flex-end;
	}
	.taskActionBtn button {
		margin: 0 5px;
	}
	.tester-dashboard {
		background: url("${pageContext.servletContext.contextPath}/static/matrix-admin-master/assets/images/testerDashboard.jpg");
		height: 500px;
		background-size: cover;
		margin: 15px 0;
		background-repeat: no-repeat;
	}
	table#testCaseTable .btn {
		margin: 2px;
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
				<h4 class="page-title">Approve Test Cases</h4>
				<div class="ml-auto text-right">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Approve Test Cases</li>
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
		
		<div class="row pageSection tableSection taskListSection">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">List of available tasks</h4>
						
						<table class='table table-striped table-bordered' id='taskListTable'>
							<thead>
								<tr>
									<th>Sr No</th>
									<th>Task Id</th>
									<th>Task Description</th>
									<th>Task Status</th>
									<th>Task End Date</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id='testCaseDataRow'></tbody>
						</table>
						
					</div>
				</div>
			</div>
		</div>
	
		<div class="row pageSection taskDetailsSection">
			<div class="col-12">

				<div class="card">
					<form class="form-horizontal" id="taskDetailsForm">
						<div class="card-body">
							<h4 class="card-title">Task Details</h4>
							
							<div class="row">
								
								<div class="col-12 col-sm-5">
									<div class="form-group row">
										<label for="taskDesc" class="col-md-5 control-label col-form-label">Task Description:</label>
										<div class="col-md-7">
											<input type="text" class="form-control" name="taskDesc" id="taskDesc" readonly>
										</div>
									</div>
								</div>
								
								<div class="col-12 col-sm-5 offset-sm-2">
									<div class="form-group row">
										<label for="taskStatus" class="col-md-5 control-label col-form-label">Task Status:</label>
										<div class="col-md-7">
											<select class="form-control custom-select" name="taskStatus" id="taskStatus" disabled>
												<option value="todo">To do</option>
												<option value="inprogs">In Progress</option>
												<option value="blocked">Blocked</option>
												<option value="complete">Completed</option>
											</select>
										</div>
									</div>
								</div>
								
								<div class="col-12 col-sm-5">
									<div class="form-group row">
										<label for="endDate" class="col-md-5 control-label col-form-label">End Date:</label>
										<div class="col-md-7 input-group">
											<input type="text" class="form-control" name="endDate" id="endDate" placeholder="mm/dd/yyyy" readonly>
											<div class="input-group-append">
												<span class="input-group-text"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>
								</div>

							</div>
						</div>
					</form>
				</div>
				
			</div>
		</div>
		
		<div class="row pageSection tableSection testCaseTableSection">
			<div class="col-12">
				<div class="card p-t-20 p-b-20">
					<div class="row">
						
					</div>
				    
					<table class='table table-striped table-bordered' id='testCaseTable'>
						<thead>
							<tr>
								<th>Sr No</th>
								<th>Test Case Id</th>
								<th>Test Case Description</th>
								<th>Test Case Input</th>
								<th>Test Case Output</th>
								<th>End Date</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id='testCaseDataRow'></tbody>
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

	var preloaderSelector = '.preloader';
	var showTaskButtonSelector = '#showTaskButton';
	
	var currentTaskId, currentModuleId, currentTestCaseId, currentTestDesc, currentTestCaseRow, currentTaskRow;
	
	var taskDetailsFormSelector = '#taskDetailsForm';
	
	var taskListTableSelector = '#taskListTable';
	var testCaseTableSelector = '.testCaseTableSection table';
	
	var taskListSectionSelector = '.taskListSection';
	var taskDetailsSectionSelector = '.taskDetailsSection';
	var testCaseTableSectionSelector = '.testCaseTableSection';
	
	var testCaseAcceptButtonSelector = "#acceptButton";
	var testCaseRejectButtonSelector = "#rejectButton";
	
	function showLoader(flag) {
		if (flag === true) {
			$(preloaderSelector).fadeIn();
		} else {
			$(preloaderSelector).fadeOut();
		}
	}

	function manageVisibleSection(section) {
		var availableSection = [
			taskListSectionSelector, 
			taskDetailsSectionSelector, 
			testCaseTableSectionSelector, 
		];
		
		availableSection.forEach(function(item) {
			$(item).hide('fast');
		})
		
		switch (section) {
				
			case "taskList":
				$(taskListSectionSelector).show('fast');
				return;
				
			case "taskDetails":
				$(taskDetailsSectionSelector).show('fast');
				$(testCaseTableSectionSelector).show('fast');
				return;

		}
	}
	
	function showTaskButtonHandler (e) {
		$(taskDetailsFormSelector).trigger("reset");
		
		currentTaskRow = $(this).closest('tr').index();
		
		var data = $(taskListTableSelector).DataTable().row(currentTaskRow).data();
		console.log(data);
		currentTaskId = data.taskId;
		currentModuleId = data.moduleId;
		
		// populate row values in task form
		for(var field in data) {
			$(taskDetailsFormSelector).find('#' + field).val(data[field]);
		}

		fetchTaskTestCaseList(data);
		manageVisibleSection('taskDetails');
	}
	
	function fetchTaskList () {
		showLoader(true);
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/testManager/listTasksForTestCaseApproval.htm", 	
            contentType: "application/json", 
            success: function (data) {
            	
            	var obj = JSON.parse(data);
				var transformedData = [];
				if(obj.STS == "200"){
					if (obj && obj.CONTENT) {
						obj.CONTENT.forEach(function(item) {
							transformedData.push({
						    	"moduleId":item.moduleId,
								"taskId": item.taskId,
						        "taskDesc": item.taskDescription,
						        "taskStatus": item.taskStatus,
						        "endDate": item.endDate,
						    })
						})
					}
				}
				
				$(taskListTableSelector).DataTable({
					data: transformedData,
				    columns: [
				    	{ data: null },
				        { data: 'taskId' },
				        { data: 'taskDesc' },
				        { data: 'taskStatus' },
				        { data: 'endDate' },
				    	{ 
				    		data: null,
				    		render: function (data, type, row, meta) {
				    			return '<button id="showTaskButton" class="btn btn-primary btn-flat showTaskButton" name="showTaskButton" type="button" >Show</button>'
				    		}
				    	},
				    ],
				    rowCallback: function ( row, data, index ) {
			        	$('td:eq(0)',row).html(index + 1);
			        }
				});
				
				showLoader(false);
            },
            error: function (err) {
               console.log('Error occurred - ', err );
            }	
        });
	}
	
	function fetchTaskTestCaseList (testData) {
		var dataOp = {
				"taskId":testData.taskId,
				"taskDescription": testData.taskDesc,
				"taskStatus": testData.taskStatus
			}
		showLoader(true);
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/tester/listTestCaseTobeApproved.htm", 	
            data: JSON.stringify(dataOp),
            contentType: "application/json", 
            success: function (data) {
            	
            	var obj = JSON.parse(data);
				var transformedData = [];
				if(obj.STS == "200"){
				
				if (obj && obj.CONTENT) {
					obj.CONTENT.forEach(function(item) {
						transformedData.push({
							"testCaseId": item.testCaseId,
					    	"testCaseTaskId": item.taskId,
					        "testCaseTesterId": item.testerId,
					        "testCaseApproved": item.testCaseStatus,
					        "testCaseDesc": item.comments,
					        "testCaseInput": item.testCaseInputs,
					        "testCaseOutput": item.testCaseOutputs,
					        "testCaseEndDate": item.endDate,
					    })
					});
				};
				}
				$(testCaseTableSelector).DataTable({
					destroy: true,
					data: transformedData,
				    columns: [
				    	{ data: null },
				        { data: 'testCaseId' },
				        { data: 'testCaseDesc' },
				        { data: 'testCaseInput' },
				        { data: 'testCaseOutput' },
				        { data: 'testCaseEndDate' },
				    	{ 
				    		data: null,
				    		render: function (data, type, row, meta) {
				    			var acceptButton = '<button id="acceptButton" class="btn btn-primary btn-flat acceptButton" name="acceptButton" type="button" >Approve</button>';
				    			var rejectButton = '<button id="rejectButton" class="btn btn-danger btn-flat rejectButton" name="rejectButton" type="button" >Reject</button>';
						    	return acceptButton + "<br>" + rejectButton;
				    		}
				    	},
				    ],
				    rowCallback: function ( row, data, index ) {
			        	$('td:eq(0)',row).html(index + 1);
			        }
				});
				
				showLoader(false);
            },
            error: function (err) {
               console.log('Error occurred - ', err )
            }	
        });
	}
	
	function testCaseAcceptButtonHandler () {
		currentTestCaseRow = $(this).closest('tr').index();
		var data = $(testCaseTableSelector).DataTable().row(currentTestCaseRow).data();
		console.log('testCaseAcceptButtonHandler - ' + data);
		
		
		var dataOp = { 
			"testCaseId": data.testCaseId,
			"testCaseStatus": 'Y'
		}
		
		showLoader(true);
    	$.ajax({
            type: "POST", 		//GET or POST or PUT or DELETE verb
            url: "${pageContext.servletContext.contextPath}/tester/modifyTestCaseStatus.htm",
            data: JSON.stringify(dataOp), 	
            contentType: "application/json",//Data sent to server
            success: function (data) {//On Successful service call
            	manageVisibleSection('taskDetails');
				showLoader(false);
            },
            error: function (err) {//On Successful service call
            	console.log('Error occurred - ', err )
            }	
        });
    	
	}
	
	function testCaseRejectButtonHandler () {
		currentTestCaseRow = $(this).closest('tr').index();
		var data = $(testCaseTableSelector).DataTable().row(currentTestCaseRow).data();
		console.log('testCaseRejectButtonHandler - ' + data);
		
		
		var dataOp = { 
			"testCaseId": data.testCaseId,
			"testCaseStatus": 'R'
		}
		
		showLoader(true);
    	$.ajax({
            type: "POST", 		//GET or POST or PUT or DELETE verb
            url: "${pageContext.servletContext.contextPath}/tester/modifyTestCaseStatus.htm",
            data: JSON.stringify(dataOp), 	
            contentType: "application/json",//Data sent to server
            success: function (data) {//On Successful service call
            	manageVisibleSection('taskDetails');
				showLoader(false);
            },
            error: function (err) {//On Successful service call
            	console.log('Error occurred - ', err )
            }	
        });
    	
	}
	
	$(document).ready(function () {

		showLoader(false);
		$('[data-toggle="tooltip"]').tooltip();
		
		$('#testCaseEndDate').datepicker({
			autoclose: true,
			todayHighlight: true,
			orientation: 'bottom',
			startDate: new Date()
		}).attr('readonly', 'true');
		
		manageVisibleSection('taskList');
		fetchTaskList();

		$(taskListTableSelector).on("click", showTaskButtonSelector, showTaskButtonHandler)
		
		$(testCaseTableSelector).on('click', testCaseAcceptButtonSelector, testCaseAcceptButtonHandler);
		$(testCaseTableSelector).on('click', testCaseRejectButtonSelector, testCaseRejectButtonHandler);

		

	});

</script>