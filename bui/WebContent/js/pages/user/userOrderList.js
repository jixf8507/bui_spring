/**
 * 
 */

jQuery(document).ready(function() {
	$('#status').chosen();

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
			}, {
				value : "查看分期还款账单明细",
				onclick : 'UserOrder.billDetail()'
			} ],
			beforeload : function() {
				this.paraData = {
					"mobilePhone" : $('#mobilePhone').val() == '' ? '' : '%'
							+ $('#mobilePhone').val() + '%',
					"name" : $('#name').val() == '' ? '' : '%'
							+ $('#name').val() + '%',
					"goodsName" : $('#goodsName').val() == '' ? '' : '%'
							+ $('#goodsName').val() + '%',					
					"status" : $('#status').val()
				};
			}
		});
