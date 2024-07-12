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

public class registration_activity extends AppCompatActivity {
    private EditText name;
    private EditText email_reg;
    private EditText pass_reg;
    private Button btnReg;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        Registration();
    }
    private void Registration(){
        name = findViewById(R.id.name_sign);
        email_reg = findViewById(R.id.email_sign);
        pass_reg = findViewById(R.id.password_sign);
        btnReg = findViewById(R.id.signup_btn);
        btnLogin = findViewById(R.id.login_signup);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString().trim();
                String email = email_reg.getText().toString().trim();
                String pass = pass_reg.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    email_reg.setError("Required field...");
                    return;

                }
                if(TextUtils.isEmpty(pass)){
                    pass_reg.setError("Required Field...");
                    return;
                }
                if(TextUtils.isEmpty(Name)){
                    name.setError("Required Field...");
                }
                mDialog.setMessage("Processing....");
                mDialog.show();
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home_Activity.class));
                            mDialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Failed...", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), login.class));


            }
        });


    }
}
