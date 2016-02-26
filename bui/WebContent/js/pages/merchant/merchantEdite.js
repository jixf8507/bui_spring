/**
 * 
 */

$(document).ready(function() {
	// 员工表单验证
	Merchant.validate($("#form1"));
	$('#bt').click(function() {
		Upload.imageUpload("上传商品图片", getPic,'','','merchant');
	});

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

var getPic = function(idArr, urlArr) {
	$('#img').val(urlArr[0]);
};
