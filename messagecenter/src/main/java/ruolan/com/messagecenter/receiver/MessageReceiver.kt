package ruolan.com.messagecenter.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.ruolan.factory.event.MessageBadgeEvent
import com.ruolan.factory.router.RouterPath
import org.json.JSONObject

/**
 * Created by wuyinlei on 2018/1/29.
 *
 * @function
 */

class MessageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent?.extras
        Log.d("MessageReceiver", "onReceive - " + intent?.action + ", extras: " + bundle)


        when {
            JPushInterface.ACTION_REGISTRATION_ID == intent?.action -> Log.d("MessageReceiver", "JPush用户注册成功")
            JPushInterface.ACTION_MESSAGE_RECEIVED == intent?.action -> {
                Log.d("MessageReceiver", "接受到推送下来的自定义消息")
                Bus.send(MessageBadgeEvent(true))
            }
            JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent?.action -> Log.d("MessageReceiver", "接受到推送下来的通知")
            JPushInterface.ACTION_NOTIFICATION_OPENED == intent?.action -> {
                Log.d("MessageReceiver", "用户点击打开了通知")
                val extra = bundle?.getString(JPushInterface.EXTRA_EXTRA)
                val json = JSONObject(extra)
                val orderId = json.getInt("orderId")
//                ARouter.getInstance().build(RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
//                        .withInt(ProviderConstant.KEY_ORDER_ID,orderId)
//                        .navigation()

            }
            else -> Log.d("MessageReceiver", "Unhandled intent - " + intent?.action)
        }
    }


}
