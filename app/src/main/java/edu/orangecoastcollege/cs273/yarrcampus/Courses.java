package edu.orangecoastcollege.cs273.yarrcampus;

/**
 * Created by kevin_000 on 11/25/2016.
 */

public class Courses {

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

}