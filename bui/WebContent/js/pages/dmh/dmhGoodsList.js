/**
 * 
 */

jQuery(document).ready(function() {

	$('#status,#isTop').chosen();

	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		userTable.exportExcel();
	});

	$('#checkAll').click(function() {
		DmhGoods.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : DmhGoods.pageUrl,
	exportUrl : DmhGoods.exportUrl,
	aoColumns : DmhGoods.tableColumns,
	addButton : [ {
		value : "新增",
		onclick : 'DmhGoods.addMerchant()'
	}, {
		value : "修改",
		onclick : 'DmhGoods.editeMerchant()'
	}, {
		value : "商品详情图片",
		onclick : 'DmhGoods.picsManager()'
	}, {
		value : "下架",
		onclick : 'DmhGoods.deleteMerchant()'

	} ],
	beforeload : function() {
		this.paraData = {
			"status" : jQuery('#status').val(),
			"isTop" : jQuery('#isTop').val(),
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%'
		};
	}
});
