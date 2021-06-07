package com.example.ewallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity  {
    public static int EXTRA_ID;
    private int id;
    private EditText name, place, amount, date;
    private RadioGroup payment;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name_change_main);
        place = findViewById(R.id.place_changes_main);
        amount=findViewById(R.id.amount_change_main);
        date=findViewById(R.id.date_change_main);
        payment=findViewById(R.id.radioGroup);


        Intent intent =getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null){
            id = extras.getInt(String.valueOf(HomeActivity.EXTRA_ID));
        }else{
            Log.d("No ID", "NO IDDD!!!!");
        }
    }

    public void cancel(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(String.valueOf(EXTRA_ID), id);
        startActivity(intent);
    }

    public void submit(View view){
        int selectedId = payment.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        Users user=UsersDatabase.getInstance(this).usersDao().get(id);
        double amountInWallet = user.getMoney();

        if(radioButton.getText().toString().equals("Yes")){
            amountInWallet+=Double.parseDouble(amount.getText().toString());
        }else{
            amountInWallet-=Double.parseDouble(amount.getText().toString());
        }

        user.setMoney(amountInWallet);
        Changes changes = new Changes(name.getText().toString(), place.getText().toString(), place.getText().toString(), Double.parseDouble(amount.getText().toString()), date.getText().toString(), id);
        UsersDatabase.getInstance(this).usersDao().update(user);
        UsersDatabase.getInstance(this).changesDao().add(changes);

        Toast.makeText(this, "Change has been made!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(String.valueOf(EXTRA_ID), id);
        startActivity(intent);
    }
    public void locationBtnClicked(View view) {

        Intent intent = new Intent(this,ShopMapActivity.class);
        startActivity(intent);
    }




}