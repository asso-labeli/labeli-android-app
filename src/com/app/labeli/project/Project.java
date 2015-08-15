package com.app.labeli.project;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.labeli.DataWithPicture;
import com.app.labeli.member.Member;

public class Project implements Parcelable, DataWithPicture{

	private Member author;
	private String name;
	private String description;
	private String pictureURL;
	private Date created;
	private Date lastEdited;
	private int status;
	private int type;
	private String id;
	
	public Project(Member author, String name, String description,
			String pictureURL, Date created, Date lastEdited, int status, int type, String id) {
		super();
		this.author = author;
		this.name = name;
		this.description = description;
		this.pictureURL = pictureURL;
		this.created = created;
		this.lastEdited = lastEdited;
		this.status = status;
		this.type = type;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "ItemProject [author=" + author + ", name=" + name
				+ ", description=" + description + ", pictureURL=" + pictureURL
				+ ", created=" + created + ", status=" + status + ", type="
				+ type + ", id=" + id +"]";
	}
	
	public Member getAuthor() {
		return author;
	}
	
	public void setAuthor(Member author) {
		this.author = author;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPictureURL() {
		return pictureURL;
	}
	
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
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
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	protected Project(Parcel in) {
        author = (Member) in.readValue(Member.class.getClassLoader());
        name = in.readString();
        description = in.readString();
        pictureURL = in.readString();
        created = new Date(in.readLong());
        lastEdited = new Date(in.readLong());
        status = in.readInt();
        type = in.readInt();
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(author);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(pictureURL);
        dest.writeLong(created.getTime());
        dest.writeLong(lastEdited.getTime());
        dest.writeInt(status);
        dest.writeInt(type);
        dest.writeString(id);
    }

    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };
	
}
