package com.example.admin.inspiros;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class DragonInhale extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragon_inhale);
        ParticleSystem ps = new ParticleSystem(this, 5, R.drawable.folego, 1000);
        ps.setScaleRange(0.27f, 0.73f);
        ps.setSpeedModuleAndAngleRange(0.07f, 0.16f, 120, 170);
        ps.setRotationSpeedRange(10, 30);
        ps.setAcceleration(0.00013f, 125);
        ps.setFadeOut(1000, new AccelerateInterpolator());
        ps.emit(Resources.getSystem().getDisplayMetrics().widthPixels * 3 / 5, Resources.getSystem().getDisplayMetrics().heightPixels / 3 * 1, 100, 2500);


        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent i = new Intent(getBaseContext(), DragonHold.class);
                startActivity(i);
            }
        }.start();

    }




}
