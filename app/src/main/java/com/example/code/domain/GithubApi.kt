package com.example.code.domain

import com.example.code.domain.Repositories
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

  @GET("search/repositories")
  suspend fun repositories(@Query("q") query: String, @Query("s") sort: String, @Query("o") order: String): Repositories

}
