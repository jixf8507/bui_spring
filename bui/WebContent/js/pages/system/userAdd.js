/**
 * 
 */

$(document).ready(function() {
	// 请求系统角色数据
	roleCombox.requestData();

	// 员工表单验证
	User.validate($("#form1"));
	
	

});

// 创建系统角色选择框
var roleCombox = Role.createRoleCombox('roleId');

/**
 * 提交页面表单方法
 */
var submit = function(callBack) {
	var formObj = $("#form1");
	if (formObj.valid()) {
		if ($("#roleId").val() == '') {
			Dialog.alert("提示：请选择系统角色");
			return false;
		}
		User.submitForm(formObj, callBack);
	}
};

