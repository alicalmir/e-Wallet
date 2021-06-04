package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import org.w3c.dom.Text;

import static android.nfc.NfcAdapter.EXTRA_ID;


public class HomeActivity extends AppCompatActivity {
    private EditText name, place, date, amount;
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        name=findViewById(R.id.name_of_change_dash);
        place=findViewById(R.id.place_of_change_dash);
        date=findViewById(R.id.date_of_change_dash);
        amount=findViewById(R.id.amount_of_change_dash);

        /*Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras==null){
            Intent intent1 = new Intent(this, LoginActivity.class);
            if(intent1.resolveActivity(getPackageManager()) == null)
                Log.i("error", "No login activity");
            else startActivity(intent1);
        }else{
            int id = extras.getInt(EXTRA_ID);
        }*/

        // variable
        bottomNavigation = findViewById(R.id.bottom_navigation);

        //add menu item
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_dashboard_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_history_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_account_box_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment=null;
                switch (item.getId()) {
                    case 1:
                        fragment=new DashboardFragment(); break;
                    case 2:
                        fragment=new HistoryFragment(); break;
                    case 3:
                        fragment=new UserFragment(); break;
                } loadfragment(fragment);

            }
        });
        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(1,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "You clicked" , Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "You reselected" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addChange(View view) {
        Fragment fragment = new DashboardFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    private void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }
}