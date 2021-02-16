package com.example.mysound;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
 //   private AssetManager mLadMana;
//    private int LoadMana mLoadMana;
    private int mSoundCollision=1;
    private int mStreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNewSoundPool();

        mSoundPool.load(this, R.raw.collision, 1); //загрузить аудиофайлы из папки Assets с поомщью метода getAssets

       Button playButton = (Button) findViewById(R.id.Playbutton);
       // Button pauseButton = (Button) findViewById(R.id.Pausebutton);playButton.setOnClickListener(onPlayButtonClickListener);
 //       pauseButton.setOnClickListener(onPauseButtonClickListener);
    }

   Button.OnClickListener onPlayButtonClickListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        /*    float curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float leftVolume = curVolume / maxVolume;
            float rightVolume = curVolume / maxVolume;
            int priority = 1;
            int no_loop = 0;
            float normal_playback_rate = 1f; */

            mStreamId = mSoundPool.play(mSoundCollision, 1,1,1,1, 1);//leftVolume, rightVolume, priority, no_loop, normal_playback_rate);

            Toast.makeText(getApplicationContext(), "soundPool.play()", Toast.LENGTH_LONG).show();
        }
    };

 /*   Button.OnClickListener onPauseButtonClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            mSoundPool.pause(mStreamId);
            Toast.makeText(getApplicationContext(), "soundPool.pause()", Toast.LENGTH_LONG).show(); }
    };
    */

   @TargetApi(Build.VERSION_CODES.LOLLIPOP) //вызов класса SoundPool.Builder
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

}