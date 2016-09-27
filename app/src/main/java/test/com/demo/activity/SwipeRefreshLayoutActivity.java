package test.com.demo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import test.com.demo.R;

/**
 * Created by liushuwei on 2016/9/27.
 */
public class SwipeRefreshLayoutActivity extends BaseActivity{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiperefreshlayout);
        initViews();
        mSwipeRefreshLayout.setColorSchemeResources(R.color.bg_orange, R.color.bg_blue, R.color.bg_green, R.color.bg_red);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText((int)(Math.random()*100)+"");
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    @Override
    protected void initViews() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mTextView = (TextView) findViewById(R.id.textView);
    }
}
