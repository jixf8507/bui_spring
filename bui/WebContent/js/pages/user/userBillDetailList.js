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
		UserBillDetail.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables(
		{
			tableId : 'userTable',
			ajaxUrl : UserBillDetail.pageUrl,
			exportUrl : UserBillDetail.exportUrl,
			aoColumns : UserBillDetail.tableColumns,
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
