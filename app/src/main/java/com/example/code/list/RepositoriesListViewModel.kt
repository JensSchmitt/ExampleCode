package com.example.code.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.code.app.AppRepository
import com.example.code.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RepositoriesListViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

  private val _state: MutableStateFlow<State> = MutableStateFlow(State(false, emptyList()))
  val state: Flow<State> get() = _state
  private val _navDirections: Channel<NavDirections> = Channel(Channel.CONFLATED)
  val navDirections: Flow<NavDirections> get() = _navDirections.receiveAsFlow()

  init {
    viewModelScope.launch {
      _state.value = _state.value.copy(isLoading = true)
      _state.value = State(
          isLoading = false,
          items = repository.items().toRepositoryItems()
      )
    }
  }

  fun onItemClicked(item: RepositoryItem) {
    _navDirections.trySend(RepositoriesListFragmentDirections.toDetails(item.id))
  }

  fun onSwipeRefresh() {
    viewModelScope.launch {
      _state.value = _state.value.copy(isLoading = true)
      _state.value = _state.value.copy(
          isLoading = false,
          items = repository.items(true)
              .toRepositoryItems()
      )
    }
  }

  data class State(val isLoading: Boolean, val items: List<RepositoryItem>)
}

private fun List<Repository>.toRepositoryItems() = map { RepositoryItem(it.id, it.name, it.description, it.stars.toString()) }
