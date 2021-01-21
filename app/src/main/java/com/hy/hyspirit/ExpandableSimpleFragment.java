package com.hy.hyspirit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.hyspirit.semang.BlankFragment;
import com.hy.testasr.R;
import com.xuexiang.xui.widget.alpha.XUIAlphaTextView;
import com.xuexiang.xui.widget.layout.ExpandableLayout;
import com.xuexiang.xui.widget.popupwindow.bar.CookieBar;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;


public class ExpandableSimpleFragment extends Fragment {
    XUIAlphaTextView mXUIAlphaTextView;

    ExpandableLayout expandableLayout1;

    ExpandableLayout expandableLayout2;
    Zt_fragment mZt_fragment=new Zt_fragment();
    Bt_Fragment mBt_fragment=new Bt_Fragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expandable_simple, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mXUIAlphaTextView=getActivity().findViewById(R.id.expand_button);
        expandableLayout2=getActivity().findViewById(R.id.expandable_layout_2);
        expandableLayout1=getActivity().findViewById(R.id.expandable_layout_1);
       getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame1,mZt_fragment).commit();//默认先加载fragment1
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame2,mBt_fragment).commit();//默认先加载fragment1

        mXUIAlphaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int min=29;
                int max=31;
                Random random = new Random();
                int num = random.nextInt(max)%(max-min+1) + min;

                if (expandableLayout1.isExpanded()) {

                    expandableLayout1.collapse();
                    expandableLayout2.collapse();
                    mXUIAlphaTextView.setText("点击查看手机使用时间监控");

                }  else {
                    if (num>=30) {
                        CookieBar.builder(getActivity())
                                .setTitle(R.string.cookie_title)
                                .setMessage(R.string.cookie_message)
                                .setDuration(4000)
                                .setBackgroundColor(R.color.main_color)
                                .setActionColor(android.R.color.white)
                                .setTitleColor(android.R.color.white)
                                .setAction(R.string.cookie_action, view1 -> XToastUtils.toast("❤"))
                                .show();
                    }
                    expandableLayout1.expand();
                    expandableLayout2.expand();

                    mXUIAlphaTextView.setText("您已经连续使用手机"+num+"分钟");
                }

            }
        });

    }

    /**
     * 初始化控件
     */
//    @Override
//    protected void initViews() {
//        expandableLayout1.setOnExpansionChangedListener((expansion, state) -> Log.d("expandableLayout1", "State: " + state));
//
//        expandableLayout2.setOnExpansionChangedListener((expansion, state) -> {
////            if (state == COLLAPSED) {
////                XToastUtils.toast("已收起");
////            } else if (state == EXPANDED) {
////                XToastUtils.toast("已展开");
////            }
//        });
//    }


}
