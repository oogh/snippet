package me.oogh.demo.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description RecyclerView中Item点击事件帮助类
 */

public class OnItemClickHelper extends RecyclerView.SimpleOnItemTouchListener {

    // 手势检测类
    private GestureDetector mGestureDetector;

    public OnItemClickHelper(@NonNull Context context, @NonNull final RecyclerView rv, @NonNull final OnItemClickListener listener) {
        /*
         * 创建一个手势检测对象
         * 传入一个手势检测监听器
         * 当检测到对应手势时，手势检测监听器执行对应的方法，方法的内部由我们自己创建的监听器处理该事件
         *
         * 如本例中：
         * 1. 创建 mGestureDetector 对象
         * 2. 传入 SimpleOnGestureListener 监听器
         * 3. 检测到 SingleTapUp 手势时，onSingleTapUp方法执行
         * 4. 在 onSingleTapUp 方法内部，真正处理该手势的是我们自己定义的OnItemClickListener.onClick(view,position)方法
         */
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View itemView = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(itemView);
                if (itemView != null) {
                    listener.onClick(itemView, position);
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View itemView = rv.findChildViewUnder(e.getX(), e.getY());
                int position = rv.getChildAdapterPosition(itemView);
                if (itemView != null) {
                    listener.onLongPress(itemView, position);
                }
            }
        });
    }

    /*
     dispatch -> intercept - on touch
     Android View 事件分发机制：
     1. dispatchTouchEvent: 分发触摸事件
        -> A. 当触摸事件发生时
            -> a. 由上一级处理(onTouchEvent返回false时)
            -> b. 还是自己处理(onInterceptTouchEvent返回true时， 在onTouchEvent中自己处理)
            -> c. 交给下一级处理(onInterceptTouchEvent返回false时)
        -> B. 内部调用 onInterceptTouchEvent 和 onTouchEvent 方法
     2. onInterceptTouchEvent: 是否拦截？ (只有ViewGroup才有这个，以为只有ViewGroup才有下一级子View)
        -> A. 拦截，自己处理，返回 true
        -> B. 不拦截，交给下一级处理， 返回 false
     3. onTouchEvent: 自己处理
        -> return true 表示自己有能力处理，自己处理
        -> return false 表示自己能力有限，处理不了，那就返回到上一级处理
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        // 在分发给子View之前，使用手势检测对该MotionEvent事件进行检测
        // 检测到什么事件，手势监听器就处理什么事件，
        // 手势监听器处理事件，由我们自定义的监听器接管了
        // 这里虽然返回了false,交给下一级子View去处理该MotionEvent事件
        // 但是，我们无法编写该子View的onTouchEvent事件（除非子View使用了自定义View）
        // 所以这里我们就借GestureDetector之手，完成我们的事件监听处理就好了
        mGestureDetector.onTouchEvent(e);
        return false;
    }
}
