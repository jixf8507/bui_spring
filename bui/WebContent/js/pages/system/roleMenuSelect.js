/**
 * 
 */

$(document).ready(function() {

	initRoleMenus($('#roleId').val());
});

/**
 * 初始化系统角色菜单
 */
var initMenuTree = function() {
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/system/roleMenu/ajaxMenus.htm?',
		data : {
			't' : new Date().getTime()
		},
		async : false,
		dataType : 'json',
		error : function(request) {

		},
		success : function(data) {
			jQuery.each(data, function(index, module) {
				var id = module['id'];
				var pid = module['pid'];
				var cbox = '<input type="checkBox" name="moduleId" id="' + id
						+ '" value="' + id + '" class="' + pid
						+ '" onclick="selectBox(this)">';
				d.add(id, pid, cbox + module['moduleName'], '');
			});
			document.write(d);
		}
	});
};

initMenuTree();

var selectBox = function(thiz) {
	var moduleId = $(thiz).val();
	selectSon(thiz);
	selectParent(moduleId);
};

// 向下选择,子状态与负状态保证一致
var selectSon = function(thiz) {
	var moduleId = $(thiz).val();
	$("." + moduleId).each(function() {
		$(this).attr('checked', thiz.checked);
		selectSon(this);
	});
};

// 向上选择
var selectParent = function(id) {
	var pid = $('#' + id).attr("class");
	if (pid > 0) {
		var parentObj = $('#' + pid);
		var checked = false;
		$("." + pid).each(function() {
			if (this.checked) {
				checked = true;
			}
		});
		parentObj.attr('checked', checked);
		selectParent(pid);
	}
};

/**
 * 初始角色菜单
 */
var initRoleMenus = function(roleId) {
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/system/roleMenu/ajaxRoleMenus.htm?',
		data : {
			'roleId' : roleId,
			't' : new Date().getTime()
		},
		async : false,
		dataType : 'json',
		error : function(request) {

		},
		success : function(data) {
			jQuery.each(data, function(index, module) {
				var menuId = module['menuId'];
				$('#' + menuId).attr("checked", "checked");
			});
		}
	});
};

var submit = function(callBack) {
	var modelIds = [];
	$("input[name=moduleId]").each(function() {
		if (this.checked) {
			modelIds.push($(this).val());
		}
	});
	$.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/system/roleMenu/saveRoleMenus.htm?',
		data : {
			roleId : $('#roleId').val(),
			mIds : JSON.stringify(modelIds)
		},
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("提示：操作失败");
		},
		success : function(data) {
			if (data.success) {
				Dialog.alert("提示：操作成功", function() {
					callBack();
				});
			} else {
				Dialog.alert("提示：操作失败");
			}
		}
	});
};