package ba.unsa.etf.rma.spirale

import android.content.pm.ActivityInfo
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.PositionAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertTrue
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.Is
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class OwnEspressoTest {

    fun withIndex(matcher: Matcher<View?>, index: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            var currentIndex = 0
            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }

    @get:Rule
    var homeRule: ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)

    /*
    * This function tests order of elements in XML files in both portrait and landscape orientation
    * We assure the correct order using Espresso assertions isCompletelyAbove(),
    * isCompletelyBelow(), isCompletelyRightOf(), isCompletelyLeftOf
    * */
    @Test
    fun z_newElementsTest(){
        homeRule.scenario.onActivity{ activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.logo_image))
            .check(PositionAssertions.isCompletelyAbove(withId(R.id.search_query_edittext)))
            .also { withId(R.id.item_title_textview) }
        onView(withId(R.id.game_list))
            .check(PositionAssertions.isCompletelyBelow(withId(R.id.search_query_edittext)))
        onView(withId(R.id.cover_imageview))
            .check(PositionAssertions.isCompletelyRightOf(withIndex(withId(R.id.item_title_textview),0)))

        homeRule.scenario.onActivity{ activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        onView(withId(R.id.logo_image))
            .check(PositionAssertions.isCompletelyAbove(withId(R.id.search_query_edittext)))
        onView(withId(R.id.game_list))
            .check(PositionAssertions.isCompletelyBelow(withId(R.id.search_query_edittext)))
        onView(withIndex(withId(R.id.item_title_textview),0))
            .check(PositionAssertions.isCompletelyAbove(withId(R.id.platform_textview)))
        onView(withId(R.id.details_list))
            .check(PositionAssertions.isCompletelyBelow(withId(R.id.description_textview)))
    }

    /*
    * Function that tests gameDetailsItem button of bottom navigation
    * First we assure that gameDetailsItem is disabled if none of the games was clicked
    * After that we simulate click on recycler view, return to home page and simulate
    * click on gameDetailsItem again
    * In the end, we assert that last selected game is displayed
    * */
    @Test
    fun scenario1(){
        onView(withId(R.id.gameDetailsItem)).check(matches(not(isEnabled())));
        var selectedGame = GameData.getAll().get(4)
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.actionOnItem<ViewHolder>(allOf(
            hasDescendant(withText(selectedGame.title)),
            hasDescendant(withText(selectedGame.releaseDate)),
            hasDescendant(withText(selectedGame.rating.toString()))
        ),click()))
        onView(withId(R.id.homeItem)).perform(click())
        onView(withId(R.id.gameDetailsItem)).perform(click())
        onView(withText(selectedGame.description)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.gameDetailsItem)).perform(click())
        onView(withText(selectedGame.description)).check(matches(isCompletelyDisplayed()))
    }

    /*
    * Function that tests RecyclerView action and switching orientations
    * We first create an object that represents RecyclerView item that is clicked,
    * and we simulate clicking with actionOnItem() function after which we want to assert that
    * clicked game is displayed on screen with onView().check(matches(isCompletelyDisplayed()).
    * After returning to portrait orientation we want to assure that we returned to home fragment
    * */
    @Test
    fun scenario2(){
        var selectedGame = GameData.getAll().get(5)
        homeRule.scenario.onActivity{ activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.game_list)).perform(RecyclerViewActions.actionOnItem<ViewHolder>(allOf(
            hasDescendant(withText(selectedGame.title)),
            hasDescendant(withText(selectedGame.releaseDate)),
            hasDescendant(withText(selectedGame.rating.toString()))
        ),click()))
        onView(withText(selectedGame.description)).check(matches(isCompletelyDisplayed()))
        homeRule.scenario.onActivity{ activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        onView(withId(R.id.game_list)).check(matches(isDisplayed()))
    }

    /*
    * Function that tests if home button from bottom navigation is disabled in home page
    * Firstly, we check if we are currently in home page by asserting that game_list is displayed
    * Then, we assure that with home button is disabled using assertion:
    * 'onView().check(matches(not(isEnabled())))'
    * */
    @Test
    fun scenario3(){
        var selectedGame = GameData.getAll().get(0)
        onView(withId(R.id.game_list)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(allOf(
            hasDescendant(withText(selectedGame.title)),
            hasDescendant(withText(selectedGame.releaseDate)),
            hasDescendant(withText(selectedGame.rating.toString()))
        ),click()))
        onView(withText(selectedGame.description)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.homeItem)).perform(click())
        onView(withId(R.id.game_list))
            .check(matches(isDisplayed()))
        onView(withId(R.id.homeItem)).check(matches(not(isEnabled())));
        onView(withId(R.id.gameDetailsItem)).perform(click())
        onView(withText(selectedGame.description)).check(matches(isCompletelyDisplayed()))
    }

}
