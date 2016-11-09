package edu.orangecoastcollege.cs273.yarrcampus;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "YarrCampus";
    static final String BUILDING_TABLE = "Buildings";
    static final String PROFESSOR_TABLE = "Professors";
    static final String UTILITY_TABLE = "Utilities";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_FIELD_ID = "id";
    private static final String FIELD_BUILDING_NAME = "name";
    private static final String FIELD_BUILDING_COORDINATE_X = "coordx";
    private static final String FIELD_BUILDING_COORDINATE_Y = "coordy";
    private static final String FIELD_BUILDING_HOURS = "hours";
    private static final String FIELD_BUILDING_DESCRIPTION = "desc";
    private static final String FIELD_BUILDING_IMAGE_URI = "uri";

    private static final String FIELD_PROFESSORS_NAME = "name";
    private static final String FIELD_PROFESSORS_CLASSES = "classes";
    private static final String FIELD_PROFESSORS_OFFICE_HOURS = "hours";
    private static final String FIELD_PROFESSORS_IMAGE_URI = "uri";
    private static final String FIELD_PROFESSORS_DESCRIPTION = "desc";

    private static final String FIELD_UTILITIES_TYPE = "type";
    private static final String FIELD_UTILITIES_COORDINATE_X = "coordx";
    private static final String FIELD_UTILITIES_COORDINATE_Y = "coordy";
    private static final String FIELD_UTILITIES_DESCRIPTION = "desc";

    public DBHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the building table
        String table = "CREATE TABLE " + BUILDING_TABLE + "(" +
                ")";
        db.execSQL(table);

        // Create the professor table
        table = "CREATE TABLE " + PROFESSOR_TABLE + "(" +
                ")";
        db.execSQL(table);

        // Create the utilities table
        table = "CREATE TABLE " + UTILITY_TABLE + "(" +
                ")";
        db.execSQL(table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BUILDING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PROFESSOR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + UTILITY_TABLE);
        onCreate(db);
    }
}
