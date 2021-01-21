package com.hy.hyspirit.semang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hy.hyspirit.BaseActivity;
import com.hy.hyspirit.MainActivity3;
import com.hy.hyspirit.Activity_eyestest;
import com.hy.testasr.R;
import com.xuexiang.xui.widget.button.ButtonView;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class SMreportActivity extends BaseActivity {
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

    TextView mTextView;
    TextView mTextView2;
    TextView mTextView3;
    ButtonView mButtonView;
    ButtonView mButtonView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_mreport);
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(this);//没有用到view注解可以先不用
        mTextView=findViewById(R.id.textView16);
        mTextView2=findViewById(R.id.textView15);
        mTextView3=findViewById(R.id.textView13);
        mButtonView=findViewById(R.id.buttonView5);
        mButtonView2=findViewById(R.id.buttonView4);
        mButtonView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SMreportActivity.this, MainActivity3.class);
                startActivity(intent);

                finish();
            }
        });

        mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });

        DbManager db = null;

                        try {// 删除1
                            db = x.getDb(daoConfig);
                            List<Smdata> users = new ArrayList<>();
                            users= db.selector(Smdata.class).orderBy("id", true).limit(1).findAll();
                            for (Smdata user : users){
                                Log.i("testdb","test" + user);
                                String r= String.valueOf(user.getRight());
                                mTextView.setText(r);
                                String f= String.valueOf(user.getFault());
                                String r2= String.valueOf((5-user.getRight())*20);
                                mTextView2.setText(r2+"%");
                                String u= String.valueOf(user.getUnlook());
                                if (user.getRight()==5){
                                    mTextView3.setText("恭喜你！没有色盲症状。");
                                }else  if (user.getRight()==4){
                                    mTextView3.setText("刚才是不是没测好，要不再来一次。");
                                }else  if (user.getRight()==3){
                                    mTextView3.setText("您有患色盲的可能，请多测几次。");
                                }else  if (user.getRight()==2){
                                    mTextView3.setText("您有很大几率是色盲患者。");
                                }else  if (user.getRight()==1){
                                    mTextView3.setText("您很大几率是色盲患者。");
                                }else {
                                    mTextView3.setText("您很大几率是色盲患者。");
                                }

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_s_mreport;
    }

    @Override
    protected void onBack() {

    }
}