function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    this.registerUser = registerUser;
    this.getUser = getUser;
    this.updateUser = updateUser;
    this.logOutUser = logOutUser;
    this.loginUser = loginUser;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    
    var self = this;
   
    function findAllUsers() {
        return fetch("/api/user")
            .then(function (response) {
                return response.json();
            });
    }
    
    function deleteUser(id) {
		var url = "/api/user/" + id;
		return fetch(url, {
			method : 'delete'
		});
	}
    
    function loginUser(userObjStr) {
		return fetch('/api/login', {
			method : 'post',
			body : userObjStr,
			headers : {
				'Content-Type' : 'application/json'
			},
			'credentials' : 'include'
		});
    }
    
    function getUser() {
		return fetch('/api/profile', {
	      'credentials': 'include'
	    });
	}
	
	function logOutUser() {
		return fetch("/api/logout");
	}
	
	function updateUser(userObj,id) {
		console.log(userObj);
		console.log(id);
		return fetch("/api/user/" + id, {
		      method: 'put',
		      body: JSON.stringify(userObj),
		      'credentials': 'include',
		      headers: {
		        'content-type': 'application/json'
		      }
		});
	}
	
	function findUserById(user_id){
		return fetch('/api/findUserById/'+ user_id, {
		      'credentials': 'include'
		});
	}
    
    function registerUser(user){
    	//alert(user)
    	return fetch('/api/register', {
			method : 'post',
			body : user,
			headers : {
				'Content-Type' : 'application/json'
			},
			'credentials' : 'include'
    	}).then(function(response){
    		return JSON.stringify(response)
    	})
    }
}