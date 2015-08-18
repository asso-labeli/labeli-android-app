package com.app.labeli.project;

import java.util.Comparator;
import java.util.Date;

import com.app.labeli.member.Member;

public class Message implements Comparable<Message>{
	
	public Project project;
	public Member author;
	public Date created;
	public Date lastEdited;
	public String id;
	public String content;
	
	public Message(Project project, Member author, Date created, Date lastEdited,
			String id, String content) {
		super();
		this.project = project;
		this.author = author;
		this.created = created;
		this.lastEdited = lastEdited;
		this.id = id;
		this.content = content;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project p) {
		this.project = p;
	}

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(Message arg0) {
		return lastEdited.compareTo(arg0.getLastEdited());
	}

}
