package edu.northeastern.cs5610.models;

import javax.persistence.Entity;

@Entity
public class LinkWidget extends Widget{
	private String linkName;

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
}