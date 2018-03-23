package me.oogh.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static me.oogh.demo.db.SnippetDBContract.SnippetEntry.*;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description 数据库帮助类
 */

public class SnippetDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "snippet.db";
    private static final int DB_VERSION = 1;

    private static SnippetDBHelper INSTANCE;

    private SnippetDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static SnippetDBHelper newInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SnippetDBHelper(context);
        }
        return INSTANCE;
    }

    private static final String CREATE_TABLE_SNIPPET =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CONTENT + " TEXT NOT NULL);";
    private static final String DROP_TABLE_SNIPPET =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SNIPPET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_SNIPPET);
        onCreate(db);
    }
}
