package com.hy.hyspirit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.hy.hyspirit.semang.Smdata;
import com.hy.testasr.R;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {



    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("test.db")
            // 不设置dbDir时, 默认存储在app的私有目录.
            // .setDbDir(new File("/sdcard")) // "sdcard"的写法并非最佳实践, 这里为了简单, 先这样写了.
            .setDbVersion(2)
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    // 开启WAL, 对写入加速提升巨大
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                    // TODO: ...
                    // db.addColumn(...);
                    // db.dropTable(...);
                    // ...
                    // or
                    // db.dropDb();
                }
            });
    public MyService.MyBinder mMyBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(this);//没有用到view注解可以先不用



        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                DbManager db = null;
                try {
                    db = x.getDb(daoConfig);
                    Sldata mSldata = new Sldata();
                    mSldata.setRight(0);
                    mSldata.setRight2(0);
                    mSldata.setShili(4.7);
                    mSldata.setShili2(4.7);

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
                    Date date = new Date(System.currentTimeMillis());

                    mSldata.setData(simpleDateFormat.format(date));
                    db.save(mSldata);
                } catch (DbException e) {
                    e.printStackTrace();
                    return;
                }
                SharedPreferences sharedPreferences= getSharedPreferences("user", MODE_PRIVATE);
                boolean islogin = sharedPreferences.getBoolean("islogin",false);//如果读不到文件或者当key不存在时 返回false
                //判断用户是否登录过

                if (islogin) {  //如果登录过 跳转到 MainActivity

                    Intent it = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(it);
                }else { //如果没有登录过 跳转到 LoginActivity

                     Intent it = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(it);
                }


                finish();
            }
        };
        timer.schedule(task, 1000 * 3);
    }


   
}