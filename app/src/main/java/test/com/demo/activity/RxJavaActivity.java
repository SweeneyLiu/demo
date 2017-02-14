package test.com.demo.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import test.com.demo.R;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);
//        testObserver();
        testConsume();
    }

    private void testConsume() {
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
                Log.d(TAG, "onNext(1)");
                e.onNext(2);
                Log.d(TAG, "onNext(2)");
                e.onComplete();
                Log.d(TAG, "onComplete(1)");
                e.onNext(3);
                Log.d(TAG, "onNext(3)");
            }
        }).subscribeOn(Schedulers.newThread()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext---main---Consumer thread is : " + Thread.currentThread().getName());
                Log.d(TAG, String.valueOf(integer));
            }
        }).observeOn(Schedulers.io()).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext---io1---Consumer thread is : " + Thread.currentThread().getName());
                Log.d(TAG, String.valueOf(integer));
            }
        }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "doOnNext---io2---Consumer thread is : " + Thread.currentThread().getName());
                Log.d(TAG, String.valueOf(integer));
            }
        });
    }


    private void testObserver() {
        //创建一个Observable
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                Log.d(TAG, "onNext(1)");
                e.onNext(2);
                Log.d(TAG, "onNext(2)");
//                e.onError(null);
//                Log.d(TAG, "onError(1)");
//                e.onError(null);
//                Log.d(TAG, "onError(2)");
                e.onComplete();
                Log.d(TAG, "onComplete(1)");
//                e.onComplete();
//                Log.d(TAG, "onComplete(2)");
                e.onNext(3);
                Log.d(TAG, "onNext(3)");

            }
        }).subscribe(new Observer<Integer>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext");
                if (value == 1) {
//                    disposable.dispose();
                    Log.d(TAG, "dispose");
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });

        //创建一个Observer
        /*Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext");
                if (value == 1) {
                    Log.d(TAG, "dispose");
                    mDisposable.dispose();
                    Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        //建立关联
        observable.subscribe(observer);*/

    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("loading......");
                progressDialog.setCancelable(true);//可以通过back键取消掉
                progressDialog.show();
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
        }
    }
}
