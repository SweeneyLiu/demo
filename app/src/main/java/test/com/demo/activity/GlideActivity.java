package test.com.demo.activity;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.com.demo.R;

public class GlideActivity extends BaseActivity {

    @BindView(R.id.image_view)
    ImageView imageView;

    String imageUrl = "http://pic1.qiyipic.com/image/20160923/f0/e6/bk_100067950_r_601.jpg";
//    String gifUri = "http://p5.qhimg.com/t01d0e0384b952ed7e8.gif";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initViews() {

        //简单的加载图片实例
        Glide.with(this).load(imageUrl).into(imageView);
        //设置加载中以及加载失败图片
//        Glide.with(this).load(imageUrl).placeholder(R.drawable.icon_placeholder).error(R.drawable.icon_failure).into(imageView);
        //设置跳过内存缓存
//        Glide.with(this).load(imageUrl).skipMemoryCache(true).into(imageView);
        //设置下载优先级
//        Glide.with(this).load(imageUrl).priority(Priority.NORMAL).into(imageView);
        //设置磁盘缓存策略
//        Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);//all:缓存源资源和转换后的资源 none:不作任何磁盘缓存 source:缓存源资源 result：缓存转换后的资源
        //设置加载动画
//        Glide.with(this).load(imageUrl).animate(android.R.anim.fade_in).into(imageView);
        //设置缩略图支持
//        Glide.with(this).load(imageUrl).thumbnail(0.1f).into(imageView);
        //设置加载尺寸
//        Glide.with(this).load(imageUrl).override(800,800).into(imageView);
        //设置动态转换
//        Glide.with(this).load(imageUrl).centerCrop().into(imageView);
//        Glide.with(this).load(imageUrl).transform(new GlideRoundTransform(this)).into(imageView);
        //设置要加载的内容
       /* Glide.with(this).load(imageUrl).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                imageView.setImageDrawable(resource);
            }
        });*/
        //设置监听请求接口
        /*Glide.with(this).load(imageUrl).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.i("isFromMemoryCache = ",""+isFromMemoryCache+";isFirstResource = "+isFirstResource);
                return false;
            }
        }).into(imageView);*/

//        Glide.with(this).load(gifUri).asBitmap().into(imageView);//显示gif静态图片
//        Glide.with(this).load(gifUri).asGif().into(imageView);//显示gif动态图片

//        Glide.get(this).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
//        Glide.get(this).clearMemory();//清理内存缓存  可以在UI主线程中进行
    }


    public class GlideRoundTransform extends BitmapTransformation {
        private float radius = 0f;
        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }

}
