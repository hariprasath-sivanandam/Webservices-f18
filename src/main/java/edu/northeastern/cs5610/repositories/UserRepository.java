package edu.northeastern.cs5610.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5610.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{	
	@Query("SELECT user FROM User user WHERE user.username=:username and user.password=:password")
	public User findUserByCredentials(@Param("username") String u,@Param("password") String p);

}