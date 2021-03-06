/**
 * 系统工作人员
 */

var Merchant = {
	pageUrl : contextPath + "/merchant/ajaxData.htm?t=" + new Date().getTime(),
	listUrl : contextPath + "/merchant/ajaxList.htm?t=" + new Date().getTime(),
	exportUrl : contextPath + "/merchant/exportToExcel.htm?",
	tableColumns : [ // 设定各列宽度
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['name'];
					return '<input style="width: 20px;" type="checkbox" name="id" id="'
							+ id + '" title="' + name + '" value="' + id + '">';
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "id",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var img = obj.aData['img'];
					return '<img src="' + contextPath + img
							+ '"  alt="图片" width="50px;" height="50px;" />';
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "code",
				"sClass" : "center"
			}, {
				"mDataProp" : "name",
				"sClass" : "center"
			}, {
				"mDataProp" : "address",
				"sClass" : "center"
			}, {
				"mDataProp" : "corporation",
				"sClass" : "center"
			}, {
				"mDataProp" : "mobilePhone",
				"sClass" : "center"
			}, {
				"mDataProp" : "totalMoney",
				"sClass" : "center"
			}, {
				"mDataProp" : "freezeMoney",
				"sClass" : "center"
			}, {
				"fnRender" : function(obj) {
					var status = obj.aData['status'];
					switch (status) {
					case '1':
						return "正常";
						break;
					case '0':
						return "冻结";
						break;
					default:
						return "其它";
						break;
					}
				},
				"sClass" : "center"
			} ],
	selectAll : function(thiz) {
		var bool = thiz.attr("checked") ? true : false;
		$("input:[name=id]").each(function(index) {
			$(this).attr('checked', bool);
		});
	},
	getSelectValue : function() {
		var selValue = [];
		$("input:[name=id]checkbox:checked").each(function(index) {
			selValue.push($(this).val());
		});
		return selValue;
	},
	// 打开创建用户对话框
	addMerchant : function() {
		var thiz = this;
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "新增商家";
		diag.URL = contextPath + "/merchant/add.htm";
		diag.MessageTitle = "新增商家";
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
	},
	editeMerchant : function() {
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择要修改的记录');
			return;
		} else if (value.length > 1) {
			alert('只能选择一条记录');
			return;
		}
		var thiz = this;
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "修改商家信息";
		diag.URL = contextPath + "/merchant/edite.htm?id=" + value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
	},
	callBack : function() {
		location.reload();
	},
	// 验证用户信息表单
	validate : function(formObj) {
		formObj.validate({
			rules : {
				'img' : {
					"required" : true
				},
				'name' : {
					"required" : true
				},
				'mobilePhone' : {
					"required" : true,
					"phone" : true
				},
				'address' : "required",
				'code' : "required",
				'corporation' : "required"
			}
		});
	},
	// 提交用户表单
	submitForm : function(formObj, callBack) {
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/submit.htm?',
			data : formObj.serialize(),
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
					Dialog.alert("提示：" + data.info);
				}
			}
		});
	},
	createMerchantCombox : function(roleId) {
		var roleCombox = new Combox({
			id : roleId,
			url : this.listUrl,
			cText : 'name',
			cValue : 'id',
			emptyText : '请选择商家'
		});
		return roleCombox;
	}
};
