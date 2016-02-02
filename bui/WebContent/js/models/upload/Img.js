/**
 * 系统角色类
 */

var Img = {
	pageUrl : contextPath + "/upload/ajaxData.htm?t=" + new Date().getTime(),
	exportUrl : contextPath + "/upload/exportToExcel.htm?",
	tableColumns : [ // 设定各列宽度
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					return '<input style="width: 20px;" type="checkbox" name="id" id="'
							+ id + '" title="' + id + '" value="' + id + '">';
				},
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var img = contextPath + '/' + obj.aData['fileUrl'];
					return '<a href="'
							+ img
							+ '" target="_blank"><img src="'
							+ img
							+ '"  alt="商品图片" width="50px;" height="50px;" /></a>';
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "createdTime",
				"sClass" : "center"
			} ],
	selectAll : function(thiz) {
		var bool = thiz.attr("checked") ? true : false;
		$("input:[name=id]").each(function(index) {
			$(this).attr('checked', bool);
		});
	},
	getSelectValue : function() {
		var selValue = [];
		$("input:[name=id]checkbox:checked").each(function(index) {
			selValue.push($(this).val());
		});
		return selValue;
	},
	// 打开创建用户对话框
	createUser : function() {
		var tableId = $('#tableId').val();
		var tableName = $('#tableName').val();
		var fileType = $('#fileType').val();
		var pathType = $('#pathType').val();
		Upload.imageUpload("上传图片", this.callBack, tableId, tableName, pathType,
				fileType);
	},
	deleteUser : function() {
		var thiz = this;
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择要删除的记录');
			return;
		}
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/upload/delete.htm?',
			data : 'ids=' + JSON.stringify(value),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						thiz.callBack();
					});
				} else {
					Dialog.alert("提示：" + data.info);
				}
			}
		});
	},

	callBack : function() {
		location.reload();
	},
	// 提交用户表单
	submitForm : function(formObj, callBack) {
		Dialog.alert("提示：操作成功", function() {
			callBack();
		});
	}
};