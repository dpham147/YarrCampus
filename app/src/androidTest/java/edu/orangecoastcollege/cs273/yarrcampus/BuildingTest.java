package edu.orangecoastcollege.cs273.yarrcampus;

import android.net.Uri;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by kevin_000 on 12/12/2016.
 */
public class BuildingTest {

    private Building testBuilding;

    @Before
    public void setUp() throws Exception {
        testBuilding = new Building(1, "", "", "", Uri.parse("T"), 0f,01f);
        testBuilding.setName("Test Building");
        testBuilding.setCode("TSNM");
        testBuilding.setHours("00:00");
        testBuilding.setImageURI(Uri.parse("TeSt"));
        testBuilding.setGPSLong(-1f);
        testBuilding.setGPSLat(1f);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals(1, testBuilding.getId());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Test Building", testBuilding.getName());
    }


    @Test
    public void getCode() throws Exception {
        assertEquals("TSNM", testBuilding.getCode());
    }

    @Test
    public void getHours() throws Exception {
        assertEquals("00:00", testBuilding.getHours());
    }

    @Test
    public void getImageURI() throws Exception {
        assertEquals(Uri.parse("TeSt"), testBuilding.getImageURI());
    }

    @Test
    public void getGPSLat() throws Exception {
        assertEquals(1f, testBuilding.getGPSLat());
    }

    @Test
    public void getGPSLong() throws Exception {
        assertEquals(-1f, testBuilding.getGPSLong());
    }

}