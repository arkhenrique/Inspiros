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

public class DragonDone extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragon_done);
        findViewById(R.id.treasure).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView texto = (TextView)findViewById(R.id.done);
        texto.setText("Pronto! Pode pegar o dinheiro!\n E não esqueça de lavar a boca!");
        ImageView treasure = (ImageView) findViewById(R.id.treasure);
        treasure.setVisibility(View.INVISIBLE);

        findViewById(R.id.coins).setOnTouchListener(new MyTouchListener());
    }

    // This defines your touch listener
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);

                Intent i = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(i);
                return true;
            } else {
                return false;
            }
        }
    }
}
