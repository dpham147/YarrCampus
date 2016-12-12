package edu.orangecoastcollege.cs273.yarrcampus;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "YarrCampus";
    private static final int DATABASE_VERSION = 6;

    private static final String BUILDING_TABLE = "Buildings";
    private static final String BUILDING_KEY_FIELD_ID = "id";
    private static final String FIELD_BUILDING_NAME = "name";
    private static final String FIELD_BUILDING_CODE = "code";
    private static final String FIELD_BUILDING_HOURS = "hours";
    private static final String FIELD_BUILDING_COORDINATE_LAT = "lat";
    private static final String FIELD_BUILDING_COORDINATE_LONG = "long";
    private static final String FIELD_BUILDING_IMAGE_URI = "uri";

    private static final String PROFESSOR_TABLE = "Professors";
    private static final String PROFESSOR_KEY_FIELD_ID = "id";
    private static final String FIELD_PROFESSORS_NAME = "name";
    private static final String FIELD_PROFESSORS_BUILDING = "building";
    private static final String FIELD_PROFESSORS_OFFICE_HOURS = "hours";
    private static final String FIELD_PROFESSORS_IMAGE_URI = "uri";
    private static final String FIELD_PROFESSORS_DESCRIPTION = "desc";
    private static final String FIELD_PROFESSORS_OFFICE_LATITUDE = "lat";
    private static final String FIELD_PROFESSORS_OFFICE_LONGITUDE = "long";

    private static final String UTILITY_TABLE = "Utilities";
    private static final String UTILITIES_KEY_FIELD_ID = "id";
    private static final String FIELD_UTILITIES_DESCRIPTION = "desc";
    private static final String FIELD_UTILITIES_TYPE = "type";
    private static final String FIELD_UTILITIES_COORDINATE_LAT = "lat";
    private static final String FIELD_UTILITIES_COORDINATE_LONG = "long";


    private static final String COURSES_TABLE = "Courses";
    private static final String FIELD_COURSES_CRN = "crn";
    private static final String FIELD_COURSES_NAME = "course_name";
    private static final String FIELD_COURSES_PROFESSOR_ID = "professor_id";
    private static final String FIELD_COURSES_BUILDING_ID = "building_id";
    private static final String FIELD_COURSES_SEMESTER_CODE = "semester_code";
    private static final String FIELD_COURSES_SUBJECT = "subject";

    private static final String CONTACTS_TABLE = "Contacts";
    private static final String CONTACTS_KEY_FIELD_ID = "id";
    private static final String FIELD_CONTACTS_NAME = "name";
    private static final String FIELD_CONTACTS_NUMBER = "phone_number";

    public DBHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the building table
        String table = "CREATE TABLE " + BUILDING_TABLE + "(" +
                BUILDING_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_BUILDING_NAME + " TEXT, " +
                FIELD_BUILDING_CODE + " TEXT, " +
                FIELD_BUILDING_HOURS + " TEXT, " +
                FIELD_BUILDING_COORDINATE_LAT + " TEXT, " +
                FIELD_BUILDING_COORDINATE_LONG + " TEXT, " +
                FIELD_BUILDING_IMAGE_URI + " TEXT" + ")";
        db.execSQL(table);

        // Create the professor table
        table = "CREATE TABLE " + PROFESSOR_TABLE + "(" +
                PROFESSOR_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_PROFESSORS_NAME + " TEXT, " +
                FIELD_PROFESSORS_DESCRIPTION + " TEXT, " +
                FIELD_PROFESSORS_OFFICE_HOURS + " TEXT, " +
                FIELD_PROFESSORS_BUILDING + " TEXT, " +
                FIELD_PROFESSORS_IMAGE_URI + " TEXT, " +
                FIELD_PROFESSORS_OFFICE_LATITUDE + " REAL, " +
                FIELD_PROFESSORS_OFFICE_LONGITUDE + " REAL" + ")";
        db.execSQL(table);

        // Create the utilities table
        table = "CREATE TABLE " + UTILITY_TABLE + "(" +
                UTILITIES_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_UTILITIES_TYPE + " TEXT, " +
                FIELD_UTILITIES_DESCRIPTION + " TEXT, " +
                FIELD_UTILITIES_COORDINATE_LAT + " REAL, " +
                FIELD_UTILITIES_COORDINATE_LONG + " REAL" + ")";
        db.execSQL(table);

        table = "CREATE TABLE " + COURSES_TABLE + "(" +
                FIELD_COURSES_CRN + " INTEGER PRIMARY KEY, " +
                FIELD_COURSES_NAME + " TEXT, " +
                FIELD_COURSES_BUILDING_ID + " INTEGER, " +
                FIELD_COURSES_PROFESSOR_ID + " INTEGER, " +
                FIELD_COURSES_SUBJECT + " TEXT, " +
                FIELD_COURSES_SEMESTER_CODE + " TEXT, " +
                " FOREIGN KEY(" + FIELD_COURSES_BUILDING_ID + ") REFERENCES " +
                BUILDING_TABLE + "(" + BUILDING_KEY_FIELD_ID + "), " +
                " FOREIGN KEY(" + FIELD_COURSES_PROFESSOR_ID + ") REFERENCES " +
                PROFESSOR_TABLE + "(" + PROFESSOR_KEY_FIELD_ID + ")" + ")";
        db.execSQL(table);

        table = "CREATE TABLE " + CONTACTS_TABLE + "(" +
                CONTACTS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_CONTACTS_NAME + " TEXT, " +
                FIELD_CONTACTS_NUMBER + " TEXT" + ")";
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BUILDING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PROFESSOR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + UTILITY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE);
        onCreate(db);
    }

    // ------ PROFESSOR TABLE OPERATIONS -----------
    public ArrayList<Professor> getAllProfessors()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Professor> allProf = new ArrayList<>();
        Cursor cursor = db.query(PROFESSOR_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                Professor newProf = new Professor( cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        Uri.parse(cursor.getString(5)),
                        cursor.getFloat(6),
                        cursor.getFloat(7));

                allProf.add(newProf);

            }
            while (cursor.moveToNext());
        }
        db.close();
        return allProf;
    }

    public Professor getProfessor(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                PROFESSOR_TABLE,
                new String[]{PROFESSOR_KEY_FIELD_ID, FIELD_PROFESSORS_NAME,
                        FIELD_PROFESSORS_BUILDING, FIELD_PROFESSORS_OFFICE_HOURS,
                        FIELD_PROFESSORS_IMAGE_URI, FIELD_PROFESSORS_DESCRIPTION,
                        FIELD_PROFESSORS_OFFICE_LATITUDE, FIELD_PROFESSORS_OFFICE_LONGITUDE},
                PROFESSOR_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Professor newProf = new Professor( cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                Uri.parse(cursor.getString(5)),
                cursor.getFloat(6),
                cursor.getFloat(7));
        db.close();
        return newProf;
    }

    public void addProfessor(Professor professor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =  new ContentValues();

        values.put(FIELD_PROFESSORS_NAME, professor.getmName());
        values.put(FIELD_PROFESSORS_BUILDING, professor.getmBuilding());
        values.put(FIELD_PROFESSORS_OFFICE_HOURS, professor.getmOfficeHours());
        values.put(FIELD_PROFESSORS_IMAGE_URI, professor.getmImageURI().toString());
        values.put(FIELD_PROFESSORS_DESCRIPTION, professor.getmDesc());
        values.put(FIELD_PROFESSORS_OFFICE_LATITUDE, professor.getmOfficeLat());
        values.put(FIELD_PROFESSORS_OFFICE_LONGITUDE, professor.getmOfficeLong());

        db.insert(PROFESSOR_TABLE, null, values);
        db.close();
    }

    public void deleteAllProfessors() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PROFESSOR_TABLE, null, null);
        db.close();
    }


    // ------------ BUILDING TABLE OPERATIONS ----------------
    public ArrayList<Building> getAllBuildings()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Building> allBuildings = new ArrayList<>();
        Cursor cursor = db.query(BUILDING_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                Building newBuilding = new Building(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        Uri.parse(cursor.getString(6)),
                        cursor.getFloat(4),
                        cursor.getFloat(5));
                allBuildings.add(newBuilding);
                Log.i("YarrCampus", "Professor ID: " + newBuilding.getId());
            }
            while (cursor.moveToNext());
        }
        db.close();
        return allBuildings;
    }

    public Building getBuilding(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                BUILDING_TABLE,
                new String[]{BUILDING_KEY_FIELD_ID,
                        FIELD_BUILDING_NAME,
                        FIELD_BUILDING_CODE,
                        FIELD_BUILDING_HOURS,
                        FIELD_BUILDING_COORDINATE_LAT,
                        FIELD_BUILDING_COORDINATE_LONG,
                        FIELD_BUILDING_IMAGE_URI}, BUILDING_KEY_FIELD_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        Building newBuilding = new Building(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                Uri.parse(cursor.getString(6)),
                cursor.getFloat(4),
                cursor.getFloat(5));

        db.close();
        return newBuilding;
    }

    public void addBuilding(Building building){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_BUILDING_NAME, building.getName());
        values.put(FIELD_BUILDING_CODE, building.getCode());
        values.put(FIELD_BUILDING_HOURS, building.getHours());
        values.put(FIELD_BUILDING_IMAGE_URI, String.valueOf(building.getImageURI()));
        values.put(FIELD_BUILDING_COORDINATE_LAT, building.getGPSLat());
        values.put(FIELD_BUILDING_COORDINATE_LONG, building.getGPSLong());
        db.insert(BUILDING_TABLE, null, values);

        db.close();
    }

    public void deleteAllBuildings(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BUILDING_TABLE, null, null);
        db.close();
    }

    // -------------- UTILITY TABLE OPERATIONS -------------
    public ArrayList<Utility> getAllUtilities()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Utility> allUtilities = new ArrayList<>();
        Cursor cursor = db.query(UTILITY_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                Utility newUtility = new Utility(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getFloat(3),
                        cursor.getFloat(4));
                allUtilities.add(newUtility);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return allUtilities;
    }

    public void addUtility(Utility restroom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_UTILITIES_TYPE, restroom.getmType());
        values.put(FIELD_UTILITIES_DESCRIPTION, restroom.getmDesc());
        values.put(FIELD_UTILITIES_COORDINATE_LAT, restroom.getmGPSLat());
        values.put(FIELD_UTILITIES_COORDINATE_LONG, restroom.getmGPSLong());

        db.insert(UTILITY_TABLE, null, values);

        db.close();
    }

    public void deleteAllUtilities(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(UTILITY_TABLE, null, null);
        db.close();
    }


    public void addCourse(int crn, String courseName, int buildingId, int profId, String subject, String code){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_COURSES_CRN, crn);
        values.put(FIELD_COURSES_NAME, courseName);
        values.put(FIELD_COURSES_BUILDING_ID, buildingId);
        values.put(FIELD_COURSES_PROFESSOR_ID, profId);
        values.put(FIELD_COURSES_SUBJECT, subject);
        values.put(FIELD_COURSES_SEMESTER_CODE, code);
        db.insert(COURSES_TABLE, null, values);

        db.close();
    }

    public ArrayList<Courses> getAllCourses(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Courses> allCourses = new ArrayList<>();
        Cursor cursor = db.query(COURSES_TABLE,
                new String[]{FIELD_COURSES_CRN, FIELD_COURSES_NAME, FIELD_COURSES_BUILDING_ID,
                        FIELD_COURSES_PROFESSOR_ID, FIELD_COURSES_SUBJECT, FIELD_COURSES_SEMESTER_CODE},
                null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                Building building = getBuilding(cursor.getInt(2));
                Professor professor = getProfessor(cursor.getInt(3));
                Courses newCourse = new Courses(
                        cursor.getInt(0),
                        cursor.getString(1),
                        building, professor,
                        cursor.getString(4),
                        cursor.getString(5));
                allCourses.add(newCourse);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return allCourses;
    }

    public void deleteCourse(Courses course){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(COURSES_TABLE, FIELD_COURSES_CRN + " = ?",
                new String[]{String.valueOf(course.getCRN())});
        db.close();
    }

    public void deleteAllCourses(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(COURSES_TABLE, null, null);
        db.close();
    }

    public void updateCourse(Courses course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_COURSES_NAME, course.getCourseName());
//        values.put(FIELD_COURSES_BUILDING_ID, course.getBuildingId());
//        values.put(FIELD_COURSES_PROFESSOR_ID, course.getProfessorId());
        values.put(FIELD_COURSES_SUBJECT, course.getSubject());
        values.put(FIELD_COURSES_SEMESTER_CODE, course.getSemesterCode());
        db.update(COURSES_TABLE, values, FIELD_COURSES_CRN + " =? ",
                new String[]{String.valueOf(course.getCRN())});

        db.close();
    }


    public ArrayList<Contacts> getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Contacts> allContacts = new ArrayList<>();
        Cursor cursor = db.query(CONTACTS_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                Contacts newContact = new Contacts(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2));
                allContacts.add(newContact);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return allContacts;
    }

    public void addContact(Contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_CONTACTS_NAME, contact.getName());
        values.put(FIELD_CONTACTS_NUMBER, contact.getPhoneNumber());
        db.insert(CONTACTS_TABLE, null, values);

        db.close();
    }

    public void deleteAllContacts(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONTACTS_TABLE, null, null);
        db.close();
    }
}