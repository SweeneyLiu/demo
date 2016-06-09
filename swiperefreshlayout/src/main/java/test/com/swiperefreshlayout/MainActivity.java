package test.com.swiperefreshlayout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mRandomTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRandomTextView = (TextView) findViewById(R.id.random);
    }

    @Override
    public void onRefresh() {
        // 显示刷新过程动画
        mSwipeRefreshLayout.setRefreshing(true);

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // 3秒后停止显示刷新过程动画
                mSwipeRefreshLayout.setRefreshing(false);
                // 随机生成一个[1, 100]之间的整数
                int i = (int)(Math.random()*100+1);
                mRandomTextView.setText(String.valueOf(i));
            }
        }, 3000);
    }
}
