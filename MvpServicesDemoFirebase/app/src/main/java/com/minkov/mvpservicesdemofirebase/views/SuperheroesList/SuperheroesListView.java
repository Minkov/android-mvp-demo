package com.minkov.mvpservicesdemofirebase.views.SuperheroesList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.minkov.mvpservicesdemofirebase.R;
import com.minkov.mvpservicesdemofirebase.models.Superhero;

import java.util.List;

public class SuperheroesListView extends Fragment implements
        SuperheroesListContracts.View, AdapterView.OnItemClickListener {

    private SuperheroesListContracts.Presenter mPresenter;
    private ListView mSuperheroesListView;
    private SuperheroesListViewAdapter mSuperheroesAdapter;
    private ProgressBar mLoadingView;
    private TextView mSuperheroesEmptyView;

    public SuperheroesListView() {
        // Required empty public constructor
    }

    public static SuperheroesListView newInstance() {
        return new SuperheroesListView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_superheroes_list_view, container, false);

        mSuperheroesAdapter = new SuperheroesListViewAdapter(getContext());

        mSuperheroesListView = view.findViewById(R.id.lv_superheroes);
        mSuperheroesListView.setAdapter(mSuperheroesAdapter);
        mSuperheroesListView.setOnItemClickListener(this);

        mLoadingView = view.findViewById(R.id.pb_loading);
        mSuperheroesEmptyView = view.findViewById(R.id.tv_nosuperheroes);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter == null) {
            return;
        }

        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void showSuperheroes(List<Superhero> superheroes) {
        mSuperheroesAdapter.clear();
        mSuperheroesAdapter.addAll(superheroes);
        mSuperheroesAdapter.notifyDataSetChanged();
        mSuperheroesListView.setVisibility(View.VISIBLE);
        mSuperheroesEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showEmptySuperheroes() {
        mSuperheroesListView.setVisibility(View.GONE);
        mSuperheroesEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(SuperheroesListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Superhero superhero = mSuperheroesAdapter.getItem(position);
    }
}
