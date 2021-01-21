package com.hy.hyspirit.semang;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.testasr.R;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.ButtonView;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment4 extends Fragment {

    ButtonView mButtonView;
    ButtonView mButtonView2;
    ButtonView mButtonView3;
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


    BlankFragment5 mBlankFragment5 = new BlankFragment5();
    TitleBar mTitleBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank4, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        x.Ext.init(getActivity().getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(getActivity());//没有用到view注解可以先不用
        init();
        onclick();
        mTitleBar=getActivity().findViewById(R.id.titleBar);
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().finish();

            }
        });

    }

    private void onclick() {
        mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    DbManager db = null;
                    db = x.getDb(daoConfig);
                    List<Smdata> users = new ArrayList<>();
                    users= db.selector(Smdata.class).orderBy("id", true).limit(1).findAll();
                    for (Smdata user : users){
                        int i=user.getFault()+1;
                        user.setFault(i);

                        db.saveOrUpdate(user);
                        // db.update(user);或者用这个方法修改
                    }


                } catch (DbException e) {
                    e.printStackTrace();
                    return;
                }

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout2, mBlankFragment5, null)
                        .addToBackStack(null)
                        .commit();

            }
        });
        mButtonView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DbManager db = null;
                    db = x.getDb(daoConfig);
                    List<Smdata> users = new ArrayList<>();
                    users= db.selector(Smdata.class).orderBy("id", true).limit(1).findAll();
                    for (Smdata user : users){

                        int i=user.getRight()+1;
                        user.setRight(i);

                        db.saveOrUpdate(user);
                        // db.update(user);或者用这个方法修改
                    }

                } catch (DbException e) {
                    e.printStackTrace();
                    return;
                }
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout2, mBlankFragment5, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        mButtonView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DbManager db = null;
                    db = x.getDb(daoConfig);
                      Smdata user = new Smdata();
                    user.setId(2);
                    int i = user.getUnlook() + 1;
                    user.setUnlook(i);

                    db.saveOrUpdate(user);
                    // db.update(user);或者用这个方法修改
                } catch (DbException e) {
                    e.printStackTrace();
                    return;
                }
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout2, mBlankFragment5, null)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void init() {
        mButtonView = getActivity().findViewById(R.id.radiobutton1);
        mButtonView2 = getActivity().findViewById(R.id.radiobutton2);
        mButtonView3 = getActivity().findViewById(R.id.radiobutton3);


    }

}
