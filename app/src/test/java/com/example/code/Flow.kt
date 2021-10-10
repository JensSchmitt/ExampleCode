package com.example.code.details

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import kotlin.time.ExperimentalTime

@ExperimentalTime
suspend fun <T> Flow<T>.awaitItem(value: T) {
  test {
    assertThat(awaitItem()).isEqualTo(value)
  }
}