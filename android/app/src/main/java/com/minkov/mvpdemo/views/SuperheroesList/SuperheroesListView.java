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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.minkov.mvpdemo.R;
import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.uiutils.DetailsNavigator;
import com.minkov.mvpdemo.views.extensions.TextWatcherExtensions;

import org.w3c.dom.Text;

import java.util.List;

public class SuperheroesListView extends Fragment implements
    SuperheroesListContracts.View, AdapterView.OnItemClickListener, TextWatcherExtensions {

    private SuperheroesListContracts.Presenter mPresenter;
    private ListView mSuperheroesListView;
    private SuperheroesListViewAdapter mSuperheroesAdapter;
    private EditText mFilterEditText;
    private ProgressBar mLoadingView;
    private DetailsNavigator<Superhero> mDetailsNavigator;
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

        mSuperheroesListView = view.findViewById(R.id.lv_superheroes);
        mSuperheroesAdapter = new SuperheroesListViewAdapter(getContext());
        mSuperheroesListView.setAdapter(mSuperheroesAdapter);
        mSuperheroesListView.setOnItemClickListener(this);


        mFilterEditText = view.findViewById(R.id.et_filter);
        mFilterEditText.addTextChangedListener(this);

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
    public void showDetails(Superhero superhero) {
        mDetailsNavigator.navigateWith(superhero);
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
        mPresenter.onSuperheroSelected(superhero);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String pattern = mFilterEditText.getText().toString();
        mPresenter.applyFilter(pattern);
    }

    public void setNavigator(DetailsNavigator<Superhero> detailsNavigator) {
        mDetailsNavigator = detailsNavigator;
    }
}
