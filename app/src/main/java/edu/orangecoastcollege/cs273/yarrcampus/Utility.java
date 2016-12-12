package edu.orangecoastcollege.cs273.yarrcampus;

import android.os.Parcel;
import android.os.Parcelable;

public class Utility implements Parcelable {
    private int mId;
    private String mType;
    private String mDesc;
    private float mGPSLat;
    private float mGPSLong;


    /**
     * Overloaded Utility constructor
     * @param id - Database ID
     * @param type - Utility type
     * @param desc - Description of the object
     * @param gpsLat - Latitudinal coordinate
     * @param gpsLong - Longitudinal coordinate
     */
    public Utility(int id, String type, String desc, float gpsLat, float gpsLong)
    {
        mId = id;
        mType = type;
        mDesc = desc;
        mGPSLat = gpsLat;
        mGPSLong = gpsLong;
    }

    /**
     * Overloaded constructor passes default -1 as ID
     * @param type - Utility type
     * @param desc - Description of the object
     * @param gpsLat - Latitudinal coordinate
     * @param gpsLong - Longitudinal coordinate
     */
    public Utility(String type, String desc, float gpsLat, float gpsLong)
    {
        this(-1, type, desc, gpsLat, gpsLong);
    }

    /**
     * Basic overloaded constructor
     * @param type - Utility type
     * @param gpsLat - Latitudinal coordinate
     * @param gpsLong - Longitudinal coordinate
     */
    public Utility(String type, float gpsLat, float gpsLong)
    {
        this(-1, type, "", gpsLat, gpsLong);
    }

    /**
     * Parcel constructor
     * @param source - Source parcel
     */
    private Utility(Parcel source)
    {
        mId = source.readInt();
        mType = source.readString();
        mDesc = source.readString();
        mGPSLat = source.readFloat();
        mGPSLong = source.readFloat();
    }

    /**
     * ID Accessor
     * @return - Utility ID
     */
    public int getmId() {
        return mId;
    }

    /**
     * ID Mutator
     * @param mId - the new ID
     */
    public void setmId(int mId) {
        this.mId = mId;
    }

    /**
     * Type Accessor
     * @return - Utility Type
     */
    public String getmType() {
        return mType;
    }

    /**
     * Type Mutator
     * @param mType - the new Type
     */
    public void setmType(String mType) {
        this.mType = mType;
    }

    /**
     * Description Accessor
     * @return - Utility Description
     * */
    public String getmDesc() {
        return mDesc;
    }

    /**
     * Description Mutator
     * @param mDesc - the new Description
     */
    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    /**
     * Latitude Accessor
     * @return - Latitude coordinate
     */
    public float getmGPSLat() {
        return mGPSLat;
    }

    /**
     * Latitude Mutator
     * @param mGPSLat - the new Latitude
     */
    public void setmGPSLat(float mGPSLat) {
        this.mGPSLat = mGPSLat;
    }

    /**
     * Longitude Accessor
     * @return - Longitude coordinate
     */
    public float getmGPSLong() {
        return mGPSLong;
    }

    /**
     * Longitude mutator
     * @param mGPSLong - the new Longitude
     */
    public void setmGPSLong(float mGPSLong) {
        this.mGPSLong = mGPSLong;
    }

    /**
     * Overloaded toString() method
     * @return - "Type Lat XXX.XXXXX Long YYY.YYYY"
     */
    @Override
    public String toString() {
        return mType + " Lat " + mGPSLat + " Long " + mGPSLong;
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
