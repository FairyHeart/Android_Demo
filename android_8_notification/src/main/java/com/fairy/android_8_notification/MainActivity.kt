package com.fairy.android_8_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channelId = "chat"
            var channelName = "聊天消息"
            var importance = NotificationManager.IMPORTANCE_HIGH
            this.createNotificationChannel(channelId, channelName, importance)

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT
            createNotificationChannel(channelId, channelName, importance)
        }

        chat_btn.setOnClickListener {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //获取通知渠道，如果发现通知关闭了，发送消息的时候可以打开通知设置页面
                val channel = notificationManager.getNotificationChannel("chat")
                if (channel.importance == NotificationManager.IMPORTANCE_NONE) {
                    val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
                    intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                    intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.id)
                    startActivity(intent)
                    Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
                }
            }
            val content = "明天上班不?${count}"
            val intent = Intent(this, NotificationActivity::class.java)
            intent.putExtra("data", content)
            val clickPending = PendingIntent.getActivity(this, 0, intent, 0)

            val notification = NotificationCompat.Builder(this, "chat")//通知渠道号
                .setContentTitle("收到一条聊天信息")
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setNumber(1)//传入未读消息的数量
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setContentIntent(clickPending)//通知点击调整到activity
                .setAutoCancel(true)
                .build()
            notificationManager.notify(count, notification)
            count++

        }

        subscribe_btn.setOnClickListener {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notification = NotificationCompat.Builder(this, "subscribe")//通知渠道号
                .setContentTitle("收到一条订阅消息")
                .setContentText("地铁沿线30万商铺抢购中！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build()
            notificationManager.notify(2, notification)
        }
    }

    /**
     * 创建通知渠道
     * @param channelId 渠道ID 唯一性
     * @param channelName 渠道名字，渠道名称是给用户看的，需要能够表达清楚这个渠道的用途
     * @param importance 重要等级
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(channelId: String, channelName: String, importance: Int) {
        val channel = NotificationChannel(channelId, channelName, importance)
        channel.setShowBadge(true)//允许这个渠道下的通知显示角标，不掉用系统默认也会显示
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
