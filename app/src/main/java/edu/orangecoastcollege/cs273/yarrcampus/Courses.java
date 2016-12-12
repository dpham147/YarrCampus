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

    /**
     * Courses overloaded constructor
     * @param crn - Course registration number (unique field id)
     * @param courseName - name of the course
     * @param building - building object class is in
     * @param professor - course instructor
     * @param subject - course subject
     * @param code - semester code
     */
    public Courses(int crn, String courseName, Building building, Professor professor, String subject, String code){
        mCRN = crn;
        mCourseName = courseName;
        mBuilding = building;
        mProfessor = professor;
        mSubject = subject;
        mSemesterCode = code;
    }

    /**
     * Course parcel constructor
     * @param parcel - source parcel
     */
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

    /**
     * CRN accessor
     * @return - crn as integer
     */
    public int getCRN() {
        return mCRN;
    }

    /**
     * CRN accessor
     * @return - crn as strin
     */
    public String getCRNString() {
        return String.valueOf(mCRN);
    }

    /**
     * CRN mutator
     * @param CRN - new CRN
     */
    public void setCRN(int CRN) {
        mCRN = CRN;
    }

    /**
     * Gets the associated building object
     * @return - the building
     */
    public Building getmBuilding() {
        return mBuilding;
    }

    /**
     * Building object mutator
     * @param building - new building
     */
    public void setmBuilding(Building building) {
        mBuilding = building;
    }

    /**
     * Accesses associated Professor class
     * @return - the professor
     */
    public Professor getmProfessor() {
        return mProfessor;
    }

    /**
     * Professor mutator
     * @param professor - new professor
     */
    public void setmProfessor(Professor professor) {
        mProfessor = professor;
    }

    /**
     * Subject accessor
     * @return - the subject
     */
    public String getSubject() {
        return mSubject;
    }

    /**
     * Subject mutator
     * @param subject - new subject
     */
    public void setSubject(String subject) {
        mSubject = subject;
    }

    /**
     * Course name mutator
     * @param courseName - new course name
     */
    public void setCourseName(String courseName){
        mCourseName = courseName;
    }

    /**
     * course name accessor
     * @return - the course name
     */
    public String getCourseName(){
        return mCourseName;
    }

    /**
     * semester code accessor
     * @return - the semester code
     */
    public String getSemesterCode() {
        return mSemesterCode;
    }

    /**
     * Semester code mutator
     * @param semesterCode - new semester code
     */
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

    /**
     * Overloaded toString() method
     * @return - "Courses {courseName, crn}"
     */
    @Override
    public String toString() {
        return "Courses {" + mCourseName + ", " + mCRN + "}";
    }
}

















