package com.example.code.base

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> Activity.viewBinding(
    bindingProducer: (LayoutInflater) -> T
): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) { bindingProducer(layoutInflater) }
