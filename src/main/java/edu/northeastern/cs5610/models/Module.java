package edu.northeastern.cs5610.models;

import java.util.ArrayList;
import java.util.List;

public class Module {
	private int id = User.autoIncrement++;
	private String title;
	private List<Lesson> lessons = new ArrayList<Lesson>();
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