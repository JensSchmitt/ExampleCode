package com.example.code.list

import com.example.code.domain.GithubApi
import com.example.code.domain.Repository

internal class Repository(private val api: GithubApi) {

  private var cached: List<Repository>? = null

  suspend fun items(shouldForceRefresh: Boolean = false): List<Repository> =
      if (shouldForceRefresh || cached == null) {
        api.getRepositories("language:java", "stars", "desc")
            .items
            .also { cached = it }
      } else {
        cached!!
      }
}