package com.minkov.mvpdemo.views.SuperheroesList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.minkov.mvpdemo.R;
import com.minkov.mvpdemo.models.Superhero;

import java.util.List;

public class SuperheroesListView extends Fragment implements
        SuperheroesListContracts.View, AdapterView.OnItemClickListener, TextWatcher {

    private SuperheroesListContracts.Presenter mPresenter;
    private ListView mSuperheroesListView;
    private SuperheroesListViewAdapter mSuperheroesAdapter;
    private EditText mFilterEditText;

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

        mSuperheroesListView = view.findViewById(R.id.lv_superheroes);
        mSuperheroesAdapter = new SuperheroesListViewAdapter(getContext());
        mSuperheroesListView.setAdapter(mSuperheroesAdapter);
        mSuperheroesListView.setOnItemClickListener(this);

        mFilterEditText = view.findViewById(R.id.et_filter);
        mFilterEditText.addTextChangedListener(this);

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
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuperhero(Superhero superhero) {
        Toast.makeText(getContext(), "HIT!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptySuperheroes() {

    }

    @Override
    public void setPresenter(SuperheroesListContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Superhero superhero = mSuperheroesAdapter.getItem(position);
        mPresenter.onSuperheroSelected(superhero);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String pattern = mFilterEditText.getText().toString();
        mPresenter.applyFilter(pattern);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
