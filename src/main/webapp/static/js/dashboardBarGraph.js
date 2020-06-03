"use strict";
var date = new Date();
var year = date.getFullYear();


$( document ).ready(function() {
	//Added by mehul
	$("#CalculatedDataYearWise").jqGrid({
			datatype: "JSON",
			mtype : "POST",
		colModel:[
				{label:"Parameter Name",name:'parameter_id',index:'parameter_id', align:"left" ,editable: false},
				{label:"January 2020",name:'January',index:'January 2020', align:"center" ,editable: false},
				{label:"February 2020",name:'February',index:'February 2020', editable:false,align:"center"},
				{label:"March 2020",name:'March',index:'March 2020', editable:false,align:"center"},
				{label:"April 2020",name:'April',index:'April 2020', align:"center" ,editable: false},
				{label:"May 2020",name:'May',index:'May 2020', editable:false,align:"center"},
				{label:"June 2020",name:'June',index:'June 2020', editable:false,align:"center"},
				{label:"July 2020",name:'July',index:'July 2020', align:"center" ,editable: false},
				{label:"August 2020",name:'August',index:'August 2020', editable:false,align:"center"},
				{label:"September 2020",name:'September',index:'September 2020', editable:false,align:"center"},
				{label:"October 2020",name:'October',index:'October 2020', align:"center" ,editable: false},
				{label:"November 2020",name:'November',index:'November 2020', editable:false,align:"center"},
				{label:"December 2020",name:'December',index:'December 2020', editable:false,align:"center"},
				],
		    	   rowNum:20,
		    	   rowList:[50,100,200],
		    	   pager: '#CalculatedDataYearWisePager',
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
		   				   //alert("data is get : "+data + " data json is :"+JSON.stringify(data));
		   			   }
		   		}
		});
	
});



/*****E-Charts function start*****/
var echartsConfig = function(configData) {
	
	if( $('#e_chart_5').length > 0 ){
	var eChart_5 = echarts.init(document.getElementById('e_chart_5'));
	
	var xData = function(){
		var data = ["Jan", "feb", "March", "Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"];
		return data;
	}();

	var option5 = {
		tooltip: {
			trigger: 'axis',
			backgroundColor: 'rgba(33,33,33,1)',
			borderRadius:0,
			padding:10,
			axisPointer: {
				type: 'cross',
				label: {
					backgroundColor: 'rgba(33,33,33,1)'
				}
			},
			textStyle: {
				color: '#fff',
				fontStyle: 'normal',
				fontWeight: 'normal',
				fontFamily: "'Montserrat', sans-serif",
				fontSize: 12
			}	
		},
		"grid": {
			show:false,
			top: 30,
			bottom: 10,
			containLabel: true,
		}, 
		"legend": {
			"x": "right", 
			"data": [ ]
		}, 
		"calculable": true, 
		"xAxis": [
			{
				type: "category", 
				splitLine: {
					"show": false
				}, 
				axisLine: {
					show:false
				},
				axisLabel: {
					textStyle: {
						color: '#878787'
					}
				},
				axisTick: {
					"show": false
				}, 
				splitArea: {
					"show": false
				}, 
				data: xData,
			}
		], 
		"yAxis": [
			{
				type: "value", 
				splitLine: {
					"show": false
				}, 
				axisLine: {
					show:false
				},
				axisLabel: {
					textStyle: {
						color: '#878787'
					}
				},
				axisTick: {
					"show": false
				}, 
				splitArea: {
					"show": false
				},
			}
		], 
		"series": configData
	}
	eChart_5.setOption(option5);
	eChart_5.resize();
	}	
}
/*****E-Charts function end*****/


var sparklineLogin = function() {
	if( $('#sparkline_5').length > 0 ){
			$("#sparkline_5").sparkline([0,2,8,6,8], {
				type: 'bar',
				width: '100%',
				height: '45',
				barWidth: '10',
				resize: true,
				barSpacing: '10',
				barColor: '#fff',
				highlightSpotColor: '#fff'
			});
		}
}

/*****Resize function start*****/
var sparkResize,echartResize;
$(window).on("resize", function () {
	/*Sparkline Resize*/
	clearTimeout(sparkResize);
	sparkResize = setTimeout(sparklineLogin, 200);
	
	/*E-Chart Resize*/
	clearTimeout(echartResize);
	echartResize = setTimeout(echartsConfig, 200);
}).resize(); 
/*****Resize function end*****/

/*****Function Call start*****/
sparklineLogin();
//echartsConfig();
/*****Function Call end*****/



//For Bargraph 
function getCategoryWiseCalculatedData(){
	//alert("getCategoryWiseCalculatedData is called");
	var categoryId = $("#productCategorySelect").val().trim();
	if(categoryId == null || categoryId ==""){
		categoryId = "P_CAT1";
	}
	getCalculatedData(categoryId,false);
	 $("#selectedCatName").html($("#productCategorySelect option:selected").html());
}

function getCalculatedData(categoryId,isSearch){
	
	blockPage();
	jQuery("#CalculatedDataYearWise").jqGrid("clearGridData");
	jQuery("#CalculatedDataYearWise").jqGrid('setGridParam', { url: '/getDashboardCalculatedData', datatype: "JSON",mtype : "POST",postData: {
		"year":year,
		"search":isSearch,
		"categoryId":categoryId
	}}).trigger("reloadGrid");
	openPage();
	
}

