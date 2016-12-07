package edu.orangecoastcollege.cs273.yarrcampus;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "YarrCampus";
    private static final int DATABASE_VERSION = 2;

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
    //private static final String FIELD_PROFESSORS_CLASSES = "classes";
    private static final String FIELD_PROFESSORS_OFFICE_HOURS = "hours";
    private static final String FIELD_PROFESSORS_IMAGE_URI = "uri";
    private static final String FIELD_PROFESSORS_DESCRIPTION = "desc";

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
                //FIELD_PROFESSORS_CLASSES + " TEXT, " +
                FIELD_PROFESSORS_IMAGE_URI + " TEXT" + ")";
        db.execSQL(table);

        // Create the utilities table
        table = "CREATE TABLE " + UTILITY_TABLE + "(" +
                UTILITIES_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_UTILITIES_TYPE + " TEXT, " +
                FIELD_UTILITIES_DESCRIPTION + " TEXT, " +
                FIELD_UTILITIES_COORDINATE_LAT + " TEXT, " +
                FIELD_UTILITIES_COORDINATE_LONG + " TEXT" + ")";
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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BUILDING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PROFESSOR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + UTILITY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COURSES_TABLE);
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
                // TODO: Retrieve data from the query
                Professor newProf = new Professor();
                // Rememer to finish this
                allProf.add(newProf);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return allProf;
    }

    public void addProfessor(Professor professor)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =  new ContentValues();

        values.put(FIELD_PROFESSORS_NAME, professor.getmName());
        //values.put(FIELD_PROFESSORS_CLASSES, professor.getmCourses());
        values.put(FIELD_PROFESSORS_OFFICE_HOURS, professor.getmOfficeHours());
        values.put(FIELD_PROFESSORS_IMAGE_URI, professor.getmImageURI().toString());
        values.put(FIELD_PROFESSORS_DESCRIPTION, professor.getmDesc());

        db.insert(PROFESSOR_TABLE, null, values);
        db.close();
    }




    // ------------ BUILDING TABLE OPERATIONS ----------------
    public ArrayList<Building> getAllBuildings()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Building> allBuildings = new ArrayList<>();
        Cursor cursor = db.query(BUILDING_TABLE, null, null, null, null, null, null);
11
        if (cursor.moveToFirst())
        {
            do {
                // TODO: Retrieve data from the query
                Building newBuilding = new Building();
                allBuildings.add(newBuilding);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return allBuildings;
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

    public void addRestroom(Utility restroom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_UTILITIES_TYPE, "Restroom");
        values.put(FIELD_UTILITIES_DESCRIPTION, restroom.getmDesc());
        values.put(FIELD_UTILITIES_COORDINATE_LAT, restroom.getmGPSLat());
        values.put(FIELD_UTILITIES_COORDINATE_LONG, restroom.getmGPSLong());

        db.insert(UTILITY_TABLE, null, values);

        db.close();
    }
    public void addPhone(Utility emergency){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_UTILITIES_TYPE, "Restroom");
        values.put(FIELD_UTILITIES_DESCRIPTION, emergency.getmDesc());
        values.put(FIELD_UTILITIES_COORDINATE_LAT, emergency.getmGPSLat());
        values.put(FIELD_UTILITIES_COORDINATE_LONG, emergency.getmGPSLong());

        db.insert(UTILITY_TABLE, null, values);

        db.close();
    }
    public void addFountain(Utility water){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_UTILITIES_TYPE, "Restroom");
        values.put(FIELD_UTILITIES_DESCRIPTION, water.getmDesc());
        values.put(FIELD_UTILITIES_COORDINATE_LAT, water.getmGPSLat());
        values.put(FIELD_UTILITIES_COORDINATE_LONG, water.getmGPSLong());

        db.insert(UTILITY_TABLE, null, values);

        db.close();
    }


//    public ArrayList<Utility> queryUtilityType (String type)
//    {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<Utility> queriedUtil = new ArrayList<>();
//
//        Cursor cursor = db.query(
//                UTILITY_TABLE,
//                new String[]{FIELD_UTILITIES_TYPE, FIELD_UTILITIES_COORDINATE_LAT, FIELD_UTILITIES_COORDINATE_LONG},
//                FIELD_UTILITIES_TYPE + " ?=",
//                new String[]{type},
//                null, null, null, null);
//
//        if (cursor.moveToFirst());
//        {
//            do {
//                Utility newUtility =  new Utility(cursor.getString(0), cursor.getFloat(1), cursor.getFloat(2));
//                queriedUtil.add(newUtility);
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return queriedUtil;
//    }

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
        Cursor cursor = db.query(UTILITY_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do {
                Courses newCourse = new Courses(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
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
        values.put(FIELD_COURSES_BUILDING_ID, course.getBuildingId());
        values.put(FIELD_COURSES_PROFESSOR_ID, course.getProfessorId());
        values.put(FIELD_COURSES_SUBJECT, course.getSubject());
        values.put(FIELD_COURSES_SEMESTER_CODE, course.getSemesterCode());
        db.update(COURSES_TABLE, values, FIELD_COURSES_CRN + " =? ",
                new String[]{String.valueOf(course.getCRN())});

        db.close();
    }

}