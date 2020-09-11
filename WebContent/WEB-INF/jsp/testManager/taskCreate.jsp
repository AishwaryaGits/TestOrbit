<style>
	.lds-ripple {
		left: calc(50% - 175px);
	}
	form .error {
		color: red;
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
				<h4 class="page-title">Manages Tasks</h4>
				<div class="ml-auto text-right">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Manages Tasks</li>
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

		<div class="row createSection">
			<div class="col-12">

				<div class="card">
					<form class="form-horizontal" id="createForm">
						<div class="card-body">
							<h4 class="card-title">Enter Task Details</h4>
							<div class="form-group row">
								<label for="taskDesc" class="col-md-3 control-label col-form-label">Task Description</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="taskDesc" id="taskDesc">
								</div>
							</div>
							<div class="form-group row">
		                        <label for="tester" class="col-md-3 control-label col-form-label">Tester</label>
		                        <div class="col-md-9">
		                           	<select class="form-control custom-select" name="tester" id="tester"></select>
		                        </div>
		                    </div>
							<div class="form-group row">
								<label for="startDate" class="col-md-3 control-label col-form-label">Start Date</label>
								<div class="col-md-9 input-group">
									<input type="text" class="form-control" name="startDate" id="startDate" placeholder="mm/dd/yyyy">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="endDate" class="col-md-3 control-label col-form-label">End Date</label>
								<div class="col-md-9 input-group">
									<input type="text" class="form-control" name="endDate" id="endDate" placeholder="mm/dd/yyyy">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="taskStatus" class="col-md-3 control-label col-form-label">Task Status</label>
								<div class="col-md-9">
									<select class="form-control custom-select" name="taskStatus" id="taskStatus">
										<option value="todo">To do</option>
										<option value="inprogs">InProgress</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="moduleId" class="col-md-3 control-label col-form-label">Module Id</label>
								<div class="col-md-9 input-group">
									<input type="text" class="form-control" name="moduleId" id="moduleId" disabled="disabled">
								</div>
							</div>

						</div>
						<div class="border-top">
							<div class="card-body">
								<button type="button" id="submitTask" class="btn btn-primary float-right m-b-20">Submit</button>
								<button type="button" class="btn btn-secondary float-right m-b-20 m-r-10 cancelBtn">Cancel</button>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>

		<div class="row editSection">
			<div class="col-12">

				<div class="card">
					<form class="form-horizontal" id="editForm">
						<div class="card-body">
							<h4 class="card-title">Edit Task Details</h4>
							<div >
		                      <input type="hidden" name="taskIdEdit" id="taskIdEdit">
		                      
		                    </div>
							<div class="form-group row">
								<label for="taskDescEdit" class="col-md-3 control-label col-form-label">Task Description</label>
								<div class="col-md-9">
									<input type="text" class="form-control" name="taskDescEdit" id="taskDescEdit">
								</div>
							</div>
							
		                     <div class="form-group row">
		                        <label for="testerEdit" class="col-md-3 control-label col-form-label">Tester</label>
		                        <div class="col-md-9">
		                           	<select class="form-control custom-select" name="testerEdit" id="testerEdit"></select>
		                        </div>
		                    </div>
							<div class="form-group row">
								<label for="startDateEdit" class="col-md-3 control-label col-form-label">Start Date</label>
								<div class="col-md-9 input-group">
									<input type="text" class="form-control" name="startDateEdit" id="startDateEdit" placeholder="mm/dd/yyyy">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="endDateEdit" class="col-md-3 control-label col-form-label">End Date</label>
								<div class="col-md-9 input-group">
									<input type="text" class="form-control" name="endDateEdit" id="endDateEdit" placeholder="mm/dd/yyyy">
									<div class="input-group-append">
										<span class="input-group-text"><i class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label for="taskStatusEdit" class="col-md-3 control-label col-form-label">Task Status</label>
								<div class="col-md-9">
									<select class="form-control custom-select" name="taskStatusEdit" id="taskStatusEdit">
										<option value="todo">To do</option>
										<option value="inprogs">InProgress</option>
										<option value="blocked">Blocked</option>
										<option value="complete">Completed</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="moduleIdEdit" class="col-md-3 control-label col-form-label">Module Id</label>
								<div class="col-md-9 input-group">
									<input type="text" class="form-control" name="moduleIdEdit" id="moduleIdEdit" disabled="disabled">
								</div>
							</div>

						</div>
						<div class="border-top">
							<div class="card-body">
								<button type="button" id="updateTask" class="btn btn-primary float-right m-b-20">Update</button>
								<button type="button" class="btn btn-secondary float-right m-b-20 m-r-10 cancelBtn">Cancel</button>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
		
		<div class="row tableSection">
			<div class="col-12">
				<div class="card p-t-20 p-b-20">
					<div class="row">
						<div class="col-12">
							<h5 class="p-2 text-primary">Module Details</h5>
						</div>
					</div>
					
					<table class='table table-striped table-bordered' id='moduleTable'>
						<thead>
							<tr>
								<th valign='top'>Sr.No</th>
								<th>Module Name</th>
								<th>Module Description </th>
								<th>End Date</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id='moduleDataRow'></tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="row tableSection">
			<div class="col-12">
				<div class="card p-t-20 p-b-20">
					<div class="row">
						<div class="col-12">
							<h5 class="p-2 text-primary">Task List:</h5>
						</div>
					</div>
					
					<table class='table table-striped table-bordered' id='taskTable'>
						<thead>
							<tr>
								<th valign='top'>Sr.No</th>
								<th>Task Id</th>
								<th>Task Description</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Task Status</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id='taskDataRow'></tbody>
					</table>
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

	var moduleTableSelector = '#moduleTable';
	var taskTableSelector = '#taskTable';
	var createFormSelector = 'form#createForm';
	var editFormSelector = 'form#editForm';
	
	var cancelButtonSelector = '.cancelBtn';
	var createTaskButtonSelector = '.createTaskButton';
	var editTaskButtonSelector = '#editTaskBtn';
	
	var submitTaskButtonSelector = '#submitTask';
	var updateTaskButtonSelector = '#updateTask';
	var tableSectionSelector = '.tableSection';
	var createSectionSelector = '.createSection';
	var editSectionSelector = '.editSection';
	var showHistorySectionSelectory = ".showtExecutionHistorySection";
	var preloaderSelector = '.preloader';
	var moduleIdSelector = '#moduleId';
	var moduleIdEditSelector = "#moduleIdEdit"
	
	var startDateSelector = "#startDateEdit";
	var endDateSelector = "#endDateEdit";
	
	var executionDateSelectSelector = "#executionDateSelect";
	var showExecutionHistoryButton = "#execHistoryTestCaseButton";
	var execHistoryChartSelector = "#execHistoryChart";
	var execHistoryBackButtonSelector = "#execHistoryBackButton";
	
	var currentRow, currentModuleId, currentModuleEndDate, currentModuleRow, currentTaskRow;
	var toastPosition = { positionClass : "toast-bottom-right"};
	
	var historyExecutionChart = "";
	
    function showLoader(flag) {
		if (flag === true) {
			$(preloaderSelector).fadeIn();
		} else {
			$(preloaderSelector).fadeOut();
		}
	}

	function manageVisibleSection(section) {
		$(tableSectionSelector).hide('fast');
		$(editSectionSelector).hide('fast');
		$(createSectionSelector).hide('fast');
		$(showHistorySectionSelectory).hide('fast');
		
		switch (section) {
			case "table":
				$(tableSectionSelector).show('fast');
				return;

			case "create":
				$(createSectionSelector).show('fast');
				return;

			case "edit":
				$(editSectionSelector).show('fast');
				return;
			case "showHistory":
				$(showHistorySectionSelectory).show('fast');
				return;
		}
	}
	
	function createTaskFormAttachValidaiton() {
		$(createFormSelector).validate({ 
		    rules: {
		    	taskDesc: "required",
		      },
		      messages: {
		    	  taskDesc: "Please enter task description",
		      },
		});
	}
	
	function editTaskFormAttachValidaiton() {
		$(editFormSelector).validate({
		    rules: {
		    	taskDescEdit: "required",
		      },
		      messages: {
		    	  taskDescEdit: "Please enter task description",
		      },
		});
	}
	
	function addTesterOption() { 
		var optionData={}, optionStr = "", optionUnsigned="";
		
		//make an ajax call here to obtain the data for manager select box. An assumption has been made here that the data provided by the api call will be as below. 
		// here the object field name is the string that is displayed in option element and the object field value is taken as the value of the option element.
		
		$.ajax({
            type: "POST", 		//GET or POST or PUT or DELETE verb
            url: "${pageContext.servletContext.contextPath}/generic/listActiveTesterForTestManagers.htm", 		// Location of the service
           	
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
    			
    			$('#tester').append(optionStr);
    			$('#testerEdit').append(optionStr);
    			
    			showLoader(false);
            	 
            },
            error: function (data) {//On Successful service call
            	console.log('Error occurred - ', err )
            }	
        });
		showLoader(true);

	} 
	
	function fetchModuleTableRows() {
		showLoader(true);
		$.ajax({
			type: "POST",
			url: "${pageContext.servletContext.contextPath}/projectManager/listProjectUnderTestManager.htm",
			contentType: "application/json",
			success: function (data) {
				var obj = JSON.parse(data);
				var transformedData = [];
				if(obj.STS == "200"){
					var objItem= obj.CONTENT;
					if (obj && obj.CONTENT) {
						transformedData.push({
							"moduleId": objItem.moduleId,
							"moduleName": objItem.moduleName,
							"endDate": objItem.endDate,
							"moduleDesc": objItem.moduleDescription,
							"status": objItem.moduleStatus,
							"testManager": objItem.testManagerId,
						})
					}
				}
				$(moduleTableSelector).DataTable({
					data: transformedData,
					columns: [
						{ data: null },
						{ data: 'moduleName' },
						{ data: 'moduleDesc' },
						{ data: 'endDate' ,"defaultContent": "<i>Not set</i>"},
						{
							data: null,
							render: function (data, type, row, meta) {
								var isEditDisabled = '';
								if (data.status === 'inactive') {
									isEditDisabled = ' disabled="disabled" ';
								}
								return '<button id="editBtn" class="btn btn-secondary btn-flat createTaskButton" name="editBtn" type="button" ' + isEditDisabled + '>Create Task</button>'
							}
						},
					],
					rowCallback: function (row, data, index) {
						$('td:eq(0)', row).html(index + 1);
					}
				});

				showLoader(false);
			},
			error: function (err) {
				console.log('Error occurred - ', err)
			}
		});
	}
	
	function fetchTaskTableRows() {
		showLoader(true);
		$.ajax({
			type: "POST",
			url: "${pageContext.servletContext.contextPath}/testManager/listTasksUnderTestManager.htm",
			contentType: "application/json",
			success: function (data) {
				var obj = JSON.parse(data);
				var transformedData = [];
				if(obj.STS == "200"){
				if (obj && obj.CONTENT) {
					obj.CONTENT.forEach(function (item, index) {
						transformedData.push({
							"taskId" :item.taskId,
							"taskDesc": item.taskDescription,
							"startDate": item.startDate,
							"endDate": item.endDate,
							"taskStatus": item.taskStatus,
							"moduleId": item.moduleId,
							"tester": item.testerId
						})
					})
				}}
				
				$(taskTableSelector).DataTable({
					data: transformedData,
					columns: [
						{ data: null },
						{ data: 'taskId' },
						{ data: 'taskDesc' },
						{ data: 'startDate', defaultContent: "<i>Not set</i>" },
						{ data: 'endDate', defaultContent: "<i>Not set</i>"},
						{ data: 'taskStatus' },
						{
							data: null,
							render: function (data, type, row, meta) {
								var action =  '<button id="editTaskBtn" class="btn btn-secondary btn-flat editTaskBtn" name="editTaskBtn" type="button" >Edit Task</button>' + 
								'<button type="button" id="execHistoryTestCaseButton" class="btn btn-success float-right">Execution History</button>'
								return action;
							}
						},
					],
					rowCallback: function (row, data, index) {
						$('td:eq(0)', row).html(index + 1);
					}
				});
				showLoader(false);
			},
			error: function (err) {
				console.log('Error occurred - ', err)
			}
		});
	}
	
	function manageTaskStatusValidation (taskStatus) {
		var taskStatusSelector = "#editForm #taskStatusEdit";
		
		$(taskStatusSelector + " option").css("display", "block");	
		$("#startDateEdit, #endDateEdit").removeAttr("disabled");
		
		if (taskStatus == "todo") {
			$(taskStatusSelector + " option").css("display", "none");
			$(taskStatusSelector + " option[value=inprogs]").css("display", "block");
		} else if( (taskStatus == "inprogs") || (taskStatus == "blocked") ) {
			$(taskStatusSelector + " option[value=todo]").css("display", "none");
			$("#startDateEdit").attr('disabled', 'disabled');
		} else if (taskStatus == "complete") {
			$("#startDateEdit, #endDateEdit").attr('disabled', 'disabled');
			$(taskStatusSelector + " option").css("display", "none");
		}
	}
	
	function showTestCaseExecutionHistoryHandler () {
		currentTaskRow = $(taskTableSelector).DataTable().row( $(this).closest('tr') ).index();
		fetchHistoryExecutionDates();
		$('.execDetailsSection').css('display', 'none');
		$('#execHistoryChart').css('display', 'none');
		manageVisibleSection('showHistory');
	}
	
	function fetchHistoryExecutionDates () {
		var data = $(taskTableSelector).DataTable().row(currentTaskRow).data();
		var dataOp = {  
			"taskId": data.taskId,
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
		manageVisibleSection('table');
	}
	
	function fetchHistoryExecutionData (dateIn) {
		showLoader(true);
		var data = $(taskTableSelector).DataTable().row(currentTaskRow).data();
		var dateData = {
			"taskId": data.taskId,
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
	
	$(document).ready(function () {

		// showLoader(false);
		$('[data-toggle="tooltip"]').tooltip();
		$('#startDate, #startDateEdit, #endDate, #endDateEdit').datepicker({
			autoclose: true,
			todayHighlight: true,
			orientation: 'bottom',
			startDate: new Date()
		}).attr('readonly', 'true');
		
		manageVisibleSection('table');
		
		addTesterOption();
		fetchModuleTableRows();
		fetchTaskTableRows();
		
		createTaskFormAttachValidaiton();
		editTaskFormAttachValidaiton()
		
		/*
		// status selection change handler
		$('#taskStatus').on("change", function (e) {
			var selectedOptionVal = e.target.value;
			if (selectedOptionVal === 'inprogs' || selectedOptionVal === 'blocked') {
				$(startDateSelector).attr('disabled', 'disabled');
			} else if (selectedOptionVal === 'active') {
				$('#endDate').removeAttr('disabled');
			}
		})

		// statusEdit selection change handler
		$('#taskStatusEdit').on("change", function (e) {
			var selectedOptionVal = e.target.value;
			
			if (selectedOptionVal === 'notStarted') {
				$('#endDateEdit').attr('disabled', 'disabled');
				$('#testManagerEdit').attr('disabled', 'disabled');
				$("#statusEdit option").removeAttr('disabled');
			} else if (selectedOptionVal === 'active') {
				$('#endDateEdit').removeAttr('disabled');
				$("#statusEdit option[value=notStarted]").attr('disabled', 'disabled');
			}
		})
		*/

		// cancel button click handler
		$(cancelButtonSelector).on('click', function () {
			manageVisibleSection('table');
		})

		// create task button click handler
		$(moduleTableSelector).on("click", createTaskButtonSelector, function () {
			currentModuleRow = $(moduleTableSelector).DataTable().row( $(this).closest('tr') ).index();
			var data = $(moduleTableSelector).DataTable().row(currentModuleRow).data();
			
			currentModuleId = data.moduleId;
			currentModuleEndDate = new Date(data.endDate);
			
			$(createFormSelector).trigger("reset");
			$("#startDate").datepicker('setEndDate', currentModuleEndDate);
			$("#endDate").datepicker('setEndDate', currentModuleEndDate);
			
			$(moduleIdSelector).val(currentModuleId);
			
			manageVisibleSection('create');
		})
		
		$("#startDate").on('change', function(e) {
			var newStartDate = new Date(e.target.value);
			$("#endDate").datepicker('setStartDate', newStartDate);
		});
		$("#startDateEdit").on('change', function(e) {
			var newStartDate = new Date(e.target.value);
			$("#endDateEdit").datepicker('setStartDate', newStartDate);
		})
		

		$(taskTableSelector).on("click", showExecutionHistoryButton, showTestCaseExecutionHistoryHandler);
		$(executionDateSelectSelector).on('change', executionDateSelectHandler);
		$(execHistoryBackButtonSelector).on('click', execHistoryBackHandler);
		
		
		// edit task button click handler
		$(taskTableSelector).on("click", editTaskButtonSelector, function () {
			currentTaskRow = $(taskTableSelector).DataTable().row( $(this).closest('tr') ).index();
			
			var data = $(taskTableSelector).DataTable().row(currentTaskRow).data();
			
			manageTaskStatusValidation(data.taskStatus)
;
			currentModuleId = data.moduleId;
			$(editFormSelector).trigger("reset");
			
			// populate row values in edit form
			for(var field in data) {
				$(editFormSelector).find('#' + field + 'Edit').val(data[field]);
			}
			
			$("#startDateEdit").datepicker('setEndDate', currentModuleEndDate);
			$("#endDateEdit").datepicker('setEndDate', currentModuleEndDate);
			
			manageVisibleSection('edit');
		})


		// form submit button click handler
		$(submitTaskButtonSelector).on("click", function () {
			if($(createFormSelector).valid()) { 
				var values = {};
				$.each($(createFormSelector).serializeArray(), function (i, field) {
					values[field.name] = field.value;
				});
				var endDateJs = new Date(values.endDate);
				var startDateJs = new Date(values.startDate);

				// data transformation
				var dataOp = {
					"moduleId":currentModuleId,
					"taskDescription": values.taskDesc,
					"testerId": values.tester,
					"startDate": startDateJs,
					"endDate": endDateJs,
					"taskStatus": values.taskStatus
				}

				showLoader(true);
				$.ajax({
					type: "POST", 		//GET or POST or PUT or DELETE verb
					url: "${pageContext.servletContext.contextPath}/testManager/createTask.htm",
					data: JSON.stringify(dataOp),
					contentType: "application/json",//Data sent to server
					success: function (data) {//On Successful service call
						//$(taskTableSelector).DataTable().row.add(values).draw(false);
						manageVisibleSection('table');
						location.reload();
						showLoader(false);
					},
					error: function (err) {//On Successful service call
						console.log('Error occurred - ', err)
					}
				});
				
			} else {
				toastr.warning('Please fill highlighted fields!', 'Warning', toastPosition);
			}
			
			
		});

		// form update button click handler
		$(updateTaskButtonSelector).on("click", function () {
			if($(editFormSelector).valid()) { 
				var values = {};
				$.each($(editFormSelector).serializeArray(), function (i, field) {
					values[field.name.slice(0, -4)] = field.value;
				});
				var endDateJs = new Date(values.endDate);
				var startDateJs = new Date(values.startDate);

				// data transformation
				var dataOp = {
					 "taskId":values.taskId,
					"moduleId":currentModuleId,
					"taskDescription": values.taskDesc,
					"testerId": values.tester,
					"startDate": startDateJs,
					"endDate": endDateJs,
					"taskStatus": values.taskStatus
				}

				showLoader(true);
				$.ajax({
					type: "POST",
					url: "${pageContext.servletContext.contextPath}/testManager/editTask.htm",
					data: JSON.stringify(dataOp),
					contentType: "application/json",
					success: function (data) {
						$(taskTableSelector).DataTable().row(currentRow).data(values).draw(false);
						manageVisibleSection('table');
						location.reload();
						showLoader(false);
					},
					error: function (err) {
						console.log('Error occurred - ', err)
					}
				});
			} else {
				toastr.warning('Please fill highlighted fields!', 'Warning', toastPosition);
			}
			
			
		});

	});
</script>