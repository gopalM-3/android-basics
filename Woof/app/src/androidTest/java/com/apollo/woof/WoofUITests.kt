package com.apollo.woof

import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.apollo.woof.ui.theme.WoofTheme
import org.junit.Rule
import org.junit.Test

class WoofUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun expansion_animation_test() {
        composeTestRule.setContent {
            WoofTheme {
                WoofApp()
            }
        }

        composeTestRule.waitUntil(5000) {
            composeTestRule
                .onNodeWithTag("DogList")
                .fetchSemanticsNode().children.isNotEmpty()
        }

        val firstDogCard = composeTestRule
            .onNodeWithTag("DogList")
            .onChildren()
            .onFirst()

        firstDogCard
            .onChildren()
            .filterToOne(hasClickAction())
            .performClick()

        composeTestRule.waitUntil(5000) {
            true
        }

        firstDogCard
            .onChildren()
            .filterToOne(hasText("About:"))
            .assertExists()
    }

}