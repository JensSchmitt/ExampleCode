package com.example.code.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.domain.GithubApi
import com.example.code.domain.Repository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class RepositoryDetailsViewModel @AssistedInject constructor(
    private val api: GithubApi,
    @Assisted private val id: String
) : ViewModel() {

  private val _state: MutableStateFlow<State> = MutableStateFlow(State(false, null))
  val state: Flow<State> get() = _state

  init {
    viewModelScope.launch {
      _state.value = _state.value.copy(isLoading = true)
      val item: Repository = api.repositories("language:java", "stars", "desc")
          .items
          .first { it.id == id }
      _state.value = State(false, item)
    }
  }

  data class State(val isLoading: Boolean, val repository: Repository?)

  @AssistedFactory
  interface Factory {

    fun create(@Assisted id: String): RepositoryDetailsViewModel
  }
}
