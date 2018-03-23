package me.oogh.demo.common;

import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import me.oogh.demo.R;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class ActionModeCallback implements ActionMode.Callback {

    private Handler mHandler;
    private Actionable mActionView;

    public ActionModeCallback(Actionable actionView, Handler handler) {
        mActionView = actionView;
        mHandler = handler;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.main_action_mode_items, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_main_delete:
                mHandler.handle(Tag.DELETE);
                break;
            case R.id.action_main_share:
                mHandler.handle(Tag.SHARE);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        mActionView.destroyActionMode();
    }

    public enum Tag {
        DELETE, SHARE
    }

    public interface Handler {
        void handle(Tag tag);
    }
}
