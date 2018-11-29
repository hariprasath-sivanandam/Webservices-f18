(function() {

	var userServiceClient = new UserServiceClient();
	var $username = $("#tUsername");
	var $password = $("#tPassword");
	var $firstName = $("#tFirstName");
	var $lastName = $("#tLastName");
	var $role = $("#tRole");
	var $dob = $("#tDate");
	var $updateBtn = $("#updateBtn");
	var $createBtn = $("#createBtn");
	var $userFlds = $("#userFlds");
	var idUpdate = null;
	init();
	function init() {
		userServiceClient.findAllUsers().then(renderUsers);
		var userGlobal = null;
		$updateBtn.click(updateUser);
		$createBtn.click(createUser);
	}

	function renderUsers(users) {
		console.log(users);
		userGlobal = users;
		var tbody = $('tbody');
		tbody.empty();
		for (var i = 0; i < users.length; i++) {
			var user = users[i];

			var tr = $('<tr>');
			var td = $('<td>');
			td.append(user.username);
			tr.append(td);

			td = $('<td>');
			td.append('*******');
			tr.append(td);

			td = $('<td>');
			td.append(user.firstname);
			tr.append(td);

			td = $('<td>');
			td.append(user.lastname);
			tr.append(td);

			td = $('<td>');
			dob = user.dateOfBirth
			if (user.dateOfBirth != null)
				dob = user.dateOfBirth.slice(0, 10)

			td.append(dob);
			tr.append(td);

			td = $('<td>');
			td.append(user.role);
			tr.append(td);

			td = $('<td>');
			var deleteBtn = $('<button class="btn btn-outline-danger btn-sm">DELETE</button>');
			deleteBtn.click(deleteUser);
			deleteBtn.attr('id', user.id);
			td.append(deleteBtn);
			tr.append(td);

			var editBtn = $('<button class="btn btn-outline-dark btn-sm">Edit</button>');
			editBtn.click(editUser);
			editBtn.attr('index', i);
			editBtn.attr('id', user.id);
			td.append(editBtn);
			tr.append(td);

			tr.appendTo(tbody);
		}
	}

	function deleteUser(event) {
		var $button = $(event.currentTarget);
		var id = $button.attr('id');
		userServiceClient.deleteUser(id).then(function() {
			userServiceClient.findAllUsers().then(renderUsers);
		});
	}

	function editUser(event) {
		var $button = $(event.currentTarget);
		var idx = $button.attr('index');
		idUpdate = $button.attr('id');
		$username.val(userGlobal[idx].username);
		$password.val();
		$firstName.val(userGlobal[idx].firstname);
		$lastName.val(userGlobal[idx].lastname);
		$role.val(userGlobal[idx].role);
		dob = userGlobal[idx].dateOfBirth
		if (userGlobal[idx].dateOfBirth != null)
			dob = userGlobal[idx].dateOfBirth.slice(0, 10)
		$dob.val(dob);

	}

	function updateUser() {
		var userObj = {
			username : $username.val(),
			password : $password.val(),
			firstname : $firstName.val(),
			lastname : $lastName.val(),
			role : $role.val(),
			dateOfBirth : $dob.val()
		};

		userServiceClient.updateUser(userObj, idUpdate).then(function() {
			$username.val("");
			$password.val("");
			$firstName.val("");
			$lastName.val("");
			$role.val("");
			$dob.val("");
			userServiceClient.findAllUsers().then(renderUsers);
		});

	}

	function createUser() {
		var userObj = {
			username : $username.val(),
			password : $password.val(),
			firstname : $firstName.val(),
			lastname : $lastName.val(),
			role : $role.val(),
			dateOfBirth : $dob.val()
		};
		userServiceClient.registerUser(userObj).then(function() {
			$username.val("");
			$password.val("");
			$firstName.val("");
			$lastName.val("");
			$role.val("");
			$dob.val("");
			userServiceClient.findAllUsers().then(renderUsers);
		});
	}
})();