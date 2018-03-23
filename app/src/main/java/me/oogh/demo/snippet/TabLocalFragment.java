package me.oogh.demo.snippet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
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
import me.oogh.demo.adapter.SnippetRecyclerAdapter;
import me.oogh.demo.common.ActionModeCallback;
import me.oogh.demo.common.Actionable;
import me.oogh.demo.common.OnItemClickHelper;
import me.oogh.demo.common.OnItemClickListener;
import me.oogh.demo.data.Snippet;
import me.oogh.demo.data.event.SnippetEvent;
import me.oogh.demo.data.event.SnippetListEvent;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class TabLocalFragment extends Fragment implements Actionable {
    private static final String TAG = TabLocalFragment.class.getSimpleName();

    @BindView(R.id.rv_local)
    RecyclerView mRecyclerView;

    private Unbinder unbinder;
    private SnippetRecyclerAdapter mAdapter;

    private ActionMode mActionMode;

    private List<Snippet> mDataSet;

    public TabLocalFragment() {
    }

    public static TabLocalFragment newInstance() {
        TabLocalFragment fragment = new TabLocalFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataSet = new ArrayList<>();
        mAdapter = new SnippetRecyclerAdapter(getContext(), mDataSet);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_tab_local_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        // TODO : 为 RecyclerView 添加click和longPress事件监听处理
        mRecyclerView.addOnItemTouchListener(new OnItemClickHelper(getContext(), mRecyclerView, new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (mActionMode != null) {
                    handleItemTouched(view, position);
                }
            }

            @Override
            public void onLongPress(View view, int position) {
                handleItemTouched(view, position);
            }
        }));
    }

    @Subscribe(sticky = true)
    public void onListSnippet(SnippetListEvent event) {
        if (event.tag == SnippetListEvent.Tag.SNIPPET_LIST) {
            mDataSet = event.snippets;
            mAdapter.updateDataSet(mDataSet);
        }
    }

    /**
     * 处理 RecyclerView 中的触摸事件
     *
     * @param view
     * @param position
     */
    private void handleItemTouched(View view, int position) {

        mAdapter.toggleSelectStatus(position);

        int count = mAdapter.getSelectedItemCount();
        boolean hasItemSelected = count > 0;

        if (hasItemSelected && mActionMode == null) {
            // 如果有选中的项，但是ActionMode没有被实例化时
            mActionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(
                    new ActionModeCallback(this, tag -> {
                        switch (tag) {
                            case DELETE:
                                removeSelectedItems();
                                break;
                            case SHARE:
                                Toast.makeText(getContext(), "分享功能正在开发中...", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }));
        } else if (!hasItemSelected && mActionMode != null) {
            // 没有选中项，但是ActionMode仍然存在时
            mActionMode.finish(); // 会执行onDestroyActionMode回调
        }

        if (mActionMode != null) {
            mActionMode.setTitle(count + " 项被选中");
        }

    }

    /**
     * 删除被选中的Item
     */
    private void removeSelectedItems() {
        SparseBooleanArray selectedItems = mAdapter.getSelectedItems();

        // TODO : 注意这里是从后往前删除
        // 猜猜从前往后删除可以的不？为什么？
        for (int i = selectedItems.size() - 1; i >= 0; i--) {
            if (selectedItems.valueAt(i)) {
                Snippet snippet = mDataSet.get(selectedItems.keyAt(i));
                EventBus.getDefault().post(
                        new SnippetEvent(SnippetEvent.Tag.REMOVE_ITEM, snippet.getId()));
            }
        }
        mAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), selectedItems.size() + " 条数据已被删除", Toast.LENGTH_SHORT).show();
        mAdapter.clearSelectedItems();
        mActionMode.finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void destroyActionMode() {
        mActionMode = mActionMode == null ? mActionMode : null;
        mAdapter.clearSelectedItems();
    }
}
