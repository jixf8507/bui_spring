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
	if (formObj.valid()) {
		MerchantDrawMoney.submitForm(formObj, callBack);
	}
};
