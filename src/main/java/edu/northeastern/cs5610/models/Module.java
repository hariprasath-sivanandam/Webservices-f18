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
public class Module {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id = User.autoIncrement++;
	private String title;
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	@OneToMany(mappedBy="module")
	private List<Lesson> lessons = new ArrayList<Lesson>();
	
	@ManyToOne
	@JsonIgnore
	private Course course;
	public Module() {}
	public Module(String title) {
		this.title = title;
	}
	
	public Module(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public Module(int id, String title, List<Lesson> lessons) {
		this.id = id;
		this.title = title;
		this.lessons = lessons;
	}
	
	public List<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
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