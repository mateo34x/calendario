package com.example.calendario;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;

import com.tombayley.activitycircularreveal.CircularReveal;

import java.util.Random;

public class Prueba extends AppCompatActivity {

    private CardView cardView;
    private View myView;
    private ImageView prueba;
    RenderEffect blur;

    ImageButton edit, block;

    Button save;
    private boolean isExpanded = false;

    private ConstraintLayout twoView;
    private boolean isBlock = false;


    private ColorStateList colorStateList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        cardView = findViewById(R.id.card);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            blur = RenderEffect.createBlurEffect(15f, 15f, Shader.TileMode.CLAMP);
        }


        String[] colores = {
                "#EDE4DB", "#F4F4F4", "#3268D4", "#DA7A52",
                "#DDB4C2", "#E0F1EB", "#BDC3F5", "#EEE9CB",
                "#F1D1DC", "#D8DDE0", "#BCC2F4", "#8796A7"
        };

        Random random = new Random();
        int indiceColorAleatorio = random.nextInt(colores.length);
        String colorAleatorio = colores[indiceColorAleatorio];

        Log.e("COLORRANDOM", colorAleatorio);

        cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorAleatorio)));


        colorStateList = cardView.getBackgroundTintList();
        int[] state = cardView.getDrawableState();
        int color = colorStateList.getColorForState(state, colorStateList.getDefaultColor());





        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ToyMamaoXD(v,Prueba.this);



            }



        });





       /* block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBlock) {

                    prueba.setVisibility(View.VISIBLE);
                    edit.setEnabled(false);
                    isBlock = true;
                } else {

                    prueba.setVisibility(View.INVISIBLE);
                    edit.setEnabled(true);
                    isBlock = false;
                }

            }
        });*/


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    private void revealLayoutFun(int color) {
        /*if (!isExpanded) {
            int x = myView.getRight() / 2;
            int y = myView.getBottom() / 2;

            float startRadius = 0;

            float endRadius = (float) Math.hypot(myView.getWidth(), myView.getHeight());

            Animator anim = ViewAnimationUtils.createCircularReveal(
                    myView,
                    x,
                    y,
                    startRadius,
                    endRadius
            );

            myView.setBackgroundColor(color);
            myView.setVisibility(View.VISIBLE);
            anim.start();
            isExpanded = true;

        } else {
            int x = myView.getRight() / 2;
            int y = myView.getBottom() / 2;

            float startRadius = Math.max(myView.getWidth(), myView.getHeight());

            float endRadius = 0;

            Animator anim = ViewAnimationUtils.createCircularReveal(
                    myView,
                    x,
                    y,
                    startRadius,
                    endRadius
            );

            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    myView.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });

            anim.start();
            isExpanded = false;
        }*/








    }


    /*private CircularReveal getInstance(){
        if (circularReveal != null){
            return circularReveal;
        }


        View reveal = findViewById(R.id.revalLayout);



        int centerY = reveal.getHeight() / 2;
        int centerX = reveal.getWidth() / 2;

        circularReveal = new CircularReveal(reveal, centerX, centerY);
        circularReveal.setExpandDur(700);
        circularReveal.setBackgroundColor(R.color.blu);

        circularReveal.setCircularRevealListener(new CircularReveal.CircularRevealListener() {
            @Override
            public void onAnimationEnd(int animState) {
                Intent intent = new Intent(Prueba.this, NewActivity.class);
                startActivity(intent);
            }
        });

        return circularReveal;
    }*/





    public void ToyMamaoXD(View v, Context context){
        CircularReveal.presentActivity(new CircularReveal.Builder(
                this,
                v,
                new Intent(context, NewActivity.class),
                500
        ));

    }






}

