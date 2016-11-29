package test.com.demo.activity;


import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.com.demo.R;

public class GlideActivity extends BaseActivity {

    @BindView(R.id.image_view)
    ImageView imageView;

    String uri = "http://pic1.qiyipic.com/image/20160923/f0/e6/bk_100067950_r_601.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void initViews() {
        Glide.with(this).load(uri).into(imageView);
    }
}
