package com.hy.hyspirit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.hy.testasr.R;
import com.xuexiang.xui.widget.actionbar.TitleBar;

public class AboutActivity extends BaseActivity {

     TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mTitleBar=findViewById(R.id.titleBar);
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void onBack() {

    }
}