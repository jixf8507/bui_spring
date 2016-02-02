/**
 * 
 */

jQuery(document).ready(function() {
	$('#status,#type').chosen();

	userTable.reloadData();

	// 点击查询按钮事件
	$('#queryBtn').click(function() {
		userTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		userTable.exportExcel();
	});

	$('#checkAll').click(function() {
		UserOrder.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables(
		{
			tableId : 'userTable',
			ajaxUrl : UserOrder.pageUrl,
			exportUrl : UserOrder.exportUrl,
			aoColumns : UserOrder.tableColumns,
			addButton : [ {
				value : "查看详情",
				onclick : 'UserOrder.detailUser()'
			} ],
			beforeload : function() {
				this.paraData = {
					"mobilePhone" : $('#mobilePhone').val() == '' ? '' : '%'
							+ $('#mobilePhone').val() + '%',
					"name" : $('#name').val() == '' ? '' : '%'
							+ $('#name').val() + '%',
					"goodsName" : $('#goodsName').val() == '' ? '' : '%'
							+ $('#goodsName').val() + '%',
					"type" : $('#type').val(),
					"status" : $('#status').val()
				};
			}
		});
