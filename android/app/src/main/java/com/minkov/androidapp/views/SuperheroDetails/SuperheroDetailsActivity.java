package com.minkov.androidapp.views.SuperheroDetails;

import android.content.Intent;
import android.os.Bundle;

import com.minkov.androidapp.R;
import com.minkov.androidapp.models.Superhero;
import com.minkov.androidapp.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class SuperheroDetailsActivity extends BaseDrawerActivity {
    public static final String EXTRA_KEY = "SUPERHERO_EXTRA_KEY";

    @Inject
    SuperheroDetailsFragment mSuperheroDetailsFragment;

    @Inject
    SuperheroDetailsContracts.Presenter mSuperheroDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Superhero superhero = (Superhero) intent.getSerializableExtra(SuperheroDetailsActivity.EXTRA_KEY);

        mSuperheroDetailsPresenter.setSuperheroId(superhero.getId());
        mSuperheroDetailsFragment.setPresenter(mSuperheroDetailsPresenter);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mSuperheroDetailsFragment)
                .commit();
    }

    @Override
    protected long getIdentifier() {
        return 0;
    }
}
