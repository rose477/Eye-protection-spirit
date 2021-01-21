package com.hy.hyspirit;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.hy.testasr.R;


/**
 * createtime:2019/8/13
 * author:Yang
 * describe:圆形的进度条
 */
public class MyRoundProgressBar extends MyProgressBar {
    private int mRadius=Dptopx(30);
    private int mMaxPaintWidth;
    public MyRoundProgressBar(Context context) {
        this(context,null);
    }
    public MyRoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MyRoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mReachHeight= (int) (2.5f*mUnReachHeight);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRoundProgressBar);
        mRadius= (int) typedArray.getDimension(R.styleable.MyRoundProgressBar_progress_radius, mRadius);
        typedArray.recycle();
        mpaint.setAntiAlias(true);
        mpaint.setDither(true);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMaxPaintWidth=Math.max(mReachHeight, mUnReachHeight);
        int expect=2*mRadius+mMaxPaintWidth+getPaddingLeft()+getPaddingRight();
        int height=resolveSize(expect, heightMeasureSpec);
        int width=resolveSize(expect,widthMeasureSpec);
        int realWidth=Math.min(height, width);
        mRadius=(realWidth-getPaddingRight()-getPaddingLeft()-mMaxPaintWidth)/2;
        setMeasuredDimension(realWidth, realWidth);
    }


    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text=getProgress()+"%";
        int textwidth= (int) mpaint.measureText(text);
        int textheight=(int)(mpaint.descent()+mpaint.ascent())/2;
        canvas.save();
        canvas.translate(getPaddingLeft()+mMaxPaintWidth/2, getPaddingTop()+mMaxPaintWidth/2);
        mpaint.setStyle(Paint.Style.STROKE);
        //绘制unReachbar
        mpaint.setColor(mUnReachColor);
        mpaint.setStrokeWidth(mUnReachHeight);
        canvas.drawCircle(mRadius, mRadius,mRadius , mpaint);
        //绘制Reachbar
        mpaint.setColor(mReachColor);
        mpaint.setStrokeWidth(mReachHeight);
        float sweepAngle=getProgress()*1.0f/getMax()*360;
        canvas.drawArc(new RectF(0,0,2*mRadius,2*mRadius)
                , 0, sweepAngle, false,mpaint );
        //绘制Text
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setColor(mTextColor);
        canvas.drawText(text, mRadius-textwidth/2, mRadius-textheight, mpaint);
        canvas.restore();

    }
}

