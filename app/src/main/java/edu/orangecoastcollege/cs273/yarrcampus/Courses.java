package edu.orangecoastcollege.cs273.yarrcampus;

/**
 * Created by kevin_000 on 11/25/2016.
 */

public class Courses {

    private int mCRN;
    private int mBuildingId;
    private int mProfessorId;
    private String mSubject;

    public Courses(){
        mCRN = -1;
        mBuildingId = -1;
        mProfessorId = -1;
        mSubject = "";
    }

    public Courses(int buildingId, int profId, String subject){
        mBuildingId = buildingId;
        mProfessorId = profId;
        mSubject = subject;
    }

    public int getCRN() {
        return mCRN;
    }

    public void setCRN(int CRN) {
        mCRN = CRN;
    }

    public int getBuildingId() {
        return mBuildingId;
    }

    public void setBuildingId(int buildingId) {
        mBuildingId = buildingId;
    }

    public int getProfessorId() {
        return mProfessorId;
    }

    public void setProfessorId(int professorId) {
        mProfessorId = professorId;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String mSubject) {
        this.mSubject = mSubject;
    }

}
