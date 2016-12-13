package edu.orangecoastcollege.cs273.yarrcampus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by kevin_000 on 12/12/2016.
 */
public class ContactsTest {

    private Contacts testContact;

    @Before
    public void setUp() throws Exception {
        testContact = new Contacts(1, "", "", "");
        testContact.setmName("Test Name");
        testContact.setHours("00:00");
        testContact.setPhoneNumber("1234567890");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals(1, testContact.getId());
    }

    @Test
    public void getPhoneNumber() throws Exception {
        assertEquals("1234567890", testContact.getPhoneNumber());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Test Name", testContact.getName());
    }

    @Test
    public void getHours() throws Exception {
        assertEquals("00:00", testContact.getHours());
    }

}