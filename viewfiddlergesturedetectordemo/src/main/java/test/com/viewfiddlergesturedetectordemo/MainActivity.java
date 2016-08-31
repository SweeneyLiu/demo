package test.com.viewfiddlergesturedetectordemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnTouchListener {

    private ViewFlipper viewFlipper;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        viewFlipper.setOnTouchListener(this);
        gestureDetector = new GestureDetector(this,new MySimpleOnGestureListener());


    }

    private void initView() {
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    class MySimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener{

        public MySimpleOnGestureListener() {
            super();
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            return super.onFling(e1, e2, velocityX, velocityY);
            // 从左往右，切换到上一个View
            if (e2.getX() - e1.getX() > 50) {
                // 设置切换时的动画
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.push_right_in));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.push_right_out));
                // 显示上一个View
                viewFlipper.showPrevious();
                // 从右往左，切换到下一个View
            } else if (e1.getX() - e2.getX() > 50) {
                // 设置切换时的动画
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.push_left_in));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.push_left_out));
                // 显示下一个View
                viewFlipper.showNext();
            }
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            return super.onContextClick(e);
        }
    }
}
