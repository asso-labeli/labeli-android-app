package com.app.labeli;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * > @SurveyItem
 *
 * Model for SurveyItem data
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public class SurveyItem implements Parcelable{
	
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
	
	protected SurveyItem(Parcel in) {
        name = in.readString();
        created = new Date(in.readLong());
        lastEdited = new Date(in.readLong());
        id = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(created.getTime());
        dest.writeLong(lastEdited.getTime());
        dest.writeString(id);
    }

    public static final Parcelable.Creator<SurveyItem> CREATOR = new Parcelable.Creator<SurveyItem>() {
        @Override
        public SurveyItem createFromParcel(Parcel in) {
            return new SurveyItem(in);
        }

        @Override
        public SurveyItem[] newArray(int size) {
            return new SurveyItem[size];
        }
    };

}
