/**
 * 
 */

$(document).ready(function() {

	$('#isTop,#type').chosen();

	// 员工表单验证
	DmhGoods.validate($("#form1"));

	$('#bt').click(function() {
		Upload.imageUpload("上传商品图片", getPic,'','','dmh');
	});

});

/**
 * 提交页面表单方法
 */
var submit = function(callBack) {
	var formObj = $("#form1");
	if (formObj.valid()) {
		if ($("#type").val() == '') {
			Dialog.alert("提示：请选择类型");
			return false;
		}
		if ($("#isTop").val() == '') {
			Dialog.alert("提示：请选择是否首页显示");
			return false;
		}
		DmhGoods.submitForm(formObj, callBack);
	}
};

var getPic = function(idArr, urlArr) {
	$('#img').val(urlArr[0]);
};
