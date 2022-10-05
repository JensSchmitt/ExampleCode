package com.example.code

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.code.app.AppNavigation
import com.example.code.details.RepositoryDetailsScreen
import com.example.code.list.RepositoriesListScreen
import com.example.code.ui.theme.ExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity: FragmentActivity() {

  @Inject
  lateinit var appNavigation: AppNavigation

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ExampleTheme {
        val navController: NavHostController = rememberNavController()
        handleNavigation(navController, appNavigation)
        NavigationComponent(navController, appNavigation)
      }
    }
  }
}

@SuppressLint("ComposableNaming")
@Composable
private fun handleNavigation(navController: NavHostController, appNavigation: AppNavigation) {
  LaunchedEffect(key1 = appNavigation) {
    appNavigation.directions.collect {
      when (it) {
        is AppNavigation.Direction.Destination -> navController.navigate(it.value)
        AppNavigation.Direction.Up -> navController.navigateUp()
      }
    }
  }
}

@Composable
private fun NavigationComponent(navController: NavHostController, appNavigation: AppNavigation) {
  NavHost(navController = navController, startDestination = Screen.RepositoriesList.route) {
    addRepositoriesList()
    addRepositoryDetails(appNavigation)
  }
}

private fun NavGraphBuilder.addRepositoriesList() {
  composable(Screen.RepositoriesList.route) {
    RepositoriesListScreen {
      DefaultTopAppBar(R.string.title_repositories_list)
    }
  }
}

private fun NavGraphBuilder.addRepositoryDetails(appNavigation: AppNavigation) {
  composable(Screen.RepositoryDetails.route) {
    RepositoryDetailsScreen {
      DefaultTopAppBar(
          textRes = R.string.title_repository_details,
          navigateUp = { appNavigation.navigateUp() }
      )
    }
  }
}