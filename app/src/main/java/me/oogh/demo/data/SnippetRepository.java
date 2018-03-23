package me.oogh.demo.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description SnippetEntry 数据仓库
 */

public class SnippetRepository implements ISnippetDataSource {

    public SnippetRepository(ISnippetDataSource localSource) {
        mLocalSource = localSource;
    }

    private ISnippetDataSource mLocalSource;


    @Override
    public void listData(@NonNull DataCallback callback) {
        mLocalSource.listData(callback);
    }

    @Override
    public void removeItem(String id) {
        mLocalSource.removeItem(id);
    }

    @Override
    public void addItems(List<Snippet> snippets) {
        mLocalSource.addItems(snippets);
    }


}
