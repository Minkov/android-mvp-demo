package com.minkov.androidapp.views.SuperheroCreate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.minkov.androidapp.R;
import com.minkov.androidapp.views.BaseDrawerActivity;
import com.minkov.androidapp.views.SuperheroesList.SuperheroesListActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SuperheroCreateActivity extends BaseDrawerActivity implements SuperheroCreateContracts.Navigator {
    public static final long IDENTIFIER = 298;

    @Inject
    SuperheroCreateFragment mView;

    @Inject
    SuperheroCreateContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_create);
        ButterKnife.bind(this);

        mView.setPresenter(mPresenter);
        mView.setNavigator(this);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mView)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(this, SuperheroesListActivity.class);
        startActivity(intent);
        finish();
    }
}
