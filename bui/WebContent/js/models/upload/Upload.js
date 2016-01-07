/**
 * 用户信息管理
 */

var Upload = {
	imageUpload : function(title, getPic) {
		var diag = new Dialog();
		diag.Width = 400;
		diag.Height = 300;
		diag.Title = title;
		diag.URL = contextPath + "/upload/uploadImages.htm?";
		diag.MessageTitle = '';
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(getPic,diag);
		};
		diag.show();
	}
};
