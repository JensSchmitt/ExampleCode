package com.example.code.app

import com.example.code.Screen
import com.example.code.domain.RepositoryId
import com.example.code.list.RepositoriesListNavigator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

internal class AppNavigation : RepositoriesListNavigator {

  private val _directions: Channel<Direction> = Channel(Channel.BUFFERED)
  val directions: Flow<Direction> = _directions.receiveAsFlow()

  override fun toDetails(repositoryId: RepositoryId) {
    _directions.trySend(Direction.Destination(Screen.RepositoryDetails.create(repositoryId.value)))
  }

  fun navigateUp() {
    _directions.trySend(Direction.Up)
  }

  sealed interface Direction {
    data class Destination(val value: String) : Direction
    object Up : Direction
  }
}