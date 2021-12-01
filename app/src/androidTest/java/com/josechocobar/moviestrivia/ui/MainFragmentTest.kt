package com.josechocobar.moviestrivia.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.josechocobar.moviestrivia.R
import dagger.hilt.android.AndroidEntryPoint
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.TestInstance

@AndroidEntryPoint
class MainFragmentTest() {
    private lateinit var scenario: FragmentScenario<MainFragment>
    private val spiderman = "spi"
    private val venom = "enom"
    private val notice = "notice"
    private val mentalista = "mentalista"
    private val shangchi = "shangchi"


    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_MoviesTrivia)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testSearch() {
        onView(withId(R.id.sv_search_movie)).perform(typeText(spiderman))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.sv_search_movie)).perform(clearText())
        onView(withId(R.id.sv_search_movie)).perform(typeText(venom))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.sv_search_movie)).perform(typeText(notice))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.sv_search_movie)).perform(typeText(mentalista))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.sv_search_movie)).perform(typeText(shangchi))
        Espresso.closeSoftKeyboard()
    }

}