/**
 * 用户信息管理
 */

var UserOrder = {
	pageUrl : contextPath + "/user/order/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/user/order/exportToExcel.htm?",
	tableColumns : [ // 设定各列宽度
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['id'];
					return '<input style="width: 20px;" type="checkbox" name="id" id="'
							+ id + '" title="' + name + '" value="' + id + '">';
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "id",
				"sClass" : "center"
			}, {
				"mDataProp" : "userName",
				"sClass" : "center"
			}, {
				"mDataProp" : "mobilePhone",
				"sClass" : "center"
			}, {
				"mDataProp" : "goodsName",
				"sClass" : "center"
			}, {
				"mDataProp" : "price",
				"sClass" : "center"			
			}, {
				"mDataProp" : "sfMoney",
				"sClass" : "center"
			}, {
				"mDataProp" : "orderPrice",
				"sClass" : "center"
			}, {
				"mDataProp" : "aging",
				"sClass" : "center"
			}, {
				"fnRender" : function(obj) {
					var status = obj.aData['status'];
					switch (status) {
					case '1':
						return "审核中";
						break;
					case '2':
						return "已通过";
						break;
					case '3':
						return "未通过";
						break;
					case '4':
						return "购买中";
						break;
					case '5':
						return "已配送";
						break;
					case '6':
						return "已收货";
						break;
					default:
						return "其它";
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
	detailUser : function() {
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
		diag.Title = "查看用户消费信息";
		diag.URL = contextPath + "/user/order/detail.htm?id=" + value[0];
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
		diag.Title = "审核用户消费信息";
		diag.URL = contextPath + "/user/order/checkDetail.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
	},
	updateOrderStatus : function(status) {
		var thiz = this;
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择要操作的记录');
			return;
		}
		if (!window.confirm('你确定要操作选中订单吗')) {
			return true;
		}
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/user/order/updateBuyStatus.htm?',
			data : {
				'ids' : JSON.stringify(value),
				'status' : status
			},
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
				+ "/user/billDetail/list.htm?orderTable=user_order&orderId="
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
			url : contextPath + '/user/order/submitCheck.htm?',
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
