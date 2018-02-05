# ARouter简单实用

标签（空格分隔）： Android开发 开源项目

---

### 一、使用方法
#### 1.1 导包
导包现在分java和kotlin， java的导包方法如下，在根moduel的build.gradle添加如下内容：
```
android {
    defaultConfig {
    ...
    javaCompileOptions {
        annotationProcessorOptions {
        arguments = [ moduleName : project.getName() ]
        }
    }
    }
}

dependencies {
    // 替换成最新版本, 需要注意的是api，最新版本看文章开头
    // 要与compiler匹配使用，均使用最新版可以保证兼容
    compile 'com.alibaba:arouter-api:x.x.x'
    annotationProcessor 'com.alibaba:arouter-compiler:x.x.x'
    ...
}
```
如果java使用的是apt，导入方法则是如下：
```
apply plugin: 'com.neenbedankt.android-apt'

buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
}

//
apt {
    arguments {
        moduleName project.getName();
    }
}

dependencies {
    //这里填写最新的版本看文章开始
    compile 'com.alibaba:arouter-api:x.x.x'
    apt 'com.alibaba:arouter-compiler:x.x.x'
    ...
}
```
kotlin的导包方法如下，在根moduel的build.gradle添加如下内容：
```
apply plugin: 'kotlin-kapt'

kapt {
    arguments {
        arg("moduleName", project.getName())
    }
}

dependencies {
    //这里填写最新的版本，看文章开始
    compile 'com.alibaba:arouter-api:x.x.x'
    kapt 'com.alibaba:arouter-compiler:x.x.x'
    ...
}
```

>说明:如果是组件化开发的话,比如有usercenter、messagecenter、maincenter,那么这几个module的build.gradle都需要**apt**这个导入

### 二、初始化
1、Applicaption中
```
 //判断当只有当前app处于debug的时候才去打印日志
        if (AppUtils.idDebug(this)) {
            //ARouter初始化
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()
        }
        ARouter.init(this)
```
2、Activity中
```

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            
        //ARouter注册  当然了这个也可以直接在Applicaption的onCreate()中直接初始化
        ARouter.getInstance().inject(this)
    }

```

### 三、简单使用
自定义一个跳转的URL地址类(kotlin)
```
object RouterPath {

    //用户模块
    class UserCenter{
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    //消息模块
    class MessageCenter {
        companion object {
            const val PATH_MESSAGE_PUSH = "/messageCenter/push"
        }
    }

    class MainCenter {
        companion object {
            const val MAIN_PATH = "/mainCenter/main"
        }
    }
}
```
在需要跳转的activity上注解如下(以LoginActivity为例)
```
@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity:BaseActivity(){

}
```
跳转只需要以下方式
```
 ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
```

#### 关于更详细的介绍可以去官网查看文档
* [点击查看官方文档][1]

### 四、遇到的问题
#### 问题描述
我使用的是组件化开发,当使用ARouter进行跳转的时候(登录到主界面)会有一个当前App的桌面图标的一个动画,也就是这个[issue][2]
#### 解决方法就是如下代码
```
 ARouter.getInstance()
                .build(RouterPath.MainCenter.MAIN_PATH)
                //在这里添加这个动画  是为了当引入ARouter的时候进行activity跳转的时候会有一个默认的带有
                //app图片的动画    下面的动画自己制定 或者给一个空的
                .withTransition(R.anim.login_activity_in, R.anim.login_activity_out)
                //这个navigation里面传入当前的**activity上下文**,要不然不起作用
                .navigation(this)
```
空的anim动画文件如下
```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
        <translate
            android:duration="0"

            />
</set>
```


  [1]: https://github.com/alibaba/ARouter
  [2]: https://github.com/alibaba/ARouter/issues/242