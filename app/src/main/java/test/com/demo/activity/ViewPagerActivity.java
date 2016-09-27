package test.com.demo.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import test.com.demo.R;
import test.com.demo.adapter.ViewPagerAdapter;

/**
 * Created by liushuwei on 2016/9/27.
 */
public class ViewPagerActivity extends BaseActivity{

    private ViewPager mViewPager;
    private List<View> mViewList;
    private ViewPagerAdapter mViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initViews();
        mViewPagerAdapter = new ViewPagerAdapter(mViewList);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    protected void initViews() {
        mViewPager = (ViewPager)findViewById(R.id.viewPager);
        View view1 = LayoutInflater.from(this).inflate(R.layout.view1,null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.view2,null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.view3,null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.view4,null);
        View view5 = LayoutInflater.from(this).inflate(R.layout.view5,null);
        mViewList = new ArrayList<View>();
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        mViewList.add(view4);
        mViewList.add(view5);
    }
}
