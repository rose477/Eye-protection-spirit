package com.hy.hyspirit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hy.testasr.R;
import com.xuexiang.xui.widget.actionbar.TitleBar;

public class HuyanwebActivity extends BaseActivity {
    WebView mWebView;
    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huyanweb);
        mWebView=findViewById(R.id.webview);
        mTitleBar=findViewById(R.id.titleBar);
        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
        WebSettings webSettings = mWebView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);//js开关默认不打开，网页中的所有js方法就无法执行，如果我们把这个方法设置为ture，网页中搞得js方法才可以执行
        //设置自适应屏幕
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(false); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        //缓存模式如下：
        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //webview缓存设置
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        mWebView.loadUrl("http://www.haoshili.com.cn/index.php?/category/huyan");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_huyanweb;
    }

    @Override
    protected void onBack() {

    }
}