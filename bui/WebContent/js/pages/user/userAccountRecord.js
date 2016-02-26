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
		UserAccountRecord.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : UserAccountRecord.pageUrl,
	exportUrl : UserAccountRecord.exportUrl,
	aoColumns : UserAccountRecord.tableColumns,	
	beforeload : function() {
		this.paraData = {
			"mobilePhone" : jQuery('#mobilePhone').val() == '' ? '' : '%'
					+ jQuery('#mobilePhone').val() + '%',
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%',
			"idCard" : jQuery('#idCard').val() == '' ? '' : '%'
					+ jQuery('#idCard').val() + '%'
		};
	}
});
