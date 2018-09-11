package com.minkov.mvpservicesdemofirebase.views.SuperheroesList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minkov.mvpservicesdemofirebase.R;
import com.minkov.mvpservicesdemofirebase.models.Superhero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuperheroesListAdapter extends RecyclerView.Adapter<SuperheroesListAdapter.ViewHolder> {
    private final Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mNameView;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            mNameView = mView.findViewById(R.id.tv_superhero_name);
            mImageView = mView.findViewById(R.id.iv_superhero);
        }
    }

    public SuperheroesListAdapter(Context context) {
        mContext = context;

   }

    public void clear() {
        mSuperheroes.clear();
    }

    public void addAll(List<Superhero> superheroes) {
        mSuperheroes.addAll(superheroes);
    }

    public Superhero getItem(int position) {
        return mSuperheroes.get(position);
    }

    @NonNull
    @Override
    public SuperheroesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.superhero_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = mSuperheroes.get(position).getName();
        holder.mNameView.setText(name);
    }

    @Override
    public int getItemCount() {
        return mSuperheroes.size();
    }
}
