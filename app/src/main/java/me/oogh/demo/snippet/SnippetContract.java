package me.oogh.demo.snippet;

import java.util.List;

import me.oogh.demo.base.BasePresenter;
import me.oogh.demo.base.BaseView;
import me.oogh.demo.data.Snippet;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class SnippetContract {

    interface View extends BaseView<Presenter> {
        void showSnippetList(List<Snippet> snippets);

        void showSaveSucceed();
    }

    interface Presenter extends BasePresenter {
        /**
         * 删除一条数据
         *
         * @param id
         */
        void removeItem(String id);

        void listSnippet();

        void addItems(List<Snippet> snippets);
    }
}
