package com.nova.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends Activity {
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册EventBus
        EventBus.getDefault().register(this);
        findViewById(R.id.text_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky("我发送");//这种方式 MAIN和POSTING都能收到
                startActivity(new Intent(MainActivity.this,OtherActivity.class));
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                      EventBus.getDefault().post("我发送了");
//                    }
//                }).start();
            }
        });

    }
    //Thread.MAIN 无论事件是在哪个先测中发布出来的，该事件处理函数都会再UI线程执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(String event){
        Toast.makeText(this,event+"MAIN方式",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"MAIN");
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void hello2(String event){
        Toast.makeText(this,event+"POSTING方式",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"POSTING");
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void hello3(String event){
        Toast.makeText(this,event+"BACKROUND方式",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"BACKROUND");
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void hello4(String event){
        Toast.makeText(this,event+"ASYNC方式",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"ASYNC");

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
