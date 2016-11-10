package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Building implements Parcelable {
    private int mId;
    private String mName;
    private String mDesc;
    private String mHours;
    private Uri mImageURI;
    private float mGPSLat;
    private float mGPSLong;

    public Building()
    {
    }

    public Building(int id, String name, String desc, String hours, Uri imageURI, float gpsLat, float gpsLong)
    {
        mId = id;
        mName = name;
        mDesc = desc;
        mHours = hours;
        mImageURI = imageURI;
        mGPSLat = gpsLat;
        mGPSLong = gpsLong;
    }

    public Building(String name, String desc, String hours, Uri imageUri, float gpsLat, float gpsLong)
    {
        this(-1, name, desc, hours, imageUri, gpsLat, gpsLong);
    }

    private Building(Parcel parcel)
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

    public String getmHours() {
        return mHours;
    }

    public void setmHours(String mHours) {
        this.mHours = mHours;
    }

    public Uri getmImageURI() {
        return mImageURI;
    }

    public void setmImageURI(Uri mImageURI) {
        this.mImageURI = mImageURI;
    }

    public float getmGPSLat() {
        return mGPSLat;
    }

    public void setmGPSLat(float mGPSLat) {
        this.mGPSLat = mGPSLat;
    }

    public float getmGPSLong() {
        return mGPSLong;
    }

    public void setmGPSLong(float mGPSLong) {
        this.mGPSLong = mGPSLong;
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
        public Building createFromParcel(Parcel source) {
            return new Building(source);
        }

        @Override
        public Building[] newArray(int size) {
            return new Building[size];
        }
    };
}
