(function(){
	 var userService = new UserServiceClient();
	 var $username,$phone,$email,$role,$dob;
	 var $updateBtn,$logOutBtn;
	 var currentUser = null;
	 jQuery(main);
	 
	function main(){
		$username 	= $("#userName");
		$phone 		= $("#phone");
		$email 		= $("#email");
		$role 		= $("#role");
		$dob 		= $("#dob");
		$updateBtn	= $("#updateBtn");
		$logOutBtn	= $("#logoutBtn");
		profileLoad().then(populateContent);
		$updateBtn.click(updateUser);
		$logOutBtn.click(logout);
	}
	
	function populateContent(user){
		currentUser = user;
		$username.val(user.username);
		$phone.val(user.phoneNo);
		$email.val(user.email);
		$role.val(user.role);
		if(user.dateOfBirth)
			$dob.val(user.dateOfBirth.slice(0,10));
		else
			$dob.val(user.dateOfBirth);
	}
	
	function profileLoad() {
	    return userService.getUser()
	    	.then(function (response) {
	    		return response.json();
	    	});
	}
	
	function logout(){
		userService.logOutUser().then(function(){
		window.location.href = "/login/login.template.client.html";});
	}
	
	function updateUser() {
	    var userObj = {
	    		username:$username.val(),
	    		phoneNo: $phone.val(),
	    		email: $email.val(),
	    		role: $role.val(),
	    		dateOfBirth: $dob.val()
	    };
	    userService.updateUser(userObj,currentUser.id);
	}
})();