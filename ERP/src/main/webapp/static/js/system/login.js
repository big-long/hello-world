//获取验证码
function getCaptcha() {
	$("img").attr("src",
			"http://localhost:8080/ERP/system/captcha.jpg?ran=" + Math.random());
}