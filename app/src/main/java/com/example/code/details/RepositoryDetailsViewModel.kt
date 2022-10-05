package com.example.code.details

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
internal class RepositoryDetailsViewModel @Inject constructor(
    private val appRepository: AppRepository,
    private val repositoryId: RepositoryId,
) : ViewModel() {

  private val _state: MutableStateFlow<State> = MutableStateFlow(State(false, null))
  val state: StateFlow<State> = _state

  init {
    viewModelScope.launch {
      _state.value = _state.value.copy(isLoading = true)
      val item: Repository = appRepository.items()
          .first { it.id == repositoryId.value }
      _state.value = State(false, item)
    }
  }

  data class State(val isLoading: Boolean, val repository: Repository?)
}
