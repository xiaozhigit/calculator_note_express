package com.example.hp.splashprj.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hp.splashprj.R;
import com.example.hp.splashprj.adapter.GuidePageAdapter;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private View view1,view2,view3;
    private ImageView iv1,iv2,iv3;
    Button btn_start;
    private ImageButtonListener listener;
    private List<View> viewList=new  ArrayList<View>() {
    };
    private ViewPager viewPager;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1000:
                    Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
        }
    };
private void setCurView(int position){
    if(position<0||position>2){
   return ;
    }
     switch(position){
            case 0:
                break;
            case 1:
                iv1.setImageResource(R.drawable.indcator2);
                iv2.setImageResource(R.drawable.indcotr1);
                iv3.setImageResource(R.drawable.indcator2);
                break;
            case 2:
                iv1.setImageResource(R.drawable.indcator2);
                iv2.setImageResource(R.drawable.indcator2);
                iv3.setImageResource(R.drawable.indcotr1);
                break;
            default :break;
        }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        view1=View.inflate(this,R.layout.view1,null);
        view2=View.inflate(this,R.layout.view2,null);
        view3=View.inflate(this,R.layout.view3,null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewPager.setAdapter(new GuidePageAdapter(viewList));
        viewPager.setOnPageChangeListener(this);
        //设置启动延时
        //handler.sendEmptyMessageDelayed(1000,7000);
        //初始化指示器
        iv1= (ImageView) findViewById(R.id.iv1);
        iv2= (ImageView) findViewById(R.id.iv2);
        iv3= (ImageView) findViewById(R.id.iv3);
        iv1.setOnTouchListener(listener);
        iv2.setOnTouchListener(listener);
        iv3.setOnTouchListener(listener);
        iv1.setImageResource(R.drawable.indcotr1);
        iv2.setImageResource(R.drawable.indcator2);
        iv3.setImageResource(R.drawable.indcator2);

           //初始化按钮
        btn_start= (Button) view3.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   Intent intent=new Intent(GuideActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("PageView","onPageScrolled position:"+position+",positionOffset:"+positionOffset+
                ",positionOffsetPixels:"+positionOffsetPixels);

    }
//新的页面选中时调用
    @Override
    public void onPageSelected(int position) {
        Log.d("PageView","onPageSelected position:"+position);
        setCurView(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("PageView","onPageScrollStateChanged state:"+state);

    }
 class ImageButtonListener implements View.OnTouchListener{

     @Override
     public boolean onTouch(View v, MotionEvent event) {
         return false;
     }
 }
}
