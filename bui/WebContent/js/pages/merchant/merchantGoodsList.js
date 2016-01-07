/**
 * 
 */

jQuery(document).ready(function() {

	merchantCombox.requestData();

	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
	});
	
	jQuery('#excelBtn').click(function() {
		userTable.exportExcel();
	});

	$('#checkAll').click(function() {
		MerchantGoods.selectAll($(this));
	});
});

// 创建系统角色选择框
var merchantCombox = Merchant.createMerchantCombox('merchantId');

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : MerchantGoods.pageUrl,
	exportUrl : MerchantGoods.exportUrl,
	aoColumns : MerchantGoods.tableColumns,
	addButton : [ {
		value : "新增",
		onclick : 'MerchantGoods.addMerchant()'
	}, {
		value : "修改",
		onclick : 'MerchantGoods.editeMerchant()'
	} ],
	beforeload : function() {
		this.paraData = {
			"merchantId" : jQuery('#merchantId').val(),
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%'
		};
	}
});
