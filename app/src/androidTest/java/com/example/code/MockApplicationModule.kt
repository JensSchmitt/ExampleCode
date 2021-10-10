package com.example.code

import com.example.code.app.AppRepository
import com.example.code.app.ApplicationModule
import com.example.code.domain.GithubApi
import com.example.code.domain.Owner
import com.example.code.domain.Repositories
import com.example.code.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import java.util.Date
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [ApplicationModule::class])
internal object MockApplicationModule {

  @Provides
  @Singleton
  fun backend() = AppRepository(object : GithubApi {
    override suspend fun getRepositories(query: String, sort: String, order: String): Repositories = Repositories(listOf(REPOSITORY1, REPOSITORY2))
  })

  val REPOSITORY1 = Repository(
      id = "someId",
      name = "name",
      description = "description",
      owner = Owner(login = "login", avatarUrl = "url", url = "url2"),
      url = "url3",
      stars = 1,
      forks = 2,
      createdAt = Date()
  )
  val REPOSITORY2 = Repository(
      id = "someId2",
      name = "name2",
      description = "description2",
      owner = Owner(login = "login2", avatarUrl = "url2", url = "url22"),
      url = "url23",
      stars = 2,
      forks = 3,
      createdAt = Date()
  )
}