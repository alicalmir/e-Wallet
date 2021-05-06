package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView register, email, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deceleration of components
        register = findViewById(R.id.registerClick);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginClick);

        //setting underline for the register textView
        SpannableString content = new SpannableString("REGISTER!");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        register.setText(content);
    }

    public void login(View view){
        Intent intent = new Intent(this, HomeActivity.class);

            if(email.getText().toString().equals("naidafatic@gmail.com") && password.getText().toString().equals("123")){
                if(intent.resolveActivity(getPackageManager()) == null)
                    Log.i("error", "No home activity");
                else startActivity(intent);
            }else {
                Toast.makeText(this, "Enter correct email or password", Toast.LENGTH_SHORT).show();
            }
    }


    public void register(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        if(intent.resolveActivity(getPackageManager()) == null)
            Log.i("error", "No home activity");
        else startActivity(intent);
    }
}