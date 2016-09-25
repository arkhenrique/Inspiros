package com.example.admin.inspiros;

import android.content.ClipData;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DragonHold extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragon_hold);
        new CountDownTimer(10000, 1000) {
            TextView text = (TextView) findViewById(R.id.hold);
            public void onTick(long millisUntilFinished) {
                text.setText("Segure o ar por " + (millisUntilFinished-1000) / 1000);
            }

            public void onFinish() {
                Intent i = new Intent(getBaseContext(), DragonBreath.class);
                i.putExtra("Origem", "hold");
                startActivity(i);
            }
        }.start();
    }
}
