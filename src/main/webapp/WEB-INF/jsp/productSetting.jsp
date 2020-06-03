<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Setting </title>
<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<title>Demand Planning</title>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/dps-commin.css" >
    <link href="${pageContext.request.contextPath}/static/vendors/bower_components/bootstrap/dist/css/bootstrap.min.css" />
	<link href="${pageContext.request.contextPath}/static/vendors/bower_components/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href=" ${pageContext.request.contextPath}/static/css/jquery-ui.css">
	<link rel="stylesheet" href=" ${pageContext.request.contextPath}/static/css/ui.jqgrid.css">
	<link href="${pageContext.request.contextPath}/static/vendors/bower_components/bootstrap-table/dist/bootstrap-table.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/vendors/bower_components/select2/dist/css/select2.min.css" rel="stylesheet" type="text/css"/>
	<!-- Custom CSS -->
	<link href="${pageContext.request.contextPath}/static/dist/css/style.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/static/dist/css/bootstrap-multiselect.css" rel="stylesheet"  type="text/css" />
	<!-- Added by mehul -->
	<link href="${pageContext.request.contextPath}src/main/webapp/static/css/jquery-ui.css" rel="stylesheet"  type="text/css" />

<style type="text/css">
#actionContainer .select2-container--default {
    width: 200px !important;
    margin-right: 11px;
}
::placeholder { /* Chrome, Firefox, Opera, Safari 10.1+ */
  color: #d4d4d4 !important;
  opacity: 1; /* Firefox */
}

:-ms-input-placeholder { /* Internet Explorer 10-11 */
  color: #d4d4d4 !important;
}

::-ms-input-placeholder { /* Microsoft Edge */
  color: #d4d4d4 !important;
}
.fixed-table-toolbar
{
	display: none;
}
.fixed-table-container{
	height: 340px !important;
}
.multiselect-container{
	max-height: 220px !important;
	width: 100% !important;
}
.input-group-btn{
	display: none;
}
.multiselect-container>li>a>label {
  padding: 0px 0px 0px 10px;
}
#skill_sub_category_id2{
	  width:100%;
 border: 1px solid #e0e0e0;
box-shadow: 0 1px 4px 0px rgba(0, 0, 0, 0.05);
}
.multiselect-container > li > a > label.checkbox, .multiselect-container > li > a > label.radio {
    margin: 0;
    display: inline-flex;
}
.checkbox input {
	display: block;
    cursor: pointer;
}
#newUserskillform .btn-group-vertical > .btn, .btn-group > .btn {
    position: relative;
    float: left;
    background-color: #fff;
    border-color: #e2e5ec;
	width:100%;
	float:left;
	white-space: initial;
	text-shadow: none;
	border-radius: 0px;
	border-top-left-radius: 0px !important;
	border-bottom-left-radius: 0px !important;
	padding: 11px 0px;
	padding-left: 5px;
}

.input-group-btn:last-child > .btn, .input-group-btn:last-child > .btn-group{
	height: 48px;
	background-color: #eee;
}

#newUserskillform .btn-group, .btn-group-vertical {
    position: relative;
    display: inline-block;
    vertical-align: middle;
    color: #333;
    background-color: #e6e6e6;
    border-color: #8c8c8c;
	box-shadow: 0 1px 1px 1px rgba(0, 0, 0, 0.2);
    width: 100%;
    text-align: left;
    float: left;
}
#newUserskillform .btn-group.open > .dropdown-menu {
    transition: opacity 1.5s cubic-bezier(.23, 1, .32, 1) 0s;
    opacity: 1;
    filter: alpha(opacity=100);
    width: 100%;
    border: 1px solid #e4e4e4 !important;
    height: 220px;
   overflow-x: scroll !important;
	-webkit-column-count: 5;
    -moz-column-count: 5;
    column-count: 5;
	
}
#newUserskillform .multiselect-clear-filter{
	display:none;
}
#newUserskillform .multiselect-container {
	padding-left: 10px !important;
    padding-right: 10px !important;
}
#newUserskillform .multiselect-container > li > a > label {
    width: 100%;
	white-space: pre-line;
}
.multiselect-container > li > a > label.checkbox{
	padding-left: 30px;
}
.multiselect-container > .btn-group, .btn-group-vertical {
    width: 100%;
}
.multiselect-container > .btn-group.open > .dropdown-menu {
    width: 100%;
}
.checkbox input[type="checkbox"], .checkbox input[type="radio"] {
    opacity: 1 !important;
    z-index: 1;
}
.multiselect-native-select{
	width: 100% !important;
	display: inline-block;
}
.multiselect-native-select .btn-group{
	width: 100% !important;
}
.multiselect-filter{
display: none;
}
.fixed-table-pagination .page-list {
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
					<select class="form-control select2" id="productCategorySelect" name = "productCategorySelect" onChange="getCategoryWiseGraphData()">
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
						  <h5 class="txt-dark">Product Setting</h5>
						</div>
					</div>
		
				<!-- Row -->
				<!-- Row -->
				<div class="row">												
						<div class="col-md-12" >
							<div class="panel panel-default">								
								<div class="panel-wrapper collapse in">
									<div class="panel-body">
										<div class="row">
									 <div class="col-md-4 text-left" >
										<!-- <h6 class="panel-title txt-dark mb10">Category Wise</h6> -->
									
										<!-- <input type="text" class="form-control text-center" placeholder="Select Active Supplier"> -->
										<div class="col-md-7">
										<select class="form-control" id='prodCatSel' name='prodCatSel'>
											<option value="1">Select Product Category</option>
											<option value="P_CAT1">Generics</option>
											<option value="P_CAT2">Parallel Import</option>
											<option value="P_CAT3">OTC</option>
											<option value="P_CAT4">Dressings</option>
											<option value="P_CAT5">Control Drugs</option>
											<option value="P_CAT6">Fridge Lines</option>
											<option value="P_CAT7">Specials Drug Tariff</option>
											<option value="P_CAT8">Specials Non-Drug Tariff</option>
											<option value="P_CAT9">Special Obtains</option>
											<option value="P_CAT10">NP8</option>
										</select>
									</div>

									<!-- <div class="col-md-1" style="padding-top: 10px;">						
											<input type="checkbox" id="showProduct">
									</div>
									<div class="col-md-3">
										Select Single Product
									</div> -->
									
									</div>	
									<div class="col-md-2" id="searchProduct" >												
						<form id="search_form" role="search" class="top-nav-search collapse pull-left" style="display: block; width:100%;">
					<div class="input-group">
						<input type="text" name="ProductSearch" class="form-control"  id="ProductSearch" placeholder="Search for a products"/>
						<inout type="hidden" name="SelectedCatalogNo" id="SelectedCatalogNo" value="" />
						<span class="input-group-btn" style="display: block;">
						<button type="button" class="btn  btn-default"  data-target="#search_form" data-toggle="collapse" aria-label="Close" aria-expanded="true" style="line-height: 1.3; height: 42px;"><i class="zmdi zmdi-search"></i></button>
							</span>
						</div>
					</form>
											</div>
											<div class="col-md-1"  style="border-right: 1px solid #ccc;">
										<!-- <button class="btn  btn-success">START</button> -->
									</div>
									<div class="col-md-1 col-xs-8 text-left" style="display:none">
										Stock Level History
									</div>
									<div class="col-md-2 col-xs-8 text-left" style="display:none">
										<select id="skill_sub_category_id2" class="btn btn-default soft_skill_list" multiple="multiple" data-size="2" data-max-options="2"  title="Product Specifications" style="width: 100%;">		
											<option value="January" id="January">January 2020</option>
											<option value="February" id="February">February 2020</option>
											<option value="March" id="March">March 2020</option>
											<option value="April" id="April">April 2020</option>
											<option value="May" id="May">May 2020</option>
											<option value="June" id="June">June 2020</option>
											<option value="July" id="July">July 2020</option>
											<option value="August" id="August">August 2020</option>
											<option value="September" id="September">September 2020</option>
											<option value="October" id="October">October 2020</option>
											<option value="November" id="November">November 2020</option>
											<option value="December" id="December">December 2020</option>
										 </select>
									</div>
										<div class="col-md-1  col-xs-8 text-left" style="display:none">
										<!-- <h6 class="panel-title txt-dark mb10">&nbsp;</h6> -->
										<button class="btn  btn-success fButton">SEARCH</button>
									</div>
									<div class="col-md-1  col-xs-8 text-left mbshowBtn" style="display:none">
										<i class="fa fa-plus fa-3x" data-toggle="tooltip" title="" data-original-title="Set New Parameters"></i>
									</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /Row -->
					<div class="row">												
						<div class="col-md-12" style="display:none">
							<div class="panel panel-default">								
								<div class="panel-wrapper collapse in">
									<div class="panel-body">
										<div class="row">											
										
									 <div class="col-md-3 text-left" >
										&nbsp;
									</div>	
									<!-- <div class="col-md-2 col-xs-8 text-right">
									<select class="form-control">
											<option value="">Download Template</option>
											<option value="Supplier1">Category Wise</option>
											<option value="Supplier2">NCSO</option>
											<option value="Supplier3">Profitable Lines</option>
										</select>
									</div> -->
									<div class="col-md-2_1 col-xs-8 text-center">
										<h6 class="txt-dark">Download Template</h6>
									</div>
									<div class="col-md-1_0 col-xs-8 text-left"  style="border-right: 1px solid #ccc;">
										   <i class="fa fa-download fa-3x"  data-toggle="tooltip" title="Download Template"></i>
									</div>
									<div class="col-md-2_1 col-xs-8 text-center">
										<h6 class="txt-dark">Upload Template</h6>
									</div>
									<div class="col-md-1 col-xs-8 text-left">
										   <i class="fa fa-upload fa-3x"  data-toggle="tooltip" title="Upload Bulk List"></i>
									</div>
								</div>										
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /Row -->




					<div class="row">												
						<div class="col-md-12">
							<div class="panel panel-default card-view">
								<div class="panel-heading text-center">
										<h6 class="panel-title txt-dark">Your Selected Category : <span id="selected_Category" name="selected_Category"> </span> </h6> 
										<h6>  Your Selection of month : <span id="selected_month"> May </span></h6>
								</div>
							</div>
						</div>
					</div>
					
	<form method="post" id="productParameterForm">
				<div class="row">												
						<div class="col-md-12">
							<div class="panel-group accordion-struct accordion-style-1" id="accordion_2" role="tablist" aria-multiselectable="true">
											<div class="panel panel-default">
												
												<div class="panel-heading" role="tab" id="heading_10">
													<a role="button" class="collapsed" data-toggle="collapse" data-parent="#accordion_2" href="#collapse_10" aria-expanded="false" >
													Set product parameters (This parameters are expressed in weeks) 
													<div class="icon-ac-wrap pr-20" style="float: right;">
													<span class="plus-ac"><i class="fa fa-plus">
													</i></span><span class="minus-ac"><i class="fa fa-minus"></i></span></div></a> 
												</div>
												<div id="collapse_10" class="panel-collapse collapse" role="tabpanel">
												
													<div class="panel-body  pa-15">
						
						 													
										<div class="row  mb-5">
										<div class="col-md-1">
										<img src="static/img/shakeHands.png">
											</div>
										<div class="col-md-3">
										<div class="btn btn-primary btn-outline width100" onClick="#">Overstock</div>
											</div>
										<div class="col-md-1 text-center">
										<label class="labelControl">From</label>
											</div>
											<div class="col-md-2">
										<input type="text" name="overstock_from" id="overstock_from"  maxlength="6" onkeypress="return isNumber(event)" class="form-control text-center">
											</div>
											<div class="col-md-1 text-center">
											<label class="labelControl">To</label>
											</div>
											<div class="col-md-2">
										<input type="text" name="overstock_to" id="overstock_to" maxlength="6" onkeypress="return isNumber(event)" class="form-control text-center" >
											</div>
										<div class="col-md-2">
												<div class="purple-box">&nbsp;</div>
											</div>
										</div>
										<div class="row  mb-5">
										<div class="col-md-1">
										<img src="static/img/healthyStock.png">
											</div>
										<div class="col-md-3">
										<div class="btn  btn-success btn-outline width100">Healthy Stock Level</div>
											</div>
										<div class="col-md-1 text-center">
										<label class="labelControl">From</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="healthy_stock_from" id="healthy_stock_from">
											</div>
											<div class="col-md-1 text-center">
											<label class="labelControl">To</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="healthy_stock_to" id="healthy_stock_to">
											</div>
										<div class="col-md-2">
												<div class="green-box">&nbsp;</div>
											</div>
										</div>
										<div class="row  mb-5">
										<div class="col-md-1">
										<img src="static/img/lowStock.png">
											</div>
										<div class="col-md-3">
										<div class="btn  btn-warning btn-outline width100">Low Stock Level</div>
											</div>
										<div class="col-md-1 text-center">
										<label class="labelControl">From</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="low_stock_from" id="low_stock_from">
											</div>
											<div class="col-md-1 text-center">
											<label class="labelControl">To</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="low_stock_to" id="low_stock_to">
											</div>
										<div class="col-md-2">
												<div class="yellow-box">&nbsp;</div>
											</div>
										</div>
										<div class="row  mb-5">
										<div class="col-md-1">
										<img src="static/img/lowStock.png">
											</div>
										<div class="col-md-3">
										<div class="btn  btn-lblue btn-outline width100">Buffer Stock Level</div>
											</div>
										<div class="col-md-1 text-center">
										<label class="labelControl">From</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)"  maxlength="6" name="buffer_stock_from" id="buffer_stock_from">
											</div>
											<div class="col-md-1 text-center">
											<label class="labelControl">To</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="buffer_stock_to" id="buffer_stock_to">
											</div>
										<div class="col-md-2">
												<div class="lblue-box">&nbsp;</div>
											</div>
										</div>
										<div class="row  mb-5">
										<div class="col-md-1">
										<img src="static/img/outofStock.png">
											</div>
										<div class="col-md-3">
										<div class="btn  btn-danger btn-outline width100">Out Stock Level</div>
											</div>
										<div class="col-md-1 text-center">
										<label class="labelControl">From</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="out_stock_from" id="out_stock_from">
											</div>
											<div class="col-md-1 text-center">
											<label class="labelControl">To</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="out_stock_to" id="out_stock_to">
											</div>
										<div class="col-md-2">
												<div class="red-box">&nbsp;</div>
											</div>
										</div>

										<div class="row mb-5">
										<div class="col-md-1">
										<img src="static/img/shortDated.png">
											</div>
										<div class="col-md-3">
										<div class="btn btn-default btn-outline width100">Short-Dated</div>
											</div>
										<div class="col-md-1 text-center">
										<label class="labelControl">From</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6"  name="short_dated_from" id="short_dated_from">
											</div>
											<div class="col-md-1 text-center">
											<label class="labelControl">To</label>
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="short_dated_to" id="short_dated_to">
											</div>
										<div class="col-md-2">
												<div class="lightgrey-box">&nbsp;</div>
											</div>
										</div>


										<div class="row mb-5">
										<div class="col-md-1">
										&nbsp;
											</div>
										<div class="col-md-3">
										<div class="btn btn-orange btn-outline width100">Lead Time</div>
											</div>
										<div class="col-md-1 text-center">
										<!--<label class="labelControl">From</label>-->
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="lead_time_from" id="lead_time_from">
											</div>
											<div class="col-md-1 text-center" style="display:none">
											<label class="labelControl">To</label>
											</div>
											<div class="col-md-2">
										<input type="text" style="display:none" class="form-control text-center" onkeypress="return isNumber(event)" maxlength="6" name="lead_time_to" id="lead_time_to">
											</div>
										<div class="col-md-2">
												&nbsp;
											</div>
										</div>


										<div class="row mb-5">
										<div class="col-md-1">
										&nbsp;
											</div>
										<div class="col-md-3">
										<div  class="btn btn-orange btn-outline width100">Rate Of Sale</div>
											</div>
										<div class="col-md-1 text-center">
										&nbsp;
											</div>
											<div class="col-md-2">
										<input type="text" class="form-control text-center" maxlength="6" onkeypress="return isNumber(event)" name="rateOfsale" id="rateOfsale">
											</div>
											
											<div class="col-md-2">
										&nbsp;
											</div>
										<div class="col-md-2">
												&nbsp;
											</div>
										</div>
										<input type="hidden" id="transaction_id" name="transaction_id" value=""/> 
	
						<div class="row">
								<div class="col-sm-12">
									<div class="panel panel-default card-view" id="actionContainer">
										<div  class="panel-wrapper collapse in">
											<div  class="panel-body">
												<center>
													<!-- <button class="btn btn-default" type="" onClick="clearProductParameters()">Cancel</button> -->
													<button class="btn btn-default" type="reset" value="Reset">Reset </button>
													&nbsp;
													<button type="button" id="saveParameterBtn" name="saveParameterBtn" class="btn  btn-success" 
														onClick="saveParameter()">SAVE</button>
												</center>
											</div>
										</div>
										</div>
									</div>
								</div>
					</div>
						</div>
							</div>
								</div>
					</div>
					</div>
					<!-- /Row -->
</form>



	<div class="row" id="yearlyTable">
<div class="col-sm-6">
<div class="panel panel-default card-view">
<div class="panel-heading">
	<div class="pull-left">
		<h6 class="panel-title txt-dark">Parameters By Products</h6>
	</div>
	<div class="clearfix"></div>
</div>
<div class="panel-wrapper collapse in">
	<div class="panel-body"	style="padding-top: 0px;">
		<div class="table-wrap">
			<div class="table-responsive">
				<table id="paramByProductGrid"></table>
				<div id="paramByProductGridPager"></div>
			</div>
		</div>	

											

								</div>	
							</div>	
						</div>	
					</div>


<div class="col-sm-6">
<div class="panel panel-default card-view">
<div class="panel-heading">
<div class="pull-left">
	<h6 class="panel-title txt-dark">Parameters By Category</h6>
</div>
<div class="clearfix"></div>
</div>
<div class="panel-wrapper collapse in">
<div class="panel-body"	style="padding-top: 0px;">
	<div class="table-wrap">
			<div class="table-responsive">
				<table id="paramByCategoryGrid"></table>
				<div id="paramByCategoryGridPager"></div>
			</div>
		</div>	
								</div>	
							</div>	
						</div>	
					</div>
				</div>
						<div class="row">
								<div class="col-sm-12">
									<div class="panel panel-default card-view" id="actionContainer">
										<div  class="panel-wrapper collapse in">
											<div  class="panel-body">
												<center>
													<button class="btn btn-default btn-outline  fButton" onClick="clearProductSettingPage()">Clear</button>&nbsp;
													<button style="display:none" class="btn btn-primary fButton" id="reuseParameterBtn" onClick ="ReUseEntry()" >Re-Use</button>&nbsp;
												</center>
											</div>
										</div>
									</div>
								</div>
							</div>	
							
							<!-- Added by mehul -->
							
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
												<div class="col-md-2 text-right pa-0" >
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
							<!-- Mehul inclusion over -->
			</div>
		</div>
        <!-- /Main Content -->
    <!-- /#wrapper -->
	
    <script src="${pageContext.request.contextPath}/static/vendors/bower_components/jquery/dist/jquery.min.js?v=03"></script>
	<script src="${pageContext.request.contextPath}/static/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/vendors/bower_components/bootstrap-table/dist/bootstrap-table.min.js"></script>	
	<script src="${pageContext.request.contextPath}/static/dist/js/bootstrap-table-data.js"></script>
	<script src="${pageContext.request.contextPath}/static/dist/js/jquery.slimscroll.js"></script>
	<script src="${pageContext.request.contextPath}/static/vendors/bower_components/moment/min/moment.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/vendors/bower_components/simpleWeather/jquery.simpleWeather.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/dist/js/simpleweather-data.js"></script>
	<script src="${pageContext.request.contextPath}/static/vendors/bower_components/select2/dist/js/select2.full.min.js"></script>	
    <script src="${pageContext.request.contextPath}/static/dist/js/dropdown-bootstrap-extended.js"></script>
	<script src="${pageContext.request.contextPath}/static/vendors/bower_components/bootstrap-switch/dist/js/bootstrap-switch.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/vendors/bower_components/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/vendors/bower_components/bootstrap-touchspin/dist/jquery.bootstrap-touchspin.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/vendors/bower_components/multiselect/js/jquery.multi-select.js"></script>
	<script src="${pageContext.request.contextPath}/static/dist/js/jquery.slimscroll.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/dist/js/form-advance-data.js"></script>
	<script src="${pageContext.request.contextPath}/static/dist/js/init.js"></script>
	<script src="${pageContext.request.contextPath}/static/dist/js/dashboard-data.js"></script>
	<script src="${pageContext.request.contextPath}/static/dist/js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/dist/js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.browser.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/grid.locale-en.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.blockUI.js"></script>
	
	<!-- JS added by mehul -->
	<script src="${pageContext.request.contextPath}/static/js/jquery-ui.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/static/js/ProductSetting_mehul.js?v=111" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/dps-common.js"></script>
	
	<script type="text/javascript">
		$('.nicescroll-bar').slimscroll({height:'100%',color: '#878787', disableFadeOut : true,borderRadius:0,size:'4px',alwaysVisible:false});
		$(document).ready(function(){
			$("#productCategorySection").hide();
			loadActivities();
		});
	</script>
	
</body>
</html>