package me.oogh.demo.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class ActivityUtils {
    public static void addFragmentToActivity(
            @NonNull int containerId,
            @NonNull FragmentManager manager,
            @NonNull Fragment fragment) {
        manager.beginTransaction().add(containerId, fragment).commit();
    }
}
