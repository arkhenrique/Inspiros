package com.example.admin.inspiros;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.media.MediaRecorder;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class DragonBreath extends AppCompatActivity implements View.OnClickListener {
    String origem;
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragon_breath);
        findViewById(R.id.treasure).setOnClickListener(this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        texto = (TextView)findViewById(R.id.exhale);
        texto.setText("Toque no baú");
        if(b!=null){
            origem = (String) b.get("Origem");
            switch (origem) {
                case "shake":
                    texto.setText("Solte todo o ar tranquilamente");
                    break;
                case "hold":
                    texto.setText("Solte o ar de novo");
                    break;
            }

        }

    }

    @Override
    public void onClick(View arg0) {

        System.out.println("estou aqui");
        start();
        System.out.println(getAmplitudeEMA());
        double amplitude = 0.0;
        boolean sopra = false;
        for (int i=0;i<1000;i++) {
            amplitude = inicio();
            if((amplitude >1) && (amplitude<5)){
                sopra = true;
                break;
            }
        }

        if (sopra) {
            ParticleSystem ps = new ParticleSystem(this, 500, R.drawable.chama_2, 1250);
            ps.setScaleRange(0.7f, 1.3f);
            ps.setSpeedModuleAndAngleRange(0.07f, 0.16f, -40, -120);
            ps.setRotationSpeedRange(10, 30);
            ps.setAcceleration(0.00013f, -60);
            ps.setFadeOut(1000, new AccelerateInterpolator());
            ps.emit(Resources.getSystem().getDisplayMetrics().widthPixels/10*4,Resources.getSystem().getDisplayMetrics().heightPixels/3*2, 100, 2500);

            new CountDownTimer(3000, 1000) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    Intent i;
                    switch (origem) {
                        case "shake":
                            stop();
                            i = new Intent(getBaseContext(), DragonLock.class);
                            startActivity(i);
                            break;
                        case "hold":
                            stop();
                            i = new Intent(getBaseContext(), DragonDone.class);
                            startActivity(i);
                            break;
                    }

                }
            }.start();
        } else {
            texto.setText("Aperte o baú novamente");
        }
    }

    // This file is used to record voice
    static final private double EMA_FILTER = 0.6;

    private MediaRecorder mRecorder = null;
    private double mEMA = 0.0;

    public void start() {
        //Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();
        if (mRecorder == null) {

            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");

            try {
                mRecorder.prepare();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            mRecorder.start();
            mEMA = 0.0;
        }
    }

    public void stop() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public double getAmplitude() {
        if (mRecorder != null){
            return (mRecorder.getMaxAmplitude() / 2700.0);
        }else{
            return 0;
        }
    }

    public double inicio(){
        start();
        System.out.println(getAmplitudeEMA());
        return getAmplitudeEMA();
    }

    public double getAmplitudeEMA() {
        double amp = getAmplitude();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;

        return mEMA;

    }

}
