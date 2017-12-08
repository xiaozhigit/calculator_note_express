package com.example.hp.splashprj.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.hp.splashprj.Fragment.CalculatorFragment;
import com.example.hp.splashprj.Fragment.ExpreFragment;
import com.example.hp.splashprj.Fragment.NoteFragment;
import com.example.hp.splashprj.R;
import com.example.hp.splashprj.Utils.SharedUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {
    private TabLayout tabLyout;
    private ViewPager viewPager;
    private List<String> tabNameList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        boolean isFist = SharedUtils.getBooleanData(MainActivity.this, "isFirst", false);
        if (!isFist) {
            Intent inten = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(inten);
        } else {
            //Intent inte=new Intent(MainActivity.this,GuideActivity.class);
            //startActivity(inte);
        }
       // ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        //填充标签
        tabNameList.add(getString(R.string.fragment_calculator));
        tabNameList.add(getString(R.string.fragment_memory));
        tabNameList.add(getString(R.string.fragment_info));
        //填充Fragment
        fragmentList.add(new CalculatorFragment());
        fragmentList.add(new NoteFragment());
        fragmentList.add(new ExpreFragment());
    }

    private void initView() {
        //btn=view1.fin
        tabLyout = (TabLayout) findViewById(R.id.tl_tablayout);
        viewPager = (ViewPager) findViewById(R.id.vp_viewPager);
        //设置viewPager的Adapter
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            //设置tab名称

            @Override
            public CharSequence getPageTitle(int position) {
                return tabNameList.get(position);
            }
        });
        //绑定tabLayout
        tabLyout.setupWithViewPager(viewPager);
    }
}
