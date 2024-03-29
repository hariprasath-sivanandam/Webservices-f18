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
public class Topic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id = User.autoIncrement++;
	private String title;
	
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	@OneToMany(mappedBy="topic")
	private List<Widget> widgets = new ArrayList<Widget>();
	
	public Topic() {}
	
	public Topic(String title) {
		this.title = title;
	}
	
	public Topic(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public Topic(int id, String title, List<Widget> widgets) {
		this.id = id;
		this.title = title;
		this.widgets = widgets;
	}
	
	public List<Widget> getWidgets() {
		return widgets;
	}
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
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