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
				"mDataProp" : "merchantName",
				"sClass" : "center"
			}, {
				"mDataProp" : "sfMoney",
				"sClass" : "center"
			}, {
				"mDataProp" : "orderPrice",
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
				"fnRender" : function(obj) {
					var accountStatus = obj.aData['type'];
					switch (accountStatus) {
					case '1':
						return "大马花消费";
						break;
					case '2':
						return "商家消费";
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
	callBack : function() {
		location.reload();
	}
};
