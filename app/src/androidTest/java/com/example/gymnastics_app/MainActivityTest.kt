package com.example.gymnastics_app


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textView = onView(
            allOf(
                withId(R.id.tvScore), withText("0"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("0")))

        val textView2 = onView(
            allOf(
                withId(R.id.tvElement), withText("Element 1 / 10"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Element 1 / 10")))

        val materialButton = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView3 = onView(
            allOf(
                withId(R.id.tvScore), withText("1"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("1")))

        val textView4 = onView(
            allOf(
                withId(R.id.tvElement), withText("Element 2 / 10"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Element 2 / 10")))

        val materialButton2 = onView(
            allOf(
                withId(R.id.btnDeduction), withText("– DEDUCTION (-2.0)"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val textView5 = onView(
            allOf(
                withId(R.id.tvScore), withText("0"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("0")))

        val textView6 = onView(
            allOf(
                withId(R.id.tvElement), withText("Element 2 / 10"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("Element 2 / 10")))

        val materialButton3 = onView(
            allOf(
                withId(R.id.btnReset), withText("RESET"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val textView7 = onView(
            allOf(
                withId(R.id.tvScore), withText("0"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView7.check(matches(withText("0")))

        val textView8 = onView(
            allOf(
                withId(R.id.tvElement), withText("Element 1 / 10"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView8.check(matches(withText("Element 1 / 10")))

        val materialButton4 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val materialButton5 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val materialButton7 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton7.perform(click())

        val materialButton8 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton8.perform(click())

        val materialButton9 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton9.perform(click())

        val materialButton10 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton10.perform(click())

        val materialButton11 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton11.perform(click())

        val materialButton12 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton12.perform(click())

        val textView9 = onView(
            allOf(
                withId(R.id.tvScore), withText("17"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView9.check(matches(withText("17")))

        val textView10 = onView(
            allOf(
                withId(R.id.tvElement), withText("Element 10 / 10"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView10.check(matches(withText("Element 10 / 10")))

        val materialButton13 = onView(
            allOf(
                withId(R.id.btnPerform), withText("PERFORM"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.main),
                        4
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton13.perform(click())

        val textView11 = onView(
            allOf(
                withId(R.id.tvScore), withText("20"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView11.check(matches(withText("20")))

        val textView12 = onView(
            allOf(
                withId(R.id.tvElement), withText("Element 10 / 10"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView12.check(matches(withText("Element 10 / 10")))

        val textView13 = onView(
            allOf(
                withText("ROUTINE COMPLETE!"),
                withParent(
                    allOf(
                        withId(R.id.tvComplete),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView13.check(matches(withText("ROUTINE COMPLETE!")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
