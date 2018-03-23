package me.oogh.demo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.oogh.demo.R;
import me.oogh.demo.snippet.TabLocalFragment;
import me.oogh.demo.snippet.TabRemoteFragment;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class SnippetPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] mTitles;

    public SnippetPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTitles = mContext.getResources().getStringArray(R.array.titles);
    }

    public SnippetPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment instance = null;
        switch (position) {
            case 0:
                instance = TabLocalFragment.newInstance();
                break;
            case 1:
                instance = TabRemoteFragment.newInstance();
                break;
        }
        return instance;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
