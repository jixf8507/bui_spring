/**
 * 
 */

$(document).ready(function() {
	$('#status,#statementDate,#repaymentDate').chosen();
	// 员工表单验证
	User.validate($("#form1"));

	$('#bt').click(function() {
		Upload.imageUpload("上传商品图片", getPic);
	});
	$('#sbt').click(function() {
		Upload.imageUpload("上传商品图片", getStuPic);
	});

});

/**
 * 提交页面表单方法
 */
var submit = function(callBack) {
	var formObj = $("#form1");
	if ($('#status').val() == '') {
		alert('请选择状态');
		return false;
	}
	if (formObj.valid()) {
		User.submitForm(formObj, callBack);
	}
};

var getPic = function(idArr, urlArr) {
	$('#idCardImg').val(urlArr[0]);
};

var getStuPic = function(idArr, urlArr) {
	$('#studentIdCardImg').val(urlArr[0]);
};