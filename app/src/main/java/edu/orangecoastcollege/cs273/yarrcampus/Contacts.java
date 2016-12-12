package edu.orangecoastcollege.cs273.yarrcampus;

/**
 * Created by kdo94 on 12/8/2016.
 */

public class Contacts {
    private int mId;
    private String mName;
    private String mPhoneNumber;

    public Contacts(int id, String name, String phone){
        mId = id;
        mName = name;
        mPhoneNumber = phone;
    }

    public Contacts(String name, String phone){
        this(-1, name, phone);
    }

    public int getId(){
        return mId;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getName() {
        return mName;
    }

    public void setmName(String name) {
        mName = name;
    }
}
