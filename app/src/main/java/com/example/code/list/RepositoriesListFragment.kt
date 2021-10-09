package com.example.code.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.code.base.navigateTo
import com.example.code.base.viewBinding
import com.example.code.databinding.RepositoriesListFragmentBinding
import com.example.code.databinding.RepositoriesListFragmentBinding.inflate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
internal class RepositoriesListFragment: Fragment() {

  private val viewBinding: RepositoriesListFragmentBinding by viewBinding(::inflate)
  private val viewModel: RepositoriesListViewModel by viewModels()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
      viewBinding.root

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewBinding.list.adapter = RepositoriesAdapter(viewModel::onItemClicked)
    viewModel.state
        .onEach { it.handle() }
        .launchIn(viewLifecycleOwner.lifecycleScope)
    viewModel.navDirections
        .onEach { navigateTo(it) }
        .launchIn(viewLifecycleOwner.lifecycleScope)
  }

  private fun RepositoriesListViewModel.State.handle() {
    viewBinding.progressOverlay.isVisible = isLoading
    (viewBinding.list.adapter as RepositoriesAdapter).submitList(items)
  }
}