package edu.northeastern.cs5610.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5610.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
