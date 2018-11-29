package edu.northeastern.cs5610.models;

import java.util.ArrayList;
import java.util.List;


public class Course {
	private int id = User.autoIncrement++;
	private String title;
    private List<Module> modules = new ArrayList<>();
    
    public Course() {}
    
    public Course(String title) {
        this.title = title;
    }
    
    public Course(int i, String string) {
		id = i; title = string;
	}
    
    public Course(int id, String title, List<Module> modules) {
        this.id = id;
        this.title = title;
        this.modules = modules;
    }
    
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }   
}