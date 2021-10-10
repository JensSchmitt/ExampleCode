package com.example.code.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class RepositoryDetailsViewModel @AssistedInject constructor(
    private val appRepository: com.example.code.list.AppRepository,
    @Assisted private val id: String
) : ViewModel() {

  private val _state: MutableStateFlow<State> = MutableStateFlow(State(false, null))
  val state: Flow<State> get() = _state

  init {
    viewModelScope.launch {
      _state.value = _state.value.copy(isLoading = true)
      val item: com.example.code.domain.Repository = appRepository.items()
          .first { it.id == id }
      _state.value = State(false, item)
    }
  }

  data class State(val isLoading: Boolean, val repository: com.example.code.domain.Repository?)

  @AssistedFactory
  interface Factory {

    fun create(@Assisted id: String): RepositoryDetailsViewModel
  }
}
