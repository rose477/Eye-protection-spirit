package com.hy.hyspirit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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


public class SgFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerSlideAdapter3 adapter;
    public   List<Sgdata> users = new ArrayList<>();
    public Sgdata mSgdata=new Sgdata();
    public List<Sgdata> mSgdata1 = new ArrayList<>();
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
        return inflater.inflate(R.layout.fragment_sg, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        recyclerView = getActivity().findViewById(R.id.recycler);
        adapter = new RecyclerSlideAdapter3(getActivity(), users);
        adapter.setOnDelListener(new RecyclerSlideAdapter3.onSlideListener() {

            @Override
            public void onDel(int position) {
                Toast.makeText(getActivity(), "删除:" + position, Toast.LENGTH_SHORT).show();
                users.remove(position);
                adapter.notifyItemChanged(position);
            }
            @Override
            public void onTop(int position) {
                Sgdata s = users.get(position);
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
//        mDatas = new ArrayList<>();
//        for (int i = 0; i < 40; i++) {
//            mDatas.add("item" + i);
//        }
        DbManager db = null;
        try {// 删除1
            db = x.getDb(daoConfig);

            mSgdata1 = db.selector(Sgdata.class).orderBy("id", false).findAll();
            for (Sgdata user2 : mSgdata1) {


                mSgdata.id = +user2.getId();
                mSgdata.right = user2.getRight();
                mSgdata.shili = String.valueOf(user2.getShili());
                mSgdata.data = user2.getData();
                mSgdata.report=user2.getReport();
                users.add(mSgdata);


            }


        } catch (DbException e) {
            e.printStackTrace();
            return;
        }
    }
}

