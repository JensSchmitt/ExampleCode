package com.example.code.app

import com.example.code.domain.GithubApi
import com.example.code.domain.Repositories
import com.example.code.domain.Repository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.then
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
internal class AppRepositoryTest {

  private val api: GithubApi = mock()

  private val repository = AppRepository(api)

  @Test
  fun `SHOULD load data from api ON loading items WHEN NOT cached`() = runBlockingTest {
    val repositories: List<Repository> = mock()
    given(api.getRepositories("language:java", "stars", "desc")).willReturn(Repositories(repositories))

    assertThat(repository.items()).isEqualTo(repositories)
  }

  @Test
  fun `SHOULD NOT invoke api ON loading items WHEN cached`() = runBlockingTest {
    val repositories: List<Repository> = mock()
    given(api.getRepositories("language:java", "stars", "desc")).willReturn(Repositories(repositories))
    assertThat(repository.items()).isEqualTo(repositories)
    reset(api)

    assertThat(repository.items()).isEqualTo(repositories)

    then(api)
        .should(never())
        .getRepositories(any(), any(), any())
  }

  @Test
  fun `SHOULD load data from api ON loading items WHEN cached AND force refreshed`() = runBlockingTest {
    val repositories1: List<Repository> = mock()
    val repositories2: List<Repository> = mock()
    given(api.getRepositories("language:java", "stars", "desc")).willReturn(Repositories(repositories1))

    assertThat(repository.items()).isEqualTo(repositories1)

    given(api.getRepositories("language:java", "stars", "desc")).willReturn(Repositories(repositories2))

    assertThat(repository.items(shouldForceRefresh = true)).isEqualTo(repositories2)
  }
}