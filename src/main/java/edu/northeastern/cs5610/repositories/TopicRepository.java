
package edu.northeastern.cs5610.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5610.models.Topic;

public interface TopicRepository
	extends CrudRepository<Topic, Integer>{

}
