/**
 * 系统工作人员
 */

var MerchantGoods = {
	pageUrl : contextPath + "/merchant/goods/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/merchant/goods/exportToExcel.htm?",
	tableColumns : [ // 设定各列宽度
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['name'];
					return '<input style="width: 20px;" type="checkbox" name="id" id="'
							+ id + '" title="' + name + '" value="' + id + '">';
				},
				"sClass" : "center"
			},
			{
				"mDataProp" : "name",
				"sClass" : "center"
			},
			{
				"mDataProp" : "price",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var img = obj.aData['img'];
					return '<img src="' + contextPath + '/' + img
							+ '"  alt="商品图片" width="50px;" height="50px;" />';
				},
				"sClass" : "center"
			}, {
				"mDataProp" : "merchantName",
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
		diag.Title = "新增商品";
		diag.URL = contextPath + "/merchant/goods/add.htm";
		diag.MessageTitle = "新增商品";
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
		diag.Title = "修改商品信息";
		diag.URL = contextPath + "/merchant/goods/edite.htm?id=" + value[0];
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
				'name' : {
					"required" : true
				},
				'price' : {
					"required" : true,
					"number" : true
				},
				'img' : "required",
				'des1' : "required"
			}
		});
	},
	// 提交用户表单
	submitForm : function(formObj, callBack) {
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/goods/submit.htm?',
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
	}
};
