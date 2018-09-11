package com.minkov.mvpservicesdemofirebase.views.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

public abstract class DrawerActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }

    private void setupDrawer() {
        IDrawerItem[] drawerItems = (IDrawerItem[]) getDrawerItems().toArray();

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getToolbar())
                .addDrawerItems(drawerItems)
                .withOnDrawerItemClickListener(this::handleOnItemClick)
                .build();
    }

    protected abstract boolean handleOnItemClick(View view, int position, IDrawerItem iDrawerItem);

    protected abstract Toolbar getToolbar();

    protected abstract List<IDrawerItem> getDrawerItems();
}
