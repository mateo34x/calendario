package com.example.calendario;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
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

public class LoginActivity extends AppCompatActivity {





    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient signInClient;
    private final int REQUEST_CODE = 100;

    ImageButton loginGoogle;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CircularProgressButton btn = findViewById(R.id.btn_id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.startAnimation();
            }
        });



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user!=null) {
            startNewAct(MainActivity.class);
        }else{

        }

        firebaseAuth = FirebaseAuth.getInstance();

        CreateRequest();

        loginGoogle = findViewById(R.id.loginGoogle);

        loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = signInClient.getSignInIntent();
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (resultCode == Activity.RESULT_OK) {
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Google.firebaseAuthWithGoogle(firebaseAuth,account,LoginActivity.this);

                } catch (ApiException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "" + task.getResult(), Toast.LENGTH_SHORT).show();
                    throw new RuntimeException(e);
                }
            } else {
                Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show();
            }


        }

    }



    private void CreateRequest() {

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(this, signInOptions);

    }


    private void startNewAct(Class clase) {
        startActivity(new Intent(LoginActivity.this, clase));
        finish();
    }
}