package me.oogh.demo.common;

import android.view.View;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description RecyclerView中的Item的点击事件监听器
 */

public interface OnItemClickListener {
    /**
     * 单击事件
     *
     * @param view
     * @param position
     */
    void onClick(View view, int position);

    /**
     * 长按事件
     *
     * @param view
     * @param position
     */
    void onLongPress(View view, int position);
}
