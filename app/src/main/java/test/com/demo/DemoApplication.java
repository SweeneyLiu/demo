package test.com.demo;

import android.app.Application;
import android.content.res.Resources;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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
    }

    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }
}
