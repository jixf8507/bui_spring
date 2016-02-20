/**
 * 
 */

jQuery(document).ready(function() {

	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		userTable.exportExcel();
	});

	$('#checkAll').click(function() {
		Merchant.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : MerchantDrawMoney.pageUrl,
	exportUrl : MerchantDrawMoney.exportUrl,
	aoColumns : MerchantDrawMoney.tableColumns,
	addButton : [ {
		value : "新增",
		onclick : 'MerchantDrawMoney.addMerchant()'
	}, {
		value : "查看详情",
		onclick : 'Merchant.detail()'
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
