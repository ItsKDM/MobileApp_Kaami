package com.example.kdm.is_kaami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText textInputLayout_email;
    private EditText textInputLayout_password;
    private FirebaseAuth fAuth;

    Button btn_signin;
    ImageView imageView;
    TextView textView, forget_pass;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        textInputLayout_email = findViewById(R.id.textInputLayout_email);
        textInputLayout_password = findViewById(R.id.textInputLayout_password);

        btn_signin = findViewById(R.id.btn_signin);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView1);
        forget_pass = findViewById(R.id.forget_pass);

        fAuth = FirebaseAuth.getInstance();

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputLayout_email.getText().toString().trim();
                String password = textInputLayout_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    textInputLayout_email.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    textInputLayout_password.setError("Password is Required");
                    return;
                }

                if (password.length() < 6){
                    textInputLayout_password.setError("Password must be >= 6 characters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()){
            public void onSwipeTop(){
            }

            public void onSwipeRight(){
                if(count == 0){
                    imageView.setImageResource(R.drawable.night_scene);
                    count = 1;
                } else{
                    imageView.setImageResource(R.drawable.morning_scene);
                    count = 0;
                }
            }

            public void onSwipeLeft(){
                if (count == 0){
                    imageView.setImageResource(R.drawable.night_scene);
                    count = 1;
                } else {
                    imageView.setImageResource(R.drawable.morning_scene);
                    count = 0;
                }
            }

            public void onSwipeBottom(){
            }
        });

        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ForgetPassword.class);
                startActivity(i);
            }
        });

    }
}
