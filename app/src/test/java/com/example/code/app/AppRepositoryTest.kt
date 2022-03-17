package com.example.code.app

import com.example.code.domain.GithubApi
import com.example.code.domain.Repositories
import com.example.code.domain.Repository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.reset
import org.mockito.kotlin.then

@ExperimentalCoroutinesApi
internal class AppRepositoryTest {

  private val api: GithubApi = mock()

  private val repository = AppRepository(api)

  @Test
  fun `SHOULD load data from api ON loading items WHEN NOT cached`() = runTest {
    val repositories: List<Repository> = mock()
    given(api.getRepositories("language:java", "stars", "desc")).willReturn(Repositories(repositories))

    assertThat(repository.items()).isEqualTo(repositories)
  }

  @Test
  fun `SHOULD NOT invoke api ON loading items WHEN cached`() = runTest {
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
  fun `SHOULD load data from api ON loading items WHEN cached AND force refreshed`() = runTest {
    val repositories1: List<Repository> = mock()
    val repositories2: List<Repository> = mock()
    given(api.getRepositories("language:java", "stars", "desc")).willReturn(Repositories(repositories1))

    assertThat(repository.items()).isEqualTo(repositories1)

    given(api.getRepositories("language:java", "stars", "desc")).willReturn(Repositories(repositories2))

    assertThat(repository.items(shouldForceRefresh = true)).isEqualTo(repositories2)
  }
}