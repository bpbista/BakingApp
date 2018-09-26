package np.com.bpb.bakingapp;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import np.com.bpb.bakingapp.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;




@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private String mNutellaPie;
    private String mBrownies;
    private String mYellowCake;
    private String mCheesecake;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initValidString() {
        mNutellaPie = "Nutella Pie";
        mBrownies = "Brownies";
        mYellowCake = "Yellow Cake";
        mCheesecake = "Cheesecake";
    }
    @Test
    public void testRecipe(){
        onView(withId(R.id.main_recipe_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }

    @Test
    public void testRecipeDisplay(){
        onView(withId(R.id.main_recipe_list)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecipeText(){
        onView(withId(R.id.main_recipe_list))
                .check(matches(hasDescendant(withText(mNutellaPie))));
        }

        @Test
    public void testRecyclerPosition(){
            onView(ViewMatchers.withId(R.id.main_recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(1, scrollTo()));
            onView(withText(mBrownies)).perform(click());
            onView(withId(R.id.tv_recipe_list_item)).check(matches(withText(mBrownies)));
        }
}
