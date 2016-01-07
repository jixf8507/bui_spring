/**
 * 
 */

$(document).ready(function() {
	// 员工表单验证
	Merchant.validate($("#form1"));

});

/**
 * 提交页面表单方法
 */
var submit = function(callBack) {
	var formObj = $("#form1");
	if (formObj.valid()) {
		Merchant.submitForm(formObj, callBack);
	}
};
