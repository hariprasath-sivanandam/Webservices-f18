package edu.northeastern.cs5610.models;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
	private int id = User.autoIncrement++;
	private String title;
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