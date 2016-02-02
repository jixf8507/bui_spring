/**
 * 用户信息管理
 */

var Upload = {
	tableId : '',
	tableName : '',
	pathType : '',
	fileType : '',
	imageUpload : function(title, getPic, tableId, tableName, pathType,
			fileType) {
		if (tableId) {
			this.tableId = tableId;
		}
		if (tableName) {
			this.tableName = tableName;
		}
		if (pathType) {
			this.pathType = pathType;
		}
		if (fileType) {
			this.fileType = fileType;
		}
		var url = contextPath + "/upload/uploadImages.htm?tableId="
				+ this.tableId + "&tableName=" + this.tableName + "&pathType="
				+ this.pathType + "&fileType=" + this.fileType;
		var diag = new Dialog();
		diag.Width = 400;
		diag.Height = 300;
		diag.Title = title;
		diag.URL = url;
		diag.MessageTitle = '';
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(getPic, diag);
		};
		diag.show();
	}
};
