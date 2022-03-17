package com.example.code

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.rules.TestRule
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@Suppress("TestFunctionName")
fun CoroutineTestRule(
    coroutineDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): CoroutineTestRule = CoroutineTestDispatcherRule(coroutineDispatcher)

interface CoroutineTestRule : CoroutineContext, TestRule