package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kevin_000 on 11/25/2016.
 */

public class Courses implements Parcelable{

    private int mCRN;
    private String mCourseName;
    private Building mBuilding;
    private Professor mProfessor;
    private String mSubject;
    private String mSemesterCode;

    public Courses(int crn, String courseName, Building building, Professor professor, String subject, String code){
        mCRN = crn;
        mCourseName = courseName;
        mBuilding = building;
        mProfessor = professor;
        mSubject = subject;
        mSemesterCode = code;
    }

    public Courses(Parcel parcel)
    {
        mCRN = parcel.readInt();
        mCourseName = parcel.readString();
        mBuilding = new Building(parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                Uri.parse(parcel.readString()),
                parcel.readFloat(),
                parcel.readFloat());
        mProfessor = new Professor(parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                Uri.parse(parcel.readString()),
                parcel.readFloat(),
                parcel.readFloat());
        mSubject = parcel.readString();
        mSemesterCode = parcel.readString();
    }

    public int getCRN() {
        return mCRN;
    }

    public void setCRN(int CRN) {
        mCRN = CRN;
    }

    public Building getmBuilding() {
        return mBuilding;
    }

    public void setmBuilding(Building building) {
        mBuilding = building;
    }

    public Professor getmProfessor() {
        return mProfessor;
    }

    public void setmProfessor(Professor professor) {
        mProfessor = professor;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public void setCourseName(String courseName){
        mCourseName = courseName;
    }

    public String getCourseName(){
        return mCourseName;
    }

    public String getSemesterCode() {
        return mSemesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        mSemesterCode = semesterCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mCRN);
        parcel.writeString(mCourseName);
        parcel.writeString(mBuilding.getName());
        parcel.writeString(mBuilding.getCode());
        parcel.writeString(mBuilding.getHours());
        parcel.writeString(mBuilding.getImageURI().toString());
        parcel.writeFloat(mBuilding.getGPSLat());
        parcel.writeFloat(mBuilding.getGPSLong());
        parcel.writeString(mProfessor.getmName());
        parcel.writeString(mProfessor.getmDesc());
        parcel.writeString(mProfessor.getmOfficeHours());
        parcel.writeString(mProfessor.getmBuilding());
        parcel.writeString(mProfessor.getmImageURI().toString());
        parcel.writeFloat(mProfessor.getmOfficeLat());
        parcel.writeFloat(mProfessor.getmOfficeLong());
        parcel.writeString(mSubject);
        parcel.writeString(mSemesterCode);
    }

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public Courses createFromParcel(Parcel parcel) {
            return new Courses(parcel);
        }

        @Override
        public Professor[] newArray(int i) {
            return new Professor[i];
        }
    };

    @Override
    public String toString() {
        return "Courses {" + mCourseName + ", " + mCRN + "}";
    }
}

















