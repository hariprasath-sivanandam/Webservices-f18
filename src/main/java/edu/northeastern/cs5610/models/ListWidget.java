package edu.northeastern.cs5610.models;

import javax.persistence.Entity;

@Entity
public class ListWidget extends Widget {
	private String listType;
	private String items;
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
}