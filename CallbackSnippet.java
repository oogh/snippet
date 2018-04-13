package me.oogh.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 工厂类
 */
class Factory {
    public static List<String> generateData() {
        List<String> dataSet = new ArrayList<>();
        dataSet.add("data1");
        dataSet.add("data2");
        dataSet.add("data3");
        dataSet.add("data4");
        return dataSet;

    }
}

/**
 * 测试类
 */
class Repository {
    public void loadData(OnDataLoadedCallback callback) {
        callback.onDataLoaded(me.oogh.demo.Factory.generateData());
    }

    // 定义 Callback
    interface OnDataLoadedCallback {
        void onDataLoaded(List<String> dataSet);
    }
}

/**
 * 销售商类
 */
class Seller {
    private me.oogh.demo.Repository repository = new me.oogh.demo.Repository();

    public void sell() {
        repository.loadData(new me.oogh.demo.Repository.OnDataLoadedCallback() {
            @Override
            public void onDataLoaded(List<String> dataSet) {
                for (String item : dataSet) {
                    System.out.println(item);
                }
            }
        });
    }
}

/**
 * 运行，测试
 */
public class CallbackSnippet {
    public static void main(String[] args) {
        me.oogh.demo.Seller seller = new me.oogh.demo.Seller();
        seller.sell();

    }
}
