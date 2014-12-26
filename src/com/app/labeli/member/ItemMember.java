package com.app.labeli.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemMember implements Parcelable{

	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String universityGroup;
	private String description;
	private String pictureURL;
	private double created;
	private double birthday;
	private int id; 
	/* Type
	 * 0 :
	 * 1 : Membre
	 * 2 : 
	 * 3 : Admin
	 */
	private int type;

	private boolean visible;

	public ItemMember(String firstName, String lastName, String email,
			String role, String universityGroup, String description,
			String pictureURL, double created, double birthday, int id, int type) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.universityGroup = universityGroup;
		this.description = description;
		this.pictureURL = pictureURL;
		this.created = created;
		this.birthday = birthday;
		this.id = id;
		this.type = type;
		this.visible = true;
	}
	@Override
	public String toString() {
		return "ItemMember [firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", universityGroup="
				+ universityGroup + ", description=" + description
				+ ", pictureURL=" + pictureURL + ", id=" + id + ", type="
				+ type + ", visible=" + visible + "]";
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isVisible(){
		return visible;
	}
	public void setVisibility(boolean v){
		this.visible = v;
	}

	public double getCreated() {
		return created;
	}
	public String getCreatedAsString(){
		Date date = new Date ();
		date.setTime((long) created);
		return new SimpleDateFormat("dd/MM/yy", Locale.FRANCE).format(date);
	}
	public void setCreated(double created) {
		this.created = created;
	}
	public double getBirthday() {
		return birthday;
	}
	public String getBirthdayAsString(){
		Date date = new Date ();
		date.setTime((long) birthday);
		return new SimpleDateFormat("dd/MM/yy", Locale.FRANCE).format(date);
	}
	public void setBirthday(double birthday) {
		this.birthday = birthday;
	}
	protected ItemMember(Parcel in) {
		firstName = in.readString();
		lastName = in.readString();
		email = in.readString();
		role = in.readString();
		universityGroup = in.readString();
		description = in.readString();
		pictureURL = in.readString();
		created = in.readDouble();
		birthday = in.readDouble();
		id = in.readInt();
		type = in.readInt();
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
		dest.writeString(email);
		dest.writeString(role);
		dest.writeString(universityGroup);
		dest.writeString(description);
		dest.writeString(pictureURL);
		dest.writeDouble(created);
		dest.writeDouble(birthday);
		dest.writeInt(id);
		dest.writeInt(type);
		dest.writeByte((byte) (visible ? 0x01 : 0x00));
	}

	public static final Parcelable.Creator<ItemMember> CREATOR = new Parcelable.Creator<ItemMember>() {
		@Override
		public ItemMember createFromParcel(Parcel in) {
			return new ItemMember(in);
		}

		@Override
		public ItemMember[] newArray(int size) {
			return new ItemMember[size];
		}
	};

}
