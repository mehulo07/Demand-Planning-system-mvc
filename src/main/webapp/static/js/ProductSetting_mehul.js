var d = new Date();
var month = d.getMonth();
month++;

var monthArray = [month];

var monthArr = ["January", "February" , "March" , "April" , "May" , "June" , "July" , "August" , "September" , "October" , "November" , "December"];

$( document ).ready(function() {
    
	//Product search hide show
	$('#showProduct').val(this.checked);
		$('#showProduct').change(function() {	
	        if(this.checked) {
	            $("#searchProduct").show();
	        }
	        else{
	        	$("#searchProduct").hide();
	        }
	        $('#showProduct').val(this.checked);        
		});
	
	$("#healthyBtn").click(function(){
	$("#healthySection").show();
	$("#outofStockSection").hide();
	$("#shortDatedSection").hide();	
	$("#graphSection").hide();
	$("#yearlyTable").hide();
	});

	$("#outofStockBtn").click(function(){
	$("#outofStockSection").show();
	$("#healthySection").hide();
	$("#shortDatedSection").hide();	
	$("#graphSection").hide();
	$("#yearlyTable").hide();
	});

	$("#shortDatedBtn").click(function(){
	$("#shortDatedSection").show();	
	$("#outofStockSection").hide();
	$("#healthySection").hide();
	$("#graphSection").hide();
	$("#yearlyTable").hide();
	});


	$('#skill_sub_category_id2').multiselect({
	enableFiltering: false,
	maxHeight:400,
	enableCaseInsensitiveFiltering:false, 
	nonSelectedText: 'Select Month', 
	includeSelectAllOption: true,
	numberDisplayed: 10
	});
	
	
	$('#skill_sub_category_id2').change(function(){
		var arr = $(this).val();
	    var i;
	    var arr1 =[];
	    if(arr!= null){
	    	for (i = 0; i < arr.length; i++) {
		    	arr1.push("<a id='"+arr[i]+"' href=javascript:getMonthWiseData('"+arr[i]+"');>"+arr[i] + "</a> , ");
		    }
		    $("#selected_month").html(arr1);	
	    }else{
	    	$("#selected_month").html(null);
	    }
	});
	
	
	//AutoCompleate for Product Search
	$('#ProductSearch').autocomplete({
		minlength : 2,
		source : function(request , response){
			
			if ($.trim($(this.element).val()) == ""  || $(this.element).val().length <3) {
				return;
			}else{
				if($('#prodCatSel').val() == ""){
					alert("Please select any product category.")
					return;
				}
			}
			$.ajax({
					url: "/getProductList" ,
			        dataType: "json",
			        data:{
			            "productDesc" : $("#ProductSearch").val(),
						"catalogNo" : "data",
						"category": $('#prodCatSel').val(),
						"contract" : "LAX",
						"limit": 11,
			      },success: function (data){
			            	response(data);
			            }
			        });
		},select: function (event, ui)
		    {
			    $("#ProductSearch").val(ui.item.productDesc);
			    $("#SelectedCatalogNo").val(ui.item.catalogNo);
			    
			    getParameterValues($('#prodCatSel').val() ,monthArray ,ui.item.catalogNo);
		        return false;
		    },
		focus : function(event , ui){
			return false;
		}
	}).data("ui-autocomplete")._renderItem = function (ul, item) {
        return $("<li>")
        .data("ui-autocomplete-item", item)
        .append("<a> " + item.productDesc + "</a>")
        .appendTo(ul);
	};
	//autocomplete  over
	
	
	$("#paramByProductGrid").jqGrid({
		datatype: "JSON",
		mtype : "POST",
	colModel:[ 
			{label:"PRODUCT NAME",name:'prod_name',index:'prod_name', editable:false,align:"left"},
			{label:"STOCKS",name:'prod_stk_name',index:'prod_stk_name', editable:false,align:"left"},
			{label:"FROM",name:'prod_stk_from',index:'prod_stk_from', editable:false,align:"left"},
			{label:"TO",name:'prod_stk_to',index:'prod_stk_to', editable:false,align:"left"},
			{label:"Transaction Id",name:'transaction_id',index:'transaction_id', hidden:true},
         ],
        	   rowNum:50,
        	   rowList:[50,100,200],
        	   pager: '#paramByProductGridPager',
        	   sortname: 'id',
        	   loadonce:true,
        	   viewrecords: false,			        	   
        	   sortorder: "desc",
        	   rownumbers : true,
        	   shrinkToFit:false,
        	   width:null,
       		   height:500,
       		   loadComplete:function(data){
       			
			},
	});
//	jQuery("#paramByProductGrid").jqGrid('filterToolbar', { stringResult: true, searchOnEnter: false, defaultSearch: "cn" });

//	$("#paramByProductGrid").jqGrid("setLabel", "rn", "Sr No.");
	
	$("#paramByCategoryGrid").jqGrid({
		datatype: "JSON",
		mtype : "GET",
	colModel:[ 
			{label:"STOCKS",name:'cat_stk_name',index:'cat_stk_name', align:"left" ,editable: false},
			{label:"FROM",name:'cat_stk_from',index:'cat_stk_from', editable:false,align:"left"},
			{label:"TO",name:'cat_stk_to',index:'cat_stk_to', editable:false,align:"left"},
			{label:"Stock Cat Id",name:'cat_stk_id',index:'cat_stk_id', hidden:true},
			{label:"Transaction Id",name:'transaction_id',index:'transaction_id', hidden:true},
         ],
        	   rowNum:50,
        	   rowList:[50,100,200],
        	   pager: '#paramByCategoryGridPager',
        	   sortname: 'id',
        	   loadonce:true,
        	   viewrecords: false,			        	   
        	   sortorder: "desc",
        	   rownumbers : true,
        	   shrinkToFit:false,
        	   multiselect: false,
        	   width:null,
       		   height:500,
       		   loadComplete:function(data){
       			   if(data.rows.length>0){
       				setProductParameters(data.rows,"grid");
       			   }else{
       				clearProductParameters();
       			   }
       		},
	});
	
	//OnChange Of category
	$("#prodCatSel").change(function() {
		$("#yearlyTable").show();
		$("#productCategorySection").hide();
		clearProductSettingPage();
		clearProductSettingPage();
		getDashboardGridData($(this).val());
		$('#ProductSearch').val(null);
		$("#selected_month").html(null);
	});
	
	//$("#selected_month").html("<a id='"+monthArr[month]+"' href=javascript:getMonthWiseData('"+monthArr[month]+"');>"+monthArr[month]+ "</a> , ");
});

function getDashboardGridData(categoryId){
	$("#selected_Category").html($('select[name=prodCatSel]').find(':selected').text());
	getParameterValues(categoryId,monthArray,$("#SelectedCatalogNo").val());
}

function saveParameter(){
	var isValidated = validateProductParameter(false);
	var isParaIsOk = checkForInclusiveAndOverlappingValue();
	var isNull = validateProductParameter(true);
	
	if(isValidated == false){
		alert("Please be aware that the 'From' value must be lower than 'To'.");
		return ;
	}
	if(isParaIsOk == false){
		alert("Value must not be overriden and not in inclusive range with other values.");
		return ;
	}
	if(isNull == false){
		alert("Form Must Be Filled Fully.");
		return ;
	}
	
	var category_id = $("#prodCatSel").val();
	if(category_id ==  null || category_id == ""){
		alert("Please select Category.");
		return;
	}
	
	var productParamDetailBeanList = [
			{ "transaction_id": $("#transaction_id").val() , "stk_cat_id":"STK_CAT1","stk_cat_from":parseInt($("#overstock_from").val()),"stk_cat_to":parseInt($("#overstock_to").val())} , 
			{ "transaction_id": $("#transaction_id").val() ,  "stk_cat_id":"STK_CAT2","stk_cat_from":parseInt($("#healthy_stock_from").val()),"stk_cat_to":parseInt($("#healthy_stock_to").val())},
			{ "transaction_id": $("#transaction_id").val() ,  "stk_cat_id":"STK_CAT3","stk_cat_from":parseInt($("#low_stock_from").val()),"stk_cat_to":parseInt($("#low_stock_to").val())},
			{ "transaction_id": $("#transaction_id").val() ,  "stk_cat_id":"STK_CAT4","stk_cat_from":parseInt($("#buffer_stock_from").val()),"stk_cat_to":parseInt($("#buffer_stock_to").val())},
			{ "transaction_id": $("#transaction_id").val() , "stk_cat_id":"STK_CAT5","stk_cat_from":parseInt($("#out_stock_from").val()),"stk_cat_to":parseInt($("#out_stock_to").val())},
			{ "transaction_id": $("#transaction_id").val() ,  "stk_cat_id":"STK_CAT6","stk_cat_from":parseInt($("#short_dated_from").val()),"stk_cat_to":parseInt($("#short_dated_to").val())},
			{ "transaction_id": $("#transaction_id").val() , "stk_cat_id":"STK_CAT7","stk_cat_from":parseInt($("#lead_time_from").val()),"stk_cat_to":parseInt($("#lead_time_to").val())},
			{ "transaction_id": $("#transaction_id").val() , "stk_cat_id":"STK_CAT8","stk_cat_from":parseInt($("#rateOfsale").val()),"stk_cat_to":parseInt(0)}
		];
	
	blockPage();
	$.ajax({
		type: 'POST',
		url: "/save",
		data:{
				"query"  : JSON.stringify(productParamDetailBeanList),
				"transaction_id" : $("#transaction_id").val(),
				"ref_prod_cat_id" : category_id,
				"catalog_no": $("#SelectedCatalogNo").val()
		},
		success: function(data, textStatus ){
			openPage();
			if(data.statusCode == 200){
					alert("Parameter data is saved.");
					getDashboardGridData();
				}else{
					alert("Error while saving parameter data.");
				}
		},
		error: function(xhr, textStatus, errorThrown){
			openPage();
			alert('request failed'+errorThrown);
			}
		});
}
function clearProductParameters(){
	$('#overstock_from').val('');
	$('#overstock_to').val('');
	$('#healthy_stock_from').val('');
	$('#healthy_stock_to').val('');
	$('#low_stock_from').val('');
	$('#low_stock_to').val('');
	$('#buffer_stock_from').val('');
	$('#buffer_stock_to').val('');
	$('#out_stock_from').val('');
	$('#out_stock_to').val('');
	$('#short_dated_from').val('');
	$('#short_dated_to').val('');
	$('#lead_time_from').val('');
	$('#lead_time_to').val('');
	$('#transaction_id').val('');
	$('#rateOfSale').val('');
	$('#rateOfsale').val('');
}

function setProductParameters(dataArr,type){
	if(type == "grid"){
		for(var i = 0;i<dataArr.length;i++){
			var data = dataArr[i];
			if(data.cell[3] == "STK_CAT1"){
				$('#overstock_from').val(data.cell[1]);
				$('#overstock_to').val(data.cell[2]);
			}
			if(data.cell[3] == "STK_CAT2"){
				$('#healthy_stock_from').val(data.cell[1]);
				$('#healthy_stock_to').val(data.cell[2]);
			}
			if(data.cell[3] == "STK_CAT3"){
				$('#low_stock_from').val(data.cell[1]);
				$('#low_stock_to').val(data.cell[2]);
			}
			if(data.cell[3] == "STK_CAT4"){
				$('#buffer_stock_from').val(data.cell[1]);
				$('#buffer_stock_to').val(data.cell[2]);
			}
			if(data.cell[3] == "STK_CAT5"){
				$('#out_stock_from').val(data.cell[1]);
				$('#out_stock_to').val(data.cell[2]);
			}
			if(data.cell[3] == "STK_CAT6"){
				$('#short_dated_from').val(data.cell[1]);
				$('#short_dated_to').val(data.cell[2]);
			}
			if(data.cell[3] == "STK_CAT7"){
				$('#lead_time_from').val(data.cell[1]);
				$('#lead_time_to').val(data.cell[2]);
			}
			if(data.cell[3] == "STK_CAT8"){
				$('#rateOfsale').val(data.cell[1]);
			}
		}
		$('#transaction_id').val(dataArr[0].cell[4]);
	}
}

function getMonthWiseData(monthName){
	var ind= monthArr.indexOf(monthName);
	ind++;
	if($('#prodCatSel').val() == ""){
		alert("Please select any product category.")
		return;
	}
	var month = [ind];
	getParameterValues($('#prodCatSel').val(),month,$("#SelectedCatalogNo").val());
}

function getParameterValues(category,Selectedmonth,catalog_no){
	var year = 2020;
	var status = "active";
	var search = false;
	var catalogNo = catalog_no;
	
	if(category!=""){
		clearProductParameters();
		jQuery("#paramByCategoryGrid").jqGrid("clearGridData");
		jQuery("#paramByProductGrid").jqGrid("clearGridData");
		
		//getCategoryData
		jQuery("#paramByCategoryGrid").jqGrid('setGridParam', { url: 'getParamsByCategory', datatype: "JSON",mtype : "POST",postData: {
			"year":year,
			"monthz":Selectedmonth,
			"status":status,
			"search":search,
			"catalog_no":catalogNo,
			"category":category
		}}).trigger("reloadGrid");
		
		//GetProductData
		/*jQuery("#paramByProductGrid").jqGrid('setGridParam', { url: 'getParamsByProduct', datatype: "JSON",mtype : "POST",postData: {
			"year":year,
			"monthz":Selectedmonth,
			"status":status,
			"search":search,
			"catalog_no":catalogNo,
			"category":category
		}}).trigger("reloadGrid");*/
		
		if( Selectedmonth[0] != month ){
			$("#saveParameterBtn").hide();
			$("#reuseParameterBtn").show();
		}else{
			$("#saveParameterBtn").show();
			$("#reuseParameterBtn").hide();
		}
	}else{
		jQuery("#paramByCategoryGrid").jqGrid("clearGridData");
		jQuery("#paramByProductGrid").jqGrid("clearGridData");
		clearProductParameters();
	}
}

function ReUseEntry(){
	var category_id = $("#prodCatSel").val();
	var transactionId = $("#transaction_id").val();
	
	if(category_id ==  null || category_id == ""){
		alert("Please select Category");
		return;
	}
	
	if(transactionId == null || transactionId == ""){
		alert("Re-Use functionality is not use with new entry.");
		return;
	}
	
	var productParamDetailBeanList = [
			{ "transaction_id": $("#transaction_id").val() , "stk_cat_id":"STK_CAT1","stk_cat_from":parseInt($("#overstock_from").val()),"stk_cat_to":parseInt($("#overstock_to").val())} , 
			{ "transaction_id": $("#transaction_id").val() ,  "stk_cat_id":"STK_CAT2","stk_cat_from":parseInt($("#healthy_stock_from").val()),"stk_cat_to":parseInt($("#healthy_stock_to").val())},
			{ "transaction_id": $("#transaction_id").val() ,  "stk_cat_id":"STK_CAT3","stk_cat_from":parseInt($("#low_stock_from").val()),"stk_cat_to":parseInt($("#low_stock_to").val())},
			{ "transaction_id": $("#transaction_id").val() ,  "stk_cat_id":"STK_CAT4","stk_cat_from":parseInt($("#buffer_stock_from").val()),"stk_cat_to":parseInt($("#buffer_stock_to").val())},
			{"transaction_id": $("#transaction_id").val() , "stk_cat_id":"STK_CAT5","stk_cat_from":parseInt($("#out_stock_from").val()),"stk_cat_to":parseInt($("#out_stock_to").val())},
			{ "transaction_id": $("#transaction_id").val() ,  "stk_cat_id":"STK_CAT6","stk_cat_from":parseInt($("#short_dated_from").val()),"stk_cat_to":parseInt($("#short_dated_to").val())},
			{"transaction_id": $("#transaction_id").val() , "stk_cat_id":"STK_CAT7","stk_cat_from":parseInt($("#lead_time_from").val()),"stk_cat_to":parseInt($("#lead_time_to").val())},
			{ "transaction_id": $("#transaction_id").val() , "stk_cat_id":"STK_CAT8","stk_cat_from":parseInt($("#rateOfsale").val()),"stk_cat_to":parseInt(0)}
		];

	$.ajax({
		type: 'POST',
		url: "/reUse",
		data:{
				"query"  : JSON.stringify(productParamDetailBeanList),
				"transaction_id" : $("#transaction_id").val(),
				"ref_prod_cat_id" : category_id,
				"catalog_no": $("#ProductSearch").val()
		},
		success: function(data, textStatus ){
			alert("ReUse Successful");
		},
		error: function(xhr, textStatus, errorThrown){
			alert('ReUse request failed');
			}
		});
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
  
function clearProductSettingPage(){
    
	clearProductParameters();
	jQuery("#paramByCategoryGrid").jqGrid("clearGridData");
	jQuery("#paramByProductGrid").jqGrid("clearGridData");
	$('#prodCatSel option[value="1"]').attr("selected",true);
	$('#ProductSearch').val(null);
	$("#selected_month").html(null);
	$("#selected_Category").html(null);
	$("#showProduct"). prop("checked", false);
	$("#searchProduct").hide();
}

function getProductCategoryData(value,label,isSearch){
	$("#productCategorySection").show();    	
    $("#graphSection").hide();
    $("#yearlyTable").hide();
    $("#productCategoryActionLabel").html(label);
    $("#productCategoryLabel").val(value);
    
    if($("#searchPartNo").val().trim()=="" || $("#searchPartNo").val().trim() == null){
   	 isSearch = false;
    }
    
    //blockPage();
   	jQuery("#productCategoryValueListGrid").jqGrid('setGridParam',	{datatype : 'JSON',mtype : 'POST',url :  '/productCategoryWiseData?productCategoryValue='+ $("#productCategoryLabel").val() +'&productCategory='+$("#productCategorySelect").val().trim() +'&searchString='+$("#searchPartNo").val().trim() +'&_search='+isSearch});
   	jQuery("#productCategoryValueListGrid").trigger("reloadGrid");
	//openPage();

	$("#searchPartNoOrDesc").val(null);
	$("#searchPartNo").val(null);
}

function loadActivities(){
	var lastSel = "";
	var id = "";
	jQuery("#productCategoryValueListGrid").jqGrid({		
		datatype : '',
		mtype: 'POST',
		colNames:['ID' , 'PRODUCT CATEGORY', 'STOCK CATEGORY','PART NO'  , 'NAME' , 'PROFITABLE/NCSO' , 'UNITS' , 'WEEKS OF COVER' , 'NO. OF PALLETS' , 'UNITS PER WEEK' ,'12 WEEK-TREND', 'UNITS' ,'WEEKS OF COVER', 'DELIVERY DATE' ,'NO. OF PALLETS', 'UNITS' ,'WEEKS OF COVER', 'LEAD TIME' ],
		colModel:[
			{name:'DPS_ID',index:'DPS_ID' ,width:80,align:"center", sortable: false ,editable:false , hidden : true}, 
			{name:'PRODUCT_CATEGORY',index:'PRODUCT_CATEGORY' ,width:10,align:"left", sortable: false ,editable:false, hidden : true},
			{name:'STOCK_CATEGORY',index:'STOCK_CATEGORY' ,width:250, sortable: false ,align:"left",editable:false, hidden : true},
			{name:'PART_NO',index:'PART_NO' ,width:110,align:"left" , sortable: false ,editable:false},
			{name:'NAME',index:'NAME' ,width:400,align:"left" , sortable: false ,editable:false},
			{name:'CS_PROFITABLE_NCSO',index:'CS_PROFITABLE_NCSO' ,width:140,align:"center", sortable: false ,editable:false }, 
			{name:'CS_UNITS',index:'CS_UNITS' ,width:120,align:"right", sortable: false ,editable:false},
			{name:'CS_WEEKS_OF_COVER',index:'CS_WEEKS_OF_COVER' ,width:140, sortable: false ,align:"right",editable:false},
			{name:'CS_NO_OF_PALLETS',index:'CS_NO_OF_PALLETS' ,width:120,align:"right" , sortable: false ,editable:false},
			{name:'ROS_UNITS_PER_WEEK',index:'ROS_UNITS_PER_WEEK' ,width:140,align:"right" , sortable: false ,editable:false},
			{name:'ROS_12_WEEK_TREND',index:'ROS_12_WEEK_TREND' ,width:120,align:"right", sortable: false ,editable:false }, 
			{name:'ITS_UNITS',index:'ITS_UNITS' ,width:120,align:"right", sortable: false ,editable:false},
			{name:'ITS_WEEKS_OF_COVER',index:'ITS_WEEKS_OF_COVER' ,width:140, sortable: false ,align:"right",editable:false},
			{name:'ITS_DELIVERY_DATE',index:'ITS_DELIVERY_DATE' ,width:140,align:"left" , sortable: false ,editable:false},
			{name:'ITS_NO_OF_PALLETS',index:'ITS_NO_OF_PALLETS' ,width:120,align:"right" , sortable: false ,editable:false},
			{name:'BN_UNITS',index:'BN_UNITS' ,width:120,align:"right", sortable: false ,editable:false }, 
			{name:'BN_WEEKS_OF_COVER',index:'BN_WEEKS_OF_COVER' ,width:120,align:"right", sortable: false ,editable:false},
			{name:'BN_LEAD_TIME',index:'BN_LEAD_TIME' ,width:120, sortable: false ,align:"right",editable:true},
			
			],       						 
			rowNum:100,         
			height :500,
			width: null,
			shrinkToFit: false,
			rowList:[100,200], 
			pager: '#productCategoryValueListGridPager',  
			onSelectRow : function(id) {
				if (id && id !== lastSel) {
					$("#productCategoryValueListGrid").jqGrid('restoreRow',lastSel);
					lastSel = id;
				}
				
				var gsr = jQuery("#productCategoryValueListGrid").jqGrid('getGridParam','selrow');		
				var prvQP = (jQuery("#productCategoryValueListGrid").jqGrid('getCell',gsr,'BN_LEAD_TIME'));
			
					editparameters = {
							"keys" : true,
							"oneditfunc" : null,
							"successfunc" : function() {
							},
							"url" : "clientArray",
							"extraparam" : {},
							"aftersavefunc" : function(rowId) {
								editBNLeadTime($("#productCategoryValueListGrid").jqGrid('getRowData',rowId) , prvQP , rowId);
								
							},
							"errorfunc" : null,
							"afterrestorefunc" : null,
							"restoreAfterError" : true,
						}
					$("#productCategoryValueListGrid").jqGrid('editRow', id, editparameters);
			},
			ondblClickRow: function(rowId) {
				var rowData = jQuery(this).getRowData(rowId);
			}, 
			loadComplete : function(data){
				openPage();
			}
	});	
	jQuery("#productCategoryValueListGrid").jqGrid('navGrid','#productCategoryValueListGridPager',{edit:false,add:false,del:false,back:false , search:false, refresh: false});
	 $('#productCategoryValueListGrid').setGroupHeaders({useColSpanStyle: true,groupHeaders: [{ "numberOfColumns": 4, "titleText": "CURRENT STOCK", "startColumnName": "CS_PROFITABLE_NCSO" },{ "numberOfColumns": 2, "titleText": "RATE OF SALE", "startColumnName": "ROS_UNITS_PER_WEEK" },{ "numberOfColumns": 4, "titleText": "IN TRANSIT STOCK", "startColumnName": "ITS_UNITS" },{ "numberOfColumns": 3, "titleText": "BUY NOW", "startColumnName": "BN_UNITS" }]});
}

function validateProductParameter(isCheckForNull){
	var returnFlag = true;
	
	var overstock_from = parseInt($("#overstock_from").val());
	var overstock_to = parseInt($("#overstock_to").val());
		
	var healthy_stock_from = parseInt($("#healthy_stock_from").val());
	var healthy_stock_to = parseInt($("#healthy_stock_to").val()); 

	var low_stock_from = parseInt($("#low_stock_from").val());
	var  low_stock_to = parseInt($("#low_stock_to").val()); 

	var buffer_stock_from = parseInt($("#buffer_stock_from").val());
	var buffer_stock_to = parseInt($("#buffer_stock_to").val()); 

	var out_stock_from = parseInt($("#out_stock_from").val());
	var out_stock_to = parseInt($("#out_stock_to").val());
	
	var short_dated_from = parseInt($("#short_dated_from").val());
	var short_dated_to = parseInt($("#short_dated_to").val()); 

	var lead_time_from = parseInt($("#lead_time_from").val());
	var lead_time_to = parseInt($("#lead_time_to").val()); 
	
	
	if(isCheckForNull){
		var tempCount = 0;
		if(isNaN(overstock_from) && isNaN(overstock_to)){
			tempCount++;
		}
		if(isNaN(healthy_stock_from)&& isNaN(healthy_stock_to)){
			tempCount++;
		}
		if(isNaN(low_stock_from) && isNaN(low_stock_to)){
			tempCount++;
		}
		if(isNaN(buffer_stock_from) && isNaN(buffer_stock_to)){
			tempCount++;
		}
		if(isNaN(out_stock_from)&& isNaN(out_stock_to)){
			tempCount++;
		}
		if(tempCount == 5){
			returnFlag = false;
		}
	}else{
		if((overstock_from > overstock_to) || overstock_to == "" ){
			$("#overstock_from").focus();
			returnFlag = false;
		}
		if( (healthy_stock_from > healthy_stock_to) || healthy_stock_to == "" ){
			$("#healthy_stock_from").focus();
			returnFlag = false;
		}
		if( low_stock_from > low_stock_to || low_stock_to == "" ){
			$("#low_stock_from").focus();
			returnFlag = false;
		}
		if(buffer_stock_from > buffer_stock_to || buffer_stock_to == "" ){
			$("#buffer_stock_from").focus();
			returnFlag = false;
		}
		if(out_stock_from >  out_stock_to || out_stock_to == "" ){
			if(out_stock_from == 0 &&  out_stock_to == 0){
				
			}else{
				$("#out_stock_from").focus();
				returnFlag = false;	
			}
		}
		if(short_dated_from > short_dated_to || short_dated_to == "" ){
			$("#short_dated_from").focus();
			returnFlag = false;
		}
		/*if(lead_time_from > lead_time_to || lead_time_to == "" ){
			$("#lead_time_from").focus();
			returnFlag = false;
		}*/
	}
	
	
	
	return returnFlag;
}

function checkForInclusiveAndOverlappingValue(){
	var returnFlag = true;
	var bugCount = 0;
	
	var overstock_from = parseInt($("#overstock_from").val());
	var overstock_to = parseInt($("#overstock_to").val());
		
	var healthy_stock_from = parseInt($("#healthy_stock_from").val());
	var healthy_stock_to = parseInt($("#healthy_stock_to").val()); 

	var low_stock_from = parseInt($("#low_stock_from").val());
	var low_stock_to = parseInt($("#low_stock_to").val()); 

	var buffer_stock_from = parseInt($("#buffer_stock_from").val());
	var buffer_stock_to = parseInt($("#buffer_stock_to").val()); 

/*	var out_stock_from = parseInt($("#out_stock_from").val());
	var out_stock_to = parseInt($("#out_stock_to").val());
	
	var short_dated_from = parseInt($("#short_dated_from").val());
	var short_dated_to = parseInt($("#short_dated_to").val()); 

	var lead_time_from = parseInt($("#lead_time_from").val());
	var lead_time_to = parseInt($("#lead_time_to").val()); 
	*/
	
	/*var parameterJson = [[overstock_from,overstock_to],[healthy_stock_from,healthy_stock_to],
			[low_stock_from,low_stock_to],[buffer_stock_from,buffer_stock_to],[out_stock_from,out_stock_to],
			[short_dated_from,short_dated_to],[lead_time_from,lead_time_to]];*/
	
	var parameterJson = [[overstock_from,overstock_to],[healthy_stock_from,healthy_stock_to],
		[low_stock_from,low_stock_to],[buffer_stock_from,buffer_stock_to]];
	
	for(var i = 0 ; i <parameterJson.length;i++){
		for(var j = 0 ; j < parameterJson.length ; j++){
			var compareFrom = 0;
			var compareTo = 0;
			var comparableFrom = 0;
			var comparableTo = 0;
			
			if(j == i){
				continue;
			}else{
				compareFrom = parameterJson[i][0];
				compareTo =parameterJson[i][1]
				
				comparableFrom =parameterJson[j][0];
				comparableTo =parameterJson[j][1];
				
				if(compareFrom == comparableFrom || compareTo == comparableTo || compareFrom == comparableTo || compareTo == comparableFrom){
					bugCount++;
				}else if(compareFrom >comparableFrom && compareFrom<comparableTo ){
					bugCount++;
				}else if(compareTo >comparableFrom && compareTo<comparableTo ){
					bugCount++;
				}
			}
		}
	}
	if(bugCount != 0){
		returnFlag = false;
	}
	return returnFlag;
}