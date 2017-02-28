package test.com.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.com.demo.R;

public class DynamicAddViewActivity extends AppCompatActivity {

    @BindView(R.id.activity_dynamic_add_view)
    LinearLayout activityDynamicAddView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_add_view);
        ButterKnife.bind(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View bagLayout = layoutInflater.inflate(R.layout.bag_item, null);
        View bagLayout1 = layoutInflater.inflate(R.layout.bag_item, null);
        activityDynamicAddView.addView(bagLayout);
        activityDynamicAddView.addView(bagLayout1);
    }
}
