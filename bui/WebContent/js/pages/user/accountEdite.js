/**
 * 
 */

$(document).ready(function() {
	$('#status').chosen();
	// 员工表单验证
	User.validate($("#form1"));

	$('#bt').click(function() {
		Upload.imageUpload("上传商品图片", getPic);
	});

});

/**
 * 提交页面表单方法
 */
var submit = function(callBack) {
	var formObj = $("#form1");
	if ($('#status') == '') {
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