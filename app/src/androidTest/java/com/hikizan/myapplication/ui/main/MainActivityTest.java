package com.hikizan.myapplication.ui.main;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.hikizan.myapplication.R;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.utils.DummyData;
import com.hikizan.myapplication.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

public class MainActivityTest {

    private final ArrayList<MovieTvshowEntity> dummyMovies = DummyData.generateDummyMovies();
    private final ArrayList<MovieTvshowEntity> dummyTvShows = DummyData.generateDummyTvShows();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition(dummyMovies.size()));
    }

    @Test
    public void loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyMovies.get(0).getTitle())));
        onView(withId(R.id.tv_detail_dateRelease)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_dateRelease)).check(matches(withText(dummyMovies.get(0).getDateRelease())));
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_rating)).check(matches(withText(dummyMovies.get(0).getRating())));
        onView(withId(R.id.tv_detail_userScore)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_userScore)).check(matches(withText(String.format("%s User Score", dummyMovies.get(0).getUserScore()))));
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_genre)).check(matches(withText(dummyMovies.get(0).getGenre())));
        onView(withId(R.id.tv_detail_duration)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_duration)).check(matches(withText(dummyMovies.get(0).getDuration())));
    }

    @Test
    public void loadDetailTv() {
        onView(withText("Tv Shows")).perform(click());
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.scrollToPosition(dummyTvShows.size()));
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyTvShows.get(0).getTitle())));
        onView(withId(R.id.tv_detail_dateRelease)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_dateRelease)).check(matches(withText(dummyTvShows.get(0).getDateRelease())));
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_rating)).check(matches(withText(dummyTvShows.get(0).getRating())));
        onView(withId(R.id.tv_detail_userScore)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_userScore)).check(matches(withText(String.format("%s User Score", dummyTvShows.get(0).getUserScore()))));
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_genre)).check(matches(withText(dummyTvShows.get(0).getGenre())));
        onView(withId(R.id.tv_detail_duration)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_duration)).check(matches(withText(dummyTvShows.get(0).getDuration())));
    }

    @Test
    public void addAndUnFavoriteTv(){
        onView(withText("Tv Shows")).perform(click());
        onView(withId(R.id.rv_tvshows)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshows)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_dateRelease)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_userScore)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_duration)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_favorite)).perform(click());
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.menu_favorite)).perform(click());
        onView(withText("Tv Shows")).perform(click());
        onView(withId(R.id.rv_tvshows_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshows_favorite)).perform(RecyclerViewActions.scrollToPosition(dummyTvShows.size()));
        onView(withId(R.id.rv_tvshows_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.fab_favorite)).perform(click());
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack());
    }

    @Test
    public void loadFavoriteTv(){
        onView(withId(R.id.menu_favorite)).perform(click());
        onView(withText("Tv Shows")).perform(click());
        onView(withId(R.id.rv_tvshows_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tvshows_favorite)).perform(RecyclerViewActions.scrollToPosition(dummyTvShows.size()));
    }

    @Test
    public void addAndUnFavoriteMovie(){
        onView(withText("Movies")).perform(click());
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_dateRelease)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_rating)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_userScore)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_detail_duration)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_favorite)).perform(click());
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.menu_favorite)).perform(click());
        onView(withText("Movies")).perform(click());
        onView(withId(R.id.rv_movies_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies_favorite)).perform(RecyclerViewActions.scrollToPosition(dummyMovies.size()));
        onView(withId(R.id.rv_movies_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.fab_favorite)).perform(click());
        onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack());
    }

    @Test
    public void loadFavoriteMovie(){
        onView(withId(R.id.menu_favorite)).perform(click());
        onView(withText("Movies")).perform(click());
        onView(withId(R.id.rv_movies_favorite)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies_favorite)).perform(RecyclerViewActions.scrollToPosition(dummyMovies.size()));
    }

}