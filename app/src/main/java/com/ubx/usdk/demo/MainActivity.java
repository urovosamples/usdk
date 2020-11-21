package com.ubx.usdk.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private SoundPool soundpool = null;
    private int heightBeepId;
    private int middleBeepId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(1);
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound(2);
            }
        });
        initSoundpool();
    }
    private void initSoundpool() {
        if (soundpool != null) {
            soundpool.release();
        }

        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//			.setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
//			.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundpool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundpool = new SoundPool(10, AudioManager.STREAM_NOTIFICATION, 0);
        }
        heightBeepId = soundpool.load(this,R.raw.scan_buzzer, 1);
        middleBeepId = soundpool.load(this,R.raw.scan_new, 1);
        soundpool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.i(TAG, "onLoadComplete ------ status:" + status + ",sampleId:" + sampleId + ",soundpool:" + (soundPool!=null));
                if(status == 0) {
                }
            }
        });
    }

    private void playSound(int type) {
        if (soundpool != null) {
            if (type == 1) {
                /*soundpool.stop(middleBeepId);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                soundpool.play(middleBeepId, 1, 1, 1, 0, 1);
            } else {
                /*soundpool.stop(heightBeepId);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                soundpool.play(heightBeepId, 1, 1, 1, 0, 1);
            }
        }
    }
}
