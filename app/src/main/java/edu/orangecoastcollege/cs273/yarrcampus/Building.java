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
    // TODO: add variables to store LAT and LONG from Google Maps API

    public Building()
    {
    }

    public Building(int id, String name, String desc, String hours, Uri imageURI)
    {
        mId = id;
        mName = name;
        mDesc = desc;
        mHours = hours;
        mImageURI = imageURI;
    }

    public Building(String name, String desc, String hours, Uri imageUri)
    {
        this(-1, name, desc, hours, imageUri);
    }

    private Building(Parcel parcel)
    {

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
