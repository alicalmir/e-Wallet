package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    //private TextView login;
    private EditText nameSurname, dob, email, password;
    private Button register;
    public static int EXTRA_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //deceleration of components
        //login=findViewById(R.id.login_register_id);
        nameSurname=findViewById(R.id.nameSurname_register_id);
        dob=findViewById(R.id.dob_register_id);
        email=findViewById(R.id.email_register_id);
        password=findViewById(R.id.password_register_id);

        /*SpannableString content = new SpannableString("LOGIN!");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        login.setText(content); */
    }

    /*public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        if(intent.resolveActivity(getPackageManager()) == null)
            Log.i("error", "No main activity");
        else startActivity(intent);
    } */

    public void register(View view){
        Users user = new Users(nameSurname.toString(), email.getText().toString(), password.getText().toString(), dob.getText().toString());
        Users check_user=UsersDatabase.getInstance(this).usersDao().getEmail(email.getText().toString());
        if (check_user == null) {
        UsersDatabase.getInstance(this).usersDao().add(user);
        int id = UsersDatabase.getInstance(this).usersDao().getId(email.getText().toString(), password.getText().toString());

        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(String.valueOf(EXTRA_ID), id);
        if(intent.resolveActivity(getPackageManager()) == null)
            Log.i("error", "No home activity");
        else startActivity(intent); Log.i("register","successfully registered");
    }
        else {
            Toast.makeText(this, "E-mail already exists", Toast.LENGTH_SHORT).show();
        }


    }

}