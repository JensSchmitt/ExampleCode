package com.example.code.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.code.R
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun RepositoryDetailsScreen(topAppBar: @Composable () -> Unit) {
  val viewModel: RepositoryDetailsViewModel = hiltViewModel()
  UI(
      state = viewModel.state.collectAsState().value,
      topAppBar = topAppBar,
  )
}

@Composable
private fun UI(state: RepositoryDetailsViewModel.State, topAppBar: @Composable () -> Unit) {
  Surface {
    Column(modifier = Modifier.fillMaxSize()) {
      topAppBar()
      with(state.repository ?: return@Surface) {
        Label(R.string.title_label)
        Content(name)
        Label(R.string.stars_label)
        Content(stars.toString())
        Label(R.string.description_label)
        Content(description)
        Label(R.string.forks_label)
        Content(forks.toString())
        Label(R.string.url_label)
        Content(url, isUrl = true)
        Label(R.string.created_at_label)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        Content(simpleDateFormat.format(createdAt))
        Label(R.string.owner_label)
        Row(modifier = Modifier.fillMaxWidth()) {
          Column(modifier = Modifier.weight(1F)) {
            Content(owner.login)
            Content(owner.url, isUrl = true)
          }
          AsyncImage(
              model = owner.avatarUrl,
              contentDescription = "Avatar image",
              modifier = Modifier.size(48.dp)
                  .padding(end = 8.dp)
          )
        }
      }
    }
  }
}

@Composable
private fun Content(text: String, modifier: Modifier = Modifier, isUrl: Boolean = false) {
  Text(
      text = text,
      style = MaterialTheme.typography.bodyLarge.copy(textDecoration = if (isUrl) TextDecoration.Underline else null),
      modifier = modifier.clickableWhenUrl(isUrl, text)
          .padding(horizontal = 8.dp)
  )
}

private fun Modifier.clickableWhenUrl(isUrl: Boolean, url: String) =
    composed {
      if (isUrl) {
        val current = LocalUriHandler.current
        clickable { current.openUri(url) }
      } else {
        this
      }
    }

@Composable
private fun Label(textRes: Int, modifier: Modifier = Modifier) {
  Text(
      modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
      style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
      text = stringResource(textRes)
  )
}