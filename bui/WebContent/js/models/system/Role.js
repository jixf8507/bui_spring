/**
 * 系统角色类
 */

var Role = {
	pageUrl : contextPath + "/system/role/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/system/role/exportToExcel.htm?",
	listUrl : contextPath + '/system/role/ajaxList.htm?t='
			+ new Date().getTime(),
	tableColumns : [ // 设定各列宽度
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['roleName'];
					return '<input style="width: 20px;" type="checkbox" name="id" id="'
							+ id + '" title="' + name + '" value="' + id + '">';
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "roleName",
				"sClass" : "center"
			}, {
				"mDataProp" : "roleRemark",
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
		var thiz = this;
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "新增系统角色";
		diag.URL = contextPath + "/system/role/add.htm";
		diag.MessageTitle = "新增系统角色";
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
		diag.Title = "修改系统角色信息";
		diag.URL = contextPath + "/system/role/edite.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
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
			url : contextPath + '/system/role/delete.htm?',
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
	meneSetting : function() {
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择一条记录');
			return;
		} else if (value.length > 1) {
			alert('只能选择一条记录');
			return;
		}
		var thiz = this;
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "系统角色菜单设置";
		diag.URL = contextPath + "/system/roleMenu/select.htm?rid="
				+ value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
	},
	callBack : function() {
		location.reload();
	},
	// 验证用户信息表单
	validate : function(formObj) {
		formObj.validate({
			rules : {
				'roleName' : {
					"required" : true
				},
				'roleRemark' : "required"
			}
		});
	},
	// 提交用户表单
	submitForm : function(formObj, callBack) {
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/system/role/submit.htm?',
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
	},
	// 创建系统角色下拉选择框
	createRoleCombox : function(roleId) {
		var roleCombox = new Combox({
			id : roleId,
			url : this.listUrl,
			cText : 'roleName',
			cValue : 'id',
			emptyText : '请选择系统角色'
		});
		return roleCombox;
	}
};