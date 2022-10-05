package com.example.code.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.app.AppRepository
import com.example.code.domain.Repository
import com.example.code.domain.RepositoryId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RepositoriesListViewModel @Inject constructor(
    private val repository: AppRepository,
    private val navigator: RepositoriesListNavigator,
) : ViewModel() {

  private val _state: MutableStateFlow<State> = MutableStateFlow(State(false, emptyList()))
  val state: StateFlow<State> = _state

  init {
    viewModelScope.launch {
      _state.value = _state.value.copy(isLoading = true)
      _state.value = State(
          isLoading = false,
          items = repository.items().toRepositoryItems()
      )
    }
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

  fun onItemClicked(item: RepositoryItem) {
    navigator.toDetails(RepositoryId(item.id))
  }

  data class State(val isLoading: Boolean, val items: List<RepositoryItem>)
}

private fun List<Repository>.toRepositoryItems() = map { RepositoryItem(it.id, it.name, it.description, it.stars.toString()) }
