package com.example.ewallet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static int EXTRA_ID;
    private int id;



    public HistoryFragment() {
        // Required empty public constructor
    }
    public HistoryFragment(int id) {
       this.id=id;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        List<Changes> ch=UsersDatabase.getInstance(getActivity()).changesDao().getChanges(id);
        String [] menuItems = new String[ch.size()];
        int i=0;
        for (Changes c: ch) {
            menuItems[i] = "\nChange done on: "+"\n"+
                    "\nName: "+c.getName()+"\n"+
                    "\nPlace: "+c.getLatitude()+" "+c.getLatitude()+"\n"+
                    "\nAmount: "+c.getAmount()+" KM";
            i++;
        }
        ListView listView = (ListView) view.findViewById(R.id.list_view_container);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(

                getActivity(), android.R.layout.simple_list_item_1, menuItems
        );

        listView.setAdapter(adapter);
        return view;
    }

    public void addChanges(View view){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(String.valueOf(EXTRA_ID), id);
        startActivity(intent);
    }


}