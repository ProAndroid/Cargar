package com.example.maxii.cargar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jere on 05/11/15.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    //public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    //  super(context, name, factory, version);
    private static final int DATABASE_VERSION=1;
    public AdminSQLiteOpenHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_CABANAS = "CREATE TABLE" + Cabana.TABLE
                + Cabana.KEY_ID + "INTIGER PRIMARY KEY AUTOINCRIMENT,"
                + Cabana.KEY_name + "TEXT,";
        db.execSQL(CREATE_TABLE_CABANAS);

        String CREATE_TABLE_ALQUILADAS = "CREATE TABLE" + Alquiladas.TABLE
                + Alquiladas.KEY_ID + "INTIGER PRIMARY KEY,"
                + Cabana.KEY_ID + "INTEGER,"
                + Alquiladas.KEY_checkin + "TEXT,"
                + Alquiladas.KEY_checkout + "TEXT";
        db.execSQL(CREATE_TABLE_ALQUILADAS);

        String CREATE_TABLE_PERSONA="CREATE TABLE "+Persona.TABLE
                +Persona.KEY_ID+ "INTIER PRIMARY,"
                +Persona.KEY_nombre+"TEXT,"
                +Persona.kEY_dni+"INT,"
                +Persona.KEY_email+"TEXT";
        db.execSQL(CREATE_TABLE_PERSONA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IS EXISTS"+ Cabana.TABLE);
        onCreate(db);

    }
}
