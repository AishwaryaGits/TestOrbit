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

	table td {
		word-break: break-all; /* IE supports this */
		word-break: break-word; /* IE doesn't support, and will ignore */
	}
	form .error {
		color: red;
	}
	canvas.execHistoryChart {
		height: 200px;
		width: 200px;
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
				<h4 class="page-title">Manage Task</h4>
				<div class="ml-auto text-right">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Manage Task</li>
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
	
		<div class="row pageSection tableSection testerDashboardSection">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">Test Dashboard</h4>
						
						<div class="row">
							<div class="col-12">
								<button type="button" id="manageTask" class="btn btn-primary float-right">Manage Task</button>
							</div>
						</div>
						
						<div class="row">
							<div class="col-12 tester-dashboard"></div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
		
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
						
						<div class="row">
							<div class="col-12">
								<button type="button" id="taskListBack" class="btn btn-secondary float-right">Back</button>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	
		<div class="row pageSection taskDetailsSection">
			<div class="col-12">

				<div class="card">
					<form class="form-horizontal" id="taskDetailsForm">
						<div class="card-body">
							<h4 class="card-title">Enter Task Details</h4>
							
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
											<select class="form-control custom-select" name="taskStatus" id="taskStatus">
												<option value="todo">To do</option>
												<option value="inprogs">InProgress</option>
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
								
								<div class="col-12 col-sm-5 offset-sm-2 taskActionBtn">
									<button type="button" id="cancelTaskDetailsBtn" class="btn btn-secondary">Back</button>
									<button type="button" id="submitTaskDetailsBtn" class="btn btn-primary">Update</button>
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
						<div class="col-12">
							<button type="button" id="execHistoryTestCaseButton" class="btn btn-primary float-right m-3">Execution History</button>
							<button type="button" id="createTestCaseButton" class="btn btn-primary float-right m-3">Create Test Case</button>
						</div>
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

		<div class="row pageSection testCaseCreateSection">
			<div class="col-12">
	
				<div class="card">
					<form class="form-horizontal" id="testCaseCreateForm">
						<div class="card-body">
							<h4 class="card-title">Enter Test Case Details</h4>
							<div class="form-group row">
								<label for="testCaseDesc" class="col-md-3 control-label col-form-label">Test Case Description:</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="testCaseDesc" id="testCaseDesc">
								</div>
							</div>
							<div class="form-group row">
								<label for="testCaseInput" class="col-md-3 control-label col-form-label">Test Case Input:</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="testCaseInput" id="testCaseInput">
								</div>
							</div>
							<div class="form-group row">
								<label for="testCaseOutput" class="col-md-3 control-label col-form-label">Test Case Output:</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="testCaseOutput" id="testCaseOutput">
								</div>
							</div>
							<div class="form-group row">
								<label for="testCaseTaskId" class="col-md-3 control-label col-form-label">Task Id:</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="testCaseTaskId" id="testCaseTaskId" readonly >
								</div>
							</div>
							<div class="form-group row">
								<label for="testCaseModuleId" class="col-md-3 control-label col-form-label">Module Id:</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="testCaseModuleId" id="testCaseModuleId" readonly >
								</div>
							</div>
							<div class="form-group row">
								<label for="testCaseEndDate" class="col-md-3 control-label col-form-label">End Date:</label>
								<div class="col-md-9 input-group">
									<input type="text" class="form-control" name="testCaseEndDate" id="testCaseEndDate" placeholder="mm/dd/yyyy">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
						</div>
						<div class="border-top">
							<div class="card-body">
								<button type="button" id="testCaseSubmitButton" class="btn btn-primary float-right m-b-20">Submit</button>
								<button type="button" id="testCaseCancelButton"  class="btn btn-secondary float-right m-b-20 m-r-10 cancelBtn">Cancel</button>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
		
		<div class="row pageSection testExecutionSection">
			<div class="col-12">

				<div class="card">
					<form class="form-horizontal" id="testCaseExecForm">
						<div class="card-body">
							<h4 class="card-title">Enter Test Execution Details</h4>
							<div class="form-group row">
								<label for="testCaseInputExec" class="col-md-3 control-label col-form-label">Test Case Input:</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="testCaseInputExec" id="testCaseInputExec">
								</div>
							</div>
							<div class="form-group row">
								<label for="testCaseOutputExec" class="col-md-3 control-label col-form-label">Test Case Output:</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="testCaseOutputExec" id="testCaseOutputExec">
								</div>
							</div>
							<div class="form-group row">
								<label for="testCaseTaskIdExec" class="col-md-3 control-label col-form-label">Task Id:</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="testCaseTaskIdExec" id="testCaseTaskIdExec" readonly >
								</div>
							</div>
							<div class="form-group row">
								<label for="testCaseModuleIdExec" class="col-md-3 control-label col-form-label">Module Id:</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="testCaseModuleIdExec" id="testCaseModuleIdExec" readonly >
								</div>
							</div>
							<div class="form-group row">
								<label for="testCaseResultExec" class="col-md-3 control-label col-form-label">Task Status:</label>
								<div class="col-md-9">
									<select class="form-control custom-select" name="testCaseResultExec" id="testCaseResultExec">
										<option value="passed">Passed</option>
										<option value="failed">Failed</option>
									</select>
								</div>
							</div>
						</div>
						<div class="border-top">
							<div class="card-body">
								<button type="button" id="testCaseSubmitExecButton" class="btn btn-primary float-right m-b-20">Submit</button>
								<button type="button" id="testCaseCancelExecButton" class="btn btn-secondary float-right m-b-20 m-r-10 cancelBtn">Cancel</button>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
		
		<div class="row pageSection showtExecutionHistorySection">
			<div class="col-12">

				<div class="card">
				
					<div class="row">
						<div class="col-12 p-4">
							<div class="row">
								<div class="col-12 col-lg-6">
									<h5 class="text-primary">Execution History:</h5>
									<p>Please select an execution date from the list for viewing its history.</p>
								</div>
								<div class="col-12 col-lg-6">
									<div class="form-group row d-flex justify-content-xl-end">
										<label for="executionDateSelect" class="col-12 col-sm-6 col-xl-3 text-sm-right control-label col-form-label">Execution Date</label>
										<select class="col-12 col-sm-6 col-xl-3 form-control custom-select" name="executionDateSelect" id="executionDateSelect"></select>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row d-flex justify-content-center m-4">
						<div class="col-12 col-sm-6 col-lg-4">
							<canvas id="execHistoryChart" width="400" height="400"></canvas>
						</div>
					</div>
					
					<div class="row execDetailsSection" style="display: none;">
						<div class="col-12 p-4 text-center">
							<h6 class="text-primary">Execution Details:</h6>
							<br>
							<div class="row justify-content-center"">
								<table class="table table-hover col-12 col-sm-10 col-md-8 col-lg-6" style="background-image: linear-gradient(to top, #a8edea 0%, #fed6e3 100%);">
								  	<tbody>
									    <tr>
									    	<td>Execution Date:</td>
									      	<td class="execDetailsDate"></td>
									    </tr>
									    <tr>
									    	<td>Number of Test Cases:</td>
									      	<td class="execDetailsTotalTC"></td>
									    </tr>
									    <tr>
									      	<td>Test cases passed:</td>
									      	<td class="execDetailsTCPassed"></td>
									    </tr>
									    <tr>
									      	<td>Test cases failed:</td>
									      	<td class="execDetailsTCFailed"></td>
									    </tr>
									    <tr>
									      	<td>Pass Percentage:</td>
									      	<td class="execDetailsPassPercent"></td>
									    </tr>
									    <tr>
									      	<td>List of Test Case Id:</td>
									      	<td class="execDetailsTCList"></td>
									    </tr>
									    
								  	</tbody>
								</table>
							</div>
							
						</div>
					</div>
					
					<div class="row">
						<div class="col-12">
							<button type="button" id="execHistoryBackButton" class="btn btn-secondary float-right m-3">Back</button>
						</div>
					</div>
					
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
	var currentTaskId, currentTaskEndDate, currentModuleId, currentTestCaseId, currentTestDesc, currentTestCaseRow, currentTaskRow;
	var toastPosition = { positionClass : "toast-bottom-right"};
	
	var taskDetailsFormSelector = '#taskDetailsForm';
	var testCaseCreateFormSelector = '#testCaseCreateForm';
	var testCaseExecFormSelector = '#testCaseExecForm';
	  
	var testCaseTaskIdSelector = '#testCaseTaskId';
	var testCaseModuleIdSelector = '#testCaseModuleId';
	var testCaseTaskIdExecSelector = '#testCaseTaskIdExec';
	var testCaseModuleIdExecSelector = '#testCaseModuleIdExec';	
	
	var taskListTableSelector = '#taskListTable';
	var testCaseTableSelector = '.testCaseTableSection table';
	
	var testerDashboardSectionSelector = '.testerDashboardSection';
	var taskListSectionSelector = '.taskListSection';
	var taskDetailsSectionSelector = '.taskDetailsSection';
	var testCaseTableSectionSelector = '.testCaseTableSection';
	var testCaseCreateSectionSelector = '.testCaseCreateSection';
	var testExecutionSectionSelector = '.testExecutionSection';
	var showHistorySectionSelectory = ".showtExecutionHistorySection";
	
	var cancelTaskDetailsButtonSelector = '#cancelTaskDetailsBtn';
	var submitTaskDetailsButtonSelector = '#submitTaskDetailsBtn';
	var managetaskButtonSelector = '#manageTask';
	var cancelButtonSelector = '.cancel';
	var taskListBackButtonSelector = '#taskListBack';
	var showTaskButtonSelector = '#showTaskButton';
	var createTestCaseButtonSelector = '#createTestCaseButton';
	var testCaseCancelButtonSelector = '#testCaseCancelButton';
	var testCaseSubmitButtonSelector = '#testCaseSubmitButton';
	var testCaseSubmitExecButtonSelector = '#testCaseSubmitExecButton';
	var testCaseCancelExecButtonSelector = '#testCaseCancelExecButton';
	var executeButtonSelector = '#executeButton';
	var submitForApprovalButtonSelector = '#submitForApprovalButton';
	var showExecutionHistoryButton = "#execHistoryTestCaseButton";
	
	var execHistoryChartSelector = "#execHistoryChart";
	var executionDateSelectSelector = "#executionDateSelect";
	var execHistoryBackButtonSelector = "#execHistoryBackButton";
	
	var historyExecutionChart = "";

	function showLoader(flag) {
		if (flag === true) {
			$(preloaderSelector).fadeIn();
		} else {
			$(preloaderSelector).fadeOut();
		}
	}

	function manageVisibleSection(section) {
		var availableSection = [
			testerDashboardSectionSelector, 
			taskListSectionSelector, 
			taskDetailsSectionSelector, 
			testCaseTableSectionSelector, 
			testCaseCreateSectionSelector, 
			testExecutionSectionSelector,
			showHistorySectionSelectory
		];
		
		availableSection.forEach(function(item) {
			$(item).hide('fast');
		})
		
		switch (section) {

			case "dashboard":
				$(testerDashboardSectionSelector).show('fast');
				return;
				
			case "taskList":
				$(taskListSectionSelector).show('fast');
				return;
				
			case "taskDetails":
				$(taskDetailsSectionSelector).show('fast');
				$(testCaseTableSectionSelector).show('fast');
				return;

			case "createTest":
				$(testCaseCreateSectionSelector).show('fast');
				return;

			case "executeTest":
				$(testExecutionSectionSelector).show('fast');
				return;
				
			case "showHistory":
				$(showHistorySectionSelectory).show('fast');
				return;
		}
	}
	
	function createTestCaseFormAttachValidaiton() {
		$(testCaseCreateFormSelector).validate({ 
		    rules: {
		    	testCaseDesc: "required",
		    	testCaseInput: "required",
		    	testCaseOutput: "required"
		      },
		      messages: {
		    	  testCaseDesc: "Please enter test case description",
		    	  testCaseInput: "Please enter test case input",
		    	  testCaseOutput: "Please enter test case output"
		      },
		});
	}
	
	function execTestCaseFormAttachValidaiton() {
		$(testCaseExecFormSelector).validate({
		    rules: {
		    	testCaseInputExec: "required",
		    	testCaseOutputExec: "required"
		      },
		      messages: {
		    	  testCaseInputExec: "Please enter test case input",
		    	  testCaseOutputExec: "Please enter test case output"
		      },
		});
	}

	function manageTaskButtonHandler (e) {
		manageVisibleSection('taskList');
	}
	
	function taskListBackButtonHandler (e) {
		manageVisibleSection('dashboard');
	}
	
	function cancelTaskDetailsButtonHandler (e) {
		fetchTaskList();
		manageVisibleSection('taskList');
	}
	
	function createTestCaseButtonHandler (e) {
		$(testCaseCreateFormSelector).trigger("reset");
		$(testCaseTaskIdSelector).val(currentTaskId);
		$(testCaseModuleIdSelector).val(currentModuleId);
		manageVisibleSection('createTest');
	}
	
	function testCaseCancelButtonHandler (e) {
		manageVisibleSection('taskDetails');
	}
	
	function testCaseCancelExecButtonHandler () {
		manageVisibleSection('taskDetails');
	}
	
	function fetchHistoryExecutionDates () {
		var dataOp = {  
			"taskId": currentTaskId,
		}
		
		showLoader(true);
		$(executionDateSelectSelector).empty();
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/testManager/listDatesTaskTestCasesExecuted.htm",	
            data: JSON.stringify(dataOp),
            contentType: "application/json",
            success: function (response) {
            	var obj=JSON.parse(response);
            	if(obj && obj.STS == '200' && obj.CONTENT) {
            		var optionData =obj.CONTENT;
					
        			var optionStr = "", optionUnsigned="<option value='' selected>Unassigned</option>";
        			optionStr += optionUnsigned;
        			
        			for (var property in optionData) {
        				optionStr += "<option value='" + optionData[property].executionDate + "' >" + optionData[property].executionDate + "</option>"
        			}
        			
        			
        			$(executionDateSelectSelector).append(optionStr);
            	} else {
            		toastr.error('Something went wrong!', 'Error', toastPosition);
            	}
				showLoader(false);
            },
            error: function (err) {
            	showLoader(false);
            	console.log('Error occurred - ', err );
            	toastr.error('Something went wrong!', 'Error', toastPosition);
            }	
        });
	}
	
	function fetchHistoryExecutionData (dateIn) {
		showLoader(true);
		var dateData = {
			"taskId": currentTaskId,
			"executionDate": new Date(dateIn)
		}
		
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/tester/getResultSummary.htm",	
            data: JSON.stringify(dateData), 
            contentType: "application/json",
            success: function (response) {
            	
            	var obj=JSON.parse(response);
            	if(obj && obj.STS == '200' && obj.CONTENT) {
            		var chartData = obj.CONTENT;
            		drawExecutionChart(chartData);
            	}
				showLoader(false);
            },
            error: function (err) {
            	showLoader(false);
            	console.log('Error occurred - ', err );
            	toastr.error('Something went wrong!', 'Error', toastPosition);
            }	
        });
	}
	
	function showTestCaseExecutionHistoryHandler () {
		fetchHistoryExecutionDates();
		$('.execDetailsSection').css('display', 'none');
		$('#execHistoryChart').css('display', 'none');
		manageVisibleSection('showHistory');
	}
	
	function drawExecutionChart (chartData) {
		
		/*
		taskId: 0
		totaltestCaseCount: 1
		passedCount: 1
		failedCount: 0
		summaryPercentage: 100
		testCaseIdList: Array(1)
		*/
		
		var ctx = $(execHistoryChartSelector);
		
		$(".execDetailsDate").html(chartData.executionDate);
		$(".execDetailsTotalTC").html(chartData.totaltestCaseCount);
		$(".execDetailsTCPassed").html(chartData.passedCount);
		$(".execDetailsTCFailed").html(chartData.failedCount);
		$(".execDetailsPassPercent").html(chartData.summaryPercentage + " %");
		$(".execDetailsTCList").html(chartData.testCaseIdList.join(", "));
		
		if(historyExecutionChart !== ""){
			ctx.empty();
			historyExecutionChart.clear();
		}
		
		// For a pie chart
		historyExecutionChart = new Chart(ctx, {
		    type: 'pie',
		    data: {
		        labels: ['Passed', 'Failed'],
		        datasets: [{
		            label: '# of tests',
		            data: [chartData.passedCount, chartData.failedCount],
		            backgroundColor: [
		                // '#1ABC9C',
		                // '#E74C3C'
		                '#a8edea',
		                '#fed6e3'
		            ],
		            borderColor: [
		                // '#1ABC9C',
		                // '#E74C3C'
		                '#a8edea',
		                '#fed6e3'
		            ],
		            borderWidth: 1
		        }]
		    },
		    options: {}
		});
	}
	
	function executionDateSelectHandler(e) {
		var executionData = e.target.value;
		if(executionData){
			fetchHistoryExecutionData(e.target.value);
			$('.execDetailsSection').css('display', 'block');
			$('#execHistoryChart').css('display', 'block');
		} else {
			$('.execDetailsSection').css('display', 'none');
			$('#execHistoryChart').css('display', 'none');
		}
	}

	function execHistoryBackHandler () {
		manageVisibleSection('taskDetails');
	}
	
	function executeButtonHandler () {
		currentTestCaseRow = $(testCaseTableSelector).DataTable().row( $(this).closest('tr') ).index();
		$(testCaseExecFormSelector).trigger("reset");
		
		var data = $(testCaseTableSelector).DataTable().row(currentTestCaseRow).data();
		for(var field in data) {
			$(testCaseExecFormSelector).find('#' + field + 'Exec').val(data[field]);
		} 
		
		$(testCaseTaskIdExecSelector).val(currentTaskId);
		$(testCaseModuleIdExecSelector).val(currentModuleId);
		
		currentTestCaseId=data.testCaseId;
		manageVisibleSection('executeTest');
	}
	
	function submitForApprovalHandler () {
		currentTestCaseRow = $(testCaseTableSelector).DataTable().row( $(this).closest('tr') ).index();
		var data = $(testCaseTableSelector).DataTable().row(currentTestCaseRow).data();

		var dataOp = { 
			"testCaseId": data.testCaseId,
			"testCaseStatus": 'N'
		}
		
		showLoader(true);
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/tester/modifyTestCaseStatus.htm",
            data: JSON.stringify(dataOp), 	
            contentType: "application/json",
            success: function (response) {
            	var obj=JSON.parse(response);
            	if(obj && obj.STS == '200') {
            		data.testCaseApproved = 'N';
            		$(testCaseTableSelector).DataTable().row(currentTestCaseRow).data(data).draw(true);
            		toastr.success('Test case submitted for approval!', 'Success', toastPosition);
            	}
            	manageVisibleSection('taskDetails');
				showLoader(false);
            },
            error: function (err) {
            	showLoader(false);
            	console.log('Error occurred - ', err );
            	toastr.error('Error occurred while submitting test case for approval', 'Error', toastPosition);
            }	
        });
	}
	
	function submitTaskDetailsButtonHandler () {
		var values= {};
		$.each($(taskDetailsFormSelector).serializeArray(), function(i, field) {
    		values[field.name] = field.value;
		});
		
		// data transformation
		var dataOp = { 
			"taskId": currentTaskId,
			"taskStatus": values.taskStatus,
		}
		
		showLoader(true);
		
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/testManager/updateTaskStatusByTester.htm", 
             data: JSON.stringify(dataOp), 	
            contentType: "application/json",
            success: function (data) {
				var obj = JSON.parse(data);
				console.log(data);
				if(obj && obj.STS == '200') {
					if(obj.CONTENT && obj.CONTENT.taskStatus == 'complete' ){
						cancelTaskDetailsButtonHandler();
					}
					
					manageTaskStatusValidation(dataOp.taskStatus);
					toastr.success('Task details Updated!', 'Success', toastPosition);
					
				}
				//manageVisibleSection('taskDetails');
				showLoader(false);
            },
            error: function (err) {
            	console.log('Error occurred - ', err )
            	toastr.error('Error occurred while submitting task details', 'Error', toastPosition);
            }	
        });
    	
	}
	
	function testCaseSubmitButtonHandler (e) {
		if($(testCaseCreateFormSelector).valid()) {
			var values = {};
			
			$.each($(testCaseCreateFormSelector).serializeArray(), function(i, field) {
	    		values[field.name] = field.value;
			});
			
			values.testCaseId = "";
			values.testCaseApproved = 'C';
			
			var endDateJs = new Date(values.testCaseEndDate);

			// data transformation
			var dataOp = { 
				"taskId": values.testCaseTaskId,
				"comments":	values.testCaseDesc,
				"testCaseInputs": values.testCaseInput,
				"testCaseOutputs": values.testCaseOutput,
				"endDate": endDateJs,
				"testCaseStatus":values.testCaseApproved
			}
			
			showLoader(true);
			
	    	$.ajax({
	            type: "POST",
	            url: "${pageContext.servletContext.contextPath}/tester/createTestCase.htm", // replace the url with create test case api
	            data: JSON.stringify(dataOp), 	
	            contentType: "application/json",
	            success: function (data) {
	           		var obj=JSON.parse(data);
		 	        if(obj.STS == "200") {
		 	        	if(obj.CONTENT) { 
		 	        		values.testCaseId = obj.CONTENT.testCaseId;
		 	        	}
						$(testCaseTableSelector).DataTable().row.add(values).draw(false);
						manageVisibleSection('taskDetails');
						showLoader(false);
						toastr.success('Test case details submitted!', 'Success', toastPosition);
		            }
		 	    },
	            error: function (err) {
	            	console.log('Error occurred - ', err )
	            	toastr.error('Error occurred while submitting test case details', 'Error', toastPosition);
	            }	
	        });
		} else {
			toastr.warning('Please fill highlighted fields!', 'Warning', toastPosition);
		}

	}
	
	function testCaseSubmitExecButtonHandler () {
		if($(testCaseExecFormSelector).valid()) {
			var values = {};
			
			$.each($(testCaseExecFormSelector).serializeArray(), function(i, field) {
	    		values[field.name] = field.value;
			});
			
			var data = $(testCaseTableSelector).DataTable().row(currentTestCaseRow).data();
			
			values.testCaseId = currentTestCaseId;
			
			// data transformation
			var dataOp = { 
				"testCaseId": values.testCaseId,
				"comments": currentTestDesc,
				"taskId": values.testCaseTaskIdExec,
				"testCaseInputs": values.testCaseInputExec,
				"testCaseOutputs": values.testCaseOutputExec,
				"testCaseResultStatus": values.testCaseResultExec,
				"moduleId":values.testCaseModuleIdExec
			}
			
			showLoader(true);
	    	$.ajax({
	            type: "POST",
	            url: "${pageContext.servletContext.contextPath}/tester/recordTestResult.htm", // replace the url with create test case api
	            data: JSON.stringify(dataOp), 	
	            contentType: "application/json",
	            success: function (data) {
					manageVisibleSection('taskDetails');
					showLoader(false);
	            	toastr.success('Test case execution details submitted!', 'Success', toastPosition);
	            },
	            error: function (err) {
	            	console.log('Error occurred - ', err )
	            	toastr.error('Error occurred while submitting test case execution details', 'Error', toastPosition);
	            }	
	        });
		} else {
			toastr.warning('Please fill highlighted fields!', 'Warning', toastPosition);
		}

	}
	
	function manageTaskStatusValidation (taskStatus) {
		var taskStatusSelector = "#taskDetailsForm #taskStatus";
		
		if(taskStatus == "todo") {
			$(taskStatusSelector + " option").css("display", "block");	
		} else if(taskStatus == "inprogs") {
			$(taskStatusSelector + " option[value=todo]").css("display", "none");
		} else if(taskStatus == "blocked") {
			$(taskStatusSelector + " option[value=todo]").css("display", "none");
		} else {
			$(taskStatusSelector + " option").css("display", "none");
		}
	}
	
	
	
	
	function showTaskButtonHandler (e) {
		$(taskDetailsFormSelector).trigger("reset");
		
		currentTaskRow = $(taskListTableSelector).DataTable().row( $(this).closest('tr') ).index();
		
		var data = $(taskListTableSelector).DataTable().row(currentTaskRow).data();
		var taskStatus = data.taskStatus;
		manageTaskStatusValidation(taskStatus);
		
		currentTaskId = data.taskId;
		currentTaskEndDate = new Date(data.endDate);
		
		// setting the end date of the test case date picker to be equal to last date of taske
		$('#testCaseEndDate').datepicker('setEndDate', currentTaskEndDate);
		
		currentModuleId = data.moduleId;
		
		// populate row values in edit form
		for(var field in data) {
			$(taskDetailsFormSelector).find('#' + field).val(data[field]);
		}

		fetchTaskTestCaseList(data);
		manageVisibleSection('taskDetails');
	}
	
	function fetchTaskList () {
		
		// replace service name and the transform data code
		showLoader(true);
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/testManager/listTasksAssignedToTester.htm", 	
            contentType: "application/json", 
            success: function (data) {
            /* 	
            	data = {
            		"STS":"200",
            		"MSG":"success",
            		"CONTENT":[
            			{
            				"taskId": '1',
            				"taskDesc": 'Desc 1',
            				"testerAssigned": 'tester 1',
            				"taskStatus": 'toDo',
            				"endDate": '03/06/2020'
            			},
            			{
            				"taskId": '2',
            				"taskDesc": 'Desc 2',
            				"testerAssigned": 'tester 2',
            				"taskStatus": 'started',
            				"endDate": '03/06/2020'
            			},
            			{
            				"taskId": '3',
            				"taskDesc": 'Desc 3',
            				"testerAssigned": 'tester 3',
            				"taskStatus": 'toDo',
            				"endDate": '03/06/2020'
            			}
            		],
            		"T":""
            	} */
            	
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
				}}
				
				$(taskListTableSelector).DataTable({
					destroy: true,
					data: transformedData,
				    columns: [
				    	{ data: null },
				        { data: 'taskId' },
				        { data: 'taskDesc' },
				        { data: 'taskStatus' },
				        { data: 'endDate', defaultContent: "<i>Not set</i>" },
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
               toastr.error('Error occurred while fetching task list!', 'Error', toastPosition);
            }	
        });
	}
	
	function fetchTaskTestCaseList (testData) {
		console.log(testData);
		// replace service name and the transform data code
		var dataOp = {
				"taskId":testData.taskId,
				"taskDescription": testData.taskDesc,
				"taskStatus": testData.taskStatus
		}
		showLoader(true);
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/tester/listTestCasebasedOnTask.htm", 	
            data: JSON.stringify(dataOp),
            contentType: "application/json", 
            success: function (data) {
            	
            	/* data = {
            		"STS":"200",
            		"MSG":"success",
            		"CONTENT":[
            			{
            				"testId": '1',
            				"taskId": 'testCaseTaskId 1',
            				"testerId": 'tester 1',
            				"testDesc": 'testCaseDesc 1',
            				"approved": 'y',
            				"testInput": 'testCaseInput 1',
            				"testOutput": 'testCaseOutput 1',
            				"endDate": '03/06/2020',
            			},
            			{
            				"testId": '2',
            				"taskId": 'testCaseTaskId 2',
            				"testerId": 'tester 2',
            				"testDesc": 'testCaseDesc 2',
            				"approved": 'n',
            				"testInput": 'testCaseInput 2',
            				"testOutput": 'testCaseOutput 2',
            				"endDate": '03/06/2020',
            			},
            			{
            				"testId": '3',
            				"taskId": 'testCaseTaskId 3',
            				"testerId": 'tester 3',
            				"testDesc": 'testCaseDesc 3',
            				"approved": 'y',
            				"testInput": 'testCaseInput 3',
            				"testOutput": 'testCaseOutput 3',
            				"endDate": '03/06/2020',
            			},
            		],
            		"T":""
            	} */
            	
	           	// var obj = JSON.parse(data);
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
				        { data: 'testCaseEndDate', defaultContent: "<i>Not set</i>" },
				    	{ 
				    		data: null,
				    		render: function (data, type, row, meta) {
				    			var submitButton = '<button id="submitForApprovalButton" class="btn btn-primary btn-flat submitForApprovalButton" name="submitForApprovalButton" type="button" >Submit For Approval</button>';
				    			var executeButton = '<button id="executeButton" class="btn btn-primary btn-flat executeButton" name="executeButton" type="button" >Execute</button>';
				    			var pendingApprovalButton = '<button id="pendingApprovalButton" class="btn btn-primary btn-flat pendingApproval" name="pendingApprovalButton" type="button" disabled="disabled" >Approval Pending</button>';
						    	
				    			if(data.testCaseApproved == 'Y') {
				    				return executeButton;
				    			} else if (data.testCaseApproved == 'N') {
				    				return pendingApprovalButton;
				    			} else {
				    				return submitButton;
				    			}
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
               toastr.error('Error occurred while fetching test cases!', 'Error', toastPosition);
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
		
		manageVisibleSection('dashboard');
		fetchTaskList();

		createTestCaseFormAttachValidaiton();
		execTestCaseFormAttachValidaiton();
		
		$(managetaskButtonSelector).on('click', manageTaskButtonHandler);
		$(taskListBackButtonSelector).on('click', taskListBackButtonHandler);
		$(cancelTaskDetailsButtonSelector).on('click', cancelTaskDetailsButtonHandler);
		$(submitTaskDetailsButtonSelector).on('click', submitTaskDetailsButtonHandler);
		
		$(createTestCaseButtonSelector).on('click', createTestCaseButtonHandler);
		$(testCaseCancelButtonSelector).on('click', testCaseCancelButtonHandler);
		$(testCaseSubmitButtonSelector).on('click', testCaseSubmitButtonHandler);
		
		$(showExecutionHistoryButton).on('click', showTestCaseExecutionHistoryHandler);
		$(executionDateSelectSelector).on('change', executionDateSelectHandler);
		$(execHistoryBackButtonSelector).on('click', execHistoryBackHandler);
		
		$(testCaseSubmitExecButtonSelector).on('click', testCaseSubmitExecButtonHandler);
		$(testCaseCancelExecButtonSelector).on('click', testCaseCancelExecButtonHandler);
		
		$(testCaseTableSelector).on('click', executeButtonSelector, executeButtonHandler);
		$(testCaseTableSelector).on('click', submitForApprovalButtonSelector, submitForApprovalHandler);
		
		// show button click handler
		$(taskListTableSelector).on("click", showTaskButtonSelector, showTaskButtonHandler);
		

	});

</script>