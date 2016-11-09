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
