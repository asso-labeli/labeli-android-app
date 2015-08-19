package com.app.labeli.project;

import com.app.labeli.member.Member;

/**
 * > @ProjectUser
 *
 * Model for ProjectUser data
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class ProjectUser {
	
	private double created;
	private int level;
	private Member author;
	private Project project;
	
	public ProjectUser(double created, int level, Member author, Project project) {
		super();
		this.created = created;
		this.level = level;
		this.author = author;
		this.project = project;
	}
	
	public double getCreated() {
		return created;
	}
	
	public void setCreated(double created) {
		this.created = created;
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
