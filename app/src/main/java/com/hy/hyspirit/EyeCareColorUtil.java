package com.hy.hyspirit;

import android.graphics.Color;

public class EyeCareColorUtil {
    /**
     * 过滤蓝光
     *
     * @param blueFilterPercent 蓝光过滤比例[10-30-80]
     */
    public static int getFilterColor(int blueFilterPercent) {
        int realFilter = blueFilterPercent;
        if (realFilter < 10) {
            realFilter = 10;
        } else if (realFilter > 80) {
            realFilter = 80;
        }
        int a = (int) (realFilter / 80f * 180);
        int r = (int) (200 - (realFilter / 80f) * 190);
        int g = (int) (180 - (realFilter / 80f) * 170);
        int b = (int) (60 - realFilter / 80f * 60);
        return Color.argb(a, r, g, b);
    }
}
