package com.hy.hyspirit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.hy.testasr.R;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;
import com.xuexiang.xui.widget.guidview.GuideCaseView;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    ShadowButton mRoundButton;
//    ButtonView mButtonView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRoundButton=findViewById(R.id.button1);


        //获取SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        //获取Editor对象的引用
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //将登录的状态放入文件
        editor.putBoolean("islogin",true);
        // 提交数据
        editor.commit();

        mRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);

                finish();
            }
        });
//        mButtonView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
//                startActivity(intent);
//            }
//        });

        new GuideCaseView.Builder(this)
                .focusOn(mRoundButton)
                .focusCircleRadiusFactor(0.95)
                .title("欢迎您的使用，点击开启护眼之路吧~")
                .focusBorderColor(Color.GREEN)
                .titleSize(24,1)
                .titleStyle(0, Gravity.BOTTOM | Gravity.CENTER)
                .fitWindowsAuto()
                .build()
                .show();
    }


}