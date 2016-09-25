package com.example.admin.inspiros;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;


public class HomeActivity extends AppCompatActivity {

    ProgressBar VerticalProgressBar;
    int value = 100;
    int moeda = 0;
    String name, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        VerticalProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        setProgressBarValue(value);
        VerticalProgressBar.setScaleY(3f);
        VerticalProgressBar.getProgressDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

        TextView text = (TextView) findViewById(R.id.petname);
        text.setText("Joao Silva Sauro");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void btnMiniGame(View v){

        value = value - 10;
        setProgressBarValue(value);
       // Intent myIntent = new Intent(HomeActivity.this, Coun.class);
       // HomeActivity.this.startActivity(myIntent);

    }
    public void btnInspiro(View v){
        Intent myIntent = new Intent(HomeActivity.this, DragonShake.class);
        HomeActivity.this.startActivity(myIntent);

    }

    public void btnLoja(View v){

        Intent i = new Intent(this,LojaActivity.class);
        startActivityForResult(i, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == LojaActivity.RESULT_OK) {
                    String namePet = data.getStringExtra("nomePet");
                    String sex = data.getStringExtra("sex");
                    TextView petName = (TextView) findViewById(R.id.petname);
                    if (namePet != null)
                        petName.setText(namePet);

                    ImageView avatar = (ImageView) findViewById(R.id.cavaleiro);
                    if (sex.equals("@drawable/cavaleiro")){
                        avatar.setImageResource(R.drawable.cavaleiro);
                    } else {
                        avatar.setImageResource(R.drawable.guerreira);
                    }
                }
                break;
            }
        }
    }

    public void btnExercicio(View v){

        Intent myIntent = new Intent(HomeActivity.this, CounterActivity.class);
        HomeActivity.this.startActivity(myIntent);
    }

    public void btnInfo(View v){
        Intent myIntent = new Intent(HomeActivity.this, InfoActivity.class);
        myIntent.putExtra("se tiver", "algum valor");
        HomeActivity.this.startActivity(myIntent);
    }

    public void btnAlarme(View v) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));


        new AlertDialog.Builder(this)
                .setTitle("Inspire e Expire")
                .setMessage("Você deseja informar os seus pais?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            File root = new File(getFilesDir(), "Notes");
                            if (!root.exists()) {
                                root.mkdirs();
                            }
                            File gpxfile = new File(root, "teste.txt");
                            FileWriter writer = new FileWriter(gpxfile, true);

                            writer.flush();
                            writer.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(R.drawable.inspiro_1)
                .show();

    }

    public void setProgressBarValue(int value) {
        if (value >= 10) {
            VerticalProgressBar.setProgress(value);
        }
    }
}
