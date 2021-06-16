function checkFormat(){
	const username = document.forms["loginForm"]["username"].value;
	// const userReg = /^\w{6,10}$/;
	if(username===""){
		document.getElementById("usernameWarning").innerHTML = "请输入用户名";
		return false;
	}else{
		document.getElementById("usernameWarning").innerHTML = "&nbsp;";
	}

	// if(!userReg.test(username)){
	// 	document.getElementById("usernameWarning").innerHTML = "请输入6-10位英文字母";
	// 	return false;
	// }else{
	// 	document.getElementById("usernameWarning").innerHTML = "";
	// }
	const pwdReg = /^\w{6,10}$/;
	const password = document.forms["loginForm"]["pwd"].value;
	if(!pwdReg.test(password)){
		document.getElementById("passwordWarning").innerHTML = "请输入6-10位字母与数字的组合";
		return false;
	}else{
		document.getElementById("passwordWarning").innerHTML = "&nbsp;";
	}
	return true;
}
function login(){
	return checkFormat();
}