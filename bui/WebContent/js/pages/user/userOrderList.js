/**
 * 
 */

jQuery(document).ready(function() {

	merchantCombox.requestData();

	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
	});

	$('#checkAll').click(function() {
		UserOrder.selectAll($(this));
	});
});

// 创建系统角色选择框
var merchantCombox = Merchant.createMerchantCombox('merchantId');

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
					"merchantId" : $('#merchantId').val()
				};
			}
		});
