/**
 * 
 */

jQuery(document).ready(function() {

	roleCombox.requestData();

	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.reloadData();
	});
	
	$('#checkAll').click(function() {
		User.selectAll($(this));
	});
});

// 创建系统角色选择框
var roleCombox = Role.createRoleCombox('roleId');

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : User.pageUrl,
	exportUrl : User.exportUrl,
	aoColumns : User.tableColumns,
	addButton : [ {
		value : "新增",
		onclick : 'User.createUser()'
	}, {
		value : "修改",
		onclick : 'User.editeUser()'
	}, {
		value : "删除",
		onclick : 'User.deleteUser()'
	} ],
	beforeload : function() {
		this.paraData = {
			"code" : jQuery('#code').val() == '' ? '' : '%'
					+ jQuery('#code').val() + '%',
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%',
			"roleId" : jQuery('#roleId').val()
		};
	}
});
