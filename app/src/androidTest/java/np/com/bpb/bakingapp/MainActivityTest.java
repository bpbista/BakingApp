package np.com.bpb.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import np.com.bpb.bakingapp.adapters.RecipeAdapter;
import np.com.bpb.bakingapp.ui.MainActivity;
import np.com.bpb.bakingapp.utils.RecipeIdlingResource;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    private String mNutellaPie;
    private String mBrownies;
    private String mYellowCake;
    private String mCheesecake;
    private RecyclerView.Adapter adapter;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

//    private IdlingResource mIdlingResource;

    @Before
    public void initTest() {
        mNutellaPie = "Nutella Pie";
        mBrownies = "Brownies";
        mYellowCake = "Yellow Cake";
        mCheesecake = "Cheesecake";
        adapter = mActivityTestRule.getActivity().mRecipeAdapter;
        Espresso.registerIdlingResources(RecipeIdlingResource.getIdlingResource());
    }

    @After
    public void clear(){
        Espresso.unregisterIdlingResources(RecipeIdlingResource.getIdlingResource());
    }

    @Test
    public void testRecipeListDisplay(){
        onView(ViewMatchers.withId(R.id.main_recipe_list)).check(matches(isDisplayed()));
    }


    @Test
    public void testIsListItemFocusable(){
        onView(ViewMatchers.withId(R.id.main_recipe_list)).check(matches(isFocusable()));
    }

    @Test
    public void testHasListItemFocus(){
        onView(ViewMatchers.withId(R.id.main_recipe_list)).check(matches(hasFocus()));
    }

    @Test
    public void testHasListItemEnabled(){
        onView(ViewMatchers.withId(R.id.main_recipe_list)).check(matches(isEnabled()));
    }

    @Test
    public void testIsListItemClickable(){
        onView(ViewMatchers.withId(R.id.main_recipe_list)).check(matches(not(isClickable())));
    }

    @Test
    public void testRecipeText(){
        onView(ViewMatchers.withId(R.id.main_recipe_list))
                .check(matches(hasDescendant(withText(mNutellaPie))));
    }

    @Test
    public void testYellowCake(){
        onView(withId(R.id.main_recipe_list)).perform(
                RecyclerViewActions.scrollToHolder(
                        withHolderTimeView(mYellowCake)
                )
        );
    }

    @Test
    public void testBrownies(){
        onView(withId(R.id.main_recipe_list))
                .check(matches(hasDescendant(withText(mBrownies))));
    }

    @Test
    public void testClick(){
        onView(withId(R.id.main_recipe_list))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(0, click())
                );
    }

    @Test
    public void testSwipeDown(){
        onView(ViewMatchers.withId(R.id.main_recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(2, swipeDown()));
    }

    @Test
    public void testSwipeUp(){
        onView(ViewMatchers.withId(R.id.main_recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(3, swipeUp()));
    }

    public static Matcher<Object> withItemContent(String recipe) {
        checkNotNull(recipe);
        return withItemContent(equalTo(recipe));
    }

    public static Matcher<RecyclerView.ViewHolder> withHolderTimeView(final String text) {
        return new BoundedMatcher<RecyclerView.ViewHolder, RecipeAdapter.ViewHolder>( RecipeAdapter.ViewHolder.class) {

            @Override
            public void describeTo(org.hamcrest.Description description) {
                description.appendText("No ViewHolder found with text: " + text);
            }

            @Override
            protected boolean matchesSafely(RecipeAdapter.ViewHolder item) {
                TextView timeViewText = item.itemView.findViewById(R.id.tv_recipe_list_item);
                if (timeViewText == null) {
                    return false;
                }
                return timeViewText.getText().toString().contains(text);
            }
        };
    }
}
