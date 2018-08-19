package com.minkov.mvpdemo.views.SuperheroesList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.minkov.mvpdemo.R;
import com.minkov.mvpdemo.SuperheroesApplication;
import com.minkov.mvpdemo.http.OkHttpRequester;
import com.minkov.mvpdemo.http.base.HttpRequester;
import com.minkov.mvpdemo.models.Superhero;
import com.minkov.mvpdemo.repositories.HttpRxRepository;
import com.minkov.mvpdemo.repositories.base.RxRepository;
import com.minkov.mvpdemo.schedulers.AsyncSchedulersFactory;
import com.minkov.mvpdemo.uiutils.DetailsNavigator;
import com.minkov.mvpdemo.utils.GsonJsonParser;
import com.minkov.mvpdemo.utils.base.JsonParser;

public class SuperheroesListActivity extends AppCompatActivity implements DetailsNavigator<Superhero> {

    private SuperheroesListView mSuperheroesView;
    private SuperheroesListContracts.Presenter mSuperheroesPresenter;
    private RxRepository<Superhero> mSuperheroesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superheroes_list);

//        mSuperheroesRepository = ((SuperheroesApplication) getApplication()).getSuperheroesRepository();
        String serverUrl = SuperheroesApplication.geServerUrl();
        HttpRequester httpRequester = new OkHttpRequester();
        JsonParser<Superhero> superheroJsonParser = new GsonJsonParser<>(Superhero.class, Superhero[].class);
        mSuperheroesRepository = new HttpRxRepository<>(
                serverUrl + "/superheroes",
                httpRequester,
                superheroJsonParser
        );

        mSuperheroesPresenter = new SuperheroesListPresenter(mSuperheroesRepository, AsyncSchedulersFactory.instance());
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
