package com.app.labeli;

import java.util.Date;

public class SurveyItem {
	
	private String name;
	private Date created;
	private Date lastEdited;
	private String id;
	
	public SurveyItem(String name, Date created, Date lastEdited, String id) {
		super();
		this.name = name;
		this.created = created;
		this.lastEdited = lastEdited;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getLastEdited() {
		return lastEdited;
	}
	
	public void setLastEdited(Date lastEdited) {
		this.lastEdited = lastEdited;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	

}
