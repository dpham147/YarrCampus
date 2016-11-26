package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Utility implements Parcelable {
    private int mId;
    private String mType;
    private String mDesc;
    private Uri mImageUri;
    private float mGPSLat;
    private float mGPSLong;

    public Utility()
    {
    }

    public Utility(int id, String type, String desc, Uri imageURI, float gpsLat, float gpsLong)
    {
        mId = id;
        mType = type;
        mDesc = desc;
        mImageUri = imageURI;
        mGPSLat = gpsLat;
        mGPSLat = gpsLong;
    }

    public Utility(String type, String desc, Uri imageURI, float gpsLat, float gpsLong)
    {
        this(-1, type, desc, imageURI, gpsLat, gpsLong);
    }

    public Utility(String type, float gpsLat, float gpsLong)
    {
        this(-1, type, "", Uri.EMPTY, gpsLat, gpsLong);
    }

    private Utility(Parcel source)
    {
        mId = source.readInt();
        mType = source.readString();
        mDesc = source.readString();
        mImageUri = Uri.parse(source.readString());
        mGPSLat = source.readFloat();
        mGPSLong = source.readFloat();
    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public Uri getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
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
        dest.writeInt(mId);
        dest.writeString(mType);
        dest.writeString(mDesc);
        dest.writeString(mImageUri.toString());
        dest.writeFloat(mGPSLat);
        dest.writeFloat(mGPSLong);
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
