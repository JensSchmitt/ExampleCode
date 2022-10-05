package com.example.code.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun RepositoriesListScreen(topAppBar: @Composable () -> Unit) {
  val viewModel: RepositoriesListViewModel = hiltViewModel()
  UI(
      state = viewModel.state.collectAsState().value,
      onSwipeRefresh = viewModel::onSwipeRefresh,
      onRepositoryClicked = viewModel::onItemClicked,
      topAppBar = topAppBar
  )
}

@Composable
private fun UI(
    state: RepositoriesListViewModel.State,
    onSwipeRefresh: () -> Unit,
    onRepositoryClicked: (RepositoryItem) -> Unit,
    topAppBar: @Composable () -> Unit,
) {
  Surface {
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
      topAppBar()
      SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = state.isLoading), onRefresh = onSwipeRefresh) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
          items(state.items) {
            RepositoryListItem(it, onRepositoryClicked)
          }
        }
      }
    }
  }
}

@Composable
fun RepositoryListItem(item: RepositoryItem, onItemClicked: (RepositoryItem) -> Unit, modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxWidth().clickable(onClick = { onItemClicked(item) }).padding(8.dp)) {
    Column(modifier = Modifier.fillMaxWidth()) {
      Row(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = item.title, style = MaterialTheme.typography.headlineSmall, modifier = Modifier.weight(1F))
        Icon(imageVector = Icons.Filled.Star, contentDescription = "Star icon", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(end = 4.dp))
        Text(text = item.stars)
      }
      Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
    }
  }
}