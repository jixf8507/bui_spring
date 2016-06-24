/**
 * 用户信息管理
 */

var UserBillDetail = {
	pageUrl : contextPath + "/user/billDetail/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/user/billDetail/exportToExcel.htm?",
	tableColumns : [ // 设定各列宽度
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['repaymentTime'];
					return '<input style="width: 20px;" type="checkbox" name="id" id="'
							+ id + '" title="' + name + '" value="' + id + '">';
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "repaymentTime",
				"sClass" : "center"
			}, {
				"mDataProp" : "orderId",
				"sClass" : "center"
			}, {
				"fnRender" : function(obj) {
					var accountStatus = obj.aData['orderTable'];
					switch (accountStatus) {
					case 'user_order':
						return "消费";
						break;
					case 'user_borrow':
						return "提现";
						break;
					default:
						return "未生成还款账单";
						break;
					}
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "name",
				"sClass" : "center"
			}, {
				"mDataProp" : "mobilePhone",
				"sClass" : "center"
			}, {
				"mDataProp" : "capital",
				"sClass" : "center"
			}, {
				"mDataProp" : "interest",
				"sClass" : "center"
			}, {
				"mDataProp" : "totleCost",
				"sClass" : "center"
			}, {
				"fnRender" : function(obj) {
					var accountStatus = obj.aData['status'];
					switch (accountStatus) {
					case '0':
						return "未还清";
						break;
					case '1':
						return "已还清";
						break;
					default:
						return "未生成还款账单";
						break;
					}
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
	callBack : function() {
		location.reload();
	}
};
