package com.hy.hyspirit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.testasr.R;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


public class Zt_fragment extends Fragment {

    ZheXianView zheXianView;
    Calendar calendar = Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zt_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        zheXianView=getActivity(). findViewById(R.id.zheXianView);
        //月
        int month = calendar.get(Calendar.MONTH)+1;
//日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Log.i("TIME","-------"+month+day);
        String s1= month + "." + (day-6);
        String s2= month + "." + (day-5);
        String s3= month + "." + (day-4);
        String s4= month + "." + (day-3);
        String s5= month + "." + (day-2);
        String s6= month + "." + (day-1);
        String s7= month + "." + day;
        Map<String ,Float> map=new LinkedHashMap<>() ;//一定要用有序的Map
        int min=6;
        int max=18;
        Random random = new Random();
        float num1 = random.nextInt(max)%(max-min+1) + min;
        float num2 = random.nextInt(max)%(max-min+1) + min;
        float num3 = random.nextInt(max)%(max-min+1) + min;
        float num4 = random.nextInt(max)%(max-min+1) + min;
        float num5 = random.nextInt(max)%(max-min+1) + min;
        float num6 = random.nextInt(max)%(max-min+1) + min;
        float num7 = random.nextInt(max)%(max-min+1) + min;

        map.put(s1,num1);
        map.put(s2,num2);
        map.put(s3,num3);
        map.put(s4,num4);
        map.put(s5,num5);
        map.put(s6,num6);
        map.put(s7,num7);
        String[] a=new String[]{"06","12","18","24"};

        zheXianView.startDraw(map,a,"小时",40,10);//map为横坐标数据和点数据，a为纵坐标刻度（为数字类型的字符串，m/s为纵坐标单位,40为坐标轴距离边界的位置dp，14是坐标轴字体大小）

    }
}