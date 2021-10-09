package com.example.code.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.code.base.assistedViewModels
import com.example.code.base.viewBinding
import com.example.code.databinding.RepositoryDetailsFragmentBinding
import com.example.code.databinding.RepositoryDetailsFragmentBinding.inflate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
internal class RepositoryDetailsFragment: Fragment() {

  @Inject lateinit var factory: RepositoryDetailsViewModel.Factory

  private val viewBinding: RepositoryDetailsFragmentBinding by viewBinding(::inflate)
  private val navArgs: RepositoryDetailsFragmentArgs by navArgs()
  private val viewModel: RepositoryDetailsViewModel by assistedViewModels {
    factory.create(navArgs.id)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
      viewBinding.root

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.state
        .onEach { it.handle() }
        .launchIn(viewLifecycleOwner.lifecycleScope)
  }

  private fun RepositoryDetailsViewModel.State.handle() = with(viewBinding) {
    progressOverlay.isVisible = isLoading
    if (repository != null) {
      starsText.text = repository.stars.toString()
      descriptionText.text = repository.description
      forksText.text = repository.forks.toString()
      urlText.text = repository.url
      val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
      createdAtText.text = simpleDateFormat.format(repository.createdAt)
      ownerText.text = repository.owner.login
      ownerUrl.text = repository.owner.url
      Glide.with(this@RepositoryDetailsFragment)
          .load(repository.owner.avatarUrl)
          .into(ownerAvatar)
    }
  }
}