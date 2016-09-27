package test.com.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import test.com.demo.R;
import test.com.demo.adapter.RecyclerViewAdapter;
import test.com.demo.decoration.*;


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
        initVariables();
        initDatas();
        initViews();

        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //设置Adapter
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        //设置动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mRecyclerViewAdapter.setmOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this,position+"========Click:",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(RecyclerViewActivity.this,position+"========LongClick:",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initVariables() {
        mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mGridLayoutManager = new GridLayoutManager(this,2);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerViewAdapter = new RecyclerViewAdapter(mArrayList);
    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView)findViewById(R.id.rv);
    }

    @Override
    protected void initDatas() {
        for(int i ='A';i<='Z';i++){
            mArrayList.add((char)i+"");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                mRecyclerViewAdapter.add(1);
                break;
            case R.id.delete:
                mRecyclerViewAdapter.delete(1);
                break;
            case R.id.listview:
                mRecyclerView.setLayoutManager(mLinearLayoutManager);
                break;
            case R.id.gridview:
                mRecyclerView.setLayoutManager(mGridLayoutManager);
                break;
            case R.id.hor_gridview:
                mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
                break;
            case R.id.staggered:
                Intent intent = new Intent(RecyclerViewActivity.this,StaggeredGridViewLayoutActivity.class);
                startActivity(intent);
            default:
                break;

        }
        return true;
    }
}
