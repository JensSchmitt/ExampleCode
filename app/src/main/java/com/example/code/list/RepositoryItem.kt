package com.example.code.list

import com.example.code.base.Identifiable

data class RepositoryItem(
    val id: String,
    val title: String,
    val description: String,
    val stars: String
): Identifiable {
  override fun id() = id
}