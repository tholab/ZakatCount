package com.example.tholab.zakatcount;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tholab.zakatcount.model.Mdl_ZF;

import java.util.ArrayList;

/**
 * Created by tholab on 21/01/18.
 */

public class Db_helper extends SQLiteOpenHelper {

    //membuat database nya
    private final static String DATABASE_NAME = "zakatdata";
    private final static int DATABASE_VERSION = 1;

    private final static String TABLE_ZF = "tblzf";
    private final static String FITRAH_ID = "id";
    private final static String COL_NAMAKEPKEL= "namaKK";
    private final static String COL_JUMLAHKEL = "jumlahAngkel";
    private final static String COL_TOTALZAKATFITRAH= "totalzakatfitrah";

    public Db_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_ZF+" ("
        +FITRAH_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
        +COL_JUMLAHKEL+" INTEGER,"
        +COL_TOTALZAKATFITRAH+" INTEGER,"
        +COL_NAMAKEPKEL+" TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ZF);
        onCreate(db);
    }

    public void saveDataZF(Mdl_ZF mdl_zf){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(COL_JUMLAHKEL, mdl_zf.getJumlahKel());
        contentValues.put(COL_TOTALZAKATFITRAH,mdl_zf.getTotalZakat());
        contentValues.put(COL_NAMAKEPKEL, mdl_zf.getNamaKK());

        sqLiteDatabase.insert(TABLE_ZF,null,contentValues);
        sqLiteDatabase.close();

    }

    public ArrayList<Mdl_ZF> getAllDataFilter(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String [] AllColums = {
                FITRAH_ID,
                COL_JUMLAHKEL,
                COL_TOTALZAKATFITRAH,
                COL_NAMAKEPKEL
        };

        Cursor cursor = sqLiteDatabase.query(TABLE_ZF, AllColums,
                null,null,null,null,null);
        ArrayList<Mdl_ZF> tempKembalian = new ArrayList<>();

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                int id=cursor.getInt(0);
                int jumlahkel = cursor.getInt(1);
                int totalZakat = cursor.getInt(2);
                String namaKK = cursor.getString(3);


                Mdl_ZF mdl_zf = new Mdl_ZF();
                mdl_zf.setId(id);
                mdl_zf.setJumlahKel(jumlahkel);
                mdl_zf.setTotalZakat(totalZakat);
                mdl_zf.setNamaKK(namaKK);
                tempKembalian.add(mdl_zf);
                cursor.moveToNext();
            }
        }

        sqLiteDatabase.close();
        return tempKembalian;
    }

    public void deleteDataZF(Mdl_ZF mdl_zf){
        int id = mdl_zf.getId();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_ZF, FITRAH_ID+"="+id, null);
       /* sqLiteDatabase.delete(TABLE_ZF, FITRAH_ID+"=?",
                new String []{String.valueOf(id)});*/
       sqLiteDatabase.close();

    }
}
