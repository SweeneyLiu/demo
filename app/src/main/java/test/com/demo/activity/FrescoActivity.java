package test.com.demo.activity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.support.v4.content.ContextCompat;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.com.demo.R;

public class FrescoActivity extends BaseActivity {

    @BindView(R.id.simpleDraweeView)
    SimpleDraweeView simpleDraweeView;

    private Uri imageUrl = Uri.parse("http://pic1.qiyipic.com/image/20160923/f0/e6/bk_100067950_r_601.jpg");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void initViews() {

        //第一阶段
        //创建DraweeController
       /* DraweeController controller = Fresco.newDraweeControllerBuilder()
                //加载的图片URI地址
                .setUri(imageUrl)
                //设置点击重试是否开启
                //注意：重复加载4次还是没有加载出来的时候才会显示 failureImage(失败图) 的图片
                .setTapToRetryEnabled(true)
                //设置旧的Controller
                .setOldController(simpleDraweeView.getController())
                //构建
                .build();

        //设置DraweeController
        simpleDraweeView.setController(controller);*/


        //第二阶段
        //初始化多张背景图集合
       /* List<Drawable> bgs = new ArrayList<Drawable>();
        bgs.add(ContextCompat.getDrawable(this,R.drawable.bg_zero));
        bgs.add(ContextCompat.getDrawable(this,R.drawable.bg_one));
        bgs.add(ContextCompat.getDrawable(this,R.drawable.bg_two));

        //初始化多张叠加图集合
        List<Drawable> overlays = new ArrayList<Drawable>();
        overlays.add(ContextCompat.getDrawable(this,R.drawable.overlay_one));
        overlays.add(ContextCompat.getDrawable(this,R.drawable.overlay_two));
        overlays.add(ContextCompat.getDrawable(this,R.drawable.overlay_three));

        GenericDraweeHierarchy genericDraweeHierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setFadeDuration(5000)
                .setBackground(ContextCompat.getDrawable(this, R.drawable.bg_zero))
                .setBackgrounds(bgs)
                .setOverlay(ContextCompat.getDrawable(this, R.drawable.overlay_one))
//                .setOverlays(overlays)
                .setPlaceholderImage(ContextCompat.getDrawable(this, R.drawable.icon_placeholder), ScalingUtils.ScaleType.CENTER_CROP)
                .setProgressBarImage(ContextCompat.getDrawable(this,R.drawable.icon_progress_bar),ScalingUtils.ScaleType.CENTER_CROP)
                .setFailureImage(ContextCompat.getDrawable(this,R.drawable.icon_failure),ScalingUtils.ScaleType.CENTER_CROP)
                .setRetryImage(ContextCompat.getDrawable(this,R.drawable.icon_retry),ScalingUtils.ScaleType.CENTER_CROP)
                .build();
        simpleDraweeView.setHierarchy(genericDraweeHierarchy);
        simpleDraweeView.setImageURI(imageUrl);

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageUrl)
                .setTapToRetryEnabled(true)
                .setOldController(simpleDraweeView.getController())
                .build();
        //在setHierarchy和setImageURI两个方法之后
        simpleDraweeView.setController(controller);*/

        //第三阶段

    }
}
