package com.hy.hyspirit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hy.testasr.R;
import com.xuexiang.xui.widget.button.ButtonView;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.x;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ReportActivity2 extends BaseActivity {
    ButtonView mButtonView;
    ButtonView mButtonView2;
    TextView mTextView;
    TextView mTextView2;
    TextView mTextView3;
    TextView mTextView4;
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
        setContentView(R.layout.activity_report2);
        mButtonView=findViewById(R.id.buttonView5);
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(this);//没有用到view注解可以先不用
        mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent=new Intent(ReportActivity2.this, Activity_eyestest.class);
//                startActivity(intent);
                finish();
            }
        });
        mButtonView2=findViewById(R.id.buttonView4);
        mButtonView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ReportActivity2.this,MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });

        mTextView=findViewById(R.id.textView6);
        mTextView2=findViewById(R.id.textView7);
        mTextView3=findViewById(R.id.textView8);
        mTextView4=findViewById(R.id.textView9);
        DbManager db = null;
        try {// 删除1
            db = x.getDb(daoConfig);
            List<Sldata> users = new ArrayList<>();
            users= db.selector(Sldata.class).orderBy("id", true).limit(1).findAll();
            for (Sldata user : users){
                String s=String.valueOf(user.getRight());
                mTextView.setText(s);
                Log.i("slreport","-----"+s);
                DecimalFormat format = new DecimalFormat("0.0");
                String x=format.format(user.getShili());
                mTextView2.setText(x);
                String s2=String.valueOf(user.getRight2());
                mTextView.setText(s2);
                Log.i("slreport","-----"+s);
                String x2=format.format(user.getShili2());
                mTextView2.setText(x2);


            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_report2;
    }

    @Override
    protected void onBack() {

    }
}