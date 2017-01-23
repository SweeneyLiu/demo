package test.com.demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import test.com.demo.R;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = "RxJavaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
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
        }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Consumer thread is : " + Thread.currentThread().getName());
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
                if(value == 1){
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
}
