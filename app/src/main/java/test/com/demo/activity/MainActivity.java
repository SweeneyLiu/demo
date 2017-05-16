package test.com.demo.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.com.demo.R;


public class MainActivity extends ListActivity {


    private static final String TAG = "MainActivity";
    
    public static final String[] testOptions = { "RecyclerView测试", "SwipeRefreshLayout测试", "ViewPager测试", "ViewFlipper测试", "Volley测试",
            "DrawerLayout测试", "fresco测试", "ImageLoader测试", "picasso测试", "glide测试", "RxJava测试", "Permission测试", "百度地图测试",
            "MaterialDesign测试", "下载测试", "Notification测试", "动态增加View测试"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testOptions));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent;

        switch (position) {
            default:
            case 0:
                intent = new Intent(this, RecyclerViewActivity.class);
                break;
            case 1:
                intent = new Intent(this, SwipeRefreshLayoutActivity.class);
                break;
            case 2:
                intent = new Intent(this, ViewPagerActivity.class);
                break;
            case 3:
                intent = new Intent(this, ViewFlipperActivity.class);
                break;
            case 4:
                intent = new Intent(this, VolleyActivity.class);
                break;
            case 5:
                intent = new Intent(this, NavigationDrawerActivity.class);
                break;
            case 6:
                intent = new Intent(this, FrescoActivity.class);
                break;
            case 7:
                intent = new Intent(this, ImageLoaderActivity.class);
                break;
            case 8:
                intent = new Intent(this, PicassoActivity.class);
                break;
            case 9:
                intent = new Intent(this, GlideActivity.class);
                break;
            case 10:
                intent = new Intent(this, RxJavaActivity.class);
                break;
            case 11:
                intent = new Intent(this, PermissionActivity.class);
                break;
            case 12:
                intent = new Intent(this, BaiDuMapActivity.class);
                break;
            case 13:
                intent = new Intent(this, MaterialDesignActivity.class);
                break;
            case 14:
                intent = new Intent(this, DownloadActivity.class);
                break;
            case 15:
                intent = new Intent(this, NotificationActivity.class);
                break;
            case 16:
                intent = new Intent(this, DynamicAddViewActivity.class);
                break;
        }

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: ");
    }

    /*@BindView(R.id.recyclerview_button)
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
    @BindView(R.id.fresco_button)
    Button frescoButton;
    @BindView(R.id.image_loader_button)
    Button imageLoaderButton;
    @BindView(R.id.picasso_button)
    Button picassoButton;
    @BindView(R.id.glide_button)
    Button glideButton;
    @BindView(R.id.RxJava_button)
    Button RxJavaButton;
    @BindView(R.id.permiss_button)
    Button permissButton;
    @BindView(R.id.baidu_map_button)
    Button baiduMapButton;
    @BindView(R.id.material_design_button)
    Button materialDesignButton;
    @BindView(R.id.download_button)
    Button downloadButton;
    @BindView(R.id.notification_button)
    Button notificationButton;
    @BindView(R.id.dynamic_add_button)
    Button dynamicAddButton;


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

    @OnClick({R.id.recyclerview_button, R.id.swiperefreshlayout_button, R.id.viewpager_button
            , R.id.viewflipper_button, R.id.volley_button, R.id.navigation_drawer_button
            , R.id.fresco_button, R.id.image_loader_button, R.id.picasso_button, R.id.glide_button
            , R.id.RxJava_button, R.id.permiss_button, R.id.baidu_map_button, R.id.material_design_button
            , R.id.download_button, R.id.notification_button, R.id.dynamic_add_button})
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
            case R.id.fresco_button:
                intent = new Intent(MainActivity.this, FrescoActivity.class);
                startActivity(intent);
                break;
            case R.id.image_loader_button:
                intent = new Intent(MainActivity.this, ImageLoaderActivity.class);
                startActivity(intent);
                break;
            case R.id.picasso_button:
                intent = new Intent(MainActivity.this, PicassoActivity.class);
                startActivity(intent);
                break;
            case R.id.glide_button:
                intent = new Intent(MainActivity.this, GlideActivity.class);
                startActivity(intent);
                break;
            case R.id.RxJava_button:
                intent = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(intent);
                break;
            case R.id.permiss_button:
                intent = new Intent(MainActivity.this, PermissionActivity.class);
                startActivity(intent);
                break;
            case R.id.baidu_map_button:
                intent = new Intent(MainActivity.this, BaiDuMapActivity.class);
                startActivity(intent);
                break;
            case R.id.material_design_button:
                intent = new Intent(MainActivity.this, MaterialDesignActivity.class);
                startActivity(intent);
                break;
            case R.id.download_button:
                intent = new Intent(MainActivity.this, DownloadActivity.class);
                startActivity(intent);
                break;
            case R.id.notification_button:
                intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.dynamic_add_button:
                intent = new Intent(MainActivity.this, DynamicAddViewActivity.class);
                startActivity(intent);
                break;
        }
    }*/
}
