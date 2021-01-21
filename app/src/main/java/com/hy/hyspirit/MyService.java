package com.hy.hyspirit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.hy.testasr.R;

import java.util.Calendar;
import java.util.Date;

public class MyService extends Service

  {  PendingIntent mPendingIntent;
      private static final int NOTIFY_ID=1234;//通知的唯一标识符
           private Calendar cal=null;

      @Override
      public void onCreate() {
          //添加过滤器并注册
          final IntentFilter filter=new IntentFilter();
          filter.addAction(Intent.ACTION_SCREEN_ON);
          filter.addAction(Intent.ACTION_SCREEN_OFF);
          filter.addAction(Intent.ACTION_TIME_TICK);
          registerReceiver(receiver, filter);
          super.onCreate();
      }

      @Nullable
      @Override
      public IBinder onBind(Intent intent) {
          return new MyBinder();
      }

      public class MyBinder extends Binder {
      }

      //主要功能，广播接收器
            private final BroadcastReceiver receiver=new BroadcastReceiver() {
          @Override
          public void onReceive(Context context, Intent intent) {
              SharedPreferences sp = getSharedPreferences("actm", Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = sp.edit();

              if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                  //保存屏幕启动时的毫秒数
                  editor.putLong("lasttime", new Date().getTime());
                  editor.commit();

                  //根据需要看是否需要在通知栏提醒
                  int sum = (int) sp.getLong("sum", 0L) / 1000;
                  int limit = sp.getInt("limit", 1) * 60;
                  if (limit <= sum) {
                      final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//获取通知管理器
//                      Notification notification = new Notification(R.drawable.a1, "警告", System.currentTimeMillis());//通知的时机

                      Notification notification = new Notification.Builder(context)
                              .setAutoCancel(true)
                              .setContentTitle("警告")
                              .setContentText("本日使用屏幕已超过预设，如需取消该警告请重新设置！！！")
                              .setContentIntent(mPendingIntent)
                              .setSmallIcon(R.drawable.a2)
                              .setWhen(System.currentTimeMillis())
                              .build();

                      notification.flags = Notification.FLAG_AUTO_CANCEL;//点击一次通知就自动消失
                      PendingIntent pIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);//跳转到主界面

                      manager.notify(NOTIFY_ID, notification);//执行

                  }
              } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                  //保存屏幕总工作时间
                  long lasttime = sp.getLong("lasttime", new Date().getTime());
                  long sum = sp.getLong("sum", 0L);
                  sum += new Date().getTime() - lasttime;
                  editor.putLong("sum", sum);
                  editor.commit();
              } else if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
                  cal = Calendar.getInstance();
                  if (cal.get(Calendar.HOUR_OF_DAY) == 0 && cal.get(Calendar.MINUTE) == 0)
                      //每天凌晨自动更新数据
                      editor.putLong("sum", 0L);
                  editor.commit();

                  //取消通知栏通知
                  final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                  manager.cancel(NOTIFY_ID);
              }
          }


      };
  }