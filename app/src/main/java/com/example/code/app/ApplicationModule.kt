package com.example.code.app

import com.example.code.domain.GithubApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.Date

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

  @Provides
  fun githubApi(): GithubApi = retrofit().create()

  private fun retrofit() =
      Retrofit.Builder()
          .baseUrl("https://api.github.com/")
          .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(Date::class.java, Rfc3339DateJsonAdapter()).build()))
          .build()

}
