package test.com.demo;

import android.app.Application;
import android.content.res.Resources;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.File;

/**
 * Created by Administrator on 2016/11/14 0014.
 */

public class DemoApplication extends Application {

    private static RefWatcher sRefWatcher;
    private static Resources sRes;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        sRefWatcher = LeakCanary.install(this);
        // Normal app init code...
        sRes = getResources();
        //fresco初始化
        Fresco.initialize(this);

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(getApplicationContext());
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }

    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }
}
