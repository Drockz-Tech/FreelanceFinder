package com.example.freelancefinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class intro extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button btnlogin;
    private Button btnRegistration;
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    Timer time;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(intro.this, login.class);
                startActivity(intent);
                finish();

            }
        }, 2000);



    }

    private void LoginFunction() {

        email = findViewById(R.id.loginkamail);
        password = findViewById(R.id.loginkapassword);
        btnlogin = findViewById(R.id.loginkaloginbutton);
        btnRegistration = findViewById(R.id.register_btn_l);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field...");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    password.setError("Required Field...");
                }
                mDialog.setMessage("Processing....");
                mDialog.show();
                mAuth.signInWithEmailAndPassword(mEmail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            mDialog.dismiss();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Login Failed....", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), registration_activity.class));

            }
        });


    }

}
