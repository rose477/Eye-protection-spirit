package com.hy.hyspirit;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.hy.testasr.R;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

public class RecyclerSlideAdapter extends RecyclerView.Adapter<RecyclerSlideAdapter.Slide> {
    private Context context;
    private LayoutInflater mInflater;
    private List<Sldata> mDatas;



    public RecyclerSlideAdapter(Context context, List<Sldata> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public Slide onCreateViewHolder(ViewGroup parent, int viewType) {

        return new Slide(mInflater.inflate(R.layout.item_shili, parent, false));
    }

    @Override
    public void onBindViewHolder(final Slide holder, final int position) {
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
        EyeTestFragment eyeTestFragment=new EyeTestFragment();
        eyeTestFragment.initData();
        Sldata sldata;
        sldata=eyeTestFragment.mSldata1.get(position);
        holder.mTextView.setText(String.valueOf(sldata.getRight()));
        holder.mTextView2.setText(String.valueOf(sldata.getShili()));
        holder.mTextView3.setText(String.valueOf(sldata.getRight2()));
        holder.mTextView4.setText(String.valueOf(sldata.getShili2()));
        holder.mTextView5.setText(sldata.data);
        holder.mTextView6.setText("第"+sldata.id+"次视力测试");


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

    private onSlideListener mOnSlideListener;

    public onSlideListener getmOnSlideListener() {
        return mOnSlideListener;
    }

    public void setOnDelListener(onSlideListener mOnDelListener) {
        this.mOnSlideListener = mOnDelListener;
    }

    public class Slide extends RecyclerView.ViewHolder {
        SwipeMenuLayout  content;
        Button btnDelete;
        TextView mTextView;
        TextView mTextView2;
        TextView mTextView3;
        TextView mTextView4;
        TextView mTextView5;
        TextView mTextView6;
        Button btnTop;

        public Slide(View itemView) {
            super(itemView);
            content =  itemView.findViewById(R.id.smlayout);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            btnTop = (Button) itemView.findViewById(R.id.btnTop);
            mTextView=itemView.findViewById(R.id.textView14);
            mTextView2=itemView.findViewById(R.id.textView17);
            mTextView3=itemView.findViewById(R.id.textView19);
            mTextView4=itemView.findViewById(R.id.textView20);
            mTextView5=itemView.findViewById(R.id.bottom_text);
            mTextView6=itemView.findViewById(R.id.top_text);
        }
    }
}