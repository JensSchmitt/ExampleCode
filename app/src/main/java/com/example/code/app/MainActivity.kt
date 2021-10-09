package com.example.code.app

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.code.R
import com.example.code.base.navHostController
import com.example.code.base.viewBinding
import com.example.code.databinding.MainActivityBinding
import com.example.code.databinding.MainActivityBinding.inflate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class MainActivity: FragmentActivity() {

  private val navController: NavController by navHostController(R.id.host)
  private val viewBinding: MainActivityBinding by viewBinding(::inflate)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(viewBinding.root)

    viewBinding.toolbar
        .setupWithNavController(navController)
  }
}