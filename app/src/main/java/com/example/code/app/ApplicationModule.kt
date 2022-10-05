package com.example.code.app

import com.example.code.list.RepositoriesListNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [])
@InstallIn(SingletonComponent::class)
internal object ApplicationModule {

  @Provides
  @Singleton
  fun appNavigation() = AppNavigation()

  @Provides
  fun repositoriesListNavigator(appNavigation: AppNavigation): RepositoriesListNavigator = appNavigation

}
