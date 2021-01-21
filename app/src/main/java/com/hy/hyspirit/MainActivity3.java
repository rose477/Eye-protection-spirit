package com.hy.hyspirit;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.hy.testasr.R;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;

public class MainActivity3 extends BaseActivity {


    NavigationView mNavigationView;
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    TextView mTextView;
    TextView mTextView2;
    ShadowButton mShadowButton;
    ShadowButton mShadowButton2;
    ShadowButton mShadowButton3;
    ShadowButton mShadowButton4;
    ShadowButton mShadowButton5;
    boolean checked;
//    MyProgressBar mSeekBar;
   SeekBar mSeekBar;
    SeekBar mSeekBar2;
    private int ld=0;
    private int ldProgress=0;
    ExpandableHorizontalFragment mExpandableHorizontalFragment=new ExpandableHorizontalFragment();
     ExpandableSimpleFragment mExpandableSimpleFragment=new ExpandableSimpleFragment();
//    String[] colorData = new String[]{"#33ffa957", "#33ffad5e", "#33ffb165", "#33ffb46b", "#33ffb872", "#33ffbb78", "#33ffbe7e", "#33ffc184", "#33ffc489", "#33ffc78f",
//            "#33ffc994", "#33ffcc99", "#33ffce9f", "#33ffd1a3", "#33ffd3a8", "#33ffd5ad", "#33ffd7b1", "#33ffd9b6", "#33ffdbba", "#33ffddbe", "#33ffdfc2", "#33ffe1c6",
//            "#33ffe3ca", "#33ffe4ce", "#33ffe6d2", "#33ffe8d5", "#33ffe9d9", "#33ffebdc", "#33ffece0", "#33ffeee3", "#33ffefe6", "#33fff0e9", "#33fff2ec", "#33fff3ef", "#33fff4f2",
//            "#33fff5f5", "#33fff6f8", "#33fff8fb", "#33fff9fd","#33ffa957", "#33ffad5e", "#33ffb165", "#33ffb46b", "#33ffb872", "#33ffbb78", "#33ffbe7e", "#33ffc184", "#33ffc489", "#33ffc78f",
//            "#33ffc994", "#33ffcc99", "#33ffce9f", "#33ffd1a3", "#33ffd3a8", "#33ffd5ad", "#33ffd7b1", "#33ffd9b6", "#33ffdbba", "#33ffddbe", "#33ffdfc2", "#33ffe1c6",
//            "#33ffe3ca", "#33ffe4ce", "#33ffe6d2", "#33ffe8d5", "#33ffe9d9", "#33ffebdc", "#33ffece0", "#33ffeee3", "#33ffefe6", "#33fff0e9", "#33fff2ec", "#33fff3ef", "#33fff4f2",
//            "#33fff5f5", "#33fff6f8", "#33fff8fb", "#33fff9fd","#33ffa957", "#33ffad5e", "#33ffb165", "#33ffb46b", "#33ffb872", "#33ffbb78", "#33ffbe7e", "#33ffc184", "#33ffc489", "#33ffc78f",
//            "#33ffc994", "#33ffcc99", "#33ffce9f", "#33ffd1a3", "#33ffd3a8", "#33ffd5ad", "#33ffd7b1", "#33ffd9b6", "#33ffdbba", "#33ffddbe", "#33ffdfc2", "#33ffe1c6",
//            "#33ffe3ca", "#33ffe4ce", "#33ffe6d2", "#33ffe8d5", "#33ffe9d9", "#33ffebdc", "#33ffece0", "#33ffeee3", "#33ffefe6", "#33fff0e9", "#33fff2ec", "#33fff3ef", "#33fff4f2",
//            "#33fff5f5", "#33fff6f8", "#33fff8fb", "#33fff9fd","#33ffa957", "#33ffad5e", "#33ffb165", "#33ffb46b", "#33ffb872", "#33ffbb78", "#33ffbe7e", "#33ffc184", "#33ffc489", "#33ffc78f",
//            "#33ffc994", "#33ffcc99", "#33ffce9f", "#33ffd1a3", "#33ffd3a8", "#33ffd5ad", "#33ffd7b1", "#33ffd9b6", "#33ffdbba", "#33ffddbe", "#33ffdfc2", "#33ffe1c6",
//            "#33ffe3ca", "#33ffe4ce", "#33ffe6d2", "#33ffe8d5", "#33ffe9d9", "#33ffebdc", "#33ffece0", "#33ffeee3", "#33ffefe6", "#33fff0e9", "#33fff2ec", "#33fff3ef", "#33fff4f2",
//            "#33fff5f5", "#33fff6f8", "#33fff8fb", "#33fff9fd"};
//String[] colorData = new String[]{"#33ffa957", "#33fad5e", "#33ffb165", "#33ffb46b", "#33ffb872",
//        "#33ffbb78", "#33ffbe7e", "#33ffc184", "#33ffc489", "#33ffc78f","#33fad5e","#33ffa957",
//        "#33fad5e", "#33ffb165", "#33ffb46b", "#33ffb872", "#33ffbb78", "#33ffbe7e", "#33ffc184", "#33ffc489"
//        };


    public int mColor;
    int i=1;
    int i2=1;
    int i3=1;
    int i4=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout3,mExpandableSimpleFragment).commit();//默认先加载fragment1
//        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout4,mExpandableHorizontalFragment).commit();//默认先加载fragment1
        mShadowButton2=findViewById(R.id.shadowButton4);
        mShadowButton3=findViewById(R.id.shadowButton3);
        mShadowButton4=findViewById(R.id.shadowButton2);
        mShadowButton5=findViewById(R.id.shadowButton5);

        mNavigationView=findViewById(R.id.navigationView);
        mDrawerLayout=findViewById(R.id.drawerLayout);
        mToolbar=findViewById(R.id.toolbar);
        mTextView=findViewById(R.id.tv_light_value);
       mTextView2=findViewById(R.id.textView28);
        mSeekBar=findViewById(R.id.seekbarLight);
//        mSeekBar2=findViewById(R.id.seekbarLight2);
        //使用ToolBar控件替代ActionBar控件
        setSupportActionBar(mToolbar);
        mShadowButton=findViewById(R.id.shadowButton);
        mShadowButton.setText("开启护眼");
        mTextView2.setText("世界很美，等你发现");
        mShadowButton.setTextSize(30);
        mShadowButton.setTextColor(getResources().getColor(R.color.button));
        final FlashUtils utils = new FlashUtils(this);
        mSeekBar.setProgress(getSystemBrightness());
        changeAppBrightness(getSystemBrightness());
        Intent intent=new Intent(MainActivity3.this,MyService.class);
        bindService(intent,new MyServiceConnection(),BIND_AUTO_CREATE);

//        permission();


        mShadowButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i>0) {
                    blow_up(mShadowButton2);
                    narrow(mShadowButton3);
                    narrow(mShadowButton4);
                    narrow(mShadowButton5);
                    eyeCareView.setBackgroundColor(Color.argb(88, 181, 102, 102));
                    mTextView2.setText("助眠模式：红光波促进褪黑素分泌，晚间有益于助眠");
                    i*=-1;
                    Log.i("idata","-------"+i);
                }else {
                    narrow(mShadowButton2);
                    narrow(mShadowButton3);
                    narrow(mShadowButton4);
                    narrow(mShadowButton5);
                    eyeCareView.setBackgroundColor(Color.TRANSPARENT);
                    mTextView2.setText("世界很美，等你发现");
                    i*=-1;
                    Log.i("idata","-------"+i);
                }

            }
        });
        mShadowButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i2>0) {
                    blow_up(mShadowButton3);
                    narrow(mShadowButton2);
                    narrow(mShadowButton4);
                    narrow(mShadowButton5);
                    i2*=-1;
                    eyeCareView.setBackgroundColor(Color.argb(88, 83, 81, 75));
                    mTextView2.setText("夜间模式：暗灰色可降低光线刺激，有助于保护视力");
                }else {
                    narrow(mShadowButton3);
                    narrow(mShadowButton2);
                    narrow(mShadowButton4);
                    narrow(mShadowButton5);
                    i2*=-1;
                    eyeCareView.setBackgroundColor(Color.TRANSPARENT);
                    mTextView2.setText("世界很美，等你发现");
                }

            }
        });
        mShadowButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i3>0) {
                    blow_up(mShadowButton4);
                    narrow(mShadowButton3);
                    narrow(mShadowButton2);
                    narrow(mShadowButton5);
                    i3*=-1;
                    eyeCareView.setBackgroundColor(Color.argb(77, 232, 198, 111));
                    mTextView2.setText("阅读模式：模拟防蓝光效果眼睛，敏感者首选");
                }else {
                    narrow(mShadowButton4);
                    narrow(mShadowButton3);
                    narrow(mShadowButton2);
                    narrow(mShadowButton5);
                    i3*=-1;
                    eyeCareView.setBackgroundColor(Color.TRANSPARENT);
                    mTextView2.setText("世界很美，等你发现");
                }

            }
        });
        mShadowButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i4>0) {
                    blow_up(mShadowButton5);
                    narrow(mShadowButton3);
                    narrow(mShadowButton4);
                    narrow(mShadowButton2);
                    i4*=-1;
                    Log.i("idata","-------"+i);
                    eyeCareView.setBackgroundColor(Color.argb(33, 47, 230, 41));
                    mTextView2.setText("游戏模式：优化亮度，通过绿光缓解眼睛疲劳");
                }else {
                    narrow(mShadowButton5);
                    narrow(mShadowButton3);
                    narrow(mShadowButton4);
                    narrow(mShadowButton2);
                    i4*=-1;
                    Log.i("idata","-------"+i);
                    eyeCareView.setBackgroundColor(Color.TRANSPARENT);
                    mTextView2.setText("世界很美，等你发现");
                }

            }
        });
        mShadowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 每次 setChecked 时会触发onCheckedChanged 监听回调，而有时我们在设置setChecked后不想去自动触发 onCheckedChanged 里的具体操作, 即想屏蔽掉onCheckedChanged;加上此判断
                Log.i("mShadowButton","点击了"+checked2);

                    if (checked2){
                        closeEye();
                        mShadowButton.setText("开启护眼");
                        narrow(mShadowButton2);
                        narrow(mShadowButton3);
                        narrow(mShadowButton4);
                        narrow(mShadowButton5);
                        mTextView2.setText("世界很美，等你发现");
                        Constant.IS_EYE_CARE_OPEN = false;
                    }else {
                        mShadowButton.setText("关闭护眼");
                        mTextView2.setText("护眼模式：顺应生物钟的变化，调节人体机能");
                        openEye();
                        narrow(mShadowButton2);
                        narrow(mShadowButton3);
                        narrow(mShadowButton4);
                        narrow(mShadowButton5);
                        Constant.IS_EYE_CARE_OPEN = true;
                    }

                }

        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {//菜单 item 监听
                switch (menuItem.getItemId()) {
                    case R.id.item_1:

                        Intent intent=new Intent(MainActivity3.this, Activity_eyestest.class);
                        startActivity(intent);

                        mDrawerLayout.closeDrawer(GravityCompat.START);// 点击item 侧滑栏收回

                        Log.i("Mainactivity","item_1");
                        break;
                    case R.id.item_2:
                        Log.i("Mainactivity","item_2");
                        utils.converse();

                        if (!checked) {

                            Toast.makeText(MainActivity3.this, "打开手电",Toast.LENGTH_SHORT).show();

                        }else {

                            Toast.makeText(MainActivity3.this, "关闭手电",Toast.LENGTH_SHORT).show();
                            mDrawerLayout.closeDrawer(GravityCompat.START);// 点击item 侧滑栏收回
                        }
                        break;


                    case R.id.item_3:
                        Intent intent2=new Intent(MainActivity3.this, HuyanwebActivity.class);
                        startActivity(intent2);
                        Log.i("Mainactivity","item_3");
                        mDrawerLayout.closeDrawer(GravityCompat.START);// 点击item 侧滑栏收回
                        break;
                    case R.id.item_4:
                        Intent intent3=new Intent(MainActivity3.this, AboutActivity.class);
                        startActivity(intent3);
                        Log.i("Mainactivity","item_4");
                        mDrawerLayout.closeDrawer(GravityCompat.START);// 点击item 侧滑栏收回
                        break;
                }

                return false;
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int add=progress-ldProgress;
                ld=ld+add;
                ldProgress=progress;
                Log.e("this","ld:"+ld);
                changeAppBrightness(ld);
            }



            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        mSeekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mTextView2.setText((int) ((float) progress / seekBar.getMax() * 100) + "%");
//                 mColor =Color.parseColor(colorData [progress == 0 ? 0 : progress - 1]);
//                //mMaskFilterView灯罩自定义view
//                Log.i("mcolr","1111"+mColor);
//               eyeCareView.setBackgroundColor(mColor);
//
//            }
//
//
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });

    }
    private void blow_up(View v) {
        float[] vaules = new float[] { 1.0f, 1.1f, 1.2f, 1.3f, 1.4f, 1.5f};
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v, "scaleX", vaules),
                ObjectAnimator.ofFloat(v, "scaleY", vaules));
        set.setDuration(150);
        set.start();
    }
    //缩小按钮动画
    private void narrow(View v) {
        float[] vaules = new float[] { 1.5f, 1.4f, 1.3f, 1.2f, 1.1f, 1.0f};
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(v, "scaleX", vaules),
                ObjectAnimator.ofFloat(v, "scaleY", vaules));
        set.setDuration(0);
        set.start();
    }

    private int getSystemBrightness() {
        int systemBrightness = 0;
                try {
                       systemBrightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
                    } catch (Settings.SettingNotFoundException e) {
                         e.printStackTrace();
                    }
                 return systemBrightness;
            }
    public void permission(){
        if (Build.VERSION.SDK_INT >= 23) {
            if(!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }
        }
    }
    public void changeAppBrightness(int brightness) {   //改变系统屏幕亮度
        WindowManager.LayoutParams lp = getWindow().getAttributes();

        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);

        getWindow().setAttributes(lp);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main3;
    }

    @Override
    protected void onBack() {
        finish();
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.share:
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, "欢迎使用护眼精灵 打开网址下载http://d.firim.vip/c2l1");
                startActivity(Intent.createChooser(textIntent, "分享"));

                break;


            case R.id.item_2:
                Intent intent4=new Intent(MainActivity3.this,FankuiActivity.class);
                startActivity(intent4);
//                Toast.makeText(this,"加载中.....",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);

        return uriPath;
    }


    public class FlashUtils {
        private CameraManager manager;
        private Camera mCamera = null;
        private Context context;
        private boolean status = false;//记录手电筒状态

        FlashUtils(Context context){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                manager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
            }
            this.context = context;
        }

        //打开手电筒
        public void open() {
            if(status){//如果已经是打开状态，不需要打开
                return;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    manager.setTorchMode("0", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                PackageManager packageManager = context.getPackageManager();
                FeatureInfo[] features = packageManager.getSystemAvailableFeatures();
                for (FeatureInfo featureInfo : features) {
                    if (PackageManager.FEATURE_CAMERA_FLASH.equals(featureInfo.name)) { // 判断设备是否支持闪光灯
                        if (null == mCamera) {
                            mCamera = Camera.open();
                        }
                        Camera.Parameters parameters = mCamera.getParameters();
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        mCamera.setParameters(parameters);
                        mCamera.startPreview();
                    }
                }
            }
            status = true;//记录手电筒状态为打开
        }

        //关闭手电筒
        public void close() {
            if(!status){//如果已经是关闭状态，不需要打开
                return;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    manager.setTorchMode("0", false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                if (mCamera != null) {

                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                }
            }
            status = false;//记录手电筒状态为关闭
        }

        //改变手电筒状态
        public void converse(){
            Log.i("converse","运行了"+status);
            if(status){
                close();
                checked=true;

            }else{
                open();
                checked=false;
            }
        }
    }


}