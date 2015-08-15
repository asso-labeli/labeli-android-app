package com.app.labeli;

import java.util.ArrayList;
import java.util.Date;

import com.app.labeli.member.Member;

public class Survey {

	private String description;
	private String name;
	private int state;
	private int numberChoices;
	private Date created;
	private Date lastEdited;
	private Member author;
	private String id;
	private Vote vote;
	private ArrayList<SurveyItem> items;

	public Survey(String description, String name, int state,
			int numberChoices, Date created, Date lastEdited,
			Member author, String id, Vote vote, ArrayList<SurveyItem> items) {
		this.description = description;	
		this.name = name;
		this.state = state;
		this.numberChoices = numberChoices;
		this.created = created;
		this.lastEdited = lastEdited;
		this.author = author;
		this.id = id;
		this.vote = vote;
		this.items = items;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getNumberChoices() {
		return numberChoices;
	}

	public void setNumberChoices(int numberChoices) {
		this.numberChoices = numberChoices;
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

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public ArrayList<SurveyItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<SurveyItem> items) {
		this.items = items;
	}

}
