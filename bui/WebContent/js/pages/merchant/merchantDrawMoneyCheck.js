/**
 * 
 */

$(document).ready(function() {
	// 员工表单验证
	MerchantDrawMoney.validate($("#form1"));

});

/**
 * 提交页面表单方法
 */
var submit = function(callBack) {
	var formObj = $("#form1");
	if ($('#status').val() == '') {
		alert('请选择审核状态');
		return false;
	}
	if (formObj.valid()) {
		MerchantDrawMoney.submitCheckForm(formObj, callBack);
	}
};
