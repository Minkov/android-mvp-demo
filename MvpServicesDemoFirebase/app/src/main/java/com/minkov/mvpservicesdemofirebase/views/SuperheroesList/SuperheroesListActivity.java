package com.minkov.mvpservicesdemofirebase.views.SuperheroesList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.minkov.mvpservicesdemofirebase.R;
import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.repositories.FirebaseRepository;
import com.minkov.mvpservicesdemofirebase.repositories.base.Repository;
import com.minkov.mvpservicesdemofirebase.services.SuperheroesServiceImpl;
import com.minkov.mvpservicesdemofirebase.services.base.SuperheroesService;

public class SuperheroesListActivity extends AppCompatActivity {

    private SuperheroesListView mSuperheroesView;
    private SuperheroesListContracts.Presenter mSuperheroesPresenter;
    private SuperheroesService mSuperheroesService;
    private Repository<Superhero> mSuperheroesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superheroes_list);

        mSuperheroesRepository = new FirebaseRepository<>(Superhero.class);
        mSuperheroesService = new SuperheroesServiceImpl(mSuperheroesRepository);

        mSuperheroesPresenter = new SuperheroesListPresenter(mSuperheroesService);
        mSuperheroesView = SuperheroesListView.newInstance();
        mSuperheroesView.setPresenter(mSuperheroesPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mSuperheroesView)
                .commit();
    }
}
