package com.hy.hyspirit.semang;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hy.hyspirit.RecyclerSlideAdapter2;
import com.hy.testasr.R;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class SMTestFragment extends Fragment {






    private RecyclerView recyclerView;
    private RecyclerSlideAdapter2 adapter;
    public   List<Smdata> users = new ArrayList<>();
   public Smdata smdata=new Smdata();
    public  List<Smdata> smdata1 = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_s_m_test, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        x.Ext.init(getActivity().getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);// 是否输出debug日志, 开启debug会影响性能.
        x.view().inject(getActivity());//没有用到view注解可以先不用

        recyclerView = getActivity().findViewById(R.id.recycler);
        adapter = new RecyclerSlideAdapter2(getActivity(), users);
        adapter.notifyDataSetChanged();
        adapter.setOnDelListener(new RecyclerSlideAdapter2.onSlideListener() {

            @Override
            public void onDel(int position) {
                Toast.makeText(getActivity(), "删除:" + position, Toast.LENGTH_SHORT).show();
                users.remove(position);
                adapter.notifyItemChanged(position);
            }


            @Override
           public void onTop(int position) {
                Smdata s = users.get(position);
                users.remove(s);
                adapter.notifyItemInserted(0);
                users.add(0, s);
                adapter.notifyItemRemoved(position + 1);
                if (mLayoutManager.findFirstVisibleItemPosition() == 0) {
                    recyclerView.scrollToPosition(0);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(getActivity()));


        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    SwipeMenuLayout viewCache = SwipeMenuLayout.getViewCache();
                    if (null != viewCache) {
                        viewCache.smoothClose();
                    }
                }
                return false;
            }
        });
        RefreshLayout refreshLayout = (RefreshLayout)getActivity().findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                users.clear();
                initData();
                adapter.notifyDataSetChanged();
            }
        });

    }


    public void initData() {

//        for (int i = 0; i < 40; i++) {
//            mDatas.add("item" + i);
//        }

        DbManager db = null;
        try {// 删除1
            db = x.getDb(daoConfig);

            smdata1= db.selector(Smdata.class).orderBy("id", false).findAll();
            for (Smdata user : smdata1){


                    smdata.id=+user.getId();
                    smdata.right=+user.getRight();
                    smdata.fault=+user.getFault();
                    smdata.unlook=+user.getUnlook();
                    smdata.data=user.getData();
                    users.add(smdata);
                Log.i("data","test" + smdata1);

        }


        } catch (DbException e) {
            e.printStackTrace();
            return ;
        }

    }

}

