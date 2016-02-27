/**
 * 
 */

jQuery(document).ready(function() {
	$('#status').chosen();
	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		userTable.exportExcel();
	});

	$('#checkAll').click(function() {
		MerchantDrawMoney.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : MerchantDrawMoney.pageUrl,
	exportUrl : MerchantDrawMoney.exportUrl,
	aoColumns : MerchantDrawMoney.tableColumns,
	addButton : [ {		
		value : "查看详情",
		onclick : 'MerchantDrawMoney.detail()'
	}, {
		value : "审核",
		onclick : 'MerchantDrawMoney.check()'
	} ],
	beforeload : function() {
		this.paraData = {
			"corporation" : jQuery('#corporation').val() == '' ? '' : '%'
					+ jQuery('#corporation').val() + '%',
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%',
			"status" : jQuery('#status').val()
		};
	}
});
