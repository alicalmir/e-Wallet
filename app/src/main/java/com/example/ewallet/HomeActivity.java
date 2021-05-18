package com.example.ewallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;


public class HomeActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

    private void loadfragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }
}