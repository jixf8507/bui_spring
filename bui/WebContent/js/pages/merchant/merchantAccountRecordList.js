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
		MerchantAccountRecord.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : MerchantAccountRecord.pageUrl,
	exportUrl : MerchantAccountRecord.exportUrl,
	aoColumns : MerchantAccountRecord.tableColumns,
	beforeload : function() {
		this.paraData = {
			"code" : jQuery('#code').val() == '' ? '' : '%'
					+ jQuery('#code').val() + '%',
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%'
		};
	}
});
