package com.example.calendario;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

public class Google {


    public static void firebaseAuthWithGoogle(FirebaseAuth firebaseAuth, GoogleSignInAccount account,Activity activity){

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = firebaseUser.getUid();

                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                    CollectionReference email = firestore.collection("googleU");
                    Query query = email.whereEqualTo("email", account.getEmail());

                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                QuerySnapshot snapshot = task.getResult();
                                if (!snapshot.isEmpty()) {
                                    Toast.makeText(activity, "Iniciando sesión con: " + account.getEmail(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(activity, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    activity.startActivity(intent);
                                } else {
                                    Log.e("zzzzB", "Tarea Completa:" + account.getEmail() + "\n" + account.getId() + "\n" + account.getDisplayName());


                                    Db_login db_login = new Db_login(uid, account.getEmail(), account.getDisplayName(), "google",account.getPhotoUrl().toString());
                                    FirebaseFirestore.getInstance().collection("googleU")
                                            .document(uid)
                                            .set(db_login, SetOptions.merge())
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {


                                                    if (task.isSuccessful()) {

                                                        Toast.makeText(activity, "Se ha registrado el usuario con google", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(activity, MainActivity.class);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        activity.startActivity(intent);

                                                    } else {
                                                        Toast.makeText(activity, "Algo ha salido mal a la hora de intentar guardar tus datos", Toast.LENGTH_LONG).show();

                                                    }

                                                }
                                            });
                                }
                            }
                        }
                    });


                }


            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
