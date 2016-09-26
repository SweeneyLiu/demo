package test.com.demo.activity;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by liushuwei on 2016/9/26.
 */
public abstract class BaseActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract void initVariables();
    protected abstract void initViews();
    protected abstract void loadData();
}
