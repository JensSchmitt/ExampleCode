package com.example.code.details

import androidx.lifecycle.SavedStateHandle
import com.example.code.domain.RepositoryId
import com.example.code.getRepositoryId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryDetailsModule {

  @Provides
  @ViewModelScoped
  fun fenceId(savedStateHandle: SavedStateHandle): RepositoryId = savedStateHandle.getRepositoryId()

}