package com.nova.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class OtherActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        //注册
        EventBus.getDefault().register(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMessageThread(String event){
        Toast.makeText(this,event+"otheractivity",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
