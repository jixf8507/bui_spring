/**
 * 
 */

$(document).ready(function() {
	$('#status,#statementDate,#repaymentDate').chosen();
	// 员工表单验证
	User.validate($("#form1"));

	$('#bt').click(function() {
		Upload.imageUpload("上传图片", getPic);
	});
	$('#sbt').click(function() {
		Upload.imageUpload("上传图片", getStuPic);
	});
	$('#obt').click(function() {
		Upload.imageUpload("上传图片", getOtherPic);
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

var getOtherPic = function(idArr, urlArr) {
	$('#otherImg').val(urlArr[0]);
};