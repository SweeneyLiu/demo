package test.com.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.toolbox.Volley;

import test.com.demo.R;


public class MainActivity extends BaseActivity {

    private Button mRecyclerViewButton;
    private Button mSwipeRefreshLayoutButton;
    private Button mViewPagerButton;
    private Button mViewFlipperButton;
    private Button mVolleyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mRecyclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        mSwipeRefreshLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SwipeRefreshLayoutActivity.class);
                startActivity(intent);
            }
        });

        mViewPagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });

        mViewFlipperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewFlipperActivity.class);
                startActivity(intent);
            }
        });

        mVolleyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VolleyActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initViews() {
        mRecyclerViewButton = (Button)findViewById(R.id.recyclerview_button);
        mSwipeRefreshLayoutButton = (Button)findViewById(R.id.swiperefreshlayout_button);
        mViewPagerButton = (Button)findViewById(R.id.viewpager_button);
        mViewFlipperButton = (Button)findViewById(R.id.viewflipper_button);
        mVolleyButton = (Button)findViewById(R.id.volley_button);
    }

}
