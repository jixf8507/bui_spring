/**
 * 
 */

jQuery(document).ready(function() {

	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
	});

	$('#checkAll').click(function() {
		Role.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : Merchant.pageUrl,
	exportUrl : Merchant.exportUrl,
	aoColumns : Merchant.tableColumns,
	addButton : [ {
		value : "新增",
		onclick : 'Merchant.addMerchant()'
	}, {
		value : "修改",
		onclick : 'Merchant.editeMerchant()'
	} ],
	beforeload : function() {
		this.paraData = {
			"corporation" : jQuery('#corporation').val() == '' ? '' : '%'
					+ jQuery('#corporation').val() + '%',
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%'
		};
	}
});
