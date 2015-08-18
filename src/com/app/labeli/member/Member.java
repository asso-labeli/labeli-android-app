package com.app.labeli.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.app.labeli.DataWithPicture;

import android.os.Parcel;
import android.os.Parcelable;

public class Member implements Parcelable, DataWithPicture{

	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String role;
	private String website;
	private String universityGroup;
	private String description;
	private String pictureURL;
	private Date created;
	private Date birthday;
	private String id; 
	/* Type
	 * 0 :
	 * 1 : Membre
	 * 2 : 
	 * 3 : Admin
	 */
	private int level;
	
	public static final int LEVEL_ADMIN = 3;
	public static final int LEVEL_MEMBER = 1;

	private boolean visible;

	public Member(String firstName, String lastName, String username,
			String email, String role, String website,
			String universityGroup, String description,
			String pictureURL, Date created, Date birthday, String id, int type) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.role = role;
		this.website = website;
		this.universityGroup = universityGroup;
		this.description = description;
		this.pictureURL = pictureURL;
		this.created = created;
		this.birthday = birthday;
		this.id = id;
		this.level = type;
		this.visible = true;
	}
	
	@Override
	public String toString() {
		return "ItemMember [firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", email=" + email + ", role="
				+ role + ", website=" + website + ", universityGroup="
				+ universityGroup + ", description=" + description
				+ ", pictureURL=" + pictureURL + ", created=" + created
				+ ", birthday=" + birthday + ", id=" + id + ", level=" + level
				+ ", visible=" + visible + "]";
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getUniversityGroup() {
		return universityGroup;
	}
	
	public void setUniversityGroup(String universityGroup) {
		this.universityGroup = universityGroup;
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
	
	public void setPictureURL(String picture) {
		this.pictureURL = picture;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisibility(boolean v){
		this.visible = v;
	}

	public Date getCreated() {
		return created;
	}
	
	public String getCreatedAsString(){
		return new SimpleDateFormat("dd/MM/yy", Locale.FRANCE).format(created);
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public String getBirthdayAsString(){
		return new SimpleDateFormat("dd/MM/yy", Locale.FRANCE).format(birthday);
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	protected Member(Parcel in) {
		firstName = in.readString();
		lastName = in.readString();
		username = in.readString();
		email = in.readString();
		role = in.readString();
		website = in.readString();
		universityGroup = in.readString();
		description = in.readString();
		pictureURL = in.readString();
		created = new Date(in.readLong());
		birthday = new Date(in.readLong());
		id = in.readString();
		level = in.readInt();
		visible = in.readByte() != 0x00;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(firstName);
		dest.writeString(lastName);
		dest.writeString(username);
		dest.writeString(email);
		dest.writeString(role);
		dest.writeString(website);
		dest.writeString(universityGroup);
		dest.writeString(description);
		dest.writeString(pictureURL);
		dest.writeLong(created.getTime());
		dest.writeLong(birthday.getTime());
		dest.writeString(id);
		dest.writeInt(level);
		dest.writeByte((byte) (visible ? 0x01 : 0x00));
	}

	public static final Parcelable.Creator<Member> CREATOR = new Parcelable.Creator<Member>() {
		@Override
		public Member createFromParcel(Parcel in) {
			return new Member(in);
		}

		@Override
		public Member[] newArray(int size) {
			return new Member[size];
		}
	};

}
