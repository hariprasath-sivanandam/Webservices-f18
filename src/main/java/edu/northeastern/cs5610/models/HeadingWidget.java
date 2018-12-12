package edu.northeastern.cs5610.models;

import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget{
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}