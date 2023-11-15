package com.example.alarmmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {
    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    private void addListenerOnButton() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = (5 * 60 * 1000);
                method(time);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = (30 * 60 * 1000);
                method(time);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = (60 * 60 * 1000);
                method(time);
            }
        });
    }

    private void method(int time) {
        // Определите значения awakeTime и targetTime
        long awakeTime = SystemClock.elapsedRealtime(); // Текущее время
        long targetTime = awakeTime + time;

        // Создайте Intent для запуска вашей службы (MyService)
        Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
        serviceIntent.putExtra("awakeTime", awakeTime);
        serviceIntent.putExtra("targetTime", targetTime);
        // Запустите службу
        startService(serviceIntent);
    }
}