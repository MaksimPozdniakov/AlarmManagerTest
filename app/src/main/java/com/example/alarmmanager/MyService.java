package com.example.alarmmanager;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private NotificationManager notificationManager;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long awakeTime = intent.getLongExtra("awakeTime", 0);
        long targetTime = intent.getLongExtra("targetTime", 0);

        long delay = targetTime - awakeTime;

        setAlarm(delay);

        return START_STICKY;
    }
    private void setAlarm(long delay) {
        try {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + delay, alarmIntent);
        } catch (SecurityException e) {
            // Обработайте ситуацию, когда нет необходимого разрешения
            System.err.println("Разрешение не предоставленно!");
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
