package com.hy.hyspirit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hy.testasr.R;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FankuiActivity extends BaseActivity {
            List<ChatMessager> list;
            ListView ch_list;
            Button mButton;
            EditText mEditText;
            ChatAdater mChatAdater;
            ChatMessager chatMessager = null;
            HttpRrequest HttpUtils;
     TitleBar mTitleBar;


    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_fankui);
                initview();
                initListener();
                initdata();
                mEditTextStyle();
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
        return R.layout.activity_fankui;
    }

    @Override
    protected void onBack() {

    }

    private void mEditTextStyle() {
                //设置EditText的显示方式为多行文本输入
                mEditText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                //文本显示的位置在EditText的最上方
                mEditText.setGravity(Gravity.TOP);
                //改变默认的单行模式
                mEditText.setSingleLine(false);
                //水平滚动设置为False
                mEditText.setHorizontallyScrolling(false);
            }

            // 获取控件id,初始视图
            private void initview() {
                ch_list=findViewById(R.id.listview);
                mEditText = (EditText) findViewById(R.id.editTextTextPersonName2);
                mButton = (Button) findViewById(R.id.button_send);
            }

            // 设置监听事件
            private void initListener() {


                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == R.id.button_send) {

                            chat();

                        }




                    }
                });
            }



            // 初始化数据
            private void initdata() {
                list = new ArrayList<ChatMessager>();
                list.add(new ChatMessager("你好，护眼小精灵为您服务", new Date(), ChatMessager.Type.INCOUNT));
                // list.add(new ChatMessager("小桃子为你服务", new Date(), Type.OUTCOUNT));
                mChatAdater = new ChatAdater(list);
                ch_list.setAdapter(mChatAdater);
                // 刷新数据
                mChatAdater.notifyDataSetChanged();


            }

            private void chat() {
                final String send_message = mEditText.getText().toString().trim();
                if (TextUtils.isEmpty(send_message)) {
                    Toast.makeText(FankuiActivity.this, "小伙伴，你还没发消息呢", Toast.LENGTH_SHORT).show();
                    return;
                }
                ChatMessager messager = new ChatMessager();
                messager.setMessager(send_message);
                messager.setData(new Date());
                messager.setType(ChatMessager.Type.OUTCOUNT);
                list.add(messager);
                mChatAdater.notifyDataSetChanged();
                mEditText.setText("");

                new Thread() {
                    public void run() {
                        ChatMessager chat = HttpUtils.sendMassager(send_message);
                        Message message = new Message();
                        // 0*1十六进制的一个标识
                        message.what = 0x1;
                        message.obj = chat;
                        handler.sendMessage(message);
                    };
                }.start();
            }

            // handler创建在主线程之中
            private Handler handler = new Handler() {

                @SuppressLint("HandlerLeak")
                public void handleMessage(android.os.Message msg) {
                    if (msg.what == 0x1) {
                        if (msg.obj != null) {
                            chatMessager = (ChatMessager) msg.obj;
                        }
                        // 添加数据到list中，更新数据
                        list.add(chatMessager);
                        mChatAdater.notifyDataSetChanged();
                    }
                };
            };

        }
