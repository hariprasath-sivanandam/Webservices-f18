package edu.northeastern.cs5610.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	@Transient
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