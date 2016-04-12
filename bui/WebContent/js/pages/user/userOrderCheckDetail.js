/**
 * 
 */

$(document).ready(function() {
	$('#status').chosen();
	// 员工表单验证
	UserOrder.validateCheck($("#form1"));

});

/**
 * 提交页面表单方法
 */
var submit = function(callBack) {
	var formObj = $("#form1");
	if ($('#status').val() == '') {
		alert('请选择审核状态');
		return;
	}
	if (formObj.valid()) {
		UserOrder.submitCheckForm(formObj, callBack);
	}
};
