package com.example.calendario;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;


public class CreateNewTask {

    public static void SaveTask(String title, String description, String date, String important ,Dialog dialog, Context context){


        try {

            String uid = FbUser.getCurrentUserId();
            String TaskID = FirebaseFirestore.getInstance().collection("usertask").document().getId();

            Task task = new Task(title,description,date,important,uid,TaskID);
            FirebaseFirestore.getInstance().collection("Task").document(uid).collection("usertask").document(TaskID).set(task, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                    Toast.makeText(context, "Tarea guardada correctamente", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context, "Error al guardar la tarea "+e.getCause().toString(), Toast.LENGTH_SHORT).show();
                }
            });


        }catch (NullPointerException e){
            e.getCause();
            Log.e("TASKERROR",e.getCause().toString());
        }



    }
}
