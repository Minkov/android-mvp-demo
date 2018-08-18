package com.minkov.mvpdemo.views.SuperheroesList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.minkov.mvpdemo.R;
import com.minkov.mvpdemo.SuperheroesApplication;
import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.repositories.base.Repository;

public class SuperheroesListActivity extends AppCompatActivity {

    private SuperheroesListView mSuperheroesView;
    private SuperheroesListContracts.Presenter mSuperheroesPresenter;
    private Repository<Superhero> mSuperheroesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superheroes_list);

        mSuperheroesRepository = ((SuperheroesApplication) getApplication()).getSuperheroesRepository();
        mSuperheroesPresenter = new SuperheroesListPresenter(mSuperheroesRepository);
        mSuperheroesView = SuperheroesListView.newInstance();
        mSuperheroesView.setPresenter(mSuperheroesPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mSuperheroesView)
                .commit();
    }
}
