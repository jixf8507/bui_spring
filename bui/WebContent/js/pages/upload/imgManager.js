/**
 * 
 */

$(document).ready(function() {

	userTable.reloadData();

	$('#checkAll').click(function() {
		Img.selectAll($(this));
	});
});

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : Img.pageUrl,
	exportUrl : Img.exportUrl,
	aoColumns : Img.tableColumns,
	addButton : [ {
		value : "新增",
		onclick : 'Img.createUser()'
	}, {
		value : "删除",
		onclick : 'Img.deleteUser()'

	} ],
	beforeload : function() {
		this.paraData = {
			"tableId" : $('#tableId').val(),
			"tableName" : $('#tableName').val(),
			"fileType" : $('#fileType').val()
		};
	}
});
