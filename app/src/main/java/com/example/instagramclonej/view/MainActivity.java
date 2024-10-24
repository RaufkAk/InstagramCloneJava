package com.example.instagramclonej.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.instagramclonej.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();


            FirebaseUser currentUser = auth.getCurrentUser();
            if(currentUser != null){
                Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                startActivity(intent);
                finish();
            }


    }
public void signInClicked (View view){
        String eMail = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();

        if(eMail.equals("")||password.equals("")){
            Toast.makeText(this,"Enter Email and Password", Toast.LENGTH_LONG).show();
        }else{
            auth.signInWithEmailAndPassword(eMail,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }



}
public void signUpClicked (View view){
        String eMail = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();
        if(eMail.equals("") || password.equals("")){
            Toast.makeText(this,"Enter Email and Password",Toast.LENGTH_LONG).show();
        }else{
            auth.createUserWithEmailAndPassword(eMail,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,FeedActivity.class);
                    startActivity(intent);
                    finish();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        }



}




}