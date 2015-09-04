package com.app.labeli.project;

import java.util.Date;

import com.app.labeli.interfaces.Data;
import com.app.labeli.member.Member;

/**
 * > @ProjectUser
 *
 * Model for ProjectUser data
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class ProjectUser implements Data{
	
	private Date created;
	private Date lastEdited;
	private int level;
	private Member author;
	private Project project;
	
	public ProjectUser(Date created, Date lastEdited, int level, Member author, Project project) {
		super();
		this.created = created;
		this.lastEdited = lastEdited;
		this.level = level;
		this.author = author;
		this.project = project;
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
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public Member getAuthor() {
		return author;
	}
	
	public void setAuthor(Member author) {
		this.author = author;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
}
