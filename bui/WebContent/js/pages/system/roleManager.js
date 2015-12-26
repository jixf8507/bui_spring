/**
 * 
 */

$(document).ready(function() {
	
	userTable.reloadData();

	// 点击查询按钮事件
	$('#queryBtn').click(function() {
		userTable.reloadData();
	});

	$('#checkAll').click(function() {
		Role.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : Role.pageUrl,
	exportUrl : Role.exportUrl,
	aoColumns : Role.tableColumns,
	addButton : [ {
		value : "新增",
		onclick : 'Role.createUser()'
	}, {
		value : "修改",
		onclick : 'Role.editeUser()'
	}, {
		value : "删除",
		onclick : 'Role.deleteUser()'
	}, {
		value : "菜单设置",
		onclick : 'Role.meneSetting()'
	} ],
	beforeload : function() {
		this.paraData = {
			"roleName" : jQuery('#roleName').val() == '' ? '' : '%'
					+ jQuery('#roleName').val() + '%'
		};
	}
});
