package me.oogh.demo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oogh <oogh216@163.com>
 * @date 2018-03-23
 * @description
 */

public class DataSourceUtils {
    public static List<String> fakeData() {
        List<String> dataSet = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataSet.add("第 " + i + " 条 Content");
        }
        return dataSet;
    }
}
