/**
 * 系统工作人员
 */

var MerchantAccountRecord = {
	pageUrl : contextPath + "/merchant/accountRecord/ajaxData.htm?t="
			+ new Date().getTime(),
	listUrl : contextPath + "/merchant/accountRecord/ajaxList.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/merchant/accountRecord/exportToExcel.htm?",
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
				"mDataProp" : "corporation",
				"sClass" : "center"
			}, {
				"mDataProp" : "price",
				"sClass" : "center"
			}, {
				"mDataProp" : "type",
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
	}
};
