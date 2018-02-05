package com.ruolan.factory.router

/**
 * Created by wuyinlei on 2018/1/30.
 *
 * @function
 */
/*
    模块路由 路径定义
 */
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

    //主模块
    class MainCenter {
        companion object {
            const val MAIN_PATH = "/mainCenter/push"
        }
    }

}