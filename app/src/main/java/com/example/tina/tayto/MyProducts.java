package com.example.tina.tayto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// In this case, the fragment displays simple text based on the page
public class MyProducts extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    List<Person> persons;

    public static MyProducts newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MyProducts fragment = new MyProducts();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_products, container, false);


        initializeData();
        setUpCardView(view);

        return view;
    }

    void setUpCardView(View view) {
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);

        rv.setLayoutManager(layoutManager);

        //if else mpage statement here
        RVAdapter adapter = new RVAdapter(persons);

        rv.setAdapter(adapter);
    }

    //async task maannn
    void initializeData() {
        persons = new ArrayList<Person>();
        persons.add(new Person("Hi", "hi"));
        persons.add(new Person("Hi", "hi"));
        persons.add(new Person("Hi", "hi"));
    }

}
