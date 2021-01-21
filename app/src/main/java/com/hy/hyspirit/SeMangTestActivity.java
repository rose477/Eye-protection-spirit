package com.hy.hyspirit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.hy.hyspirit.semang.BlankFragment;
import com.hy.testasr.R;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.x;

public class SeMangTestActivity extends BaseActivity   {


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


    BlankFragment mBlankFragment=new BlankFragment();
    TextView mTextView;
    TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se_mang_test);
        mTextView=findViewById(R.id.textView15);
        mTextView2=findViewById(R.id.textView18);
        x.Ext.init(getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(this);//没有用到view注解可以先不用




        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,mBlankFragment).commit();

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //对ui的操作 只能放到主线程
//                Timer timer=new Timer();
//                TimerTask task = new TimerTask() {
//                    public void run() {
//                        //每次需要执行的代码放到这里面。
//                        DbManager db = null;
//
//                        try {// 删除1
//                            db = x.getDb(daoConfig);
//                            List<Smdata> users = new ArrayList<>();
//                            users= db.selector(Smdata.class).orderBy("id", true).limit(1).findAll();
//                            for (Smdata user : users){
//                                Log.i("testdb","test" + user);
//                                String r= String.valueOf(user.getRight());
//                                mTextView.setText(r);
//                                String f= String.valueOf(user.getFault());
//                                mTextView2.setText(f);
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            return;
//                        }
//
//                    }
//                };
//
//                //firstTime为Date类型,period为long，表示从firstTime时刻开始，每隔period毫秒执行一次。
//                timer.schedule(task, 0, 50);
//
//
//            }
//        });



    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_se_mang_test;

    }

    @Override
    protected void onBack() {

    }


}