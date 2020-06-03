var date = new Date();
var year = date.getFullYear();
	
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

function searchProductCategoryData(){
	getProductCategoryData($("#productCategoryLabel").val(),$("#productCategoryActionLabel").html(),true);
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
     
    blockPage();
    jQuery("#productCategoryValueListGrid").jqGrid('setGridParam',	{datatype : 'JSON',mtype : 'POST',url :  '/productCategoryWiseData?productCategoryValue='+ $("#productCategoryLabel").val() +'&productCategory='+$("#productCategorySelect").val().trim() +'&searchString='+$("#searchPartNo").val().trim() +'&_search='+isSearch});
	jQuery("#productCategoryValueListGrid").trigger("reloadGrid");
	openPage();

	$("#searchPartNoOrDesc").val(null);
	$("#searchPartNo").val(null);
}


function editBNLeadTime(rowData , prvQP , rowId){
	
	if(rowData.BN_LEAD_TIME.trim() != ""){
		if(isNaN(rowData.BN_LEAD_TIME)){
			showWarningToast("Invalid lead time!." ,"warning");
			rowData.BN_LEAD_TIME = prvQP;
			$('#productCategoryValueListGrid').jqGrid('setRowData', rowId, rowData);
			return false 
		}
	}
	
	blockPage();
	$.ajax({
		url :  '/editBNLeadTime',
		data : {
			"id" : rowId,
			"leadTime" : rowData.BN_LEAD_TIME.trim(),
			"time" : new Date().getTime()
		},
		type : 'post',
		dataType : 'JSON',
		success : function(data) {
			openPage();
			
			if (data.Expire == "Expire") {
				window.location = "login";
			}
			if(data.error != undefined){
				showWarningToastSticky(data.error , "error");
				return;
			}
			if(data.success != undefined){
				alert(data.success);
			}
		},error : function(e){	  
	    	 $('.loaderMain').addClass('hide');
	 	     alert(e);
	             } 
	});
	
}

//Add by Mehul
$(document).ready(function(){
		
	$('#searchPartNoOrDesc').autocomplete({
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
			            "productDesc" : $("#searchPartNoOrDesc").val(),
						"catalogNo" : "data",
						"category":  $("#productCategorySelect").val(),
						"contract" : "LAX",
						"limit": 10,
			      },success: function (data){
			            	response(data);
			            }
			        });
		},select: function (event, ui)
		    {
			    $("#searchPartNoOrDesc").val(ui.item.productDesc);
			    $("#searchPartNo").val(ui.item.catalogNo);
			    
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
	
});

function getGraphData(categoryId){
	blockPage();
	$.ajax({
		url : '/getGraphData',
		data : {
			"year" : year ,
			"categoryId" : categoryId
		},
		type : 'post',
		dataType : 'JSON',
		success : function(data) {
			openPage();
			getCategoryWiseCalculatedData();
			getDashboardData();
			echartsConfig(data.object);
		} ,
		error :function(data) {
			openPage();
			getCategoryWiseCalculatedData();
			getDashboardData();
		}  
	});
}

function getCategoryWiseGraphData(){
	var categoryId = $("#productCategorySelect").val().trim();
	if(categoryId == null || categoryId ==""){
		categoryId = "P_CAT3";
	}
	getGraphData(categoryId);
}
function getDashboardData(){
	var categoryId = $("#productCategorySelect").val().trim();
	if(categoryId == null || categoryId ==""){
		categoryId = "P_CAT3";
	}
	getDashboardDataAjax(categoryId);
}
function getDashboardDataAjax(categoryId){
	$.ajax({
		url : '/getDashboardData',
		data : {
			"year" : year ,
			"categoryId" : categoryId
		},
		type : 'post',
		dataType : 'JSON',
		success : function(data) {
			$("#totalLiceance").html(data.object.LiceanceCount);
		}
	});
}
$("#productCategorySelect").change(function() {
	getCategoryWiseGraphData();
	getDashboardData();
	
    if($("#productCategorySection").is(":hidden")){
    } else{
        getProductCategoryData($("#productCategorySelect").val(),'Overstock',false);
    }
    
    
    if($("#productCategorySelect option:selected").html()=="Generics"){
    	$("#BuyerName").html('Piyush Patel');
    }else if ($("#productCategorySelect option:selected").html()=="Parallel Imports"){
    	$("#BuyerName").html('Nash Patel');
    }else{
    	$("#BuyerName").html('-');
    }
    
});