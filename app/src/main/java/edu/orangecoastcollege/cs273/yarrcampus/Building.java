package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Building implements Parcelable {
    private int mId;
    private String mName;
    private String mCode;
    private String mHours;
    private Uri mImageURI;
    private float mGPSLat;
    private float mGPSLong;

    public Building()
    {
    }

    public Building(int id, String name, String code, String hours, Uri imageURI, float gpsLat, float gpsLong)
    {
        mId = id;
        mName = name;
        mCode = code;
        mHours = hours;
        mImageURI = imageURI;
        mGPSLat = gpsLat;
        mGPSLong = gpsLong;
    }

    public Building(String name, String code, String hours, Uri imageUri, float gpsLat, float gpsLong)
    {
        this(-1, name, code, hours, imageUri, gpsLat, gpsLong);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mCode);
        dest.writeString(mHours);
        dest.writeString(String.valueOf(mImageURI));
        dest.writeFloat(mGPSLat);
        dest.writeFloat(mGPSLong);
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



    private Building(Parcel parcel)
    {
        mId = parcel.readInt();
        mName = parcel.readString();
        mCode = parcel.readString();
        mHours = parcel.readString();
        mImageURI = Uri.parse(parcel.readString());
        mGPSLat = parcel.readFloat();
        mGPSLong = parcel.readFloat();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setCode(String code){
        mCode = code;
    }

    public String getCode(){
        return mCode;
    }


    public String getHours() {
        return mHours;
    }

    public void setHours(String mHours) {
        this.mHours = mHours;
    }

    public Uri getImageURI() {
        return mImageURI;
    }

    public void setImageURI(Uri mImageURI) {
        this.mImageURI = mImageURI;
    }

    public float getGPSLat() {
        return mGPSLat;
    }

    public void setGPSLat(float mGPSLat) {
        this.mGPSLat = mGPSLat;
    }

    public float getGPSLong() {
        return mGPSLong;
    }

    public void setGPSLong(float mGPSLong) {
        this.mGPSLong = mGPSLong;
    }


}
