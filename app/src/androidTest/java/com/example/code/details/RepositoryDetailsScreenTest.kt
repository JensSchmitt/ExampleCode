package com.example.code.details

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.code.MainActivity
import com.example.code.MockApplicationModule
import com.example.code.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
internal class RepositoryDetailsScreenTest {

  @get: Rule val rule = createAndroidComposeRule<MainActivity>()

  @get:Rule val hiltAndroid = HiltAndroidRule(this)

  @Before
  fun setUp() {
    rule.onNodeWithText(MockApplicationModule.REPOSITORY1.name).performClick()
  }

  @Test
  fun shouldDisplayRepositoryItem() {
    rule.onNodeWithTextRes(R.string.title_label).assertIsDisplayed()
    rule.onNodeWithText(MockApplicationModule.REPOSITORY1.name).assertIsDisplayed()
    rule.onNodeWithTextRes(R.string.description_label).assertIsDisplayed()
    rule.onNodeWithText(MockApplicationModule.REPOSITORY1.description).assertIsDisplayed()
    rule.onNodeWithTextRes(R.string.stars_label).assertIsDisplayed()
    rule.onNodeWithText(MockApplicationModule.REPOSITORY1.stars.toString()).assertIsDisplayed()
  }

  private fun SemanticsNodeInteractionsProvider.onNodeWithTextRes(textRes: Int) = onNodeWithText(rule.activity.getString(textRes))
}