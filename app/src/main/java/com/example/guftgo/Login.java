package com.example.guftgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
EditText login_txt;
EditText password_txt;
Button login_btn;
Button signup_btn;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_txt=findViewById(R.id.email_txt_login);
        password_txt=findViewById(R.id.password_txt_login);
        login_btn=findViewById(R.id.login_btn);
        signup_btn=findViewById(R.id.signup_btn);
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this,signup.class));
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=login_txt.getText().toString();
                String pass=password_txt.getText().toString();
                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           //successful
                           startActivity(new Intent(Login.this,DashboardActivity.class));
                           Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
                       }else{
                           //not successful
                           Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT)
                                   .show();
                       }
                    }
                });
            }
        });
    }
}