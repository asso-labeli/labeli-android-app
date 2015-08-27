package com.app.labeli.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.labeli.interfaces.DataWithPicture;
import com.app.labeli.member.Member;

/**
 * > @Event
 *
 * Model for Event data
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class Event implements Parcelable, DataWithPicture{

	private Member author;
	private String name;
	private String description;
	private String pictureURL;
	private double created;
	private int status;
	private int type;
	private int id;

	public Event(Member author, String name, String description,
			String picture, double created, int status, int type, int id) {
		super();
		this.author = author;
		this.name = name;
		this.description = description;
		this.pictureURL = picture;
		this.created = created;
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

	@Override
	public String getPictureURL() {
		return pictureURL;
	}

	@Override
	public void setPictureURL(String picture) {
		this.pictureURL = picture;
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

	public double getCreated() {
		return created;
	}
	public void setCreated(double created) {
		this.created = created;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean getCounterVisibility() {
		// TODO Auto-generated method stub
		return true;
	}
	
	protected Event(Parcel in) {
		author = (Member) in.readValue(Member.class.getClassLoader());
		name = in.readString();
		description = in.readString();
		pictureURL = in.readString();
		created = in.readDouble();
		status = in.readInt();
		type = in.readInt();
		id = in.readInt();
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
		dest.writeDouble(created);
		dest.writeInt(status);
		dest.writeInt(type);
		dest.writeInt(id);
	}

	public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
		@Override
		public Event createFromParcel(Parcel in) {
			return new Event(in);
		}

		@Override
		public Event[] newArray(int size) {
			return new Event[size];
		}
	};


}
