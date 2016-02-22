/**
 * 
 */

jQuery(document).ready(function() {

	userTable.reloadData();

	// 点击查询按钮事件
	$('#queryBtn').click(function() {
		userTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		userTable.exportExcel();
	});

	$('#checkAll').click(function() {
		MerchantOrder.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables(
		{
			tableId : 'userTable',
			ajaxUrl : MerchantOrder.pageUrl,
			exportUrl : MerchantOrder.exportUrl,
			aoColumns : MerchantOrder.tableColumns,
			addButton : [ {
				value : "查看详情",
				onclick : 'MerchantOrder.detailUser()'
			}, {
				value : "查看分期还款账单明细",
				onclick : 'MerchantOrder.billDetail()'
			} ],
			beforeload : function() {
				this.paraData = {
					"mobilePhone" : $('#mobilePhone').val() == '' ? '' : '%'
							+ $('#mobilePhone').val() + '%',
					"name" : $('#name').val() == '' ? '' : '%'
							+ $('#name').val() + '%',
					"merchantName" : $('#merchantName').val() == '' ? '' : '%'
							+ $('#merchantName').val() + '%'
				};
			}
		});
