package com.example.ewallet;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView user_fullname, email, date, money;
    private String passed_fullname, passed_email, passed_birth;
    private int id;
    private Button logout;

    //public static final String CHANNEL_ID="My channel";
    //public static final int NOT_ID=1;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }
    public UserFragment(String fullname,String email,String birth ,int id) {
        passed_fullname=fullname;
        passed_email=email;
        passed_birth=birth;
        this.id=id;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel(CHANNEL_ID, "My custom channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager= (NotificationManager) getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel);
        }*/



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_user, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Users users = UsersDatabase.getInstance(getActivity()).usersDao().get(id);

        user_fullname = getView().findViewById(R.id.user_fullname);
        email=getView().findViewById(R.id.user_email);
        date=getView().findViewById(R.id.user_date);
        money=getView().findViewById(R.id.current_money_dash);
        logout=getView().findViewById(R.id.logout_user_id);

        user_fullname.setText("Full name: "+passed_fullname);
        email.setText("Email: "+passed_email);
        date.setText("Birth: "+passed_birth);
        money.setText(""+users.getMoney());
        if(users.getMoney()<0){



                /*new AlertDialog.Builder(UserFragment.this)
                        .setMessage("R.string.eat")
                        .setTitle("EGG TIMER")
                        .show();

                Intent intent= new Intent(UserFragment.this, Notification.class);
                PendingIntent pendingIntent= PendingIntent.getActivity(UserFragment.this, 0, intent, 0);


                NotificationCompat.Builder builder=new NotificationCompat.Builder(UserFragment.this,CHANNEL_ID );
                builder.setContentTitle("EGG_TIMER")
                        .setContentText("Let's eat")
                        //.setSmallIcon(R.drawable.ic_baseline_notifications_24)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                        //.setColor(Color.RED)
                        //.addAction(R.drawable.ic_baseline_snooze_24, "SNOOZE", pendingIntent);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(UserFragment.this);
                managerCompat.notify(NOT_ID, builder.build());*/






            money.setTextColor(Color.parseColor("#e85b51"));
        }else{
            money.setTextColor(Color.parseColor("#02c22f"));
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            }
        });

    }





}