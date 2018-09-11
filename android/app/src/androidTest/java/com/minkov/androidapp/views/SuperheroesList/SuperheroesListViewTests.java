package com.minkov.androidapp.views.SuperheroesList;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.minkov.androidapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SuperheroesListViewTests {
    @Rule
    public ActivityTestRule<SuperheroesListActivity> mSuperheroesListActivityRule =
            new ActivityTestRule<SuperheroesListActivity>(SuperheroesListActivity.class);

    /**
     * Prepare your test fixture for this test. In this case we register an IdlingResources with
     * Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize your test actions, which makes tests
     * significantly more reliable.
     */
    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(
                mSuperheroesListActivityRule.getActivity().getIdlingResource()
        );
    }


    @Test
    public void showAllSuperheroes() {
        // Add 2 active tasks
        createSuperhero("S 1", "I 1", "asd");
        createSuperhero("S 2", "I 2", "asd");

        //Verify that all our tasks are shown
        viewAllSuperheroes();
        onView(withText("S 1")).check(matches(isDisplayed()));
        onView(withText("S 2")).check(matches(isDisplayed()));
    }


    private void viewAllSuperheroes() {
        onView(withId(R.id.drawer_toolbar)).perform(click());
        onView(withText("Superheroes")).perform(click());
    }

    private void createSuperhero(String name, String secretIdentity, String url) {
        onView(withId(R.id.drawer_toolbar)).perform(click());
        onView(withText("Create superhero")).perform(click());
        onView(withId(R.id.et_name)).perform(typeText(name));
        onView(withId(R.id.et_secret_identity)).perform(typeText(secretIdentity));
        onView(withId(R.id.btn_save)).perform(click());
    }

}
