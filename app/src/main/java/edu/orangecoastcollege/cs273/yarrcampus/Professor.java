package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Professor implements Parcelable {

    private int mId;
    private String mName;
    private String mDesc;
    private String mOfficeHours;
    private String mBuilding;
    private float mOfficeLat;
    private float mOfficeLong;
    private String mEmail;
    private String mPhoneNumber;
    private Uri mImageURI;

    public Professor()
    {
        mName = "";
        mDesc = "";
        mOfficeHours = "";
        mBuilding = "";
        mOfficeLat = 0.0f;
        mOfficeLong = 0.0f;
        mImageURI = Uri.parse("getUriToResource(this, R.drawable.empty_profile_pic)");
    }

    public Professor(int id, String name, String desc, String hours, String building, Uri imageUri, float latitude, float longitude)
    {
        mId = id;
        mName = name;
        mDesc = desc;
        mOfficeHours = hours;
        mBuilding = building;
        mImageURI = imageUri;
        mOfficeLat = latitude;
        mOfficeLong = longitude;

    }

    public Professor(String name, String desc, String hours, String building, Uri imageUri, float latitude, float longitude)
    {
        this(-1, name, desc,hours, building, imageUri, latitude, longitude);
    }

    private Professor(Parcel parcel)
    {
        mId = parcel.readInt();
        mName = parcel.readString();
        mDesc = parcel.readString();
        mOfficeHours = parcel.readString();
        mBuilding = parcel.readString();
        mImageURI = Uri.parse(parcel.readString());
        mOfficeLat = parcel.readFloat();
        mOfficeLong = parcel.readFloat();
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getmOfficeHours() {
        return mOfficeHours;
    }

    public void setmOfficeHours(String mOfficeHours) {
        this.mOfficeHours = mOfficeHours;
    }

    public String getmBuilding() {
        return mBuilding;
    }

    public void setmBuilding(String mBuilding) {
        this.mBuilding = mBuilding;
    }

    public Uri getmImageURI() {
        return mImageURI;
    }

    public void setmImageURI(Uri mImageURI) {
        this.mImageURI = mImageURI;
    }

    public String getAllDetails()
    {
        String details = mDesc + "\n"
                + mOfficeHours + "\n"
                + mBuilding;
        return details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mDesc);
        dest.writeString(mOfficeHours);
        dest.writeString(mBuilding);
        dest.writeString(String.valueOf(mImageURI));
        dest.writeFloat(mOfficeLat);
        dest.writeFloat(mOfficeLong);
    }

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public Professor createFromParcel(Parcel source) {
            return new Professor(source);
        }

        @Override
        public Professor[] newArray(int size) {
            return new Professor[size];
        }
    };

    public float getmOfficeLat() {
        return mOfficeLat;
    }

    public void setmOfficeLat(float mOfficeLat) {
        this.mOfficeLat = mOfficeLat;
    }

    public float getmOfficeLong() {
        return mOfficeLong;
    }

    public void setmOfficeLong(float mOfficeLong) {
        this.mOfficeLong = mOfficeLong;
    }
}
