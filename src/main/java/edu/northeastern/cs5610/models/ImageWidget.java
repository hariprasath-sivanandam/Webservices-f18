package edu.northeastern.cs5610.models;

import javax.persistence.Entity;

@Entity
public class ImageWidget {
	private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

}
