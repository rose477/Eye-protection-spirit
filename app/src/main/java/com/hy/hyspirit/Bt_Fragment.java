package com.hy.hyspirit;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.Utils;
import com.hy.testasr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Bt_Fragment extends Fragment {
     PieChart pc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bt_, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pc = (PieChart) getActivity().findViewById(R.id.pc);
        List<PieEntry> yVals = new ArrayList<>();
        int min=40;
        int max=50;
        Random random = new Random();
        float num1 = random.nextInt(max)%(max-min+1) + min;
        int min2=20;
        int max2=40;
        float num2 = random.nextInt(max2)%(max2-min2+1) + min2;

        float num3 = 100-num2-num1;
        yVals.add(new PieEntry(num1, "白天"));
        yVals.add(new PieEntry(num2, "夜晚"));
        yVals.add(new PieEntry(num3, "凌晨"));
       pc.setEntryLabelTextSize(10f);
       pc.setCenterText("护眼精灵");

       pc.setCenterTextSize (pc.getHoleRadius()/3);

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FF16E1A7"));
        colors.add(Color.parseColor("#0676D8"));
        colors.add(Color.parseColor("#FF7110FB"));

        PieDataSet pieDataSet = new PieDataSet(yVals, "");
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        String descriptionStr = "今日使用手机时间分布";
        Description description = new Description();
        description.setText(descriptionStr);
        description.setTextColor(Color.WHITE);
        description.setTextSize(16);
        pc.setDescription(description);
        pc.setHoleColor(Color.parseColor("#5510C6DD"));


        float s= (float) (pc.getHoleRadius()/(8.5));
        pc.setExtraOffsets(0f,s,0f,s);
        description.setTextSize(10f);

        pc.setData(pieData);

    }


}