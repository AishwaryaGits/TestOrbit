<style>
  	html, body, .main-wrapper, .auth-wrapper {
		height: 100%;
	}
	
	.mainPageBg {
		background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);
	}
	
	.comment-widgets .comment-row {
		background: #E0EAFC;  /* fallback for old browsers */
		background: -webkit-linear-gradient(to right, #CFDEF3, #E0EAFC);  /* Chrome 10-25, Safari 5.1-6 */
		background: linear-gradient(to right, #CFDEF3, #E0EAFC); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
		display: inline-block;
		border-radius: 10px;
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
	            <h4 class="page-title">Dashboard</h4>
	            <div class="ml-auto text-right">
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb">
	                        <li class="breadcrumb-item"><a href="#">Home</a></li>
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
	    <!-- ============================================================== -->
	    <!-- Sales Cards  -->
	    <!-- ============================================================== -->
	    <div class="row">
	        <!-- Column -->
	        <div class="col-md-6 col-lg-2 col-xlg-3">
	            <div class="card card-hover">
	                <div class="box bg-cyan text-center">
	                    <h1 class="font-light text-white"><i class="mdi mdi-view-dashboard"></i></h1>
	                    <h6 class="text-white">Create Project</h6>
	                </div>
	            </div>
	        </div>
	        <!-- Column -->
	        <div class="col-md-6 col-lg-2 col-xlg-3">
	            <div class="card card-hover">
	                <div class="box bg-success text-center">
	                    <h1 class="font-light text-white"><i class="mdi mdi-relative-scale"></i></h1>
	                    <h6 class="text-white">Approve/ Users</h6>
	                </div>
	            </div>
	        </div>
	         <!-- Column -->
	        <div class="col-md-6 col-lg-2 col-xlg-3">
	            <div class="card card-hover">
	                <div class="box bg-warning text-center">
	                    <h1 class="font-light text-white"><i class="mdi mdi-collage"></i></h1>
	                    <h6 class="text-white">Roles Requirements</h6>
	                </div>
	            </div>
	        </div>
	        <!-- Column -->
	        <div class="col-md-6 col-lg-2 col-xlg-3">
	            <div class="card card-hover">
	                <div class="box bg-danger text-center">
	                    <h1 class="font-light text-white"><i class="mdi mdi-border-outside"></i></h1>
	                    <h6 class="text-white">Reassign Projects</h6>
	                </div>
	            </div>
	        </div>
	        <!-- Column -->


	    </div>
	    <!-- ============================================================== -->
	    <!-- Projects -->
	    <!-- ============================================================== -->
	    <div class="row">
	        <div class="col-md-6">
	            <div class="card">
	                <div class="card-body">
	                    <div class="d-md-flex align-items-center">
	                        <div>
	                            <h4 class="card-title">Projects</h4>
	                            <h5 class="card-subtitle">List of current projects</h5>
	                        </div>
	                    </div>

						<div class="comment-widgets scrollable">
						    <!-- Comment Row -->
						    <div class="d-flex flex-row comment-row m-t-0">
						        <div class="comment-text w-100">
						            <h6 class="font-medium">Open Banking</h6>
						            <span class="m-b-15 d-block">Regression test for Open Banking Project </span>
						            <span class="m-b-15 p-b-5 d-block">To develop a regression suite for an Open Banking project </span>
						            <div class="comment-footer m-t-5">
						                <span class="text-muted float-right">Last accessed - Feb 04, 2020</span> 
						                <button type="button" class="btn btn-cyan btn-sm">Open</button>
						            </div>
						        </div>
						    </div>
						    <!-- Comment Row -->
							<!-- Comment Row -->
						    <div class="d-flex flex-row comment-row m-t-0">
						        <div class="comment-text w-100">
						            <h6 class="font-medium">Autonomous Car</h6>
						            <span class="m-b-15 d-block">E2E Test for Autonomous Car Project </span>
						            <span class="m-b-15 p-b-5 d-block">To test the E2E flow for the AI module developed </span>
						            <div class="comment-footer m-t-5">
						                <span class="text-muted float-right">Last accessed - Feb 04, 2020</span> 
						                <button type="button" class="btn btn-cyan btn-sm">Open</button>
						            </div>
						        </div>
						    </div>
						    <!-- Comment Row -->
						    <!-- Comment Row -->
						    <div class="d-flex flex-row comment-row m-t-0">
						        <div class="comment-text w-100">
						            <h6 class="font-medium">Front Revamp 2.x</h6>
						            <span class="m-b-15 d-block">Manual testing for front end revamp project.</span>
						            <span class="m-b-15 p-b-5 d-block">To test the validity of the revamp process with the requirements </span>
						            <div class="comment-footer m-t-5">
						                <span class="text-muted float-right">Last accessed - Feb 04, 2020</span> 
						                <button type="button" class="btn btn-cyan btn-sm">Open</button>
						            </div>
						        </div>
						    </div>
						    <!-- Comment Row -->
						    <!-- Comment Row -->
						    <div class="d-flex flex-row comment-row m-t-0">
						        <div class="comment-text w-100">
						            <h6 class="font-medium">Revolt Node BE</h6>
						            <span class="m-b-15 d-block">Performance testing for Revolt Node project</span>
						            <span class="m-b-15 p-b-5 d-block">To test the performance of the Revolt Node project with the aggreed value matrix</span>
						            <div class="comment-footer m-t-5">
						                <span class="text-muted float-right">Last accessed - Feb 04, 2020</span> 
						                <button type="button" class="btn btn-cyan btn-sm">Open</button>
						            </div>
						        </div>
						    </div>
						    <!-- Comment Row -->
						</div>
                            
	                </div>
	            </div>
	        </div>
	        
	        <div class="col-md-6">
	        	<div class="card">
	                <div class="card-body">
	                    <h4 class="card-title">Last Execution report</h4>
	                    <div class="m-t-20">
	                        <div class="d-flex no-block align-items-center">
	                            <span>Open Banking</span>
	                            <div class="ml-auto">
	                                <span>Pass Test Cases - 101 / 125</span>
	                            </div>
	                        </div>
	                        <div class="progress">
	                            <div class="progress-bar progress-bar-striped" role="progressbar" style="width: 81%" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
	                        </div>
	                    </div>
	                    <div class="m-t-25">
	                        <div class="d-flex no-block align-items-center m-t-15">
	                            <span>Autonomous Car</span>
	                            <div class="ml-auto">
	                                <span>Pass Test Cases - 86 / 120</span>
	                            </div>
	                        </div>
	                        <div class="progress">
	                            <div class="progress-bar progress-bar-striped bg-success" role="progressbar" style="width: 72%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
	                        </div>
	                    </div>
	                    <div class="m-t-25">
	                        <div class="d-flex no-block align-items-center m-t-15">
	                            <span>Front Revamp 2.xs</span>
	                            <div class="ml-auto">
	                                <span>Pass Test Cases - 416 / 785</span>
	                            </div>
	                        </div>
	                        <div class="progress">
	                            <div class="progress-bar progress-bar-striped bg-info" role="progressbar" style="width: 53%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
	                        </div>
	                    </div>
	                    <div class="m-t-25 m-t-40">
	                        <div class="d-flex no-block align-items-center m-t-15">
	                            <span>Revolt Node BE</span>
	                            <div class="ml-auto">
	                                <span>Pass Test Cases - 3 / 80</span>
	                            </div>
	                        </div>
	                        <div class="progress">
	                            <div class="progress-bar progress-bar-striped bg-danger" role="progressbar" style="width: 3%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <!-- ============================================================== -->
	    <!-- Projects -->
	    <!-- ============================================================== -->



	</div>
	<!-- ============================================================== -->
	<!-- End Container fluid  -->
	<!-- ============================================================== -->

</div>

 <script type="text/javascript">
	 $('[data-toggle="tooltip"]').tooltip();
	 $(".preloader").fadeOut();
 </script>
 
 <script type="text/javascript">
 
 </script>
