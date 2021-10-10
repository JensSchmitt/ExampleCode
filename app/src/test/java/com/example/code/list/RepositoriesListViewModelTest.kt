package com.example.code.list

import app.cash.turbine.test
import com.example.code.CoroutineTestRule
import com.example.code.app.AppRepository
import com.example.code.details.awaitItem
import com.example.code.domain.Owner
import com.example.code.domain.Repository
import com.example.code.list.RepositoriesListViewModel.State
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.then
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import java.util.Date
import kotlin.LazyThreadSafetyMode.NONE
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
internal class RepositoriesListViewModelTest {

  @get:Rule val coroutineContext = CoroutineTestRule()

  private val repository: AppRepository = mock()

  private val viewModel by lazy(NONE) { RepositoriesListViewModel(repository) }

  @Test
  fun `SHOULD emit correct state ON init`() = runBlockingTest(coroutineContext) {
    given(repository.items()).willReturn(listOf(REPOSITORY1))
    pauseDispatcher()
    viewModel.state
        .test {
          assertThat(awaitItem()).isEqualTo(State(false, emptyList()))
          assertThat(awaitItem()).isEqualTo(State(true, emptyList()))
          assertThat(awaitItem()).isEqualTo(State(false, listOf(REPOSITORY1.toItem())))
        }
    resumeDispatcher()
    then(repository)
        .should()
        .items()
  }

  @Test
  fun `SHOULD navigate to details ON item clicked`() = runBlockingTest(coroutineContext) {
    given(repository.items()).willReturn(listOf(REPOSITORY1))

    viewModel.onItemClicked(REPOSITORY1.toItem())

    viewModel.navDirections
        .awaitItem(RepositoriesListFragmentDirections.toDetails(REPOSITORY1.id))
  }

  @Test
  fun `SHOULD force refresh AND emit new item ON swipe refresh`() = runBlockingTest(coroutineContext) {
    given(repository.items()).willReturn(listOf(REPOSITORY1))
    given(repository.items(true)).willReturn(listOf(REPOSITORY2))

    viewModel.state
        .test {
          assertThat(awaitItem()).isEqualTo(State(false, listOf(REPOSITORY1.toItem())))
          viewModel.onSwipeRefresh()
          assertThat(awaitItem()).isEqualTo(State(true, listOf(REPOSITORY1.toItem())))
          assertThat(awaitItem()).isEqualTo(State(false, listOf(REPOSITORY2.toItem())))
        }
  }
}

private fun Repository.toItem() = RepositoryItem(id, name, description, stars.toString())

private val REPOSITORY1 = Repository(
    id = "someId",
    name = "name",
    description = "description",
    owner = Owner(login = "login", avatarUrl = "url", url = "url2"),
    url = "url3",
    stars = 1,
    forks = 2,
    createdAt = Date()
)
private val REPOSITORY2 = Repository(
    id = "someId2",
    name = "name2",
    description = "description2",
    owner = Owner(login = "login2", avatarUrl = "url2", url = "url22"),
    url = "url23",
    stars = 2,
    forks = 3,
    createdAt = Date()
)