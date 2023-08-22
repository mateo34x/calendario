package com.example.calendario;

import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class TimePicker {


    private static Calendar calendar;
    private static SimpleDateFormat timeFormat;





    public void showTimePickerDialog(Context context, TextView fecha) {

        calendar = Calendar.getInstance();
        timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());


        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);


        TimePickerDialog timePickerDialog = new TimePickerDialog(context,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        String selectedTime = timeFormat.format(calendar.getTime());
                        fecha.append(selectedTime);

                    }


                }, hour, minute, false);


        timePickerDialog.show();


    }

}
