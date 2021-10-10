package com.example.code

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.rules.TestRule
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@Suppress("TestFunctionName")
fun CoroutineTestRule(
    coroutineDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
): CoroutineTestRule = CoroutineTestDispatcherRule(coroutineDispatcher)

interface CoroutineTestRule : CoroutineContext, TestRule