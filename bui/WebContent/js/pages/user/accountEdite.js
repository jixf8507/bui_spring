/**
 * 
 */

$(document).ready(function() {
	// 员工表单验证
	User.validate($("#form1"));

});

/**
 * 提交页面表单方法
 */
var submit = function(callBack) {
	var formObj = $("#form1");
	if (formObj.valid()) {
		User.submitForm(formObj, callBack);
	}
};
