package com.example.calendario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.tombayley.activitycircularreveal.CircularReveal;

public class NewActivity extends AppCompatActivity {

    private CircularReveal mActivityCircularReveal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        View rootView = findViewById(R.id.revalLayout);
        mActivityCircularReveal = new CircularReveal(rootView);
        mActivityCircularReveal.onActivityCreate(getIntent());

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mActivityCircularReveal.unRevealActivity(this);
    }
}