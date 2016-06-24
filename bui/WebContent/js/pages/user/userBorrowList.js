/**
 * 
 */

jQuery(document).ready(function() {
	$('#status').chosen();
	$("#beginDate,#endDate").datepicker();
	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		userTable.exportExcel();
	});

	$('#checkAll').click(function() {
		UserBorrow.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables(
		{
			tableId : 'userTable',
			ajaxUrl : UserBorrow.pageUrl,
			exportUrl : UserBorrow.exportUrl,
			aoColumns : UserBorrow.tableColumns,
			addButton : [ {
				value : "查看详情",
				onclick : 'UserBorrow.detail()'
			}, {
				value : "查看分期还款账单明细",
				onclick : 'UserBorrow.billDetail()'
			} ],
			beforeload : function() {
				this.paraData = {
					"status" : $('#status').val(),
					"monthBillUuid" : $('#monthBillUuid').val(),
					"mobilePhone" : $('#mobilePhone').val() == '' ? '' : '%'
							+ $('#mobilePhone').val() + '%',
					"name" : $('#name').val() == '' ? '' : '%'
							+ $('#name').val() + '%',
					"beginDate" : $('#beginDate').val(),
					"endDate" : $('#endDate').val() == '' ? '' : $('#endDate')
							.val()
							+ ' 23:59:59'
				};
			}
		});
