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

## 其他

> 创建本地仓库，分支并push到远程仓库

```shell
git init
git checout -b <Branch Name>
git remote add origin <Remote Address>
git add .
git commit -m "<Message Something>"
git push -u origin <Branch Name>
```

