package com.minkov.mvpservicesdemofirebase.views.SuperheroesList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.minkov.mvpservicesdemofirebase.R;
import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.services.FirebaseSuperheroesService;

public class SuperheroesListActivity extends AppCompatActivity {

    private SuperheroesListView mSuperheroesView;
    private SuperheroesListContracts.Presenter mSuperheroesPresenter;
    private FirebaseSuperheroesService mSuperheroesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superheroes_list);

        mSuperheroesService = new FirebaseSuperheroesService();
        mSuperheroesPresenter = new SuperheroesListPresenter(mSuperheroesService);
        mSuperheroesView = SuperheroesListView.newInstance();
        mSuperheroesView.setPresenter(mSuperheroesPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, mSuperheroesView)
                .commit();
    }
}
