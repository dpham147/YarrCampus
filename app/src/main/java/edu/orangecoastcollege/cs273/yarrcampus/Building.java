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

    /**
     * Constructor for a building, initializes all the variables to the parameters being passed
     *
     * @param id int, id of the building
     * @param name String, the name of the building
     * @param code String, the shortened name of the building
     * @param hours String, hours the building is operable, format 00:00 AM - 00:00 PM
     * @param imageURI Uri, link to the image of the building
     * @param gpsLat float, Latitude of the building in Decimal Format
     * @param gpsLong float, Longitude of the building in Decimal Format
     */
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

    /**
     * Constructor for a building, initializes all the variables to the parameters being passed
     *
     * @param name String, the name of the building
     * @param code String, the shortened name of the building
     * @param hours String, hours the building is operable, format 00:00 AM - 00:00 PM
     * @param imageUri Uri, link to the image of the building
     * @param gpsLat float, Latitude of the building
     *
     */
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

    /**
     *
     * @return id of the building
     */
    public int getId() {
        return mId;
    }

    /**
     * Gets name of the building
     *
     * @return name of the building
     */
    public String getName() {
        return mName;
    }

    /**
     * Changes the name of this building
     *
     * @param name Building's new name
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * Changes the shortened name of the building to the new one
     *
     * @param code New shortened name for the building
     */
    public void setCode(String code){
        mCode = code;
    }

    /**
     * Gets the shortened name of the building
     *
     * @return code for the building
     */
    public String getCode(){
        return mCode;
    }

    /**
     * Gets the hours of the building
     *
     * @return The hours of the building
     */
    public String getHours() {
        return mHours;
    }

    /**
     * Changes the hours of the building
     *
     * @param mHours The new hours of the building
     */
    public void setHours(String mHours) {
        this.mHours = mHours;
    }

    /**
     * Gets the link to the image of the building
     *
     * @return The image of the building
     */
    public Uri getImageURI() {
        return mImageURI;
    }

    /**
     * Changes the link to the image of the building
     *
     * @param mImageURI New image of the building
     */
    public void setImageURI(Uri mImageURI) {
        this.mImageURI = mImageURI;
    }

    /**
     * Gets the Latitude of the building in Decimal format
     *
     * @return The Latitude of the building
     */
    public float getGPSLat() {
        return mGPSLat;
    }

    /**
     * Changes the Latitude of the building, may be a bit difficult
     *
     * @param mGPSLat The new latitude location of the building in Decimal Format
     */
    public void setGPSLat(float mGPSLat) {
        this.mGPSLat = mGPSLat;
    }

    /**
     * Gets the Longitude of the building in Decimal Format
     *
     * @return The Longitude of the building
     */
    public float getGPSLong() {
        return mGPSLong;
    }

    /**
     * Changes the Longitude of the building, may be a bit difficult
     *
     * @param mGPSLong The new Longitude location of the building in Decimal Format
     */
    public void setGPSLong(float mGPSLong) {
        this.mGPSLong = mGPSLong;
    }

    /**
     * Gets the name and id of the building and gives it in a certain format
     * "Building{Building Name, Building ID}"
     *
     * @return Name and ID of the building
     */
    @Override
    public String toString() {
        return "Building {" + mName + ", " + mId + "}";
    }

}
