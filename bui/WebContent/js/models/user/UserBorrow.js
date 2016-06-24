/**
 * 用户信息管理
 */

var UserBorrow = {
	pageUrl : contextPath + "/user/borrow/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/user/borrow/exportToExcel.htm?",
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
				"mDataProp" : "id",
				"sClass" : "center"
			}, {
				"mDataProp" : "name",
				"sClass" : "center"
			}, {
				"mDataProp" : "mobilePhone",
				"sClass" : "center"
			}, {
				"mDataProp" : "cost",
				"sClass" : "center"
			}, {
				"mDataProp" : "aging",
				"sClass" : "center"
			}, {
				"mDataProp" : "bankName",
				"sClass" : "center"
			}, {
				"mDataProp" : "cardNumber",
				"sClass" : "center"
			}, {
				"fnRender" : function(obj) {
					var accountStatus = obj.aData['status'];
					switch (accountStatus) {
					case '1':
						return "审核中";
						break;
					case '2':
						return "已通过";
						break;
					case '3':
						return "未通过";
						break;
					default:
						return "状态错误";
						break;
					}
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "createTime",
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
	detail : function() {
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择一条的记录');
			return;
		} else if (value.length > 1) {
			alert('只能选择一条记录');
			return;
		}
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "查看用户提现信息";
		diag.URL = contextPath + "/user/borrow/detail.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.show();
	},
	checkOrder : function() {
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择一条的记录');
			return;
		} else if (value.length > 1) {
			alert('只能选择一条记录');
			return;
		}
		var thiz = this;
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "审核用户提现信息";
		diag.URL = contextPath + "/user/borrow/checkDetail.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
	},
	billDetail : function() {
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择一条的记录');
			return;
		} else if (value.length > 1) {
			alert('只能选择一条记录');
			return;
		}
		var diag = new Dialog();
		diag.Width = 900;
		diag.Height = 500;
		diag.Title = "查看分期账单明细记录";
		diag.URL = contextPath
				+ "/user/billDetail/list.htm?orderTable=user_borrow&orderId="
				+ value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.show();
	},
	callBack : function() {
		location.reload();
	},
	validateCheck : function(formObj) {
		formObj.validate({
			rules : {
				'checkDisc' : {
					"required" : true
				}
			}
		});
	},
	// 提交用户表单
	submitCheckForm : function(formObj, callBack) {
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/user/borrow/submitCheck.htm?',
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
