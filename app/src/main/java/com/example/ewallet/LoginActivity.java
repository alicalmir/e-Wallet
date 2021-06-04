package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView register, email, password;
    Button login;
    public static int EXTRA_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //deceleration of components
        register = findViewById(R.id.register_login_id);
        email = findViewById(R.id.email_login_id);
        password = findViewById(R.id.password_login_id);
        login = findViewById(R.id.login_button_login);

        //setting underline for the register textView
        SpannableString content = new SpannableString("REGISTER!");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        register.setText(content);
    }

    public void login(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        Users user = UsersDatabase.getInstance(this).usersDao().getUser(email.getText().toString(), password.getText().toString());

        if(user != null){
            intent.putExtra(String.valueOf(EXTRA_ID), user.getId());
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