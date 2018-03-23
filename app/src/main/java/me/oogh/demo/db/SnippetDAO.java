package me.oogh.demo.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.oogh.demo.data.Snippet;

import static me.oogh.demo.db.SnippetDBContract.SnippetEntry.COLUMN_CONTENT;
import static me.oogh.demo.db.SnippetDBContract.SnippetEntry.TABLE_NAME;
import static me.oogh.demo.db.SnippetDBContract.SnippetEntry._ID;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class SnippetDAO {
    public SnippetDBHelper mDBHelper;

    public SnippetDAO(SnippetDBHelper DBHelper) {
        mDBHelper = DBHelper;
    }


    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Snippet> findAll() {
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        List<Snippet> dataSet = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(_ID));
            String content = cursor.getString(cursor.getColumnIndex(COLUMN_CONTENT));
            dataSet.add(new Snippet(id, content));
        }
        return dataSet;
    }

    /**
     * 添加数据
     *
     * @param snippets
     */
    public void insertItems(List<Snippet> snippets) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        for (Snippet snippet : snippets) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CONTENT, snippet.getContent());
            db.insert(TABLE_NAME, null, values);
        }
    }

    public void remove(String id) {
        // 获取数据库对象
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        // 删除记录的过滤条件
        String selection = _ID + " = ?";
        String[] selectionArgs = {id};

        // 删除
        db.delete(
                TABLE_NAME,
                selection,
                selectionArgs);
    }
}
