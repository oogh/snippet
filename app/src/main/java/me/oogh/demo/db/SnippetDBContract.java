package me.oogh.demo.db;

import android.provider.BaseColumns;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class SnippetDBContract {
    private SnippetDBContract() {
    }

    public static class SnippetEntry implements BaseColumns {
        public static final String TABLE_NAME = "snippet";
        public static final String COLUMN_CONTENT = "content";
    }
}
