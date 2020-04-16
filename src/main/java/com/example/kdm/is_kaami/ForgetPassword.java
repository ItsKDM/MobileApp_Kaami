package com.example.kdm.is_kaami;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ForgetPassword extends AppCompatActivity {

    private TextInputLayout textInputLayout_email;

    Button btn_confirm;
    TextView textView_confirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        textInputLayout_email = findViewById(R.id.textInputLayout_email);

        btn_confirm = findViewById(R.id.btn_confirm);
        textView_confirmation = findViewById(R.id.textView_confirmation);

    }

    private boolean validateEmail(){
        String emailInput = textInputLayout_email.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()){
            textInputLayout_email.setError("Field can't be Empty");
            return false;
        } else{
            textInputLayout_email.setError(null);
            return true;
        }
    }

    public void confirmInput(View v){
        if (!validateEmail()){
            return;
        }

        String input = "Email: " + textInputLayout_email.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

        textView_confirmation.setVisibility(View.VISIBLE);
    }
}
