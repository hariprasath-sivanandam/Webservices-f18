(function () {

    jQuery(main);
    var userService = new UserServiceClient();
    
    function main() {
        $('#registerBtn').click(registerUser);
        $('#loginBtn').click(loginHandler);
    }
    
    function loginHandler(){
    	window.location.href = '/login/login.template.client.html';
    }
    
    function registerUser() {
		var usernameVal = $('#usernameFld').val();
		var passwordVal = $('#passwordFld').val();
		var passwordVeirfyFld = $('#verifyPasswordFld').val();
		
		var user = {
			username : usernameVal,
			password : passwordVal
		};

		if (passwordVal == passwordVeirfyFld && passwordVal.length > 0
				&& passwordVeirfyFld.length > 0) {
			var user = JSON.stringify(user);
			userService.registerUser(user).then(function(response){
				if(response!=null)
		            window.location='/profile/profile.template.client.html';
		        else
		        	alert("UserName Alreay Taken!!! Try with another name.");		        
			})
		} 
		else
			alert("Password does not match. Please try again!!!");
	}
})();
