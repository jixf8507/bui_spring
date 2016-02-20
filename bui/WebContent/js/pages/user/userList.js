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
		User.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : User.pageUrl,
	exportUrl : User.exportUrl,
	aoColumns : User.tableColumns,
	addButton : [ {
		value : "查看详情",
		onclick : 'User.detailUser()'
	} ],
	beforeload : function() {
		this.paraData = {
			"mobilePhone" : jQuery('#mobilePhone').val() == '' ? '' : '%'
					+ jQuery('#mobilePhone').val() + '%',
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%',
			"idCard" : jQuery('#idCard').val() == '' ? '' : '%'
					+ jQuery('#idCard').val() + '%',
			"status" : $('#status').val()
		};
	}
});
