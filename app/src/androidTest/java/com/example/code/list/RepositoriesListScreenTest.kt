package com.example.code.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.code.MainActivity
import com.example.code.MockApplicationModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
internal class RepositoriesListScreenTest {

  @get: Rule val rule = createAndroidComposeRule<MainActivity>()

  @get:Rule val hiltAndroid = HiltAndroidRule(this)

  @Test
  fun shouldDisplayRepositoryList() {
    rule.onNodeWithText(MockApplicationModule.REPOSITORY1.name).assertIsDisplayed()
    rule.onNodeWithText(MockApplicationModule.REPOSITORY1.description).assertIsDisplayed()
    rule.onNodeWithText(MockApplicationModule.REPOSITORY2.name).assertIsDisplayed()
    rule.onNodeWithText(MockApplicationModule.REPOSITORY2.description).assertIsDisplayed()
  }
}