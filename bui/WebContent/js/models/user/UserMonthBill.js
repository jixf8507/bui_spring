/**
 * 用户信息管理
 */

var UserMonthBill = {
	pageUrl : contextPath + "/user/monthBill/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/user/monthBill/exportToExcel.htm?",
	tableColumns : [ // 设定各列宽度
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['repaymentDate'];
					return '<input style="width: 20px;" type="checkbox" name="id" id="'
							+ id + '" title="' + name + '" value="' + id + '">';
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "repaymentDate",
				"sClass" : "center"
			}, {
				"mDataProp" : "userName",
				"sClass" : "center"
			}, {
				"mDataProp" : "mobilePhone",
				"sClass" : "center"
			}, {
				"mDataProp" : "curBalance",
				"sClass" : "center"
			}, {
				"mDataProp" : "paid",
				"sClass" : "center"
			}, {
				"mDataProp" : "lastLnterest",
				"sClass" : "center"
			}, {
				"mDataProp" : "curLnterest",
				"sClass" : "center"
			}, {
				"fnRender" : function(obj) {
					var accountStatus = obj.aData['type'];
					switch (accountStatus) {
					case '1':
						return "未还清";
						break;
					case '2':
						return "已还清";
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
		diag.URL = contextPath + "/user/monthBill/detail.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.show();
	},
	callBack : function() {
		location.reload();
	}
};
