package com.example.calendario;

import static java.sql.Types.NULL;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


public class ChangeMode {

    private static boolean isWeekMode = true;




    public static void mode(MaterialCalendarView calendarView){

        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f); /////////para que desaparezca el titulo de saludo
        fadeOut.setDuration(8000);
        fadeOut.setStartOffset(8000);
        fadeOut.setFillAfter(true);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f); ///para que aparezca el titulo chats
        fadeIn.setDuration(600);
        fadeIn.setStartOffset(1000);
        fadeIn.setFillAfter(true);


        if (isWeekMode) {

            calendarView.setAnimation(fadeOut);
            calendarView.state().edit()
                    .setCalendarDisplayMode(CalendarMode.MONTHS)
                    .commit();
            calendarView.startAnimation(fadeIn);

            isWeekMode = false;

        } else {


            calendarView.setAnimation(fadeOut);
            calendarView.state().edit()
                    .setCalendarDisplayMode(CalendarMode.WEEKS)
                    .commit();
            calendarView.startAnimation(fadeIn);
            isWeekMode = true;


        }


    }




}
