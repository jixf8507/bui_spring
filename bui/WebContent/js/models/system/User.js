/**
 * 系统工作人员
 */

var User = {
	pageUrl : contextPath + "/system/user/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/system/user/exportToExcel.htm?",
	tableColumns : [ // 设定各列宽度
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['name'];
					return '<input style="width: 20px;" type="checkbox" name="id" id="'
							+ id + '" title="' + name + '" value="' + id + '">';
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "code",
				"sClass" : "center"
			}, {
				"mDataProp" : "name",
				"sClass" : "center"
			}, {
				"mDataProp" : "phone",
				"sClass" : "center"
			}, {
				"mDataProp" : "roleName",
				"sClass" : "center"
			}, {
				"mDataProp" : "sex",
				"sClass" : "center"
			} ],
	selectAll : function(thiz) {
		var bool =thiz.attr("checked") ? true : false;
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
		var thiz = this;
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "新增系统员工";
		diag.URL = contextPath + "/system/user/add.htm";
		diag.MessageTitle = "新增系统员工";
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
	},
	editeUser : function() {
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择要修改的记录');
			return;
		} else if (value.length > 1) {
			alert('只能选择一条记录进行修改');
			return;
		}
		var thiz = this;
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "修改系统员工信息";
		diag.URL = contextPath + "/system/user/edite.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
	},
	deleteUser : function() {
		var thiz = this ;
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择要删除的记录');
			return;
		}
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/system/user/delete.htm?',
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
	// 验证用户信息表单
	validate : function(formObj) {
		formObj.validate({
			rules : {
				'code' : {
					"required" : true
				},
				'phone' : {
					"required" : true,
					"phone" : true
				},
				'name' : "required",
				'roleId' : "required"
			}
		});
	},
	// 提交用户表单
	submitForm : function(formObj, callBack) {
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/system/user/submit.htm?',
			data : formObj.serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBack();
					});
				} else {
					Dialog.alert("提示：" + data.info);
				}
			}
		});
	}
};
