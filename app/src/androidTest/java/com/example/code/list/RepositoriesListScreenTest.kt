package com.example.code.list

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.agoda.kakao.screen.Screen.Companion.onScreen
import com.example.code.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
internal class RepositoriesListScreenTest {

  @get: Rule val rule = ActivityScenarioRule(MainActivity::class.java)

  @get:Rule val hiltAndroid = HiltAndroidRule(this)

  @Test
  fun shouldDisplayRepositoryList() {
    onScreen<RepositoriesListScreen> {
      list {
        hasSize(2)
        childAt<Item>(0) {
          title.hasText("name")
          description.hasText("description")
        }
        childAt<Item>(1) {
          title.hasText("name2")
          description.hasText("description2")
        }
      }
    }
  }
}