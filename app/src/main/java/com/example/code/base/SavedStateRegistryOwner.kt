package com.example.code.base

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner

@PublishedApi
@Suppress("UNCHECKED_CAST")
internal fun <T : ViewModel> SavedStateRegistryOwner.viewModelFactory(
    producer: (SavedStateHandle) -> T,
    arguments: Bundle? = null,
): ViewModelProvider.Factory = object : AbstractSavedStateViewModelFactory(this, arguments) {
  override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
    return producer(handle) as T
  }
}