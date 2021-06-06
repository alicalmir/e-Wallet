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
    MeowBottomNavigation bottomNavigation;
    private int id;
    private String full_name, email, dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null) {
            id = extras.getInt(String.valueOf(LoginActivity.EXTRA_ID));
            if (id == 0) {
                id = extras.getInt(String.valueOf(RegisterActivity.EXTRA_ID));
            }
            Log.d("error", " " + id);
            Users user = UsersDatabase.getInstance(this).usersDao().get(id);
            full_name = user.getFullname();
            email = user.getEmail();
            dob = user.getDob();
        }else{
            Intent intent1 = new Intent(this, LoginActivity.class);
            startActivity(intent1);
        }

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
                        fragment=new DashboardFragment(id); break;
                    case 2:
                        fragment=new HistoryFragment(); break;
                    case 3:
                        fragment=new UserFragment(full_name, email, dob, id); break;
                } loadfragment(fragment);

            }
        });
        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(1,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(), "You clicked" , Toast.LENGTH_SHORT).show();
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }

    private void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }
}