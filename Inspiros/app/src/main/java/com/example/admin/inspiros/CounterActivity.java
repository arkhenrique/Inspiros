package com.example.admin.inspiros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.hardware.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class CounterActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private TextView count;
    boolean activityRunning;
    float inicial;
    boolean primeiro;
    boolean terceiro;
    int lastday;
    int contavezes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        count = (TextView) findViewById(R.id.count);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        primeiro = true;
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        if (lastday != currentDay){
            terceiro = false;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        activityRunning = false;
        // if you unregister the last listener, the hardware will stop detecting step events
//        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float valor;
        if (activityRunning) {
            int lastday = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
            if (!terceiro) {
                if (primeiro) {
                    inicial = event.values[0];
                    primeiro = false;
                }
                if ((event.values[0] - inicial) <= 0) {
                    valor = 0;
                } else {
                    valor = (event.values[0] - inicial);
                }
                count.setText(String.valueOf(valor));

                if ((event.values[0] - inicial) > 20 && primeiro == false) {
                    Toast.makeText(this, "Meta atingida", Toast.LENGTH_LONG).show();
                    primeiro = true;
                    Button imgb1 = (Button) findViewById(R.id.toggleButton);
                    imgb1.setVisibility(View.VISIBLE);
                }
            } else{
                Toast.makeText(this, "Limite diário atingido", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void getCoin1(View v){
        Button imgb1 = (Button) findViewById(R.id.toggleButton);
        imgb1.setVisibility(View.GONE);
        contavezes ++;
        if ( contavezes >=3){
            terceiro = true;
        }
        if (terceiro == false) {
            TextView t = (TextView) findViewById(R.id.textView4);
            t.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}