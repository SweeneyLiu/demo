package test.com.demo.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.com.demo.R;

public class PicassoActivity extends BaseActivity {

    @BindView(R.id.image_view)
    ImageView imageView;

    String uri = "http://pic1.qiyipic.com/image/20160923/f0/e6/bk_100067950_r_601.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void initViews() {
        Picasso.with(this).load(uri).into(imageView);
    }
}
