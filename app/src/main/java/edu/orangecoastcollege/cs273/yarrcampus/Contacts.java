package edu.orangecoastcollege.cs273.yarrcampus;

/**
 * Created by kdo94 on 12/8/2016.
 */

public class Contacts {
    private String mName;
    private String mPhoneNumber;

    public Contacts(String name, String phone){
        mName = name;
        mPhoneNumber = phone;
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
