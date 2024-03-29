package edu.northeastern.cs5610.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Lesson {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id = User.autoIncrement++;
	private String title;
	
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	@ManyToOne
	@JsonIgnore
	private Module module;
	
	@OneToMany(mappedBy="lesson")
	private List<Topic> topics = new ArrayList<Topic>();
	
	public Lesson() {}
	public Lesson(String title) {
		this.title = title;
	}
	
	public Lesson(int id, String title) {
	      this.id = id;
	      this.title = title;
	  }
	
	public Lesson(int id, String title, List<Topic> topics) {
      this.id = id;
      this.title = title;
      this.topics = topics;
	}
	
	public List<Topic> getTopics() {
		return topics;
	}
	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}