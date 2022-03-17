package com.example.code

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class CoroutineTestDispatcherRule(
    private val coroutineDispatcher: TestDispatcher
) : CoroutineContext by coroutineDispatcher,
    CoroutineTestRule,
    TestWatcher() {

  override fun starting(description: Description) {
    Dispatchers.setMain(coroutineDispatcher)
  }

  override fun finished(description: Description) {
    Dispatchers.resetMain()
  }
}