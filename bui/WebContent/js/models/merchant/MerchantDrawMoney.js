/**
 * 系统工作人员
 */

var MerchantDrawMoney = {
	pageUrl : contextPath + "/merchant/drawMoney/ajaxData.htm?t="
			+ new Date().getTime(),
	listUrl : contextPath + "/merchant/drawMoney/ajaxList.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/merchant/drawMoney/exportToExcel.htm?",
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
				"mDataProp" : "code",
				"sClass" : "center"
			}, {
				"mDataProp" : "name",
				"sClass" : "center"
			}, {
				"mDataProp" : "money",
				"sClass" : "center"
			}, {
				"mDataProp" : "bankName",
				"sClass" : "center"
			}, {
				"mDataProp" : "cardNumber",
				"sClass" : "center"
			}, {
				"fnRender" : function(obj) {
					var status = obj.aData['status'];
					switch (status) {
					case '1':
						return "完成提款";
						break;
					case '0':
						return "申请中";
						break;
					case '2':
						return "不通过";
						break;
					case '3':
						return "已取消";
						break;
					default:
						return "其它";
						break;
					}
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "createdTime",
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
		diag.Title = "申请提款";
		diag.URL = contextPath + "/merchant/drawMoney/add.htm";
		diag.MessageTitle = "申请提款";
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(thiz.callBack);
		};
		diag.show();
	},
	detail : function() {
		var value = this.getSelectValue();
		if (value.length == 0) {
			alert('请先选择要修改的记录');
			return;
		} else if (value.length > 1) {
			alert('只能选择一条记录');
			return;
		}
		var diag = new Dialog();
		diag.Width = 700;
		diag.Height = 500;
		diag.Title = "查看提款信息";
		diag.URL = contextPath + "/merchant/drawMoney/detail.htm?id="
				+ value[0];
		diag.MessageTitle = $('#' + value[0]).attr('title');
		diag.show();
	},
	callBack : function() {
		location.reload();
	},
	// 验证用户信息表单
	validate : function(formObj) {
		formObj.validate({
			rules : {
				'remarks' : {
					"required" : true
				},
				'money' : {
					"required" : true,
					"number" : true
				},
				'bankName' : "required",
				'cardNumber' : "required"
			}
		});
	},
	// 提交用户表单
	submitForm : function(formObj, callBack) {
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/drawMoney/submit.htm?',
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
