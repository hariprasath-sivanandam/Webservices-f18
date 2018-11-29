package edu.northeastern.cs5610.models;

import java.util.ArrayList;
import java.util.List;

public class User {
	public static int autoIncrement = 0;
	private int id = autoIncrement++;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String role;
	private String dob;
	private List<Course> courses = new ArrayList<Course>();
	
	public User() {}
	public User(String username) {
		this.username = username;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String username, String password) {
		this(username, password);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(int id, String username, String password, String email, String phoneNumber, String role, String dob) {
		  this.id = id;
		  this.username = username;
		  this.password = password;
		  this.email = email;
		  this.phoneNumber = phoneNumber;
		  this.role = role;
		  this.dob = dob;
	}
	
	public User(int id, String username, String firstName, String lastName, String password, String email, String phoneNumber, String role, String dob, List<Course> courses) {
		  this.id = id;
		  this.username = username;
		  this.password = password;
		  this.firstName = firstName;
		  this.lastName = lastName;
		  this.email = email;
		  this.phoneNumber = phoneNumber;
		  this.role = role;
		  this.dob = dob;
		  this.courses = courses;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}

	public List<Course> getCourses() {
		return courses;
	}
	
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}