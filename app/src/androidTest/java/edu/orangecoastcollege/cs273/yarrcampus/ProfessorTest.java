package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by roman on 12/12/2016.
 */
public class ProfessorTest {
    private Professor professor;
    @Before
    public void setUp() throws Exception {

        professor = new Professor();
        professor.setmBuilding("MBCC");
        professor.setmDesc("Hacker");
        professor.setmEmail("1@gmail.com");
        professor.setmId(1);
        professor.setmImageURI(Uri.parse("test"));
        professor.setmName("Mario");
        professor.setmOfficeHours("1 - 2");
        professor.setmOfficeLat(0.0f);
        professor.setmOfficeLong(0.0f);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getmId() throws Exception {
        professor.getmId();
    }

    @Test
    public void getmName() throws Exception {
        professor.getmName();
    }


    @Test
    public void getmDesc() throws Exception {
        professor.getmDesc();
    }

    @Test
    public void getmOfficeHours() throws Exception {
        professor.getmOfficeHours();
    }

    @Test
    public void getmBuilding() throws Exception {
        professor.getmBuilding();
    }


    @Test
    public void getmImageURI() throws Exception {
        professor.getmImageURI();
    }
    @Test
    public void getAllDetails() throws Exception {
        professor.getAllDetails();
    }

    @Test
    public void getmOfficeLat() throws Exception {
        professor.getmOfficeLat();
    }


    @Test
    public void getmOfficeLong() throws Exception {
        professor.getmOfficeLong();
    }

}