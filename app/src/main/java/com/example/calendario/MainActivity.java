package com.example.calendario;

import android.animation.Animator;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.threeten.bp.Clock;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.temporal.TemporalAdjusters;

import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView fechaT,fechaselected,fin;
    private ImageButton selectend;
    String valor,aux;
    private Spinner importanceSpinner;
    private EditText tarea, description;
    private Button guardar,saveenddate;


    private AppCompatButton task,fecha;

    private ShapeableImageView ImgUser;



    private ListenerRegistration registrationUserDatos;


    private final EventListener<DocumentSnapshot> userDatosListeners = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
            try {
                if (documentSnapshot != null) {
                    Db_login usuario = documentSnapshot.toObject(Db_login.class);
                    if (usuario != null) {
                        String imgUrl = usuario.getImgUrl();



                        Glide.with(MainActivity.this).load(imgUrl).placeholder(R.drawable.user_img_default)
                                .error(R.drawable.user_img_default).into(ImgUser);


                    }
                }
            } catch (NullPointerException | IllegalStateException e) {
                e.getCause();
            }
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FIrebaseAll.getUserDatos("googleU",registrationUserDatos,userDatosListeners);

        valor = "Nueva tarea comienza: Hoy";
        String dateformat= "EE, dd MMMM y ";


        task = findViewById(R.id.newTask);
        fecha = findViewById(R.id.changemode);
        ImgUser = findViewById(R.id.ImgUser);

        ImgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Prueba.class));
            }
        });
        MaterialCalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader);
        calendarView.setDateTextAppearance(R.style.CalendarWidgetHeader);
        calendarView.setWeekDayTextAppearance(R.style.CalendarWidgetHeader);

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChangeMode.mode(calendarView);
        }

        });





        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();


        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat, Locale.getDefault());
        String formattedDate = dateFormat.format(currentDate);
        aux = formattedDate;



        ObtenerDatos(valor,formattedDate);




        LocalDate localDate = LocalDate.now();
        CalendarDay calendarDay = CalendarDay.from(localDate);


        calendarView.addDecorator(new CurrentDayDecorator(calendarDay));
        calendarView.setSelectedDate(calendarDay);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {


                if (date.getDate().equals(localDate)) {

                    valor = "Nueva tarea comienza: Hoy";
                    aux = date.getDate().format(DateTimeFormatter.ofPattern(dateformat));
                    ObtenerDatos(valor,aux);

                } else if (date.getDate().equals(localDate.plusDays(1))) {

                    valor = "Nueva tarea comienza: Mañana";
                    aux = date.getDate().format(DateTimeFormatter.ofPattern(dateformat));
                    ObtenerDatos(valor,aux);

                } else if (date.getDate().equals(localDate.plusDays(3))) {

                    valor = "Nueva tarea comienza: Dentro de 3 días";
                    aux = date.getDate().format(DateTimeFormatter.ofPattern(dateformat));
                    ObtenerDatos(valor,aux);

                } else if (date.getDate().isAfter(LocalDate.of(localDate.getYear(), 12, 31))) {


                    valor = "Nueva tarea";
                    aux = date.getDate().format(DateTimeFormatter.ofPattern(dateformat));
                    ObtenerDatos(valor,aux);

                } else {



                    valor = "Nueva tarea";
                    aux = date.getDate().format(DateTimeFormatter.ofPattern(dateformat));
                    ObtenerDatos(valor,aux);


                }




            }
        });







    }


    public void ObtenerDatos(String valor, String aux){
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LocalTime currentTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
                String formattedTime = currentTime.format(formatter);

                BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
                View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.newtask, null);

                importanceSpinner = contentView.findViewById(R.id.importanceSpinner);
                tarea = contentView.findViewById(R.id.descriptionEditText);
                description = contentView.findViewById(R.id.description);
                guardar = contentView.findViewById(R.id.saveButton);
                fechaT = contentView.findViewById(R.id.fecha);
                fin = contentView.findViewById(R.id.fechaendselected);
                selectend = contentView.findViewById(R.id.selectend);


                fin.setText(aux+" "+formattedTime);


                List<String> importanceOptions = new ArrayList<>();
                importanceOptions.add("Baja");
                importanceOptions.add("Media");
                importanceOptions.add("Alta");



                ArrayAdapter<String> importanceAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, importanceOptions);
                importanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                importanceSpinner.setAdapter(importanceAdapter);

                fechaT.setText(valor);

                selectend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BottomSheetDialog dialog2 = new BottomSheetDialog(MainActivity.this);
                        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.calendarselect, null);
                        LocalDate localDate = LocalDate.now();
                        CalendarDay calendarDay = CalendarDay.from(localDate);
                        saveenddate = contentView.findViewById(R.id.saveenddate);


                        String dateformat= "EE, dd MMMM y ";

                        MaterialCalendarView calendarView = contentView.findViewById(R.id.calendarView);

                        calendarView.setHeaderTextAppearance(R.style.CalendarWidgetHeader);
                        calendarView.setDateTextAppearance(R.style.CalendarWidgetHeader);
                        calendarView.setWeekDayTextAppearance(R.style.CalendarWidgetHeader);

                        calendarView.addDecorator(new CurrentDayDecorator(calendarDay));
                        calendarView.setSelectedDate(calendarDay);

                        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                            @Override
                            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                                String aux;


                                if (date.getDate().isBefore(localDate)){
                                    Toast.makeText(MainActivity.this, "Crear una tarea para ayer no es lógico", Toast.LENGTH_SHORT).show();
                                    aux = "";
                                    fin.setText(aux);
                                }else {


                                    aux = date.getDate().format(DateTimeFormatter.ofPattern(dateformat));
                                    Toast.makeText(MainActivity.this, aux, Toast.LENGTH_SHORT).show();
                                    fin.setText(aux);


                                    saveenddate.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog2.dismiss();

                                            TimePicker timePicker = new TimePicker();

                                            timePicker.showTimePickerDialog(MainActivity.this,fin);


                                        }
                                    });
                                }
                            }
                        });

                        saveenddate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog2.dismiss();
                            }
                        });

                        dialog2.setContentView(contentView);
                        dialog2.show();
                    }
                });

                dialog.setContentView(contentView);
                dialog.show();


                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String item1 = tarea.getText().toString();
                        String item2 = description.getText().toString();
                        String item3 = importanceSpinner.getSelectedItem().toString();
                        String item5 = fin.getText().toString();

                        Toast.makeText(MainActivity.this, "" + item1 + "\n" +
                                item2 + "\n" + item3 + "\n" + item5, Toast.LENGTH_SHORT).show();


                        Log.e("ZZZZZ",item1+"\n" +
                                item2+"\n" +
                                item3+"\n" +
                                item5+"\n");

                        dialog.dismiss();
                    }
                });
            }
        });
    }


    private class CurrentDayDecorator implements DayViewDecorator {
        private CalendarDay currentDay;

        public CurrentDayDecorator(CalendarDay currentDay) {
            this.currentDay = currentDay;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return day.equals(currentDay);
        }


        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(10, getResources().getColor(R.color.pink)));



        }
    }




}