package me.oogh.demo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.oogh.demo.R;
import me.oogh.demo.data.Snippet;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class SnippetRecyclerAdapter extends RecyclerView.Adapter<SnippetRecyclerAdapter.ViewHolder> {

    private List<Snippet> mDataSet;

    /*
     *  一个Map<Integer, Boolean>集合: 用于存放被选中的itemView
     *  <position,boolean>: position位置的itemView是否被选中
     */
    private SparseBooleanArray mSelectedItems;

    private Context mContext;

    public SnippetRecyclerAdapter(Context context, List<Snippet> dataSet) {
        mContext = context;
        mDataSet = dataSet;
    }

    public void updateDataSet(List<Snippet> dataSet) {
        mDataSet = dataSet;
        notifyDataSetChanged();
        mSelectedItems = new SparseBooleanArray();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_item_local_recycle, parent, false);
        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Snippet data = mDataSet.get(position);
        holder.content.setText(data.getContent());
        Drawable ripple = mContext.getResources().getDrawable(R.drawable.ic_ripple, null);
        Drawable color = mContext.getResources().getDrawable(R.drawable.ic_color, null);
        Drawable drawable = mSelectedItems.get(position) ? color : ripple;
        holder.itemView.setBackground(drawable);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     * 清空存放被选中项的集合
     */
    public void clearSelectedItems() {
        mSelectedItems.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_main_local_content)
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 切换 position 位置 itemView 的选中状态
     *
     * @param position
     */
    public void toggleSelectStatus(int position) {
        if (mSelectedItems.get(position)) {
            mSelectedItems.delete(position);
        } else {
            mSelectedItems.put(position, true);
        }
        notifyDataSetChanged();
    }

    /**
     * 获取选中项的集合
     *
     * @return
     */
    public SparseBooleanArray getSelectedItems() {
        return mSelectedItems;
    }

    /**
     * 返回被选中Item的数量
     *
     * @return
     */
    public int getSelectedItemCount() {
        return mSelectedItems.size();
    }
}
