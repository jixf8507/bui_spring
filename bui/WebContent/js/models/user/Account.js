/**
 * 用户信息管理
 */

var User = {
	pageUrl : contextPath + "/user/account/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/user/account/exportToExcel.htm?",
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
				"mDataProp" : "name",
				"sClass" : "center"
			}, {
				"mDataProp" : "mobilePhone",
				"sClass" : "center"
			}, {
				"mDataProp" : "idCard",
				"sClass" : "center"
			}, {
				"mDataProp" : "usableLimit",
				"sClass" : "center"
			}, {
				"mDataProp" : "curWhiteBarLimit",
				"sClass" : "center"
			}, {
				"mDataProp" : "balance",
				"sClass" : "center"
			}, {				
				"fnRender" : function(obj) {
					var accountStatus = obj.aData['accountStatus'];
					switch (accountStatus) {
					case '1':
						return "正常";
						break;
					case '2':
						return "不可取现可分期";
						break;
					case '3':
						return "不可取现不可分期";
						break;
					default:
						return "其它";
						break;
					}
				},
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
		diag.Title = "编辑用户信息";
		diag.URL = contextPath + "/user/account/edite.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
	},
	detailUser : function() {
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择要修改的记录');
			return;
		} else if (value.length > 1) {
			alert('只能选择一条记录进行修改');
			return;
		}
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "查看用户信息";
		diag.URL = contextPath + "/user/account/detail.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.show();
	},
	callBack : function() {
		location.reload();
	},
	// 验证用户信息表单
	validate : function(formObj) {
		formObj.validate({
			rules : {
				'user.name' : {
					"required" : true
				},
				'user.mobilePhone' : {
					"required" : true,
					"phone" : true
				},
				'user.idCard' : {
					"required" : true,
					"idcard" : true
				},
				'usableLimit' : {
					"required" : true,
					"number" : true
				},
				'whiteBarLimit' : {
					"required" : true,
					"number" : true
				},
				'statementDate' : {
					"required" : true
				},
				'repaymentDate' : {
					"required" : true
				}
			}
		});
	},
	// 提交用户表单
	submitForm : function(formObj, callBack) {
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/user/account/submit.htm?',
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
