package com.example.code.list

import com.example.code.domain.RepositoryId

interface RepositoriesListNavigator {
  fun toDetails(repositoryId: RepositoryId)
}
