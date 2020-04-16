package com.example.kdm.is_kaami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    private EditText textInputLayout_name;
    private EditText textInputLayout_email;
    private EditText textInputLayout_password;

    Button btn_signup;
    TextView textView_already;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textInputLayout_name = findViewById(R.id.textInputLayout_name);
        textInputLayout_email = findViewById(R.id.textInputLayout_email);
        textInputLayout_password = findViewById(R.id.textInputLayout_password);

        btn_signup = findViewById(R.id.btn_signup);
        textView_already = findViewById(R.id.textView_already);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }

        textView_already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup.this, Login.class);
                startActivity(i);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = textInputLayout_name.getText().toString().trim();
                String email = textInputLayout_email.getText().toString().trim();
                String password = textInputLayout_password.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    textInputLayout_name.setError("Name is Required.");
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    textInputLayout_email.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    textInputLayout_password.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6){
                    textInputLayout_password.setError("Password must be >= 6 character");
                    return;
                }

                //register the user in Firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Signup.this, "User Created", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = fAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        } else{
                            Toast.makeText(Signup.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
