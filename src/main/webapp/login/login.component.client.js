(function() {
	var userService = new UserServiceClient();
	jQuery(main);
	
	function main(){
		var loginBtn = $('#login');
		var signupBtn = $('#signup');
		loginBtn.click(loginHandler);
		signupBtn.click(signupHandler);
	}

	function signupHandler(){
		window.location.href = "/register/register.template.client.html";
	}
	function loginHandler() {
		var usernameVal = $('#username').val();
		var passwordVal =  $('#password').val();
		var userObj = {
			username : usernameVal,
			password : passwordVal
		};
		console.log(usernameVal)
		console.log("------")
		console.log(passwordVal)
		if (usernameVal.length> 0 && passwordVal.length> 0) {
			var userObjStr = JSON.stringify(userObj);
			userService.loginUser(userObjStr)
			.then(function (response){
				if(response!=null)
					window.location.href = "/profile/profile.template.client.html";
				else
					alert("Enter the Correct Credentials");
			});
		}
		else
			alert("Enter the User Credentials.If you are a New User Please Sign up")
	}

})();