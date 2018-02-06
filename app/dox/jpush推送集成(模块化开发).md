# 多模块极光推送集成

标签（空格分隔）： Android开发 开源项目

---
### 一、官网文档地址
* [Android SDK 集成指南][1]

### 推荐Gradle集成
#### jcenter 自动集成步骤
>说明 ： 使用jcenter自动集成的开发者，不需要在项目中添加jar和so，jcenter会自动完成依赖；在AndroidManifest.xml中不需要添加任何JPush SDK 相关的配置，jcenter会自动导入。

* 如果开发者需要修改组件属性，可以在本地的 AndroidManifest 中定义同名的组件并配置想要的属性，然后用 xmlns:tools 来控制本地组件覆盖 jcenter 上的组件。示例：
```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.android.tests.flavorlib.app"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:icon="@drawable/icon"
        android:name="com.example.jpushdemo.ExampleApplication"
        android:label="@string/app_name" >

        <service android:name="cn.jpush.android.service.PushService"
            android:process=":multiprocess"
            tools:node="replace" >

            ……
        </service>

    ……
  </application>

  ……
</manifest>
```

* 确认android studio的 Project 根目录的主 gradle 中配置了jcenter支持。（新建project默认配置就支持）
```
buildscript {
    repositories {
        jcenter()
    }
    ......
}

allprojets {
    repositories {
        jcenter()
    }
}
```
* 在 module 的 gradle 中添加依赖和AndroidManifest的替换变量。
```
android {
    ......
    defaultConfig {
        applicationId "com.xxx.xxx" //JPush上注册的包名.
        ......

        ndk {
            //选择要添加的对应cpu类型的.so库。
            abiFilters 'armeabi', 'armeabi-v7a', 'arm64-v8a'
            // 还可以添加 'x86', 'x86_64', 'mips', 'mips64'
        }

        manifestPlaceholders = [
            JPUSH_PKGNAME : applicationId,
            JPUSH_APPKEY : "你的appkey", //JPush上注册的包名对应的appkey.
            JPUSH_CHANNEL : "developer-default", //暂时填写默认值即可.
        ]
        ......
    }
    ......
}

dependencies {
    ......

    compile 'cn.jiguang.sdk:jpush:3.1.1'  // 此处以JPush 3.1.1 版本为例。
    compile 'cn.jiguang.sdk:jcore:1.1.9'  // 此处以JCore 1.1.9 版本为例。
    ......
}
```

>注 : 如果在添加以上 abiFilter 配置之后android Studio出现以下提示：
```
 NDK integration is deprecated in the current plugin. Consider trying the new experimental plugin
```

则在 Project 根目录的gradle.properties文件中添加：
```
 android.useDeprecatedNdk=true
```
>说明：若没有res/drawable-xxxx/jpush_notification_icon这个资源默认使用应用图标作为通知icon，在5.0以上系统将应用图标作为statusbar icon可能显示不正常，用户可定义没有阴影和渐变色的icon替换这个文件，文件名不要变。

###  说明(模块化开发集成步骤)
#### 遇到的问题
比如我们的messageCenter这个模块需要用到极光推送,当然其他的模块也需要这个模块提供服务,因此如果我们按照极光推送的集成流程只做了如下的代码
```
 manifestPlaceholders = [
                JPUSH_PKGNAME : "xxxxxx",
                JPUSH_APPKEY : "xxxxxx", //JPush上注册的包名对应的appkey.
                JPUSH_CHANNEL : "developer-default", //暂时填写默认值即可.
        ]
```
这个在编译的时候会出现如下的错误
```

	Attribute provider#cn.jpush.android.service.DataProvider@authorities at AndroidManifest.xml requires a placeholder substitution but no value for <JPUSH_PKGNAME> is provided.
/Users/wuyinlei/ASCode/CNMarket4Kotlin/app/src/main/AndroidManifest.xml:14:9-47 Error:
	Attribute permission#${JPUSH_PKGNAME}.permission.JPUSH_MESSAGE@name at AndroidManifest.xml:14:9-47 requires a placeholder substitution but no value for <JPUSH_PKGNAME> is provided.
/Users/wuyinlei/ASCode/CNMarket4Kotlin/app/src/main/AndroidManifest.xml:16:5-81 Error:
	Attribute uses-permission#${JPUSH_PKGNAME}.permission.JPUSH_MESSAGE@name at AndroidManifest.xml:16:5-81 requires a placeholder substitution but no value for <JPUSH_PKGNAME> is provided.

```
也就是提示JPUSH_PKGNAME这个没有提供值,解决方法如下,也就是在我们的主module(最顶级的)的build.gradle中加入如下语句
![image.png](http://upload-images.jianshu.io/upload_images/1316820-9133f3dc5d4070d3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```
 manifestPlaceholders = [
                JPUSH_PKGNAME: applicationId
        ]
```
在此编译就可以通过了`BUILD SUCCESSFUL in 8s`

  [1]: https://docs.jiguang.cn/jpush/client/Android/android_guide/