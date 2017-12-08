package com.example.hp.splashprj.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 2017/10/13.
 */

public class NoteDBOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME="note";
    public static final int VERSION=1;
    public static final String TITLE="title";
    public static final String CONTENT="content";
    public static final String TIME="time";
    public static final String ID="_id";
public NoteDBOpenHelper(Context context){
    super(context,TABLE_NAME,null,VERSION);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"("+ID+" INTEGER primary key autoincrement ,"+CONTENT+"" +
                " text not null,"+TITLE+" text not null ,"+TIME+" text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
