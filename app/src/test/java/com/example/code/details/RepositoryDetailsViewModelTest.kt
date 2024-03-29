package com.example.code.details

import app.cash.turbine.test
import com.example.code.CoroutineTestRule
import com.example.code.app.AppRepository
import com.example.code.details.RepositoryDetailsViewModel.State
import com.example.code.domain.Owner
import com.example.code.domain.Repository
import com.example.code.domain.RepositoryId
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import java.util.Date
import kotlin.LazyThreadSafetyMode.NONE
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
internal class RepositoryDetailsViewModelTest {

  @get:Rule val coroutineContext = CoroutineTestRule(StandardTestDispatcher())

  private val repository: AppRepository = mock()

  private val viewModel by lazy(NONE) { RepositoryDetailsViewModel(repository, RepositoryId(SOME_REPOSITORY.id)) }

  @Test
  fun `SHOULD emit correct state ON init`() = runTest {
    given(repository.items()).willReturn(listOf(SOME_REPOSITORY))
    viewModel.state
        .test {
          assertThat(awaitItem()).isEqualTo(State(false, null))
          assertThat(awaitItem()).isEqualTo(State(true, null))
          assertThat(awaitItem()).isEqualTo(State(false, SOME_REPOSITORY))
        }
  }
}

private val SOME_REPOSITORY = Repository(
    id = "someId",
    name = "name",
    description = "description",
    owner = Owner(login = "login", avatarUrl = "url", url = "url2"),
    url = "url3",
    stars = 1,
    forks = 2,
    createdAt = Date()
)