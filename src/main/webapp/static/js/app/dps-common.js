function blockPage() {
	$.blockUI({
		message : '<img src="static/img/loader.png" class="loader_img"  /> ' /* class="loader_img" */
	});
}
function openPage() {
	$(document).ajaxStop($.unblockUI);
}