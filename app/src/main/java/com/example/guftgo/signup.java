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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {
 EditText name_txt;
 EditText email_txt;
 EditText password_txt;
 Button create_btn;
 Button longin_btn;
 FirebaseFirestore database;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name_txt=findViewById(R.id.name);
        email_txt=findViewById(R.id.email_signup);
        password_txt=findViewById(R.id.password_signup);
        create_btn=findViewById(R.id.create_btn_signup);
        longin_btn=findViewById(R.id.login_btn_signup);
        database= FirebaseFirestore.getInstance();
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        longin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this,Login.class));
            }
        });
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email=email_txt.getText().toString();
               String pass=password_txt.getText().toString();
               String name=name_txt.getText().toString();
               Users user=new Users();
               user.setEmail(email);
               user.setName(name);
               user.setPass(pass);
               mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           //success
                           database.collection("users").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                  startActivity(new Intent(signup.this,Login.class));
                               }
                           });
                           Toast.makeText(getApplicationContext(),"signup successful",Toast.LENGTH_SHORT).show();
                       }else{
                           //not success
                           Toast.makeText(getApplicationContext(),task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                       }
                   }
               });
            }
        });
    }
}