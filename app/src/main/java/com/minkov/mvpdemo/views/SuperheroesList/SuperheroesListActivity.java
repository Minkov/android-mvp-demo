package com.minkov.mvpdemo.views.SuperheroesList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.minkov.mvpdemo.R;
import com.minkov.mvpdemo.SuperheroesApplication;
import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.repositories.base.Repository;
import com.minkov.mvpdemo.uiutils.DetailsNavigator;

public class SuperheroesListActivity extends AppCompatActivity implements DetailsNavigator<Superhero> {

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
        mSuperheroesView.setNavigator(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mSuperheroesView)
                .commit();
    }

    @Override
    public void navigateWith(Superhero superhero) {
        Toast.makeText(this, superhero.getName(), Toast.LENGTH_SHORT)
                .show();
    }
}
