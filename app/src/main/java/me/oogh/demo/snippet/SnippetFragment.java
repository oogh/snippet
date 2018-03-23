package me.oogh.demo.snippet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.oogh.demo.R;
import me.oogh.demo.adapter.SnippetPagerAdapter;
import me.oogh.demo.data.Snippet;
import me.oogh.demo.data.event.SnippetEvent;
import me.oogh.demo.data.event.SnippetListEvent;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description MVP -> View
 */

public class SnippetFragment extends Fragment implements SnippetContract.View {

    private static final String TAG = SnippetFragment.class.getSimpleName();
    @BindView(R.id.vp_main)
    ViewPager mViewPager;

    private SnippetContract.Presenter mPresenter;
    private Unbinder mUnbinder;
    private SnippetPagerAdapter mAdapter;

    @Override
    public void setPresenter(SnippetContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public SnippetFragment() {
    }

    public static SnippetFragment newInstance() {
        SnippetFragment fragment = new SnippetFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mAdapter = new SnippetPagerAdapter(getContext(), getChildFragmentManager());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_toolbar_action_items, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_insert_items) {
            List<Snippet> snippets = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                snippets.add(new Snippet("" + i, "第 " + i + " 条 Content"));
            }
            mPresenter.addItems(snippets);
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.listSnippet();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((TabLayout) getActivity().findViewById(R.id.tab_main)).setupWithViewPager(mViewPager);
    }

    @Subscribe
    public void onRemoveItem(SnippetEvent event) {
        if (event.tag == SnippetEvent.Tag.REMOVE_ITEM) {
            mPresenter.removeItem(event.id);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void showSnippetList(List<Snippet> snippets) {
        EventBus.getDefault().postSticky(new SnippetListEvent(SnippetListEvent.Tag.SNIPPET_LIST, snippets));
    }

    @Override
    public void showSaveSucceed() {
        Toast.makeText(getContext(), "测试数据添加成功", Toast.LENGTH_SHORT).show();
    }
}
