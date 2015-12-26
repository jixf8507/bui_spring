/**
 * 
 */

$(document).ready(function() {
	// 请求系统角色数据
	roleCombox.requestData();

	// 员工表单验证
	User.validate($("#form1"));
	
	$('#bt').click(function(){
		upload();
	});

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

var diag ;
var upload = function(){
	diag = new Dialog();
	diag.Width = 400;
	diag.Height = 300;
	diag.Title = "修改系统员工信息";
	diag.URL = contextPath + "/upload/uploadImages.htm?" ;
	diag.MessageTitle = '';
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(getPic);
	};
	diag.show();
};

var getPic = function (idArr,urlArr){
	alert(idArr[0]+ '  ==  '+urlArr[0]);
	diag.close();
};