package com.minkov.androidapp.views.SuperheroCreate;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.minkov.androidapp.R;
import com.minkov.androidapp.models.Superhero;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuperheroCreateFragment extends Fragment implements SuperheroCreateContracts.View {
    private SuperheroCreateContracts.Presenter mPresenter;

    @BindView(R.id.et_name)
    EditText mNameEditText;

    @BindView(R.id.et_secret_identity)
    EditText mSecretIdentity;
    private SuperheroCreateContracts.Navigator mNavigator;

    @Inject
    public SuperheroCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_superhero_create, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @OnClick(R.id.btn_save)
    public void onSuperheroSaveClicked() {
        String name = mNameEditText.getText().toString();
        String secretIdentity = mSecretIdentity.getText().toString();
        String imageUrl = "http://idrawproart.com/CapOneSuperhero.jpg";
        Superhero superhero = new Superhero(name, secretIdentity, imageUrl);
        mPresenter.save(superhero);
    }

    @Override
    public void setPresenter(SuperheroCreateContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void navigateToHome() {
        mNavigator.navigateToHome();
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    public void setNavigator(SuperheroCreateContracts.Navigator navigator) {
        mNavigator = navigator;
    }
}
