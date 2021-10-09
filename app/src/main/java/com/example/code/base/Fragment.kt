package com.example.code.base

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

fun Fragment.navigateTo(navDirections: NavDirections) {
  val navController: NavController = requireActivity()
      .supportFragmentManager
      .primaryNavigationFragment
      ?.findNavController()
      ?: throw UnsupportedOperationException("NavDirections cannot be used without a NavHost")
  navController.navigate(navDirections)
}


inline fun <reified T : ViewModel> Fragment.assistedViewModels(
    noinline producer: (SavedStateHandle) -> T,
): Lazy<T> = viewModels {
  viewModelFactory(producer, arguments)
}

fun <T : ViewBinding> Fragment.viewBinding(
    bindingProducer: (LayoutInflater) -> T
): Lazy<T> = object : Lazy<T> {

  private var cached: T? = null

  override val value: T
    get() = cached ?: run {
      val lifecycleOwner: LifecycleOwner = viewLifecycleOwner
      lifecycleOwner.lifecycle.addObserver(
          object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
              lifecycle.removeObserver(this)
              cached = null
            }
          }
      )

      val layoutInflater: LayoutInflater = layoutInflater
      val binding: T = bindingProducer(layoutInflater)

      cached = binding
      return binding
    }

  override fun isInitialized(): Boolean {
    return cached != null
  }
}