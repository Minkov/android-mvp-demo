package com.minkov.mvpservicesdemofirebase.views.SuperheroCreate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.minkov.mvpservicesdemofirebase.R;
import com.minkov.mvpservicesdemofirebase.models.Superhero;
import com.minkov.mvpservicesdemofirebase.uiutils.NavigationUtils;
import com.minkov.mvpservicesdemofirebase.views.base.DrawerActivity;

import java.util.List;

public class SuperheroCreateActivity extends DrawerActivity {

    public static final String TITLE_TEXT = "Add";
    public static final long IDENTIFIER = 719;
    private SuperheroCreateView mSuperheroCreateView;
    private SuperheroCreatePresenter mSuperheroCreatePresener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_create);

        mSuperheroCreateView = SuperheroCreateView.instance();
        mSuperheroCreatePresener = new SuperheroCreatePresenter();
    }

    @Override
    protected boolean handleOnItemClick(View view, int position, IDrawerItem iDrawerItem) {
        return false;
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
