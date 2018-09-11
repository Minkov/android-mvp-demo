package com.minkov.androidapp.diconfig;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.views.SuperheroesList.SuperheroesAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {
    @Provides
    public RecyclerView.Adapter<SuperheroesAdapter.SuperheroViewHolder> superheroArrayAdapter(Context context) {
        return new SuperheroesAdapter();
    }
}
