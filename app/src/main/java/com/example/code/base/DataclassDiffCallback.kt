package com.example.code.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DataclassDiffCallback<T : Identifiable> : DiffUtil.ItemCallback<T>() {
  override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id() == newItem.id()

  @SuppressLint("DiffUtilEquals")
  override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
