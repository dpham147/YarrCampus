package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Utility implements Parcelable {
    private int mId;
    private String mType;
    private String mDesc;
    private Uri mImageUri;

    public Utility()
    {
    }

    public Utility(int id, String type, String desc, Uri imageURI)
    {
        mId = id;
        mType = type;
        mDesc = desc;
        mImageUri = imageURI;
    }

    public Utility(String type, String desc, Uri imageURI)
    {
        this(-1, type, desc, imageURI);
    }

    private Utility(Parcel source)
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
        public Utility createFromParcel(Parcel source) {
            return new Utility(source);
        }

        @Override
        public Utility[] newArray(int size) {
            return new Utility[size];
        }
    };
}
