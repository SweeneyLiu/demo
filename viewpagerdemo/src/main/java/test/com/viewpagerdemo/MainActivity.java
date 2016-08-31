package test.com.viewpagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ViewPager viewPager;
    private List<View> arrayList = new ArrayList<View>();
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        myViewPagerAdapter = new MyViewPagerAdapter(arrayList);
        viewPager.setAdapter(myViewPagerAdapter);
    }

    private void initView() {

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        View view1 = LayoutInflater.from(this).inflate(R.layout.view1,null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.view2,null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.view3,null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.view4,null);
        View view5 = LayoutInflater.from(this).inflate(R.layout.view5,null);
        arrayList.add(view1);
        arrayList.add(view2);
        arrayList.add(view3);
        arrayList.add(view4);
        arrayList.add(view5);
    }
}
