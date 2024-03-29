package com.example.code.domain

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

  @GET("search/repositories")
  suspend fun getRepositories(@Query("q") query: String, @Query("s") sort: String, @Query("o") order: String): Repositories

}
