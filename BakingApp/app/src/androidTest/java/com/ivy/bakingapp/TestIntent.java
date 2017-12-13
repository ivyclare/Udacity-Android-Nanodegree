package com.ivy.bakingapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by Ivoline-Clarisse on 12/13/2017.
 */
public class TestIntent {


    @Rule
    public IntentsTestRule<RecipeListActivity> intentsTestRule = new IntentsTestRule<>(
            RecipeListActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = intentsTestRule.getActivity().getIdlingResource();
        // register resource
        Espresso.registerIdlingResources(mIdlingResource);


        // Stub all external intents
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

    }


    @Test
    public void clickRecyclerOpensDetailsView(){

        // Click on the RecyclerView item at position 0
        onView(withId(R.id.recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));


        //Checks if a key is sent with the intent.
        intended(hasExtraWithKey("recipe"));

        //check if detailed activity is launched
        intended(hasComponent(RecipeDetail.class.getName()));


    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }
}
