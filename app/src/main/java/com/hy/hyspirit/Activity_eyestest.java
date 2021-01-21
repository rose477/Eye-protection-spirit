package com.hy.hyspirit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hy.hyspirit.semang.SMTestFragment;
import com.hy.hyspirit.semang.Smdata;
import com.hy.testasr.R;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.ButtonView;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Activity_eyestest extends BaseActivity {


    EyeTestFragment mEyeTestFragment=new EyeTestFragment();
    SMTestFragment mSMTestFragment=new SMTestFragment();
    SgFragment mSgFragment=new SgFragment();
    TitleBar mTitleBar;
    ConstraintLayout mConstraintLayout1;
    ConstraintLayout mConstraintLayout2;
    ConstraintLayout mConstraintLayout3;
    ButtonView mButtonView1;
    ButtonView mButtonView2;
    ButtonView mButtonView3;

    public  Smdata smdata = new Smdata();
    public  Sldata mSldata = new Sldata();
    public  Sgdata mSgdata = new Sgdata();
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
        setContentView(R.layout.activity_eyestest);
        mTitleBar=findViewById(R.id.titleBar);
        mConstraintLayout1=findViewById(R.id.constraintLayout2);
        mConstraintLayout1.setSelected(true);
        x.Ext.init(this.getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(this);//没有用到view注解可以先不用
        Log.i("idata","-------"+i);
        DbManager db = null;
        try {
            db = x.getDb(daoConfig);

            smdata.setRight(0);
            smdata.setFault(0);
            smdata.setUnlook(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间

            Date date = new Date(System.currentTimeMillis());


            smdata.setData(simpleDateFormat.format(date));
            db.save(smdata);
        } catch (DbException e) {
            e.printStackTrace();
            return;
        }
        DbManager db2 = null;
        try {
            db2 = x.getDb(daoConfig);

            mSgdata.setRight("未检测");
            mSgdata.setShili("未检测");
           mSgdata.setReport("未检测");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
            Date date = new Date(System.currentTimeMillis());

            mSgdata.setData(simpleDateFormat.format(date));
            db2.save(mSgdata);
        } catch (DbException e) {
            e.printStackTrace();
            return;
        }
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mEyeTestFragment).commit();//默认先加载fragment1


        mConstraintLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mEyeTestFragment).commit();//默认先加载fragment1
                mConstraintLayout1.setSelected(true);
                mConstraintLayout2.setSelected(false);
                mConstraintLayout3.setSelected(false);




            }
        });

        mConstraintLayout2=findViewById(R.id.constraintLayout);
        mConstraintLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mSMTestFragment).commit();//默认先加载fragment1
                mConstraintLayout1.setSelected(false);
                mConstraintLayout2.setSelected(true);
                mConstraintLayout3.setSelected(false);



            }
        });
        mConstraintLayout3=findViewById(R.id.constraintLayout3);
        mConstraintLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mSgFragment).commit();//默认先加载fragment1
                mConstraintLayout1.setSelected(false);
                mConstraintLayout2.setSelected(false);
                mConstraintLayout3.setSelected(true);



            }
        });
        mButtonView1=findViewById(R.id.buttonView2);
        mButtonView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mEyeTestFragment).commit();//默认先加载fragment1
                Intent intent=new Intent(Activity_eyestest.this,TestEyeActivity.class);



                startActivity(intent);
                mConstraintLayout1.setSelected(true);
                mConstraintLayout2.setSelected(false);
                mConstraintLayout3.setSelected(false);
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

            }
        });
        mButtonView2=findViewById(R.id.buttonView);
        mButtonView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Activity_eyestest.this,SeMangTestActivity.class);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mSMTestFragment).commit();//默认先加载fragment1

                startActivity(intent);
                mConstraintLayout1.setSelected(false);
                mConstraintLayout2.setSelected(true);
                mConstraintLayout3.setSelected(false);
                DbManager db = null;
                try {
                    db = x.getDb(daoConfig);

                    smdata.setRight(0);
                    smdata.setFault(0);
                    smdata.setUnlook(0);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间

                    Date date = new Date(System.currentTimeMillis());


                    smdata.setData(simpleDateFormat.format(date));
                    db.save(smdata);
                } catch (DbException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
        mButtonView3=findViewById(R.id.buttonView3);
        mButtonView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,mSgFragment).commit();//默认先加载fragment1
                Intent intent=new Intent(Activity_eyestest.this,SgTestActivity.class);
                startActivity(intent);
                mConstraintLayout1.setSelected(false);
                mConstraintLayout2.setSelected(false);
                mConstraintLayout3.setSelected(true);
                DbManager db2 = null;
                try {
                    db2 = x.getDb(daoConfig);

                    mSgdata.setRight("未检测");
                    mSgdata.setShili("未检测");
                    mSgdata.setReport("未检测");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
                    Date date = new Date(System.currentTimeMillis());

                    mSgdata.setData(simpleDateFormat.format(date));
                    db2.save(mSgdata);
                } catch (DbException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_eyestest;
    }

    @Override
    protected void onBack() {

    }

    @Override
    protected void initView() {
        super.initView();


    }
}