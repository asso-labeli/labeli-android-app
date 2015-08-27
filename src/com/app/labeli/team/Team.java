package com.app.labeli.team;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.labeli.interfaces.DataWithPicture;
import com.app.labeli.member.Member;

/**
 * > @Team
 *
 * Model for Team data
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class Team implements Parcelable, DataWithPicture{
	
	private Member author;
	private String name;
	private String description;
	private String pictureURL;
	private Date created;
	private int status;
	private int type;
	private int id;
	
	public Team(Member author, String name, String description,
			String pictureURL, Date created, int status, int type, int id) {
		super();
		this.author = author;
		this.name = name;
		this.description = description;
		this.pictureURL = pictureURL;
		this.created = created;
		this.status = status;
		this.type = type;
		this.id = id;
	}

	@Override
	public String toString() {
		return "ItemTeam [author=" + author + ", name=" + name
				+ ", description=" + description + ", pictureURL=" + pictureURL
				+ ", created=" + created + ", status=" + status + ", type="
				+ type + ", id=" + id + "]";
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
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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
	
	protected Team(Parcel in) {
        author = (Member) in.readValue(Member.class.getClassLoader());
        name = in.readString();
        description = in.readString();
        pictureURL = in.readString();
        created = new Date(in.readLong());
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
        dest.writeLong(created.getTime());
        dest.writeInt(status);
        dest.writeInt(type);
        dest.writeInt(id);
    }

    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

}
