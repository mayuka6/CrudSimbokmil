package com.example.maspool.simbokmil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Datahelpert extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbumil.db";
    private static final int DATABASE_VERSION = 1;

    public Datahelpert(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table bumil (id integer primary key, nama text null, alamat text null, " +
                "tensi ,beratbdn interger null, ket text null, kondisi text null, gambar BLOB NOT NULL);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO bumil (id, nama, alamat, tensi, beratbdn, ket, kondisi, gambar) VALUES " +
                "('2451', 'Ana lika', 'Bungurasih gg senggol no. 3', 'Normal', '45', 'Sehat banyak makan sayur', 'Bulan ke 1 baik','');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }
}