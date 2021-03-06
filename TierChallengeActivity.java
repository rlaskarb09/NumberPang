package com.anjinma.numberpang;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TierChallengeActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db = null;
    TextView tierChallenge = null;
    TextView tierChallengeGo = null;
    TextView tierChallengeBack = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tier_challenge);
        tierChallenge = (TextView)findViewById(R.id.Tier_Challenge);
        tierChallengeBack = (TextView) findViewById(R.id.Tier_Challenge_Back);
        tierChallengeBack.setOnClickListener(this);
        tierChallengeGo = (TextView) findViewById(R.id.Tier_Challenge_Go);
        tierChallengeGo.setOnClickListener(this);

        setTeer("Challenge");

    }

    public void setTeer(String tableName) {
        Cursor cursor;
        int score;

        db = openOrCreateDatabase("scoreDB.db", MODE_PRIVATE, null);
        createTable(tableName);
        cursor = queryData(tableName);

        if(cursor.getCount() == 1) {
            cursor.moveToNext();
            score = Integer.parseInt(cursor.getString(0));

            if(score >= 0 && score < 500) {
                tierChallenge.setText("스톤");
            } else if(score >=  500 && score < 1000) {
                tierChallenge.setText("브론즈");
            } else if(score >=  1000 && score < 1500) {
                tierChallenge.setText("실버");
            } else if(score >=  1500 && score < 2000) {
                tierChallenge.setText("골드");
            } else if(score >=  2000 && score < 3000) {
                tierChallenge.setText("플래티넘");
            } else if(score >=  3000 && score < 5000) {
                tierChallenge.setText("다이아");
            } else if(score >=  5000 && score < 10000) {
                tierChallenge.setText("마스터");
            } else if(score >=  10000) {
                tierChallenge.setText("그랜드마스터");
            }

        }
    }

    private void createDatabase() {
        String name = "scoreDB.db";
        db = SQLiteDatabase.openOrCreateDatabase(name,  null);
    }

    private void createTable(String tableName) {

        String sql = "create table " + tableName + " (score int, date text)";

        try {
            db.execSQL(sql);//slq문 실행
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "Create Table 오류", Toast.LENGTH_LONG).show();
        }
    }

    public Cursor queryData(String tableName) {
        String sql = "select * from " + tableName + " order by score desc limit 1";
        Cursor cursor = db.rawQuery(sql, null);


        return cursor;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Tier_Challenge_Back:
                Intent intent = new Intent(getApplicationContext(), TierInfiniteActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Tier_Challenge_Go:
                Intent intent2 = new Intent(getApplicationContext(), TierTimeAttackActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}
