/**
 * 用户信息管理
 */

var MerchantOrder = {
	pageUrl : contextPath + "/merchant/order/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/merchant/order/exportToExcel.htm?",
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
				"mDataProp" : "merchantName",
				"sClass" : "center"
			}, {
				"mDataProp" : "cost",
				"sClass" : "center"
			}, {
				"mDataProp" : "aging",
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
		diag.URL = contextPath + "/merchant/order/detail.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
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
				+ "/user/billDetail/list.htm?orderTable=merchant_order&orderId="
				+ value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.show();
	}
};
