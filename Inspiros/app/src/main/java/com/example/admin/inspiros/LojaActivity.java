package com.example.admin.inspiros;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.ByteArrayOutputStream;
import java.util.zip.Inflater;


public class LojaActivity extends AppCompatActivity {

    String name, eyes, hair, gender, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eyes = "aqua";
        gender = "male";
        hair = "fire";

        setContentView(R.layout.activity_loja);
    }

    public void changeGenderM(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        gender = "male";
        i.setImageResource(R.drawable.cavaleiro);
        RadioGroup g1 = (RadioGroup) findViewById(R.id.grupo1);
        g1.setVisibility(View.VISIBLE);
        RadioGroup g2 = (RadioGroup) findViewById(R.id.grupo2);
        g2.setVisibility(View.GONE);
        switch (eyes) {

            case "aqua":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.cavaleiro);
                        image = "@drawable/cavaleiro";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.cavaleiro);
                        image = "@drawable/cavaleiro";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.cavaleiro);
                        image = "@drawable/cavaleiro";
                        break;
                    default:
                        break;

                }
                break;
            case "ruby":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.cavaleiro);
                        image = "@drawable/cavaleiro";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.cavaleiro);
                        image = "@drawable/cavaleiro";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.cavaleiro);
                        image = "@drawable/cavaleiro";
                        break;
                    default:
                        break;
                }
                break;
            case "stone":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.cavaleiro);
                        image = "@drawable/cavaleiro";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.cavaleiro);
                        image = "@drawable/cavaleiro";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.cavaleiro);
                        image = "@drawable/cavaleiro";
                        break;
                    default:
                        break;
                }
                break;



        }

    }

    public void changeGenderF(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        // i.setImageResource(R.drawable.eyes1);
        gender = "female";
        RadioGroup g1 = (RadioGroup) findViewById(R.id.grupo1);
        RadioGroup g2 = (RadioGroup) findViewById(R.id.grupo2);
        g2.setVisibility(View.VISIBLE);
        g1.setVisibility(View.GONE);
        switch (eyes) {
            case "aqua":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.guerreira);
                        image = "@drawable/guerreira";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.guerreira);
                        image = "@drawable/guerreira";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.guerreira);
                        image = "@drawable/guerreira";
                        break;
                    default:
                        break;
                }
                break;
            case "ruby":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.guerreira);
                        image = "@drawable/guerreira";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.guerreira);
                        image = "@drawable/guerreira";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.guerreira);
                        image = "@drawable/guerreira";
                        break;
                    default:
                        break;
                }
                break;
            case "stone":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.guerreira);
                        image = "@drawable/guerreira";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.guerreira);
                        image = "@drawable/guerreira";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.guerreira);
                        image = "@drawable/guerreira";
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    public void changeEyesA(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        //  i.setImageResource(R.drawable.eyes1);
        RadioButton r1 = (RadioButton) findViewById(R.id.checkBox11);
        r1.setChecked(false);
        RadioButton r2 = (RadioButton) findViewById(R.id.checkBox12);
        r2.setChecked(false);
        eyes = "Aqua";
        /*
        switch (gender){
            case "male":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
                break;
            case "female":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
        */

    }
    public void changeEyesR(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        //  i.setImageResource(R.drawable.eyes1);
        RadioButton r1 = (RadioButton) findViewById(R.id.checkBox10);
        r1.setChecked(false);
        RadioButton r2 = (RadioButton) findViewById(R.id.checkBox12);
        r2.setChecked(false);
        /*
        eyes = "Ruby";
        switch (gender){
            case "male":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
                break;
            case "female":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
        */
    }
    public void changeEyesS(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        // i.setImageResource(R.drawable.eyes1);
        RadioButton r1 = (RadioButton) findViewById(R.id.checkBox10);
        r1.setChecked(false);
        RadioButton r2 = (RadioButton) findViewById(R.id.checkBox11);
        r2.setChecked(false);
        eyes = "Stone";
        /*
        switch (gender){
            case "male":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
                break;
            case "female":
                switch (hair){
                    case "fire":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "raven":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "aurelia":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
        */
    }
    public void changeHairF(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        // i.setImageResource(R.drawable.eyes1);
        RadioButton r1 = (RadioButton) findViewById(R.id.checkBox8);
        r1.setChecked(false);
        RadioButton r2 = (RadioButton) findViewById(R.id.checkBox9);
        r2.setChecked(false);
        hair = "fire";
        /*
        switch (gender){
            case "male":
                switch (eyes){
                    case "aqua":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "ruby":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "stone":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
                break;
            case "female":
                switch (eyes){
                    case "aqua":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "ruby":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "stone":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
        */
    }
    public void changeHairR(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        //i.setImageResource(R.drawable.eyes1);
        RadioButton r1 = (RadioButton) findViewById(R.id.checkBox7);
        r1.setChecked(false);
        RadioButton r2 = (RadioButton) findViewById(R.id.checkBox9);
        r2.setChecked(false);
        hair = "raven";
        /*
        switch (gender){
            case "male":
                switch (eyes){
                    case "aqua":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "ruby":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "stone":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
                break;
            case "female":
                switch (eyes){
                    case "aqua":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "ruby":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "stone":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
        */
    }
    public void changeHairA(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        //i.setImageResource(R.drawable.eyes1);
        RadioButton r1 = (RadioButton) findViewById(R.id.checkBox7);
        r1.setChecked(false);
        RadioButton r2 = (RadioButton) findViewById(R.id.checkBox8);
        r2.setChecked(false);
        hair = "aurelia";
        /*
        switch (gender){
            case "male":
                switch (eyes){
                    case "aqua":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "ruby":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "stone":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
                break;
            case "female":
                switch (eyes){
                    case "aqua":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "ruby":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    case "stone":
                        i.setImageResource(R.drawable.eyes1);
                        image = "@drawable/1";
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
        */
    }

    public void changeName(View v){
        EditText editText = (EditText) findViewById(R.id.editText2);
        String message = editText.getText().toString();
        editText.setText(message);
        name = message;
        Intent resultIntent = new Intent();
        resultIntent.putExtra("nomePet", name);
        resultIntent.putExtra("sex", image);

        setResult(LojaActivity.RESULT_OK, resultIntent);
        finish();
    }


    public void changeClothesC(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.setImageResource(R.drawable.cavaleiro);
        image = "@drawable/cavaleiro";
    }

    public void changeClothesGo(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.setImageResource(R.drawable.cavaleiro);
        image = "@drawable/cavaleiro";
    }

    public void changeClothesP(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.setImageResource(R.drawable.guerreira);
        image = "@drawable/guerreira";
    }

    public void changeClothesGa(View v){
        ImageView i = (ImageView) findViewById(R.id.imageView);
        i.setImageResource(R.drawable.guerreira);
        image = "@drawable/guerreira";
    }

}
