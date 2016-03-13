/**
 * 
 */

jQuery(document).ready(function() {
	$('#status').chosen();
	$("#repaymentDate").datepicker();
	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		userTable.exportExcel();
	});

	$('#checkAll').click(function() {
		UserMonthBill.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables(
		{
			tableId : 'userTable',
			ajaxUrl : UserMonthBill.pageUrl,
			exportUrl : UserMonthBill.exportUrl,
			aoColumns : UserMonthBill.tableColumns,
			addButton : [ {
				value : "查看详情",
				onclick : 'UserMonthBill.detailUser()'
			}, {
				value : "查看分期账单明细",
				onclick : 'UserMonthBill.billDetail()'
			} ],
			beforeload : function() {
				this.paraData = {
					"status" : jQuery('#status').val(),
					"mobilePhone" : $('#mobilePhone').val() == '' ? '' : '%'
							+ $('#mobilePhone').val() + '%',
					"name" : $('#name').val() == '' ? '' : '%'
							+ $('#name').val() + '%',
					"repaymentDate" : $('#repaymentDate').val() == '' ? ''
							: '%' + $('#repaymentDate').val() + '%'
				};
			}
		});
