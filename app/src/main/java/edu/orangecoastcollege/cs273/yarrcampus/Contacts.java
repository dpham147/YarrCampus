package edu.orangecoastcollege.cs273.yarrcampus;

/**
 * Created by kdo94 on 12/8/2016.
 */

public class Contacts {
    private int mId;
    private String mName;
    private String mPhoneNumber;
    private String mHours;

    /**
     * Represents a contact at Orange Coast College
     *
     * @param id int, ID to be stored in the database
     * @param name String, Name of the contact
     * @param phone String, Phone number of the contact, no format necessary, just the numbers
     * @param hours String, Hours the contact is available, in the format 00:00 AM - 00:00 PM
     */
    public Contacts(int id, String name, String phone, String hours){
        mId = id;
        mName = name;
        mPhoneNumber = phone;
        mHours = hours;
    }

    /**
     * Represents a contact at Orange Coast College without the id,
     *
     * @param name String, Name of the contact
     * @param phone String, Phone number of the contact, no format necessary, just the numbers
     */
    public Contacts(String name, String phone, String hours){
        this(-1, name, phone, hours);
    }

    /**
     * Gets the id of the contact
     *
     * @return The id of the contact stored in the database
     */
    public int getId(){
        return mId;
    }

    /**
     * Gets the phone number of the contact
     *
     * @return The phone number of the contact
     */
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    /**
     * Changes the phone number of the contact
     *
     * @param phoneNumber New phone number for the contact
     */
    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    /**
     * Gets the name of the contact
     *
     * @return The name of the contact
     */
    public String getName() {
        return mName;
    }

    /**
     * Changes the name of the contact
     *
     * @param name The new name of the contact
     */
    public void setmName(String name) {
        mName = name;
    }

    /**
     * Gets the hours the contact is available for
     *
     * @return The hours for the contact
     */
    public String getHours(){
        return mHours;
    }


    /**
     * Changes the hours the contact is available for
     *
     * @param hours New hours the contact is available for
     */
    public void setHours(String hours){
        mHours = hours;
    }
}
