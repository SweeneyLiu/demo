package test.com.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.com.demo.R;


public class MainActivity extends BaseActivity {

    @BindView(R.id.recyclerview_button)
    Button recyclerviewButton;
    @BindView(R.id.swiperefreshlayout_button)
    Button swiperefreshlayoutButton;
    @BindView(R.id.viewpager_button)
    Button viewpagerButton;
    @BindView(R.id.viewflipper_button)
    Button viewflipperButton;
    @BindView(R.id.volley_button)
    Button volleyButton;
    @BindView(R.id.navigation_drawer_button)
    Button navigationDrawerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.recyclerview_button, R.id.swiperefreshlayout_button, R.id.viewpager_button, R.id.viewflipper_button, R.id.volley_button, R.id.navigation_drawer_button})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.recyclerview_button:
                intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.swiperefreshlayout_button:
                intent = new Intent(MainActivity.this, SwipeRefreshLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.viewpager_button:
                intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
                break;
            case R.id.viewflipper_button:
                intent = new Intent(MainActivity.this, ViewFlipperActivity.class);
                startActivity(intent);
                break;
            case R.id.volley_button:
                intent = new Intent(MainActivity.this, VolleyActivity.class);
                startActivity(intent);
                break;
            case R.id.navigation_drawer_button:
                intent = new Intent(MainActivity.this, NavigationDrawerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
