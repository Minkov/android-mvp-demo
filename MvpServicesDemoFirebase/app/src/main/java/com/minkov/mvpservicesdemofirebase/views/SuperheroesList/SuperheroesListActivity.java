package com.minkov.mvpservicesdemofirebase.views.SuperheroesList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.minkov.mvpservicesdemofirebase.R;
import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.repositories.FirebaseRepository;
import com.minkov.mvpservicesdemofirebase.repositories.base.Repository;
import com.minkov.mvpservicesdemofirebase.services.SuperheroesServiceImpl;
import com.minkov.mvpservicesdemofirebase.services.base.SuperheroesService;
import com.minkov.mvpservicesdemofirebase.uiutils.NavigationUtils;
import com.minkov.mvpservicesdemofirebase.views.SuperheroCreate.SuperheroCreateActivity;
import com.minkov.mvpservicesdemofirebase.views.base.DrawerActivity;

import java.util.Arrays;
import java.util.List;

public class SuperheroesListActivity extends DrawerActivity {
    public static final String TITLE_TEXT = "Superheroes";
    public static final long IDENTIFIER = 403;

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

    @Override
    protected boolean handleOnItemClick(View view, int position, IDrawerItem drawerItem) {
        if (drawerItem.getIdentifier() == getIdentifier()) {
            return false;
        } else if (drawerItem.getIdentifier() == SuperheroCreateActivity.IDENTIFIER) {

            Intent intent = new Intent(this, SuperheroCreateActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    private long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    protected Toolbar getToolbar() {
        return findViewById(R.id.drawer_toolbar);
    }

    @Override
    protected List<IDrawerItem> getDrawerItems() {
        return NavigationUtils.getMainDrawerNavigationItems();
    }
}
