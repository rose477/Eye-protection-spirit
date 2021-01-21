package com.hy.hyspirit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hy.hyspirit.semang.Smdata;
import com.hy.testasr.R;
import com.xuexiang.xui.widget.button.ButtonView;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class SgTestActivity extends BaseActivity {

    ButtonView mButtonView;
    ButtonView mButtonView2;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sg_test);
        mButtonView=findViewById(R.id.buttonView6);
        mButtonView2=findViewById(R.id.buttonView7);
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(this);//没有用到view注解可以先不用

        mButtonView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DbManager db = null;
                    db = x.getDb(daoConfig);
                    List<Sgdata> users = new ArrayList<>();
                    users= db.selector(Sgdata.class).orderBy("id", true).limit(1).findAll();
                    for (Sgdata user : users){


                        user.setRight("正确");
                        user.setShili("无症状");
                        user.setReport("恭喜您，没有散光症状");
                        db.saveOrUpdate(user);
                        // db.update(user);或者用这个方法修改
                    }

                } catch (DbException e) {
                    e.printStackTrace();
                    return;
                }

                Intent intent=new Intent(SgTestActivity.this,ReportActivity3.class);
                startActivity(intent);
                finish();

            }
        });
        mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DbManager db = null;
                    db = x.getDb(daoConfig);
                    List<Sgdata> users = new ArrayList<>();
                    users= db.selector(Sgdata.class).orderBy("id", true).limit(1).findAll();
                    for (Sgdata user : users){


                        user.setRight("错误");
                        user.setShili("疑似");
                        user.setReport("请您多测几次，疑似散光症状。");
                        db.saveOrUpdate(user);
                        // db.update(user);或者用这个方法修改
                    }

                } catch (DbException e) {
                    e.printStackTrace();
                    return;
                }

                Intent intent=new Intent(SgTestActivity.this,ReportActivity3.class);
                startActivity(intent);
                finish();

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sg_test;
    }

    @Override
    protected void onBack() {

    }
}