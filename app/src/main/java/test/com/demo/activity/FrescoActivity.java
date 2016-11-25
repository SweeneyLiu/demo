package test.com.demo.activity;

import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
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
        List<Drawable> bgs = new ArrayList<Drawable>();
        bgs.add(ContextCompat.getDrawable(this,R.drawable.bg_zero));
        bgs.add(ContextCompat.getDrawable(this,R.drawable.bg_one));
        bgs.add(ContextCompat.getDrawable(this,R.drawable.bg_two));

        //初始化多张叠加图集合
        List<Drawable> overlays = new ArrayList<Drawable>();
        overlays.add(ContextCompat.getDrawable(this,R.drawable.overlay_one));
        overlays.add(ContextCompat.getDrawable(this,R.drawable.overlay_two));
        overlays.add(ContextCompat.getDrawable(this,R.drawable.overlay_three));

        RoundingParams rp = new RoundingParams();
        //设置图像是否为圆形
        rp.setRoundAsCircle(true);
        //设置圆角半径
//        rp.setCornersRadius(20);
        //分别设置左上角、右上角、左下角、右下角的圆角半径
//        rp.setCornersRadii(20,25,30,35);
        //分别设置（前2个）左上角、(3、4)右上角、(5、6)左下角、(7、8)右下角的圆角半径
//        rp.setCornersRadii(new float[]{20,25,30,35,40,45,50,55});
        //设置边框颜色及其宽度
        rp.setBorder(Color.RED,10);
        //设置叠加颜色
        rp.setOverlayColor(Color.GRAY);
        //设置圆形圆角模式
//        rp.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
        //设置圆形圆角模式
        rp.setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR);


        GenericDraweeHierarchy genericDraweeHierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setFadeDuration(5000)
//                .setBackground(ContextCompat.getDrawable(this, R.drawable.bg_zero))
//                .setBackgrounds(bgs)
//                .setOverlay(ContextCompat.getDrawable(this, R.drawable.overlay_one))
//                .setOverlays(overlays)
                .setPlaceholderImage(ContextCompat.getDrawable(this, R.drawable.icon_placeholder), ScalingUtils.ScaleType.CENTER_CROP)
                .setProgressBarImage(ContextCompat.getDrawable(this,R.drawable.icon_progress_bar),ScalingUtils.ScaleType.CENTER_CROP)
                .setFailureImage(ContextCompat.getDrawable(this,R.drawable.icon_failure),ScalingUtils.ScaleType.CENTER_CROP)
                .setRetryImage(ContextCompat.getDrawable(this,R.drawable.icon_retry),ScalingUtils.ScaleType.CENTER_CROP)
//                .setRoundingParams(rp)
                //设置圆角半径
                .setRoundingParams(RoundingParams.fromCornersRadius(20))
                //分别设置左上角、右上角、左下角、右下角的圆角半径
                .setRoundingParams(RoundingParams.fromCornersRadii(20,25,30,35))
                //设置圆形圆角参数；RoundingParams.asCircle()是将图像设置成圆形
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        simpleDraweeView.setHierarchy(genericDraweeHierarchy);

        ControllerListener controllerListener = new BaseControllerListener(){

            @Override
            public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                Log.i("lsw","onFinalImageSet");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                Log.i("lsw","onFailure");
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageUrl)
                .setTapToRetryEnabled(true)
                .setControllerListener(controllerListener)
                .build();

        simpleDraweeView.setController(controller);

        //第三阶段
        //初始化圆角圆形参数对象
        /*RoundingParams rp = new RoundingParams();
        //设置图像是否为圆形
        rp.setRoundAsCircle(true);
        //设置圆角半径
//        rp.setCornersRadius(20);
        //分别设置左上角、右上角、左下角、右下角的圆角半径
//        rp.setCornersRadii(20,25,30,35);
        //分别设置（前2个）左上角、(3、4)右上角、(5、6)左下角、(7、8)右下角的圆角半径
//        rp.setCornersRadii(new float[]{20,25,30,35,40,45,50,55});
        //设置边框颜色及其宽度
        rp.setBorder(Color.RED,10);
        //设置叠加颜色
        rp.setOverlayColor(Color.GRAY);
        //设置圆形圆角模式
//        rp.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
        //设置圆形圆角模式
        rp.setRoundingMethod(RoundingParams.RoundingMethod.OVERLAY_COLOR);


        //获取GenericDraweeHierarchy对象
        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(getResources())
                .setRoundingParams(rp)
                //设置圆角半径
//                .setRoundingParams(RoundingParams.fromCornersRadius(20))
                //分别设置左上角、右上角、左下角、右下角的圆角半径
//                .setRoundingParams(RoundingParams.fromCornersRadii(20,25,30,35))
                //分别设置（前2个）左上角、(3、4)右上角、(5、6)左下角、(7、8)右下角的圆角半径
//                .setRoundingParams(RoundingParams.fromCornersRadii(new float[]{20,25,30,35,40,45,50,55}))
                //设置圆形圆角参数；RoundingParams.asCircle()是将图像设置成圆形
//                .setRoundingParams(RoundingParams.asCircle())
                //设置淡入淡出动画持续时间(单位：毫秒ms)
                .setFadeDuration(5000)
                //构建
                .build();

        //设置Hierarchy
        simpleDraweeView.setHierarchy(hierarchy);

        //构建Controller
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //设置需要下载的图片地址
                .setUri(imageUrl)
                //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                //构建
                .build();

        //设置Controller
        simpleDraweeView.setController(controller);*/

        //第四阶段
        /*ControllerListener controllerListener = new BaseControllerListener(){

            @Override
            public void onSubmit(String id, Object callerContext) {
                super.onSubmit(id, callerContext);
                Log.i("lsw","onSubmit");
            }

            @Override
            public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                Log.i("lsw","onFinalImageSet");
            }

            @Override
            public void onIntermediateImageSet(String id, Object imageInfo) {
                super.onIntermediateImageSet(id, imageInfo);
                Log.i("lsw","onIntermediateImageSet");
            }

            @Override
            public void onIntermediateImageFailed(String id, Throwable throwable) {
                super.onIntermediateImageFailed(id, throwable);
                Log.i("lsw","onIntermediateImageFailed");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                Log.i("lsw","onFailure");
            }

            @Override
            public void onRelease(String id) {
                super.onRelease(id);
                Log.i("lsw","onRelease");
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //加载的图片URI地址
                .setUri(imageUrl)
                .setControllerListener(controllerListener)
                .build();

        //设置DraweeController
        simpleDraweeView.setController(controller);*/

    }
}
