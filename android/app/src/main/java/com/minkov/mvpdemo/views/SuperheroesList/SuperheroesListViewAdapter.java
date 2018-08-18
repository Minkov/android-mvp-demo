package com.minkov.mvpdemo.views.SuperheroesList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.minkov.mvpdemo.models.Superhero;

import java.util.ArrayList;

class SuperheroesListViewAdapter extends ArrayAdapter<Superhero> {

    public SuperheroesListViewAdapter(@NonNull Context context) {
        super(context, -1);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView view = convertView == null
            ? new TextView(getContext())
            : (TextView) convertView;
        view.setText(getItem(position).getName());

        return view;
    }
}
