package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by kdo94 on 11/22/2016.
 */
public class BuildingTest {

    private Building mBuilding;
    private Context mContext;

    @Before
    public void setUp() throws Exception {
        mBuilding = new Building();
        mBuilding.setId(-2);
        mBuilding.setDesc("Test Description");
        mBuilding.setHours("8:00 AM - 10:00 PM");
        mBuilding.setGPSLat(1.0f);
        mBuilding.setGPSLong(0.1f);
        mBuilding.setImageURI(null);
        mBuilding.setName("Test Building");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void describeContents() throws Exception {

    }

    @Test
    public void writeToParcel() throws Exception {

    }

    @Test
    public void getmId() throws Exception {
        assertEquals(-2, mBuilding.getId());
    }

    @Test
    public void getmName() throws Exception {
        assertEquals("Test Building", mBuilding.getName());
    }

    @Test
    public void getmDesc() throws Exception {
        assertEquals("Test Description", mBuilding.getDesc());
    }


    @Test
    public void getmHours() throws Exception {
        assertEquals("8:00 AM - 10:00 PM", mBuilding.getHours());
    }


    @Test
    public void getmImageURI() throws Exception {
        assertNull(mBuilding.getImageURI());
    }


    @Test
    public void getmGPSLat() throws Exception {
        assertEquals(1.0f, mBuilding.getGPSLat());
    }

    @Test
    public void getmGPSLong() throws Exception {
        assertEquals(0.1f, mBuilding.getGPSLong());
    }

}