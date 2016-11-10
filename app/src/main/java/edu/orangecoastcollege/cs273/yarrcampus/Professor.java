package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Professor implements Parcelable {

    private int mId;
    private String mName;
    private String mDesc;
    private String mClasses;
    private String mHours;
    private String mBuilding;
    private Uri mImageURI;

    public Professor()
    {
    }

    public Professor(int id, String name, String desc, String classes, String hours, String building, Uri imageUri)
    {
        mId = id;
        mName = name;
        mDesc = desc;
        mClasses = classes;
        mHours = hours;
        mBuilding = building;
        mImageURI = imageUri;
    }

    public Professor(String name, String desc, String classes, String hours, String building, Uri imageUri)
    {
        this(-1, name, desc, classes, hours, building, imageUri);
    }

    private Professor(Parcel source)
    {

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

    public String getmClasses() {
        return mClasses;
    }

    public void setmClasses(String mClasses) {
        this.mClasses = mClasses;
    }

    public String getmHours() {
        return mHours;
    }

    public void setmHours(String mHours) {
        this.mHours = mHours;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

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
}
