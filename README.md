# snippet

> 代码片段，记录学习过程



## 项目说明

### 1. 不同项目对应不同的分支

- 主分支:  *master*
- 代码片段分支: snippet_xx_xx_xx

### 2. 分支的命令格式: 

- *snippet\_年\_月\_日*

### 3. 如何克隆指定分支: 

- *git clone -b <分支名称> <仓库地址>* ，例如: 

```shell
git clone -b snippet_18_03_24 https://github.com/oogh/snippet.git
```



## 项目集合

### 1. [snippet_18_03_24](https://github.com/oogh/snippet/tree/snippet_18_03_24)

​	MVP + RecyclerView + GestureDetector + ButterKnife + EventBus + ActionMode + ...

### 2. [snnipet_18_04_11](https://github.com/oogh/snippet/tree/snnipet_18_04_11)

​	Android 中点击空白位置 隐藏软键盘

### 3. [snippet_18_04_12](https://github.com/oogh/snippet/tree/snippet_18_04_12)

​	Java将阿拉伯数字转换成大写字母，并正确拼读，eg:

```html
输入: 1024
输出: 一千零二十四 (可根据需要更改大写方式, 如可写成：壹仟零贰拾肆)
```

> 注: 暂只支持 Integer

### 4. [snippet_18_04_13](https://github.com/oogh/snippet/tree/snippet_18_04_13)

​	在Kotlin中如何使用lambda风格的回调

```kotlin
repository.loadData { dataSet -> {
    // do something ...
}}
```
- **功能描述**

  *销售商* 从 *仓库* 拿货销售，*货物* 在 *工厂* 中生产


- **callback**

  - 角色: 物流人员

  - *销售商(Seller)* 派 *物流人员(Callback)* 去 *仓库中(Repository)* 取货 

    ```java
    // Seller类中
    repository.loadData(new OnDataLoadedCallback(){// 派遣 callback 去取货
       @Override
        public void onDataLoaded(List<String> dataSet){
            // callback拿到货后，运回来
        }
    });
    ```

  - *仓库(Repository)* 从 *工厂(Factory)* 得到 *货物(dataSet)* 以后交给 *物流人员(Callback)*

    ```java
    // Repository类中
    public void loadData(OnDataLoadedCallback callback){ // 接收一个物流人员
        callback.onDataLoaded(Factory.generateData()); // 让物流人员把货运回去
    }
    ```

- **kotlin写法**

  ```kotlin
  // Seller类中
  repository.loadData(dataSet -> {
      // 拿到获货物以后
  })
  ```

  ```kotlin
  // Repository类中
  fun loadData(callback : (dataSet : List<String>) -> Unit) {
      callback.invoke(Factory.generateData())
  }
  ```

## 其他

> 创建本地仓库，分支并push到远程仓库

```shell
git init
git checkout -b <Branch Name>
git remote add origin <Remote Address>
git add .
git commit -m "<Message Something>"
git push -u origin <Branch Name>
```

> Git 邮箱问题
>
> 【描述】：未配置全局邮箱时，git commit后会有一个提示，自动配置一个邮箱，那往往不是我们想要的。

```shell
# 1. 输入命令，打开配置文件，去掉注释，配置邮箱
git config --global --edit
# 2. 更新已提交的内容的邮箱设置
git commit --amend --reset-author
```

> Git 删除远程分支

```shell
# 查看远程分支
git branch -r

# 删除远程分支
git branch -r -d origin/<branch-name>
git push origin :<branch-name>
```



