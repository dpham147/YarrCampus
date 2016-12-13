package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class Professor
 */
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

    /**
     * Default Professor Constructor
     */
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

    /**
     * First Overloaded Professor Constructor,  initializes all the variables to the parameters being passed
     * @param id
     * @param name
     * @param desc
     * @param hours
     * @param building
     * @param imageUri
     * @param latitude
     * @param longitude
     */
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

    /**
     * Second OverLoaded constructor,  initializes all the variables to the parameters being passed
     * calls first Overloaded constructor.
     * @param name
     * @param desc
     * @param hours
     * @param building
     * @param imageUri
     * @param latitude
     * @param longitude
     */


    public Professor(String name, String desc, String hours, String building, Uri imageUri, float latitude, float longitude)
    {
        this(-1, name, desc, hours, building, imageUri, latitude, longitude);
    }

    /**
     * Professor Parcel Constructor
     * @param parcel
     */
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

    /**
     * Returns Professor ID
     * @return
     */
    public int getmId() {
        return mId;
    }

    /**
     * Sets Professor ID
     * @param mId
     */
    public void setmId(int mId) {
        this.mId = mId;
    }

    /**
     * Returns Professor Name
     * @return
     */
    public String getmName() {
        return mName;
    }

    /**
     * Sets Professor Name
     * @param mName
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * Returns Professor Description
     * @return
     */
    public String getmDesc() {
        return mDesc;
    }

    /**
     * Sets Professor Description
     * @param mDesc
     */
    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    /**
     * Returns Professor Office Hours
     * @return
     */
    public String getmOfficeHours() {
        return mOfficeHours;
    }


    /**
     * Sets Professor Office Hours
     * @param mOfficeHours
     */
    public void setmOfficeHours(String mOfficeHours) {
        this.mOfficeHours = mOfficeHours;
    }

    /**
     * Returns Professor Building Location
     * @return
     */
    public String getmBuilding() {
        return mBuilding;
    }

    /**
     * Sets Professor Building Location
     * @param mBuilding
     */
    public void setmBuilding(String mBuilding) {
        this.mBuilding = mBuilding;
    }

    /**
     * Returns Professor image URI
     * @return
     */
    public Uri getmImageURI() {
        return mImageURI;
    }

    /**
     * sets Professor Image URI
     * @param mImageURI
     */

    public void setmImageURI(Uri mImageURI) {
        this.mImageURI = mImageURI;
    }

    /**
     * Returns All Details of PRofessor
     * @return
     */
    public String getAllDetails()
    {
        String details = mOfficeHours + "\n"
                + mBuilding;
        return details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes professor to parcel
     * @param dest
     * @param flags
     */
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

    /**
     * Returns Professor Office Latitude
     * @return
     */
    public float getmOfficeLat() {
        return mOfficeLat;
    }

    /**
     * sets ProfessorOfficeLatitude
     * @param mOfficeLat
     */
    public void setmOfficeLat(float mOfficeLat) {
        this.mOfficeLat = mOfficeLat;
    }

    /**
     * Returns Professor Office Longitude
     * @return
     */
    public float getmOfficeLong() {
        return mOfficeLong;
    }

    /**
     * sets Professor Longitude
     * @param mOfficeLong
     */
    public void setmOfficeLong(float mOfficeLong) {
        this.mOfficeLong = mOfficeLong;
    }

    /**
     * Returns Professor Name and ID
     * @return
     */
    @Override
    public String toString() {
        return "Professor {" + mName + ", " + mId + "}";
    }
}
