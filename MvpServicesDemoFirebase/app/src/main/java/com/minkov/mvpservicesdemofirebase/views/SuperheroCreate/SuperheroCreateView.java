package com.minkov.mvpservicesdemofirebase.views.SuperheroCreate;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minkov.mvpservicesdemofirebase.R;

public class SuperheroCreateView extends Fragment {


    public SuperheroCreateView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_superhero_create_view, container, false);

        return view;
    }

    public static SuperheroCreateView instance() {
        return new SuperheroCreateView();
    }
}
