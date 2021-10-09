package com.example.code.base

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun FragmentActivity.navHostController(
    @IdRes resId: Int
): Lazy<NavController> = lazy(LazyThreadSafetyMode.NONE) {
  supportFragmentManager
      .findFragmentById(resId)
      .let { it as NavHostFragment }
      .navController
}
