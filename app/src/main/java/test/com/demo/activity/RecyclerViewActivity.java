package test.com.demo.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import test.com.demo.R;
import test.com.demo.adapter.RecyclerViewAdapter;


/**
 * Created by liushuwei on 2016/9/26.
 */
public class RecyclerViewActivity extends BaseActivity{

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ArrayList<String> mArrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        loadData();
        initViews();
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView)findViewById(R.id.rv);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mGridLayoutManager = new GridLayoutManager(this,2);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        mRecyclerViewAdapter = new RecyclerViewAdapter(mArrayList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    protected void loadData() {
        for(int i =0;i<100;i++){
            mArrayList.add(i+"");
        }
    }
}
