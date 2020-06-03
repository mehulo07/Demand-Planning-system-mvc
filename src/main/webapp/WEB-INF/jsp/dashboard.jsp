	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DPS Dashboard</title>
<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<title>Demand Planning</title>
	<link href="${pageContext.request.contextPath}/static/vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/static/vendors/bower_components/select2/dist/css/select2.min.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/static/vendors/bower_components/owl.carousel/dist/assets/owl.carousel.min.css" rel="stylesheet" type="text/css"/>

	<link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/ui.jqgrid.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/dps-commin.css" >
	
	<style type="text/css">
		#actionContainer .select2-container--default {
              width: 200px !important;
              margin-right: 11px;
          }
          .fixed-table-toolbar{
               display: none;
		   }
     </style>
     
</head>
<body>
	<!-- Preloader -->
	<div class="preloader-it">
		<div class="la-anim-1"></div>
	</div>
	<!-- /Preloader -->
    <div class="wrapper theme-5-active pimary-color-pink">
		<!-- Top Menu Items -->
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="mobile-only-brand pull-left">
				<div class="nav-header pull-left">
					<div class="logo-wrap">
						<a href="/dashboard">
							<img class="brand-img" src="${pageContext.request.contextPath}/static/img/BNS_Distribution_logo_Horizontal.svg" alt="brand"/>
							<!-- <span class="brand-text">Grandin</span> -->
						</a>
					</div>
				</div>	
				<a id="toggle_nav_btn" class="toggle-left-nav-btn inline-block ml-20 pull-left" href="javascript:void(0);"><i class="zmdi zmdi-menu"></i></a>
				<a id="toggle_mobile_search" data-toggle="collapse" data-target="#search_form" class="mobile-only-view" href="javascript:void(0);"><i class="zmdi zmdi-search"></i></a>
				<a id="toggle_mobile_nav" class="mobile-only-view" href="javascript:void(0);"><i class="zmdi zmdi-more"></i></a>
				
			</div>
			<div id="mobile_only_nav" class="mobile-only-nav pull-right">
				<ul class="nav navbar-right top-nav pull-right">
					<li class="dropdown auth-drp">
						<a href="#" class="dropdown-toggle pr-0" data-toggle="dropdown">
							<i class="fa fa-cog fa-2x"></i>
						</a>
						<ul class="dropdown-menu user-auth-dropdown" data-dropdown-in="flipInX" data-dropdown-out="flipOutX">
							<li>
								<a href="dashboard"><i class="fa fa-tachometer"></i><span>Dashboard</span></a>
							</li>
							<li>
								<a href="viewProduct"><i class="zmdi zmdi-settings"></i><span>Product Settings</span></a>
							</li>
							<li class="divider"></li>
							
							<li>
								<a href="logout"><i class="zmdi zmdi-power"></i><span>Log Out</span></a>
							</li>
						</ul>
					</li>
				</ul>
			</div>	
		</nav>
		<!-- /Top Menu Items -->
		
		<!-- Left Sidebar Menu -->
		<div class="fixed-sidebar-left">
			<ul class="nav navbar-nav side-nav nicescroll-bar">				
					<!-- User Profile -->
					<li>
						<div class="user-profile text-center">
							
							<div class="dropdown">
														
							</div>
						</div>
					</li>
					<!-- /User Profile -->
				
				<li>
					<a class="active" href="dashboard" ><div class="pull-left"><i class="fa fa-tachometer mr-20"></i><span class="right-nav-text">Dashboard</span></div><div class="pull-right"></div><div class="clearfix"></div></a>
					
				</li>
				<li>
					<a class="active" href="viewProduct" ><div class="pull-left"><i class="zmdi zmdi zmdi-settings mr-20"></i><span class="right-nav-text">Product Settings</span></div><div class="pull-right"></div><div class="clearfix"></div></a>
					
				</li>
				<li>
					<hr />
				</li>
				
				<li>
					<div class="form-group ml-20" style="width: 80% !important;">
						<h6 class="panel-title txt-dark">Product Category</h6>
					</div>
				</li>

					<li>
					<div class="form-group ml-20" style="width: 80% !important;">
						<span class="right-nav-text">
					<select class="form-control select2 testOptionCls" id="productCategorySelect" name = "productCategorySelect">
						<option value="P_CAT1">Generics</option>
						<option value="P_CAT2">Parallel Imports</option>
						<option value="Food P_CAT3">Food Supplements</option>
						<option value="Insulin">Insulin</option>
						<option value="P_CAT5">Control Drugs</option>
						<option value="Contraceptives">Contraceptives</option>
						<option value="NCSOs">NCSOs</option>
						<option value="P_CAT10">NP8s</option>
						<option value="P_CAT3">OTCs</option>
						<option value="Perfumes">Perfumes</option>
						<option value="Specials">Specials</option>
						<option value="P_CAT9">Special Obtains</option>
						<option value="P_CAT4">Dressings</option>
						<option value="P_CAT6">Fridge Lines</option>
						<option value="P_CAT7">Specials Drug Tariff</option>
						<option value="P_CAT8">Specials Non-Drug Tariff</option>
				</select>
				</span>
				</div>
				</li>
				<li class="navigation-header  ml-20">					
						<span>Action Filters</span> 
						<i class="zmdi zmdi-more"></i>					
					</li>
<li>
					<div class="form-group ml-20">
						<span class="right-nav-text">
					<button class="btn btn-default btn-outline" onclick = "getProductCategoryData('STK_CAT1','Overstock',false)" style="width: 89% !important;">Overstock</button>
				</span>
				</div>
				</li>
				
				<li>
					<div class="form-group ml-20">
						<span class="right-nav-text">
					<button class="btn  btn-success btn-outline" id="healthyBtn" onclick = "getProductCategoryData('STK_CAT2' , 'Healthy Stock Level',false)" style="width: 89% !important;">Healthy Stock Level</button>
					</span>
					
				</div>
				</li>

				<li>
					<div class="form-group ml-20">
						<span class="right-nav-text">
					<button class="btn btn-warning btn-outline"  onclick = "getProductCategoryData('STK_CAT3', 'Low Stock Level', false)" style="width: 89% !important;">Low Stock Level</button>
					</span>
					
				</div>
				</li>
				
				<li>
					<div class="form-group ml-20">
						<span class="right-nav-text">
					<button class="btn  btn-primary btn-outline"  onclick = "getProductCategoryData('STK_CAT4' , 'Buffer Stock Level' ,false)" style="width: 89% !important;">Buffer Stock Level</button>
					</span>
					
				</div>
				</li>

				<li>
					<div class="form-group ml-20">
						<span class="right-nav-text">
					<button class="btn btn-danger btn-outline" id="outofStockBtn"  onclick = "getProductCategoryData('STK_CAT5', 'Out Stock Level',false)" style="width: 89% !important;">Out Stock Level</button>
					</span>
					
				</div>
				</li>

				<li>
					<div class="form-group ml-20">
						<span class="right-nav-text">
					<button class="btn btn-default btn-outline" id="shortDatedBtn"  onclick = "getProductCategoryData('STK_CAT6' , 'Short-dated',false)" style="width: 89% !important;">Short-dated</button>
					</span>
					
				</div>
				</li>


				<li>
					<div class="form-group ml-20">
						<span class="right-nav-text">
					<button class="btn  btn-info btn-outline" onclick = "getProductCategoryData('' , 'NCSOs',false)" style="width: 89% !important;">NCSOs</button>
					</span>
					
				</div>
				</li>

				<li>
					<div class="form-group ml-20">
						<span class="right-nav-text">
					<button class="btn btn-warning btn-outline"  onclick = "getProductCategoryData('' , 'Profitable Lines',false)" style="width: 89% !important;">Profitable Lines</button>
					</span>
					
				</div>
				</li>
				
				
			</ul>
		</div>
		<!-- /Left Sidebar Menu -->

        <!-- Main Content -->
		<div class="page-wrapper">
					<div class="row heading-bg mb-0">
						<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						  <h5 class="txt-dark">Dashboard</h5>
						</div>
					</div>
               
                    <div class="row" id="productCategorySection">
					<div class="col-sm-12">
						<div class="panel panel-default card-view">
							<div class="panel-wrapper collapse in">
								<div class="panel-body" style="padding-top: 0px;">
									
									<div class="row">
										<div class="col-md-6">
											<h6 class="pt-10"><label id = "productCategoryActionLabel"></label></h6>
											<h6 class="pt-10"><input type="hidden" id = "productCategoryLabel"/></h6>
										</div>
										<div class="col-md-6">
											<div class="row ma-0">
											<div class="col-md-3">
													<div class="form-group">
													</div>
												</div>
												<div class="col-md-7">
													<div class="form-group">
														
														<input type="text" id="searchPartNoOrDesc" name="searchPartNoOrDesc" 
														class="form-control" AUTOCOMPLETE=OFF tabindex="1" placeholder="Enter Part Description">
														<input type="hidden" value="" id="searchPartNo"/>
													</div>
												</div>
												<div class="col-md-2 text-right pa-0">
													<button class="btn btn-primary dash_search_button" onClick="searchProductCategoryData()">Search</button>
												</div>
											</div>
										</div>
										
									</div>
									
									<div class="table-wrap">
										<div class="table-responsive">
											<table id="productCategoryValueListGrid"></table>
											<div id="productCategoryValueListGridPager"></div>
										</div>
									</div>	
								</div>	
							</div>	
						</div>	
					</div>
				</div>



                    <div class="row" id="outofStockSection">
					<div class="col-sm-12">
						<div class="panel panel-default card-view">
							<div class="panel-wrapper collapse in">
								<div class="panel-body" style="padding-top: 0px;">
									<div class="table-wrap">
										<div class="table-responsive">
											<table id="datable_3" class="table table-hover table-bordered display mb-30" >
												<thead>
													<tr>
													  <th rowspan="2">Part No</th>
														<th rowspan="2">Name</th>
														<th colspan="4">Current Stock</th>
														<th colspan="2">Rate of Sale</th>
														<th colspan="4" class="bglightgrey">In Transit Stock</th>
														<th colspan="3">Buy Now</th>
													</tr>
													<tr>
														<th>Profitable/NCSO</th>
														<th>Units</th>
														<th>Weeks of cover</th>
														<th>No. of pallets</th>
														<th>Units per week</th>
														<th>12 week-Trend</th>
														<th class="bglightgrey">Units</th>
														<th class="bglightgrey">Weeks of Cover</th>
														<th class="bglightgrey">Delivery Date</th>
														<th class="bglightgrey">No. of pallets</th>
														<th>Units</th>
														<th>Weeks of cover</th>
														<th>Lead Time</th>
													</tr>
												</thead>
												
												<tbody>
													<tr>
													  <td>LaxPartNo</td>
														<td>Product Name, Strength, Pack Size</td>
														<td>N/A</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bgdarkblue">&nbsp;</td>
														<td class="bglightgreen">
															<i class="fa fa-trash deleteLine" data-toggle="tooltip" title="" data-original-title="Delete Lines"></i>
																<i class="fa fa-plus-circle editLine" data-toggle="tooltip" title="" data-original-title="Add Lines"></i>
															
														</td>
													</tr>

													<tr>
													  <td>LaxPartNo</td>
														<td>Product Name, Strength, Pack Size</td>
														<td>NCSO</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">&nbsp;</td>
														<td class="bglightred">
															<i class="fa fa-trash deleteLine" data-toggle="tooltip" title="" data-original-title="Delete Lines"></i>
																<i class="fa fa-plus-circle editLine" data-toggle="tooltip" title="" data-original-title="Add Lines"></i>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>	
								</div>	
							</div>	
						</div>	
					</div>
				</div>

                    <div class="row" id="shortDatedSection">
					<div class="col-sm-12">
						<div class="panel panel-default card-view">
							<div class="panel-wrapper collapse in">
								<div class="panel-body" style="padding-top: 0px;">
									<div class="table-wrap">
										<div class="table-responsive">
											<table id="datable_4" class="table table-hover table-bordered display mb-30" >
												<thead>
													<tr>
													  <th rowspan="2">Part No</th>
														<th rowspan="2">Name</th>
														<th colspan="4" class="text-center">Current Stock</th>
														<th colspan="2" class="text-center">Rate of Sale</th>
														<th colspan="4" class="bglightgrey text-center">In Transit Stock</th>
														<th colspan="3" class="text-center">Buy Now</th>
													</tr>
													<tr>
														<th>Profitable/NCSO</th>
														<th>Units</th>
														<th>Weeks of cover</th>
														<th>No. of pallets</th>
														<th>Units per week</th>
														<th>12 week-Trend</th>
														<th class="bglightgrey">Units</th>
														<th class="bglightgrey">Weeks of Cover</th>
														<th class="bglightgrey">Delivery Date</th>
														<th class="bglightgrey">No. of pallets</th>
														<th>Units</th>
														<th>Weeks of cover</th>
														<th>Lead Time</th>
													</tr>
												</thead>
												
												<tbody>
													<tr>
													  <td>LaxPartNo</td>
														<td>Product Name, Strength, Pack Size</td>
														<td>Profitable</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">&nbsp;</td>
														<td class="bglightyellow">
															<i class="fa fa-trash deleteLine" data-toggle="tooltip" title="" data-original-title="Delete Lines"></i>
																<i class="fa fa-plus-circle editLine" data-toggle="tooltip" title="" data-original-title="Add Lines"></i>
															
														</td>
													</tr>

													<tr>
													  <td>SDLaxPartNo</td>
														<td>Product Name, Strength, Pack Size</td>
														<td>N/A</td>
														<td class="bglightwhite">&nbsp;</td>
														<td class="bglightwhite">&nbsp;</td>
														<td class="bglightwhite">&nbsp;</td>
														<td class="bglightwhite">&nbsp;</td>
														<td class="bglightwhite">&nbsp;</td>
														<td class="bglightwhite">&nbsp;</td>
														<td class="bglightwhite">&nbsp;</td>
														<td class="bglightwhite">&nbsp;</td>
														<td class="bglightwhite">&nbsp;</td>
														<td class="bgdarkgrey">&nbsp;</td>
														<td class="bgdarkgrey">&nbsp;</td>
														<td class="bgdarkgrey">
															<i class="fa fa-trash deleteLine" data-toggle="tooltip" title="" data-original-title="Delete Lines"></i>
																<i class="fa fa-plus-circle editLine" data-toggle="tooltip" title="" data-original-title="Add Lines"></i>
														</td>
													</tr>
													<tr>
													  <td>LaxPartNo</td>
														<td>Product Name, Strength, Pack Size</td>
														<td>N/A</td>
														<td class="bglightpink">&nbsp;</td>
														<td class="bglightpink">&nbsp;</td>
														<td class="bglightpink">&nbsp;</td>
														<td class="bglightpink">&nbsp;</td>
														<td class="bglightpink">&nbsp;</td>
														<td class="bglightpink">&nbsp;</td>
														<td class="bglightpink">&nbsp;</td>
														<td class="bglightpink">&nbsp;</td>
														<td class="bglightpink">&nbsp;</td>
														<td class="bgdarkgrey">&nbsp;</td>
														<td class="bgdarkgrey">&nbsp;</td>
														<td class="bgdarkgrey">
															<i class="fa fa-trash deleteLine" data-toggle="tooltip" title="" data-original-title="Delete Lines"></i>
																<i class="fa fa-plus-circle editLine" data-toggle="tooltip" title="" data-original-title="Add Lines"></i>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>	
								</div>	
							</div>	
						</div>	
					</div>
				</div>

                    <div class="row" id="graphSection">
                         <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
                              <div class="panel panel-default card-view">
                                   <div class="panel-heading">
                                        <div class="pull-left">
                                             <h6 class="panel-title txt-dark">Product Category Info</h6>
                                        </div>
                                        <div class="clearfix"></div>
                                   </div>
                                   <div class="panel-wrapper collapse in">
								<div class="panel-body row pa-0">
									<div class="table-wrap">
										<div class="table-responsive">
										  <table class="table table-hover mb-0">
											<thead style="display: none;">
											  <tr>
												<th>&nbsp;</th>
												<th>&nbsp;</th>
												<th>&nbsp;</th>
												<th>&nbsp;</th>
												<th>&nbsp;</th>
												<th>&nbsp;</th>
												<th>&nbsp;</th>
												<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody>
											  <tr>
											  	<td>Tot. No. of Licenses:</td>
												<td><span id="totalLiceance"> </span></td>
											  </tr>
											  <tr>
												<td>Buyer Name:</td>
												<td>
													<span id="BuyerName"></span>
												</td>
											  </tr>
											  <tr>
												<td>Tot. No. of Suppliers:</td>
												<td>-</td>
											  </tr>
											  <tr>
												<td>Countries of Trading:</td>
												<td>-</td>
											  </tr>
											</tbody>
										  </table>
										</div>
									</div>
								</div>
							</div>
                              </div>
                         </div>

                         <div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
                              <div class="panel panel-default card-view">
                                   <div class="panel-heading" style="padding-bottom: 0px;">
                                        <div class="pull-left">
                                             <h6 class="panel-title txt-dark" style="display:none">Overstock January</h6>
                                        </div>
                                        <div class="pull-right" style="display:none">
                                             <a href="#" class="pull-left inline-block full-screen mr-15">
                                                  <i class="zmdi zmdi-fullscreen"></i>
                                             </a>							
                                        </div>
                                        <div class="clearfix"></div>
                                   </div>
                                   <div class="panel-body" style="padding-top: 0px; padding-bottom: 0px;">
                                        <div id="e_chart_5" class="" style="height:233px;"></div>
                                   </div>
                              </div>
                         </div>
				</div>
				<!-- Row -->
				<div class="row" id="yearlyTable">								
					<div class="col-sm-12">
						<div class="panel panel-default card-view">
                                   <div class="panel-wrapper collapse in">
                                   <div class="panel-body"	style="padding-top: 0px;">
                                        <div class="col-md-12 mb20">
                                             <div class="col-md-1_1">
                                                  &nbsp;
                                             </div>
					<!-- <div class="col-md-2 col-xs-8 text-left">
									<label style="margin-top: 10px; width: 100%" class="text-right">Search By Date</label>
									</div>
									<div class="col-md-2 col-xs-8 text-left">
										<h6 class="panel-title txt-dark mb10">Set Rate of Sale</h6>
									<input type="date" class="form-control text-center" placeholder="From">
									</div>
									<div class="col-md-2 col-xs-8 text-left">
										<input type="date" class="form-control text-center" placeholder="To">
									</div>

										<div class="col-md-1  col-xs-8 text-left">
										<h6 class="panel-title txt-dark mb10">&nbsp;</h6>
										<button class="btn  btn-success fButton">SEARCH</button>
									</div>
						</div> -->

<div>Product Category : <span id="selectedCatName"></span></div>
<br/>
			
			<!-- calculated data start -->
			<div class="table-responsive">
				<table id="CalculatedDataYearWise"></table>
				<div id="CalculatedDataYearWisePager"></div>
			</div>
			<!-- calculated data over -->
			
			<div class="row">
				<div class="col-sm-12">
					<center>
						<button class="btn btn-default btn-outline  fButton">Clear</button>&nbsp;
						<button class="btn  btn-success fButton">APPLY</button>
					</center>
				</div>
			</div>		
								<div class="row">
									<div class="col-md-12">
									</div>
								</div>

								</div>	
							</div>	
						</div>	
					</div>
				</div>
				<!-- /Row -->
				<!-- Row -->
			</div>
		</div>
        <!-- /Main Content -->
    <!-- /#wrapper -->

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/vendors/bower_components/jquery/dist/jquery.min.js?v=8464648656351"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.browser.js" ></script>
   	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/grid.locale-en.js" type="text/javascript" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.jqGrid.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/dist/js/dropdown-bootstrap-extended.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/vendors/bower_components/select2/dist/js/select2.full.min.js"></script>

	<script src="${pageContext.request.contextPath}/static/dist/js/jquery.slimscroll.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/vendors/bower_components/echarts/dist/echarts-en.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/vendors/echarts-liquidfill.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/vendors/bower_components/bootstrap-switch/dist/js/bootstrap-switch.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/dist/js/toast-data.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/dist/js/init.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dashboardBarGraph.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/dashboard.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/dps-common.js"></script>
	

	<script type="text/javascript">
	$('.nicescroll-bar').slimscroll({height:'100%',color: '#878787', disableFadeOut : true,borderRadius:0,size:'4px',alwaysVisible:false});
	$(document).ready(function(){
		//alert("done");
		$("#productCategorySection").hide();
		$(".select2").select2();
		$("#BuyerName").html('Piyush Patel');
		getCategoryWiseGraphData();
        loadActivities();
    });
	
</script>
</body>
</html>