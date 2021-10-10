package com.example.code.details

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.example.code.MainActivity
import com.example.code.MockApplicationModule
import com.example.code.R
import com.example.code.list.Item
import com.example.code.list.RepositoriesListScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
internal class RepositoryDetailsScreenTest {

  @get: Rule val rule = ActivityScenarioRule(MainActivity::class.java)

  @get:Rule val hiltAndroid = HiltAndroidRule(this)

  @Before
  fun setUp() {
    onScreen<RepositoriesListScreen> {
      list {
        childAt<Item>(0) {
          click()
        }
      }
    }
  }

  @Test
  fun shouldDisplayRepositoryList() {
    onScreen<RepositoryDetailsScreen> {
      titleLabel.hasText(R.string.title_label)
      titleText.hasText(MockApplicationModule.REPOSITORY1.name)
      descriptionLabel.hasText(R.string.description_label)
      descriptionText.hasText(MockApplicationModule.REPOSITORY1.description)
      starsLabel.hasText(R.string.stars_label)
      starsText.hasText(MockApplicationModule.REPOSITORY1.stars.toString())
    }
  }
}