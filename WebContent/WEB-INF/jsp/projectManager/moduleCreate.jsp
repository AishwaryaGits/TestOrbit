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
	            <h4 class="page-title">Create Module</h4>
	            <div class="ml-auto text-right">
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb">
	                        <li class="breadcrumb-item"><a href="#">Home</a></li>
	                         <li class="breadcrumb-item active" aria-current="page">Create Module</li>
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
		    	<div class="float-right m-b-10">
					<button class="btn btn-primary" id="createNewBtn">
						<i class="glyphicon glyphicon-plus" style="cursor: pointer;"></i> 
						Create New
					</button>
		        </div>
		    </div>
	   </div>
	   
	    <div class="row createSection">
			<div class="col-12">
	
		    	<div class="card">
		            <form class="form-horizontal" id="createForm">
		                <div class="card-body">
		                    <h4 class="card-title">Enter Module Details</h4>
		                    <div class="form-group row">
		                        <label for="moduleName" class="col-md-3 control-label col-form-label">Module Name</label>
		                        <div class="col-md-9">
		                            <input type="text" class="form-control" name="moduleName" id="moduleName" >
		                        </div>
		                    </div>
		                    <div class="form-group row">
		                        <label for="moduleDesc" class="col-md-3 control-label col-form-label">Module Description</label>
		                        <div class="col-md-9">
		                            <textarea class="form-control" name="moduleDesc" id="moduleDesc" ></textarea>
		                        </div>
		                    </div>
		                    <div class="form-group row">
		                        <label for="status" class="col-md-3 control-label col-form-label">Status</label>
		                        <div class="col-md-9">
		                           	<select class="form-control custom-select" name="status" id="status" >
		                           		<option value="notStarted">Not Started</option>
		                           		<option value="active">Active</option>
		                           		<option value="inactive">Inactive</option>
		                           	</select>
		                        </div>
		                    </div>
		                    <div class="form-group row">
		                    	<label for="datepicker-autoclose" class="col-md-3 control-label col-form-label">End Date</label>
		                        <div class="col-md-9 input-group">
		                            <input type="text" class="form-control" name="endDate" id="endDate" placeholder="mm/dd/yyyy" readonly >
		                            <div class="input-group-append">
		                                <span class="input-group-text"><i class="fa fa-calendar"></i></span>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="form-group row">
		                        <label for="testManager" class="col-md-3 control-label col-form-label">Test Manager</label>
		                        <div class="col-md-9">
		                           	<select class="form-control custom-select" name="testManager" id="testManager" ></select>
		                        </div>
		                    </div>
	
		                </div>
		                <div class="border-top">
		                    <div class="card-body">
		                        <button type="submit" id="submitModule" class="btn btn-primary float-right m-b-20">Submit</button>
		                    	<button type="button" class="btn btn-secondary float-right m-b-20 m-r-10 cancelBtn" >Cancel</button>
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
		                    <h4 class="card-title">Edit Module Details</h4>
		                     <div >
		                      <input type="hidden" name="moduleIdEdit" id="moduleIdEdit">
		                      
		                    </div>
		                    <div class="form-group row">
		                        <label for="moduleNameEdit" class="col-md-3 control-label col-form-label">Module Name</label>
		                        <div class="col-md-9">
		                            <input type="text" class="form-control" name="moduleNameEdit" id="moduleNameEdit">
		                        </div>
		                    </div>
		                    <div class="form-group row">
		                        <label for="moduleDescEdit" class="col-md-3 control-label col-form-label">Module Description</label>
		                        <div class="col-md-9">
		                            <textarea class="form-control" name="moduleDescEdit" id="moduleDescEdit"></textarea>
		                        </div>
		                    </div>
		                    <div class="form-group row">
		                        <label for="statusEdit" class="col-md-3 control-label col-form-label">Status</label>
		                        <div class="col-md-9">
		                           	<select class="form-control custom-select" name="statusEdit" id="statusEdit">
		                           	<option value="notStarted">Not Started</option>
		                           		<option value="active">Active</option>
		                           		<option value="inactive">Inactive</option>
		                           	</select>
		                        </div>
		                    </div>
		                    <div class="form-group row">
		                    	<label for="endDateEdit" class="col-md-3 control-label col-form-label">End Date</label>
		                        <div class="col-md-9 input-group">
		                            <input type="text" class="form-control" name="endDateEdit" id="endDateEdit" placeholder="mm/dd/yyyy" readonly>
		                            <div class="input-group-append">
		                                <span class="input-group-text"><i class="fa fa-calendar"></i></span>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="form-group row">
		                        
		                        	  <input type="hidden" name="existingTestManagerIdEdit" id="existingTestManagerIdEdit">
		                          
		                      
		                    </div>
		                    <div class="form-group row">
		                        <label for="testManagerEdit" class="col-md-3 control-label col-form-label">Test Manager</label>
		                        <div class="col-md-9">
		                           	<select class="form-control custom-select" name="testManagerEdit" id="testManagerEdit"></select>
		                        </div>
		                    </div>
	
		                </div>
		                <div class="border-top">
		                    <div class="card-body">
		                        <button type="button" id="updateModule" class="btn btn-primary float-right m-b-20">Update</button>
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
					<table class='table table-striped table-bordered' id='moduleTable'>
						<thead>
							<tr>
								<th valign='top'>Sr.No</th>
								<th>Module Name</th>
								<th>End Date</th>
								<th>Module Description </th>
								<th>Status </th>
								<th>Test Manager</th>
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
 	var toastPosition = { positionClass : "toast-bottom-right"};
	var tableSelector = '#moduleTable';
	var createFormSelector = 'form#createForm';
	var editFormSelector = 'form#editForm';
	var createNewButtonSelector = '#createNewBtn';
	var cancelButtonSelector = '.cancelBtn';
	var editButtonSelector = '.editButton';
	var submitModuleButtonSelector = '#submitModule';
	var updateModuleButtonSelector = '#updateModule';
	var tableSectionSelector =  '.tableSection';
	var createSectionSelector =  '.createSection';
	var editSectionSelector =  '.editSection';
	var preloaderSelector = '.preloader';
	var existingTesterIdSelector = '#existingTestManagerIdEdit';
	var currentRow;
	var editStatusSelectSelector = "#statusEdit";
	var statusSelectSelector = "#status";
	var endDateSelector = "#endDate";
	var endDateEditSelector = "#endDateEdit";
	var testManagerSelector = "#testManager";
	var testManagerEditSelector = "#testManagerEdit";
	var currentModuleStatus;
	
    function showLoader(flag) {
   	 if (flag === true) {
   		 $(preloaderSelector).fadeIn();
   	 } else {
   		 $(preloaderSelector).fadeOut();
   	 }
    }
    
	function manageVisibleSection(section){
		$(tableSectionSelector).hide('fast');
		$(editSectionSelector).hide('fast');
		$(createSectionSelector).hide('fast');
		
		switch(section) {
		case "table" :
			$(tableSectionSelector).show('fast');
			return;
			
		case "create" :
			$(createSectionSelector).show('fast');
			return;
			
		case "edit" :
			$(editSectionSelector).show('fast');
			return;
		}
	}
	
	function createFormAttachValidaiton() {
		$(createFormSelector).validate({
		    rules: {
		    	moduleName: "required",
		    	moduleDesc: "required",
		    	status: "required"
		      },
		      messages: {
		    	  moduleName: "Please enter module name",
		    	  moduleDesc: "Please enter module description",
		    	  status: "Please select status"
		      },
		});
	}
	
	function editFormAttachValidaiton() {
		$(editFormSelector).validate({
		    rules: {
		    	moduleNameEdit: "required",
		    	moduleDescEdit: "required",
		    	statusEdit: "required"
		      },
		      messages: {
		    	  moduleNameEdit: "Please enter module name",
		    	  moduleDescEdit: "Please enter module description",
		    	  statusEdit: "Please select status"
		      },
		});
	}
	
	function addTestManagerOption() { 
		var optionData={}, optionStr = "", optionUnsigned="";

		$.ajax({
            type: "POST", 		//GET or POST or PUT or DELETE verb
            url: "${pageContext.servletContext.contextPath}/generic/listUnassignedTestManagers.htm", 		// Location of the service
           	
            contentType: "application/json",//Data sent to server
            success: function (data) {//On Successful service call
             	var obj=JSON.parse(data);
             	if(obj && obj.STS == "200"){
		        	if (obj.CONTENT) {
		            	optionData = obj.CONTENT;
	             	}
	        	}
	            
    			optionUnsigned="<option value='' selected>Unassigned</option>";
    			optionStr += optionUnsigned;
    			
    			for (var property in optionData) {
    				optionStr += "<option value='" + optionData[property].userId + "' >" + optionData[property].firstName + " " + optionData[property].lastName + "</option>"
    			}
    			
    			$(testManagerSelector).append(optionStr);
    			$(testManagerEditSelector).append(optionStr);
    			
    			showLoader(false);
            	 
            },
            error: function (data) {//On Successful service call
            	console.log('Error occurred - ', err )
            	toastr.warning('Something went wrong!', 'Warning', toastPosition);
            }	
        });
		showLoader(true);
	
	} 
	 
	function fetchModuleTableRows() { 
		
		showLoader(true);
    	$.ajax({
            type: "POST",
            url: "${pageContext.servletContext.contextPath}/projectManager/listProjectsUnderProjManager.htm", 	
            contentType: "application/json", 
            success: function (data) {
	           	var obj=JSON.parse(data);
				var transformedData = [];
				var testManagerId="";
				
				if(obj && obj.STS == "200"){
					if (Array.isArray(obj.CONTENT)) {
						obj.CONTENT.forEach(function(item) {
							
							transformedData.push({
								"moduleId": item.moduleId,
								"moduleName": item.moduleName,
						        "endDate": item.endDate,
						        "moduleDesc": item.moduleDescription,
						        "status": item.moduleStatus,
						        "testManager": item.testManagerId,
						    })
						})
					}
				}

				$(tableSelector).DataTable({
				    language: {
				        "emptyTable": "No modules available."
				    },
					data: transformedData,
				    columns: [
				    	{ data: null },
				    	
				        { data: 'moduleName' },
				        { data: 'endDate', defaultContent: "<i>Not set</i>"},
				        { data: 'moduleDesc' },
				        { data: 'status' },
				        { data: 'testManager', defaultContent: "<i>Not set</i>" },
				    	{ 
				    		data: null,
				    		render: function (data, type, row, meta){	
				    			var isEditDisabled = '';	
				    			if(data.status === 'inactive') {	
				    				isEditDisabled = ' disabled="disabled" ';	
				    			}	
				    			return '<button id="editBtn" class="btn btn-secondary btn-flat editButton" name="editBtn" type="button" ' + isEditDisabled + '>Edit</button>'	
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
               toastr.error('Something went wrong!', 'Error', toastPosition);
            }	
        });
	} 
	
	$(document).ready(function() {
		
		// showLoader(false);
		var dateToday = new Date();
		$('[data-toggle="tooltip"]').tooltip();
		$(endDateSelector).datepicker({
		    autoclose: true,
		    todayHighlight: true,
		    orientation: 'bottom',
			startDate: dateToday,
		});
		$(endDateEditSelector).datepicker({
		    autoclose: true,
		    todayHighlight: true,
		    orientation: 'bottom',
			startDate: dateToday,
		});
		manageVisibleSection('table');
		
		createFormAttachValidaiton();
		editFormAttachValidaiton();
		
		addTestManagerOption();
		fetchModuleTableRows();
		
		// status selection change handler	
		$(statusSelectSelector).on("change", function(e) {	
			var selectedOptionVal = e.target.value;	
			if(selectedOptionVal === 'notStarted') {	
				$(endDateSelector).attr('disabled', 'disabled');	
			} else if(selectedOptionVal === 'active') {	
				$(endDateSelector).removeAttr('disabled');	
				$(endDateSelector).removeAttr('readonly');	
			}	
		})	
			
		// statusEdit selection change handler	
		$(editStatusSelectSelector).on("change", function(e) {	
			
			var selectedOptionVal = e.target.value;
			if(currentModuleStatus=== 'active'){
				$(editStatusSelectSelector + " option[value=notStarted]").attr('disabled', 'disabled');	
			}
			if(selectedOptionVal === 'notStarted') {	
				$(endDateEditSelector).attr('disabled', 'disabled');	
				/* $(testManagerEditSelector).attr('disabled', 'disabled');	 */
				$(editStatusSelectSelector + " option").removeAttr('disabled');	
			} else if(selectedOptionVal === 'active') {	
				$(endDateEditSelector).removeAttr('disabled');	
				$(testManagerEditSelector).removeAttr('disabled');	
			
			}else if(selectedOptionVal === 'inactive') {	
				$(endDateEditSelector).attr('disabled', 'disabled');	
				$(testManagerEditSelector).attr('disabled', 'disabled');
			
			}	
		})
	
		// createNew button click handler
		$(createNewButtonSelector).on("click", function() {
			$(createFormSelector).trigger("reset");
			$(statusSelectSelector + " option[value=inactive]").attr('disabled', 'disabled');	
			$(statusSelectSelector).trigger('change');
			manageVisibleSection('create');
		})
		
		// cancel button click handler
		$(cancelButtonSelector).on('click', function() {
			manageVisibleSection('table');
		})
		
		// edit button click handler
		$(tableSelector).on("click", editButtonSelector, function() {
			$(editFormSelector).trigger("reset");
			$(statusSelectSelector + " option[value=inactive]").removeAttr('disabled');
			currentRow =  $(tableSelector).DataTable().row( $(this).closest('tr') ).index();
			var data = $(tableSelector).DataTable().row(currentRow).data();
			console.log(data);
			// populate row values in edit form
			for(var field in data) {
				console.log(field);
				$(editFormSelector).find('#' + field + 'Edit').val(data[field]);
			}
			currentModuleStatus=data.status;
			$(existingTesterIdSelector).val(data.testManager);
			$(editStatusSelectSelector).trigger('change');
			manageVisibleSection('edit');
		})
	
		
		
		// form submit button click handler
		$(submitModuleButtonSelector).on("click", function() {
			if($(createFormSelector).valid()) {
				var values = {};
				$.each($(createFormSelector).serializeArray(), function(i, field) {
					
		    		values[field.name] = field.value;
				});
				var endDateJs=new Date(values.endDate);

				// data transformation
				var dataOp = { 
					"moduleName": values.moduleName,
					"moduleDescription": values.moduleDesc,
					"testManagerId": values.testManager,
					"endDate":endDateJs,
					"moduleStatus": values.status
				}
				
				showLoader(true);
		    	$.ajax({
		            type: "POST", 		//GET or POST or PUT or DELETE verb
		            url: "${pageContext.servletContext.contextPath}/projectManager/createModule.htm",
		            data: JSON.stringify(dataOp), 	
		            contentType: "application/json",//Data sent to server
		            success: function (data) {//On Successful service call
						$(tableSelector).DataTable().row.add(values).draw(true);
						manageVisibleSection('table');
						toastr.success('Module is created!', 'Success', toastPosition);
						location.reload();
						showLoader(false);
						
		            },
		            error: function (err) {//On Successful service call
		            	console.log('Error occurred - ', err );
		            	toastr.error('Error occurred while creating module!', 'Error', toastPosition);
		            }	
		        });
			} else {
				toastr.warning('Please fill highlighted fields!', 'Warning', toastPosition);
			}
		});
		
		// form update button click handler
		$(updateModuleButtonSelector).on("click", function() {
			if($(editFormSelector).valid()) {
				var values = {};
				$.each($(editFormSelector).serializeArray(), function(i, field) {
		    		values[field.name.slice(0, -4)] = field.value;
				});
				var endDateJs=new Date(values.endDate);
				// data transformation
				var dataOp = { 
					"moduleId":	values.moduleId,
					"moduleName": values.moduleName,
					"moduleDescription": values.moduleDesc,
					"testManagerId": values.testManager,
					"endDate":endDateJs,
					"moduleStatus": values.status,
					"currentTestManagerId": values.existingTestManagerId
				}
				
				showLoader(true);
		    	$.ajax({
		            type: "POST",
		            url: "${pageContext.servletContext.contextPath}/projectManager/editModule.htm",
		            data: JSON.stringify(dataOp), 	
		            contentType: "application/json",
		            success: function (data) {
		            	 var obj=JSON.parse(data);
		 	           	
		 	           	if(obj.STS == "200") {
		 	           		
						$(tableSelector).DataTable().row(currentRow).data(values).draw(true);
						setTimeout(function(){ 
							toastr.success('Module is updated!', 'Success', toastPosition);
						}, 1500);
						location.reload();
						
						
		            } else if((obj.STS == "500")){
		            	alert(obj.MSG);
		            	
		            }
		            manageVisibleSection('table');
		            showLoader(false);
		            
		            },
		            error: function (err) {
		            	console.log('Error occurred - ', err );
		            	toastr.error('Error occurred while updating module!', 'Success', toastPosition);
		            }	
		        }); 
			} else {
				toastr.warning('Please fill highlighted fields!', 'Warning', toastPosition);
			}
		});
		
	 });
</script>
