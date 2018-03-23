package me.oogh.demo.snippet;

import android.support.annotation.NonNull;

import java.util.List;

import me.oogh.demo.data.Snippet;
import me.oogh.demo.data.SnippetRepository;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class SnippetPresenter implements SnippetContract.Presenter {

    private SnippetContract.View mView;
    private SnippetRepository mRepository;

    public SnippetPresenter(@NonNull SnippetContract.View view, @NonNull SnippetRepository repository) {
        mView = view;
        mView.setPresenter(this);
        mRepository = repository;
    }

    @Override
    public void start() {

    }

    @Override
    public void removeItem(String id) {
        mRepository.removeItem(id);
        listSnippet();
    }

    @Override
    public void listSnippet() {
        mRepository.listData(snippets -> mView.showSnippetList(snippets));
    }

    @Override
    public void addItems(List<Snippet> snippets) {
        mRepository.addItems(snippets);
        mView.showSaveSucceed();
        listSnippet();
    }
}
