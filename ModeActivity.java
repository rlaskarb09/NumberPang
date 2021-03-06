package com.anjinma.numberpang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ModeActivity extends AppCompatActivity implements View.OnClickListener{
    TextView infiniteText = null;
    TextView challengeText = null;
    TextView timeattackText = null;
    TextView hellText = null;
    MediaPlayer mp = null;
    SharedPreferences backgroundPreferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        infiniteText = (TextView) findViewById(R.id.Infinite);
        infiniteText.setOnClickListener(this);
        challengeText = (TextView) findViewById(R.id.Challenge);
        challengeText.setOnClickListener(this);
        timeattackText = (TextView) findViewById(R.id.Timeattack);
        timeattackText.setOnClickListener(this);
        hellText = (TextView) findViewById(R.id.Hell);
        hellText.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Infinite:
                Intent infinite = new Intent(getApplicationContext(), InfiniteActivity.class);
                startActivity(infinite);
                finish();
                break;
            case R.id.Challenge:
                Intent challenge = new Intent(getApplicationContext(),ChallengeActivity.class);
                startActivity(challenge);
                finish();
                break;
            case R.id.Timeattack:
                Intent timeattack = new Intent(getApplicationContext(),TimeAttackActivity.class);
                startActivity(timeattack);
                finish();
                break;
            case R.id.Hell:
                Intent hell = new Intent(getApplicationContext(),HellActivity.class);
                startActivity(hell);
                finish();
                break;
        }
    }

    public void onResume() {
        backgroundPreferences = getSharedPreferences("background", MODE_PRIVATE);

        if(backgroundPreferences.getBoolean("background" ,true)) {
            if(mp == null) {
                mp = MediaPlayer.create(this, R.raw.modebgm);
            }
            mp.start();   // 노래 시작
            mp.setLooping(true);   // 반복 true 설정
        }
        super.onResume();
    }

    public void onPause() {
        if(mp != null) {
            mp.pause();
        }
        super.onPause();
    }
    public void onBackPressed() {
        if (mp != null) {
            mp.stop();   // 미디어 플레이어 중지
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    try {
                        mp.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    try {
                        mp.stop();
                        mp.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
            finish();
        }
    }

}