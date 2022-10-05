package com.example.code

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(textRes: Int, modifier: Modifier = Modifier, navigateUp: (() -> Unit)? = null) {
  TopAppBar(
      modifier = modifier,
      title = { Text(text = stringResource(textRes)) },
      colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
      navigationIcon = {
        if (navigateUp != null) {
          IconButton(onClick = { navigateUp() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Navigate Up")
          }
        }
      }
  )
}