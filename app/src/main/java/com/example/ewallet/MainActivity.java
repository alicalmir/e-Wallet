package com.example.ewallet;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;


import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  {
    public static int EXTRA_ID;
    private int id;
    private EditText name, place, amount, date;
    private RadioGroup payment;
    private RadioButton radioButton;
    public static String LOC_MESSAGE;
    String search_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name_change_main);
        place = findViewById(R.id.place_changes_main);
        amount=findViewById(R.id.amount_change_main);
        date=findViewById(R.id.date_change_main);
        payment=findViewById(R.id.radioGroup);

        search_location=place.getText().toString();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void locationBtnClicked(View view) {

        if(isPermissionGranted()) {
            String search_location=place.getText().toString();
            Intent intent = new Intent(this,ShopMapActivity.class);
            intent.putExtra(LOC_MESSAGE,search_location);
            startActivity(intent);
        } else
            requestStoragePermission();
    }

    public boolean isPermissionGranted(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission(){
        if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)){
            Toast.makeText(this, "Please allow the device to open GoogleMaps!", Toast.LENGTH_SHORT).show();
        }else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 25);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults){
        if(requestCode == 25){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                search_location=place.getText().toString();
                Intent intent = new Intent(this,ShopMapActivity.class);
                intent.putExtra(LOC_MESSAGE,search_location);
                startActivity(intent);
            }
            else Toast.makeText(this, "Please allow the device to open GoogleMaps!", Toast.LENGTH_SHORT).show();
        }
    }


}