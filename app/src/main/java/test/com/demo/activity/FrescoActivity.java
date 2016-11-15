package test.com.demo.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.com.demo.R;

public class FrescoActivity extends Activity {

    @BindView(R.id.my_image_view)
    SimpleDraweeView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.my_image_view)
    public void onClick() {
        Uri uri = Uri.parse("http://pic1.qiyipic.com/image/20160923/f0/e6/bk_100067950_r_601.jpg");
        myImageView.setImageURI(uri);
    }
}
