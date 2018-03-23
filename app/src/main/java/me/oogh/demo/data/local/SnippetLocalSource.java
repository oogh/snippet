package me.oogh.demo.data.local;

import android.content.Context;

import java.util.List;

import me.oogh.demo.data.ISnippetDataSource;
import me.oogh.demo.data.Snippet;
import me.oogh.demo.db.SnippetDAO;
import me.oogh.demo.db.SnippetDBHelper;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description 本地数据源
 */

public class SnippetLocalSource implements ISnippetDataSource {
    private SnippetDAO mDAO;

    public SnippetLocalSource(Context context) {
        mDAO = new SnippetDAO(SnippetDBHelper.newInstance(context));
    }

    @Override
    public void listData(DataCallback callback) {
        callback.onDataListed(mDAO.findAll());
    }

    @Override
    public void removeItem(String id) {
        mDAO.remove(id);
    }

    @Override
    public void addItems(List<Snippet> snippets) {
        mDAO.insertItems(snippets);
    }
}
