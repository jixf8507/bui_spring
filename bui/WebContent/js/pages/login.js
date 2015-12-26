/**
 * 
 */

$(function() {
	$('#submit').click(function() {
		submitLogin();
	});
});

var submitLogin = function() {

	$.ajax({
		type : "post",
		url : contextPath + '/login/submit.htm?',
		data : {
			username : $('#username').val(),
			password : $('#password').val(),
		},
		async : true,
		dataType : 'json',
		success : function(data) {			
			if (data.success) {
				location.href = contextPath + '/main/index.htm?';
			} else {
				alert(data.info);
			}
		},
		error : function() {
			alert('登录失败');
		}
	});
};