package me.oogh.demo.data;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description 数据仓库和源之间定义的规范
 */

public interface ISnippetDataSource {
    void listData(@NonNull DataCallback callback);

    /**
     * 删除一条数据
     *
     * @param id
     */
    void removeItem(String id);

    void addItems(List<Snippet> snippets);

    public interface DataCallback {
        void onDataListed(List<Snippet> dataSet);
    }
}
