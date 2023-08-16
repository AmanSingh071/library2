package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Models.users;
import com.example.myapplication.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    EditText email,pass,userName,confpass;
    ActivitySignupBinding binding;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;

    users Users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);
                signUpuser();
            }
        });
    }
    private void signUpuser() {
        email=findViewById(R.id.emailsignup);
        userName=findViewById(R.id.usernamesignup);
        pass=findViewById(R.id.passwordsignup);
        confpass=findViewById(R.id.confirmpasswordsignup);



        String userName1 = userName.getText().toString();
        String email1 = email.getText().toString();
        String pass1= pass.getText().toString();
        String   confpass1  = binding.confirmpasswordsignup.getText().toString();

        if(email1.isEmpty() ||pass1.isEmpty() ||userName1.isEmpty())
        {
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(signup.this, "Email and password cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        }


        if(pass1.length()<6 && confpass1.length()<6)
        {

            Toast.makeText(signup.this, "\"enter at leat 6 digit password", Toast.LENGTH_SHORT).show();
            binding.progressBar.setVisibility(View.GONE);
            return;
        }

        auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    storeuserdata(email1,pass1, userName1);
                    Toast.makeText(signup.this, "authentication successfull", Toast.LENGTH_SHORT).show();
                    binding.progressBar.setVisibility(View.GONE);
                    Intent intent=new Intent(signup.this,login.class);
                    Toast.makeText(signup.this, "please login", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
                else
                {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(signup.this, "authentication failed ,this user might already exists", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void storeuserdata(String email1, String pass1, String userName1) {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference("user").child(auth.getCurrentUser().getUid()).push();

        Users=new users(email1,pass1,userName1);

        databaseReference.setValue(Users);


    }
}