/**
 * 
 */

jQuery(document).ready(function() {

	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
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
			} ],
			beforeload : function() {
				this.paraData = {
					"mobilePhone" : $('#mobilePhone').val() == '' ? '' : '%'
							+ $('#mobilePhone').val() + '%',
					"name" : $('#name').val() == '' ? '' : '%'
							+ $('#name').val() + '%',
					"repaymentDate" : $('#repaymentDate').val() == '' ? ''
							: '%' + $('#repaymentDate').val() + '%'
				};
			}
		});
