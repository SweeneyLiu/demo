package test.com.demo.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.com.demo.R;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.send_notice)
    Button sendNotice;
    @BindView(R.id.cancle_notice)
    Button cancleNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.send_notice,R.id.cancle_notice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(NotificationActivity.this, DownloadActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(NotificationActivity.this)
                        .setContentTitle("This is content title")
//                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)//点击通知，通知自动取消
                        /*.setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Bounce.ogg")))//控制通知声音
                        .setVibrate(new long[]{0,1000,1000,1000})//控制通知振动*/
                        .setLights(Color.GREEN,1000,1000)//设置LED灯闪烁
//                        .setDefaults(NotificationCompat.DEFAULT_ALL)//使用通知的默认效果
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.android1)))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.orange)).build();
                notificationManager.notify(1, notification);
                break;
            case R.id.cancle_notice:
                /*NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                manager.cancel(1);*/
                break;
        }
    }

}
