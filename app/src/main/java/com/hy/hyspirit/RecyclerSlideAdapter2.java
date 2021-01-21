package com.hy.hyspirit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.hy.hyspirit.semang.SMTestFragment;
import com.hy.hyspirit.semang.Smdata;
import com.hy.testasr.R;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;



import java.util.List;



public class RecyclerSlideAdapter2 extends RecyclerView.Adapter<com.hy.hyspirit.RecyclerSlideAdapter2.Slide> {

    private Context context;
    private LayoutInflater mInflater;
    Activity_eyestest mActivity_eyestest=new Activity_eyestest();
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




    private List<Smdata> mDatas;


    public RecyclerSlideAdapter2(Context context, List<Smdata> mDatas) {
        this.context = context;

        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public com.hy.hyspirit.RecyclerSlideAdapter2.Slide onCreateViewHolder(ViewGroup parent, int viewType) {

        return new com.hy.hyspirit.RecyclerSlideAdapter2.Slide(mInflater.inflate(R.layout.semang_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final com.hy.hyspirit.RecyclerSlideAdapter2.Slide holder, final int position) {
        //这句话关掉IOS阻塞式交互效果，并依此打开左滑右滑
//        ((SwipeMenuLayout) holder.itemView).setIos(false).setLeftSwipe(position % 2 == 0 ? true : false);

//        holder.content.setText(mDatas.get(position));

        //验证长按
        holder.content.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "longclig", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 如果删除时，不适用adapter.notifyItemRemoved(position),则删除没有动画效果
                 * 如果想让侧滑菜单同时关闭，需要同时调用(CstSwipeDelMenu)holder.itemView).quickClose();
                 */


                    mOnSlideListener.onDel(holder.getAdapterPosition());





            }
        });
        //注意事项，设置item点击，不能对真个holder.itemView设置，只能对第一个TextView设置
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "onClick:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //置顶
        holder.btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mOnSlideListener) {
                    mOnSlideListener.onTop(holder.getAdapterPosition());
                }
            }
        });

        SMTestFragment smTestFragment=new SMTestFragment();
        smTestFragment.initData();
            Smdata smdata;
            smdata=smTestFragment.smdata1.get(position);

            holder.mTextView.setText(String.valueOf(smdata.right));
            holder.mTextView2.setText(String.format("%d%%", (5 - (smdata.right)) * 20));
            holder.mTextView3.setText(String.valueOf(smdata.fault));
            holder.mTextView4.setText(String.valueOf(smdata.unlook));
            holder.mTextView5.setText("第"+smdata.id+"次色盲测试");
            holder.mTextView6.setText(smdata.data);
            Log.i("data","data"+smdata);
        Log.i("data","data111111"+mDatas);
        Log.i("data","data22222222"+smTestFragment.users);
        Log.i("data","data333333333"+smTestFragment.smdata1);
        Log.i("data","data454444444"+smTestFragment.smdata);


    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSlideListener {
        void onDel(int position);

        void onTop(int position);
    }

    private com.hy.hyspirit.RecyclerSlideAdapter2.onSlideListener mOnSlideListener;

    public com.hy.hyspirit.RecyclerSlideAdapter2.onSlideListener getmOnSlideListener() {
        return mOnSlideListener;
    }

    public void setOnDelListener(com.hy.hyspirit.RecyclerSlideAdapter2.onSlideListener mOnDelListener) {
        this.mOnSlideListener = mOnDelListener;
    }

    public class Slide extends RecyclerView.ViewHolder {
        SwipeMenuLayout content;
        Button btnDelete;

        Button btnTop;
        TextView mTextView;
        TextView mTextView2;
        TextView mTextView3;
        TextView mTextView4;
        TextView mTextView5;
        TextView mTextView6;

        public Slide(View itemView) {
            super(itemView);
            content =  itemView.findViewById(R.id.semang_item);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            mTextView=itemView.findViewById(R.id.textView21);
            mTextView2=itemView.findViewById(R.id.textView23);
            mTextView3=itemView.findViewById(R.id.textView22);
            mTextView4=itemView.findViewById(R.id.textView24);
            mTextView5=itemView.findViewById(R.id.top_text);
            mTextView6=itemView.findViewById(R.id.bottom_text);

            btnTop = (Button) itemView.findViewById(R.id.btnTop);
        }
    }
}