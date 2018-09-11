package com.minkov.mvpservicesdemofirebase.uiutils;

import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.minkov.mvpservicesdemofirebase.views.SuperheroCreate.SuperheroCreateActivity;
import com.minkov.mvpservicesdemofirebase.views.SuperheroesList.SuperheroesListActivity;

import java.util.Arrays;
import java.util.List;

public class NavigationUtils {
    public static List<IDrawerItem> getMainDrawerNavigationItems() {
        return Arrays.asList(
                new PrimaryDrawerItem()
                        .withName(SuperheroesListActivity.TITLE_TEXT)
                        .withIdentifier(SuperheroesListActivity.IDENTIFIER),
                new PrimaryDrawerItem()
                        .withName(SuperheroCreateActivity.TITLE_TEXT)
                        .withIdentifier(SuperheroCreateActivity.IDENTIFIER)
        );
    }
}
