/**
 * 
 */

$(document).ready(function() {

	merchantCombox.requestData();
	// 员工表单验证
	MerchantGoods.validate($("#form1"));

	$('#bt').click(function() {
		Upload.imageUpload("上传商品图片", getPic);
	});

});
// 创建系统角色选择框
var merchantCombox = Merchant.createMerchantCombox('merchantId');

/**
 * 提交页面表单方法
 */
var submit = function(callBack) {
	var formObj = $("#form1");
	if (formObj.valid()) {
		if ($("#merchant").val() == '') {
			Dialog.alert("提示：请选择商家");
			return false;
		}
		MerchantGoods.submitForm(formObj, callBack);
	}
};

var getPic = function(idArr, urlArr) {	
	$('#img').val( urlArr[0]);
};
