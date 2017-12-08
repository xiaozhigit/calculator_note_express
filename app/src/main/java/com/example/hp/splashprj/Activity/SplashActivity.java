package com.example.hp.splashprj.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.hp.splashprj.R;
import com.example.hp.splashprj.Utils.SharedUtils;

public class SplashActivity extends Activity {
    private TextView tv_show;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        SharedUtils.putBooleanData(SplashActivity.this, "isFirst", true);
        //设置启动延时
        handler.sendEmptyMessageDelayed(1000, 5000);
        tv_show = (TextView) findViewById(R.id.tv_show);

    }
}
