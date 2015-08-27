package com.app.labeli.project;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.labeli.member.Member;

/**
 * > @Message
 *
 * Model for Message data
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class Message implements Comparable<Message>, Parcelable{
	
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
	
	protected Message(Parcel in) {
		super();
		this.project = (Project) in.readValue(Project.class.getClassLoader());
		this.author = (Member) in.readValue(Member.class.getClassLoader());
		this.created = new Date(in.readLong());
		this.lastEdited = new Date(in.readLong());
		this.id = in.readString();
		this.content = in.readString();
	}
	
	@Override
	public String toString() {
		return "Message [project=" + project + ", author=" + author
				+ ", created=" + created + ", lastEdited=" + lastEdited
				+ ", id=" + id + ", content=" + content + "]";
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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(project);
		dest.writeValue(author);
		dest.writeLong(created.getTime());
		dest.writeLong(lastEdited.getTime());
		dest.writeString(id);
		dest.writeString(content);
	}
	
	public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

}
