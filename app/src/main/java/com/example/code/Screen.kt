package com.example.code

import androidx.lifecycle.SavedStateHandle
import com.example.code.domain.RepositoryId

private const val P_REPOSITORY_ID: String = "repositoryId"

sealed class Screen(val route: String) {

  object RepositoriesList: Screen("repositoriesList")
  object RepositoryDetails: Screen("repositoryDetails/{$P_REPOSITORY_ID}") {
    fun create(repositoryId: String) =
        route.replace("{$P_REPOSITORY_ID}", repositoryId)
  }
}

fun SavedStateHandle.getRepositoryId(): RepositoryId = RepositoryId(get<String>(P_REPOSITORY_ID)!!)